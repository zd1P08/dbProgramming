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
	<h1>アカウントの削除</h1>
	<p>${delete}</p>
	<p>
		<a href="#" id="eventListLink">ログイン画面へ戻る</a>
	</p>
	<script>
		var adminDiv =
	<%=session.getAttribute("adminDiv")%>
		; 

		document.getElementById("eventListLink").addEventListener("click",
				function() {
					if (adminDiv === 1) {
						window.location.href = "listAdminEvent";
					} else {
						window.location.href = "login";
					}
				});
	</script>
</body>
</html>