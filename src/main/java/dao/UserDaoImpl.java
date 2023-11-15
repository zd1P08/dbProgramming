package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.mindrot.jbcrypt.BCrypt;

import domain.User;

public class UserDaoImpl implements UserDao {

	private DataSource ds;

	public UserDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<User> findAll() throws Exception {
		List<User> userList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT * FROM users";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				userList.add(mapToUser(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return userList;
	}

	@Override
	public User findByUserId(Integer userId) throws Exception {
		User user = new User();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT"
					+ " users.user_id, users.login_id, users.login_pass,"
					+ " users.user_name, users.level_id, users.admin_div"
					+ " FROM users"
					+ " JOIN levels"
					+ " ON users.level_id = levels.level_id"
					+ " WHERE users.user_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, userId, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			if (rs.next() == true) {
				user = mapToUser(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return user;
	}

	@Override
	public void insert(User user) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO users"
					+ " (login_id, login_pass, user_name, level_id, admin_div)"
					+ " VALUES (?, ?, ?, ?, 0)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, user.getLoginId());
			stmt.setString(2, user.getLoginPass());
			stmt.setString(3, user.getUserName());
			stmt.setObject(4, user.getLevelId(), Types.INTEGER);
			// stmt.setObject(4, user.getAdminDiv(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void update(User user) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE users"
					+ " SET user_name = ?, level_id = ?"
					+ " WHERE user_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, user.getUserName());
			stmt.setObject(2, user.getLevelId(), Types.INTEGER);
			// stmt.setObject(4, user.getAdminDiv(), Types.INTEGER);
			stmt.setObject(3, user.getUserId(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void delete(User user) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "DELETE FROM users WHERE user_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, user.getUserId(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public User findByLoginIdAndLoginPass(String loginId, String loginPass) throws Exception {
		User user = null;
		try (Connection con = ds.getConnection()) {
			String spl = "SELECT * FROM users WHERE login_id=?";
			PreparedStatement stmt = con.prepareStatement(spl);
			stmt.setString(1, loginId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				if (BCrypt.checkpw(loginPass, rs.getString("login_pass"))) {
					user = mapToUser(rs);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return user;
	}

	private User mapToUser(ResultSet rs) throws Exception {
		User user = new User();
		user.setUserId((Integer) rs.getObject("user_id"));
		user.setLoginId(rs.getString("login_id"));
		user.setLoginPass(rs.getString("login_pass"));
		user.setUserName(rs.getString("user_name"));
		user.setLevelId((Integer) rs.getObject("level_id"));
		user.setAdminDiv((Integer) rs.getObject("admin_div"));

		return user;
	}

}
