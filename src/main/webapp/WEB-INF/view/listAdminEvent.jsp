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
			<th>主催者</th>
			<th>開始時間</th>
			<th>終了時間</th>
			<th>場所</th>
			<th>金額</th>
			<th>募集人数</th>
			<th>現在の参加人数</th>
			<th>参加者のレベル</th>
			<th>イベント内容</th>
			<th colspan="2">データの操作</th>
		</tr>
		<c:forEach items="${eventList }" var="event">
			<tr>
				<td><c:out value="${event.userName }" /></td>
				<td><c:out value="${event.eventStartDate }" /></td>
				<td><c:out value="${event.eventEndDate }" /></td>
				<td><c:out value="${event.locationName }" /></td>
				<td><c:out value="${event.amount }" />円</td>
				<td><c:out value="${event.capacity }" /></td>
				<td><c:out value="${event.participantsNumber }" /></td>
				<td><c:out value="${event.levelName }" /></td>
				<td><c:out value="${event.content }" /></td>
				<td><a href="deleteEvent?id=<c:out value="${event.eventId}" />">削除
				</a></td>
			</tr>
		</c:forEach>
	</table>
	<p>
		<a href="listAdminUser">ユーザー一覧へ</a>
	</p>
</body>
</html>