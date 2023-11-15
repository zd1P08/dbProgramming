<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>テニスイベント管理</title>
</head>
<body>
	<h1>管理者用画面</h1>
	<p>
		<a href="logout">ログアウト</a>
	</p>
	<h2>
		<%=(String) request.getSession().getAttribute("userName")%>
	</h2>
	<table border="1">
		<tr>
			<th>名前</th>
			<th>レベル</th>
			<th>ログインID</th>
			<th>パスワード</th>
			<th colspan="2"></th>
		</tr>
		<c:forEach items="${userList }" var="user">
			<tr>
				<td><c:out value="${user.userName }" /></td>
				<td><c:out value="${user.levelName }" /></td>
				<td><c:out value="${user.loginId }" /></td>
				<td><c:out value="${user.loginPass }" /></td>
				<td><a href="deleteUser?id=<c:out value="${user.userId}" />">削除
				</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>