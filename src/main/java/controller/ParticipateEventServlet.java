package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.EventDao;
import dao.UserDao;
import domain.Event;
import domain.User;

/**
 * Servlet implementation class ParticipateEventServlet
 */
@WebServlet("/participateEvent")
public class ParticipateEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

			request.setAttribute("eventId", event.getEventId());

			if (event.getParticipantsNumber() >= event.getCapacity()) {
				request.setAttribute("hull", "募集人数に達しているため、参加できません。");
				request.getRequestDispatcher("/WEB-INF/view/eventFull.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/WEB-INF/view/participateEvent.jsp").forward(request, response);
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String eventId = request.getParameter("eventId");
		Integer userId = Integer.parseInt(request.getParameter("userId"));

		try {
			EventDao eventDao = DaoFactory.createEventDao();
			Event event = eventDao.findByEventId(Integer.parseInt(eventId));

			UserDao userDao = DaoFactory.createUserDao();
			User user = userDao.findByUserId(userId);

			if (user.getLevelId() < event.getLevelId()) {
				request.setAttribute("error", "ユーザーのレベルが不足しているため、参加できません。");
				request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
			} else {
				eventDao.joinEvent(Integer.parseInt(eventId), userId);

				request.setAttribute("message", "参加しました！");
				request.setAttribute("eventStartDate", event.getEventStartDate());
				request.setAttribute("eventEndDate", event.getEventEndDate());

				request.getRequestDispatcher("/WEB-INF/view/participateEventDone.jsp").forward(request, response);
			}
		} catch (NumberFormatException e) {
			request.setAttribute("error", "自分のイベントには参加できません。");
			request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("participated", "既に参加済みです。");
			request.getRequestDispatcher("/WEB-INF/view/participatedEventDone.jsp").forward(request, response);
		}
	}

}
