package dao;

import java.util.List;

import domain.Event;
import domain.User;

public interface EventDao {

	List<Event> findAll() throws Exception;

	List<Event> listAllUserEvents(Integer user_id) throws Exception;

	Event findByEventId(Integer event_id) throws Exception;

	void insert(Event event) throws Exception;

	void update(Event event) throws Exception;

	void delete(Event event) throws Exception;

	void joinEvent(Integer eventId, Integer userId) throws Exception;

	List<User> getEventParticipants(Integer eventId) throws Exception;

	void incrementParticipantsNumber(int parseInt);
	
	List<Event> listJoinedEvents(Integer userId) throws Exception;
	
	void cancelEventJoin(Integer eventId, Integer userId) throws Exception;

}
