package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import domain.Event;
import domain.User;

public class EventDaoImpl implements EventDao {

	private DataSource ds;

	public EventDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<Event> findAll() throws Exception {
		List<Event> eventList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String spl = "SELECT" + " events.event_id, events.user_id, events.event_start_date, events.event_end_date,"
					+ " events.location_id, events.amount, events.capacity,"
					+ " events.participants_number, events.level_id, events.content,"
					+ " levels.level_name, users.user_name, locations.location_name" + " FROM events" + " JOIN levels"
					+ " ON events.level_id = levels.level_id" + " JOIN locations"
					+ " ON events.location_id = locations.location_id" + " JOIN users"
					+ " ON events.user_id = users.user_id";
			PreparedStatement stmt = con.prepareStatement(spl);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				eventList.add(mapToEvent(rs));
			}
		} catch (Exception e) {
			throw e;
		}

		return eventList;
	}

	private Event mapToEvent(ResultSet rs) throws Exception {
		Integer eventId = (Integer) rs.getObject("event_id");
		Integer userId = (Integer) rs.getObject("user_id");
		Date eventStartDate = rs.getTimestamp("event_start_date");
		Date eventEndDate = rs.getTimestamp("event_end_date");
		Integer locationId = (Integer) rs.getObject("location_id");
		Integer amount = (Integer) rs.getObject("amount");
		Integer capacity = (Integer) rs.getObject("capacity");
		Integer participantsNumber = (Integer) rs.getObject("participants_number");
		Integer levelId = (Integer) rs.getObject("level_id");
		String content = rs.getString("content");
		String levelName = rs.getString("level_name");
		String userName = rs.getString("user_name");
		String locationName = rs.getString("location_name");

		return new Event(eventId, userId, eventStartDate, eventEndDate, locationId, amount, capacity,
				participantsNumber, levelId, content, levelName, userName, locationName);
	}

	@Override
	public Event findByEventId(Integer eventId) throws Exception {
		Event event = null;

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT" + " events.event_id, events.user_id, events.event_start_date, events.event_end_date,"
					+ " events.location_id, events.amount, events.capacity,"
					+ " events.participants_number, events.level_id, events.content,"
					+ " levels.level_name, users.user_name, locations.location_name" + " FROM events"
					+ " JOIN locations" + " ON events.location_id = locations.location_id" + " JOIN levels"
					+ " ON events.level_id = levels.level_id" + " JOIN users" + " ON events.user_id = users.user_id"
					+ " WHERE events.event_id = ?";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, eventId, Types.INTEGER);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				event = mapToEvent(rs);
			}
		} catch (Exception e) {
			throw e;
		}

		return event;
	}

	@Override
	public void insert(Event event) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO events"
					+ " (user_id, event_start_date, event_end_date, location_id, amount, capacity, participants_number, level_id, content)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, event.getUserId(), Types.INTEGER);
			stmt.setTimestamp(2, new Timestamp(event.getEventStartDate().getTime()));
			stmt.setTimestamp(3, new Timestamp(event.getEventEndDate().getTime()));
			stmt.setObject(4, event.getLocationId(), Types.INTEGER);
			stmt.setObject(5, event.getAmount(), Types.INTEGER);
			stmt.setObject(6, event.getCapacity(), Types.INTEGER);
			stmt.setObject(7, event.getParticipantsNumber(), Types.INTEGER);
			stmt.setObject(8, event.getLevelId(), Types.INTEGER);
			stmt.setString(9, event.getContent());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void update(Event event) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE events"
					+ " SET user_id = ?, event_start_date = ?, event_end_date = ?, location_id = ?, amount = ?,"
					+ " capacity = ?, participants_number = ?, level_id = ?, content = ?" + " WHERE event_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, event.getUserId(), Types.INTEGER);
			stmt.setTimestamp(2, new Timestamp(event.getEventStartDate().getTime()));
			stmt.setTimestamp(3, new Timestamp(event.getEventEndDate().getTime()));
			stmt.setObject(4, event.getLocationId(), Types.INTEGER);
			stmt.setObject(5, event.getAmount(), Types.INTEGER);
			stmt.setObject(6, event.getCapacity(), Types.INTEGER);
			stmt.setObject(7, event.getParticipantsNumber(), Types.INTEGER);
			stmt.setObject(8, event.getLevelId(), Types.INTEGER);
			stmt.setString(9, event.getContent());
			stmt.setObject(10, event.getEventId(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void delete(Event event) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "DELETE FROM events WHERE event_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, event.getEventId(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<Event> listAllUserEvents(Integer user_id) throws Exception {
		List<Event> eventList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String spl = "SELECT" + " events.event_id, events.user_id, events.event_start_date, events.event_end_date,"
					+ " events.location_id, events.amount, events.capacity,"
					+ " events.participants_number, events.level_id, events.content,"
					+ " levels.level_name, users.user_name, locations.location_name" + " FROM events" + " JOIN levels"
					+ " ON events.level_id = levels.level_id" + " JOIN locations"
					+ " ON events.location_id = locations.location_id" + " JOIN users"
					+ " ON events.user_id = users.user_id" + " WHERE events.user_id = ?";
			PreparedStatement stmt = con.prepareStatement(spl);
			stmt.setObject(1, user_id, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				eventList.add(mapToEvent(rs));
			}
		} catch (Exception e) {
			throw e;
		}

		return eventList;
	}

	@Override
	public void joinEvent(Integer eventId, Integer userId) throws Exception {
		try (Connection con = ds.getConnection()) {
			con.setAutoCommit(false);

			EventDao eventDao = DaoFactory.createEventDao();
			Event event = eventDao.findByEventId(eventId);

			UserDao userDao = DaoFactory.createUserDao();
			User user = userDao.findByUserId(userId);

			if (event.getUserId() == userId) {
				con.rollback();
				throw new NumberFormatException("自分が作成したイベントには参加できません。");
			}

			if (isUserAlreadyJoinedEvent(con, eventId, userId)) {
				con.rollback();
				throw new Exception("既に参加済みです。");
			}

			if (user.getLevelId() < event.getLevelId()) {
				con.rollback();
				throw new Exception("ユーザーのレベルが不足しているため、参加できません。");
			} else {
				String sql = "INSERT INTO event_participants (event_id, user_id, status) VALUES (?, ?, ?)";
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, eventId);
				stmt.setInt(2, userId);
				stmt.setString(3, "Joined");
				stmt.executeUpdate();

				incrementParticipantsNumber(con, eventId);

				con.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private void incrementParticipantsNumber(Connection con, Integer eventId) throws SQLException {
		String sql = "UPDATE events SET participants_number = participants_number + 1 WHERE event_id = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, eventId);
		stmt.executeUpdate();
	}

	private boolean isUserAlreadyJoinedEvent(Connection con, Integer eventId, Integer userId) throws SQLException {
		String sql = "SELECT COUNT(*) FROM event_participants WHERE event_id = ? AND user_id = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, eventId);
			stmt.setInt(2, userId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					int count = rs.getInt(1);
					return count > 0;
				}
			}
		}
		return false;
	}

	@Override
	public List<User> getEventParticipants(Integer eventId) throws Exception {
		List<User> participants = new ArrayList<>();
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT users.* FROM users "
					+ "INNER JOIN event_participants ON users.user_id = event_participants.user_id "
					+ "WHERE event_participants.event_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, eventId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				User participant = mapToUser(rs);
				participants.add(participant);
			}
		} catch (Exception e) {
			throw e;
		}
		return participants;
	}

	private User mapToUser(ResultSet rs) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void incrementParticipantsNumber(int eventId) {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE events SET participants_number = participants_number + 1 WHERE event_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, eventId);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Event> listJoinedEvents(Integer userId) throws Exception {
		List<Event> joinedEvents = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT events.event_id, events.user_id, events.event_start_date, events.event_end_date,"
					+ " events.location_id, events.amount, events.capacity,"
					+ " events.participants_number, events.level_id, events.content,"
					+ " levels.level_name, users.user_name, locations.location_name" + " FROM events" + " JOIN levels"
					+ " ON events.level_id = levels.level_id" + " JOIN locations"
					+ " ON events.location_id = locations.location_id" + " JOIN users"
					+ " ON events.user_id = users.user_id" + " JOIN event_participants"
					+ " ON events.event_id = event_participants.event_id" + " WHERE event_participants.user_id = ?";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, userId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				joinedEvents.add(mapToEvent(rs));
			}
		} catch (Exception e) {
			throw e;
		}

		return joinedEvents;
	}

	@Override
	public void cancelEventJoin(Integer eventId, Integer userId) throws Exception {
		try (Connection con = ds.getConnection()) {
			con.setAutoCommit(false);

			// イベント情報を取得
			EventDao eventDao = DaoFactory.createEventDao();
			Event event = eventDao.findByEventId(eventId);

			// ユーザーがイベントの作成者であるかを確認
			if (event.getUserId() == userId) {
				con.rollback();
				throw new IllegalArgumentException("自分が作成したイベントには参加できません。");
			}

			// ユーザーがイベントに参加しているかを確認
			if (!isUserAlreadyJoinedEvent(con, eventId, userId)) {
				con.rollback();
				throw new IllegalArgumentException("ユーザーはこのイベントに参加していません。");
			}

			// イベントからユーザーの参加情報を削除
			String deleteSql = "DELETE FROM event_participants WHERE event_id = ? AND user_id = ?";
			PreparedStatement deleteStmt = con.prepareStatement(deleteSql);
			deleteStmt.setInt(1, eventId);
			deleteStmt.setInt(2, userId);
			deleteStmt.executeUpdate();

			// イベントの参加者数を減少
			decrementParticipantsNumber(con, eventId);

			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private void decrementParticipantsNumber(Connection con, Integer eventId) throws SQLException {
		String sql = "UPDATE events SET participants_number = participants_number - 1 WHERE event_id = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, eventId);
		stmt.executeUpdate();
	}
}
