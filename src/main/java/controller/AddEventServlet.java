package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.EventDao;
import dao.LevelDao;
import dao.LocationDao;
import domain.Event;
import domain.Level;
import domain.Location;

/**
 * Servlet implementation class AddEventServlet
 */
@WebServlet("/addEvent")
public class AddEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setMaster(request);
		request.getRequestDispatcher("/WEB-INF/view/addEvent.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isError = false;

		setMaster(request);

		Integer userId = (Integer) request.getSession().getAttribute("userId");

		String eventStartDateStr = request.getParameter("eventStartDate");
		request.setAttribute("eventStartDate", eventStartDateStr);
		Date eventStartDate = null;
		if (eventStartDateStr.isEmpty()) {
			request.setAttribute("eventStartDateError", "開始時間が未入力です。");
			isError = true;
		} else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			java.util.Date parsedDate;
			try {
				parsedDate = dateFormat.parse(eventStartDateStr);
				eventStartDate = new Date(parsedDate.getTime());
			} catch (ParseException e) {
				request.setAttribute("eventStartDateError", "開始時間が正しくありません。");
				isError = true;
			}
		}
		String eventEndDateStr = request.getParameter("eventEndDate");
		request.setAttribute("eventEndDate", eventEndDateStr);
		Date eventEndDate = null;
		if (eventEndDateStr.isEmpty()) {
			request.setAttribute("eventEndDateError", "終了時間が未入力です。");
			isError = true;
		} else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			java.util.Date parsedDate;
			try {
				parsedDate = dateFormat.parse(eventEndDateStr);
				eventEndDate = new Date(parsedDate.getTime());
			} catch (ParseException e) {
				request.setAttribute("eventEndDateError", "終了時間が正しくありません。");
				isError = true;
			}
		}
		String strLocationId = request.getParameter("locationId");
		Integer locationId = null;
		request.setAttribute("locationId", strLocationId);
		try {
			locationId = Integer.parseInt(strLocationId);
		} catch (NumberFormatException e) {
			request.setAttribute("locationIdError", "場所を選択してください。");
			isError = true;
		}
		String strAmount = request.getParameter("amount");
		Integer amount = null;
		request.setAttribute("amount", strAmount);
		try {
			amount = Integer.parseInt(strAmount);
		} catch (NumberFormatException e) {
			request.setAttribute("amountError", "金額は整数で入力してください。");
			isError = true;
		}

		String strCapacity = request.getParameter("capacity");
		Integer capacity = null;
		request.setAttribute("capacity", strCapacity);
		try {
			capacity = Integer.parseInt(strCapacity);
		} catch (NumberFormatException e) {
			request.setAttribute("capacityError", "募集人数は整数で入力してください。");
			isError = true;
		}
		String strParticipantsNumber = request.getParameter("participantsNumber");
		Integer participantsNumber = null;
		request.setAttribute("participantsNumber", strParticipantsNumber);
		try {
			participantsNumber = Integer.parseInt(strParticipantsNumber);
		} catch (NumberFormatException e) {
			request.setAttribute("participantsNumberError", "現在の参加人数は整数で入力してください。");
			isError = true;
		}
		String strLevelId = request.getParameter("levelId");
		Integer levelId = null;
		request.setAttribute("levelId", strLevelId);
		try {
			levelId = Integer.parseInt(strLevelId);
		} catch (NumberFormatException e) {
			request.setAttribute("levelIdError", "レベルを選択してください。");
			isError = true;
		}
		String content = request.getParameter("content");
		request.setAttribute("content", content);
		if (content.isEmpty()) {
			request.setAttribute("contentError", "練習内容が未入力です。");
			isError = true;
		}
		String userName = request.getParameter("userName");

		if (isError == true) {
			request.getRequestDispatcher("/WEB-INF/view/addEvent.jsp").forward(request, response);
			return;
		}

		Event event = new Event();
		event.setUserId(userId);
		event.setEventStartDate(eventStartDate);
		event.setEventEndDate(eventEndDate);
		event.setLocationId(locationId);
		event.setAmount(amount);
		event.setCapacity(capacity);
		event.setParticipantsNumber(participantsNumber);
		event.setLevelId(levelId);
		event.setContent(content);
		event.setUserName(userName);
		try {
			EventDao eventDao = DaoFactory.createEventDao();
			eventDao.insert(event);

			request.setAttribute("add", "イベントを作成しました。");
			request.getRequestDispatcher("/WEB-INF/view/addEventDone.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private void setMaster(HttpServletRequest request) throws ServletException {
		try {
			LevelDao levelDao = DaoFactory.createLevelDao();
			List<Level> levelList = levelDao.findAll();
			request.setAttribute("levelList", levelList);
			LocationDao locationDao = DaoFactory.createLocationDao();
			List<Location> locationList = locationDao.findAll();
			request.setAttribute("locationList", locationList);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
