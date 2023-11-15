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
 * Servlet implementation class CancelEventJoinServlet
 */
@WebServlet("/cancelEventJoin")
public class CancelEventJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String eventId = request.getParameter("eventId");
		Integer id = Integer.parseInt(eventId);

		// ユーザーIDをセッションから取得
		Integer userId = (Integer) request.getSession().getAttribute("userId");

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
			request.getRequestDispatcher("/WEB-INF/view/cancelEventJoin.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String eventId = request.getParameter("eventId");
		Integer id = Integer.parseInt(eventId);

		// ユーザーIDをセッションから取得
		Integer userId = (Integer) request.getSession().getAttribute("userId");

		Event event = new Event();
		event.setEventId(id);

		try {
			EventDao eventDao = DaoFactory.createEventDao();
			eventDao.cancelEventJoin(id, userId);

			request.setAttribute("cancel", "参加をキャンセルしました。");
			request.getRequestDispatcher("/WEB-INF/view/cancelEventJoinDone.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
