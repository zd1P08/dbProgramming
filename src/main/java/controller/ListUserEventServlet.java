package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.EventDao;
import domain.Event;

/**
 * Servlet implementation class ListEventServlet
 */
@WebServlet("/listUserEvent")
public class ListUserEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			EventDao eventDao = DaoFactory.createEventDao();
			Integer user_id = (Integer)request.getSession().getAttribute("userId");
			List<Event> eventList = eventDao.listAllUserEvents(user_id);
			List<Event> joinedEventList = eventDao.listJoinedEvents(user_id);
			request.getSession().getAttribute("userId");
			request.setAttribute("eventList", eventList);
			request.setAttribute("joinedEventList", joinedEventList);
			request.getRequestDispatcher("/WEB-INF/view/listUserEvent.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
