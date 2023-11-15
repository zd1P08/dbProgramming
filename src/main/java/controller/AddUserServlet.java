package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import dao.DaoFactory;
import dao.LevelDao;
import dao.UserDao;
import domain.Level;
import domain.User;

/**
 * Servlet implementation class AddEventServlet
 */
@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setMaster(request);
		request.getRequestDispatcher("/WEB-INF/view/addUser.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isError = false;

		setMaster(request);

		String loginId = request.getParameter("loginId");
		request.setAttribute("loginId", loginId);
		if (loginId.isEmpty()) {
			request.setAttribute("loginIdError", "ログインIDが未入力です。");
			isError = true;
		}

		String loginPass = request.getParameter("loginPass");
		request.setAttribute("loginPass", loginPass);
		if (loginPass.isEmpty()) {
			request.setAttribute("loginPassError", "パスワードが未入力です。");
			isError = true;
		}

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
		// パスワードをハッシュ化
		String hashed = BCrypt.hashpw(loginPass, BCrypt.gensalt());
		// adminDivを常に0に設定
		Integer adminDiv = 0;

		if (isError == true) {
			request.getRequestDispatcher("/WEB-INF/view/addUser.jsp").forward(request, response);
			return;
		}

		User user = new User();
		user.setLoginId(loginId);
		// ハッシュ化されたパスワードをセット
		user.setLoginPass(hashed);
		user.setUserName(userName);
		user.setLevelId(levelId);
		user.setAdminDiv(adminDiv);

		try {
			UserDao userDao = DaoFactory.createUserDao();
			userDao.insert(user);
			request.setAttribute("add", "アカウント登録が完了しました。");
			request.getRequestDispatcher("/WEB-INF/view/addUserDone.jsp").forward(request, response);
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
