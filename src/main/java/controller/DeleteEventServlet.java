package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.EventDao;
import domain.Event;

/**
 * Servlet implementation class DeleteEventServlet
 */
@WebServlet("/deleteEvent")
public class DeleteEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String eventId = request.getParameter("id");
		Integer id = Integer.parseInt(eventId);
		
		try {
			EventDao eventDao = DaoFactory.createEventDao();
		    Event event = eventDao.findByEventId(id);
			
		    request.setAttribute("eventStartDate", event.getEventStartDate());
			request.setAttribute("eventEndDate", event.getEventEndDate());
			request.setAttribute("locationName", event.getLocationName());
			request.setAttribute("amount", event.getAmount());
			request.setAttribute("capacity", event.getCapacity());
			request.setAttribute("participantsNumber", event.getParticipantsNumber());
			request.setAttribute("levelName", event.getLevelName());
			request.setAttribute("content", event.getContent());
			request.getRequestDispatcher("/WEB-INF/view/deleteEvent.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		request.getRequestDispatcher("/WEB-INF/view/deleteEvent.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String eventId = request.getParameter("id");
		Integer id = Integer.parseInt(eventId);
		
		Event event = new Event();
		event.setEventId(id);
		
		try {
			EventDao eventDao = DaoFactory.createEventDao();
			eventDao.delete(event);
			
			request.setAttribute("delete", "削除しました。");
			request.getRequestDispatcher("/WEB-INF/view/deleteEventDone.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
