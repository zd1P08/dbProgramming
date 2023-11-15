package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.LevelDao;
import dao.UserDao;
import domain.Level;
import domain.User;

/**
 * Servlet implementation class AddEventServlet
 */
@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setMaster(request);
		Integer userId = (Integer) request.getSession().getAttribute("userId");

		try {
			UserDao userDao = DaoFactory.createUserDao();
			User user = userDao.findByUserId(userId);

			request.setAttribute("userName", user.getUserName());
//			request.setAttribute("loginId", user.getLoginId());
//			request.setAttribute("loginPass", user.getLoginPass());
			request.setAttribute("levelId", user.getLevelId());

			request.getRequestDispatcher("/WEB-INF/view/updateUser.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		request.getRequestDispatcher("/WEB-INF/view/updateUser.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isError = false;
		setMaster(request);

		String userName = request.getParameter("userName");
		request.setAttribute("userName", userName);
		if (userName.isEmpty()) {
			request.setAttribute("userNameError", "名前が未入力です。");
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

		if (isError == true) {
			request.getRequestDispatcher("/WEB-INF/view/updateUser.jsp").forward(request, response);
			return;
		}

		try {
			User user = new User();
			Integer userId = (Integer) request.getSession().getAttribute("userId");
			String loginId = (String) request.getSession().getAttribute("loginId");
			user.setUserId(userId); // ユーザーIDを設定
			user.setLoginId(loginId); // ログインIDを設定
			user.setUserName(userName);
			user.setLevelId(levelId);

			UserDao userDao = DaoFactory.createUserDao();
			userDao.update(user); // ユーザー情報を更新

			// アップデートが成功した場合、セッションのデータを更新
			request.getSession().setAttribute("userName", userName);
			request.getSession().setAttribute("levelId", levelId);

			request.setAttribute("update", "プロフィール編集が完了しました。");
			request.getRequestDispatcher("/WEB-INF/view/updateUserDone.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private void setMaster(HttpServletRequest request) throws ServletException {
		try {
			LevelDao levelDao = DaoFactory.createLevelDao();
			List<Level> levelList = levelDao.findAll();
			request.setAttribute("levelList", levelList);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
