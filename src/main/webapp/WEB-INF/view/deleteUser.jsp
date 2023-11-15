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
	<h1>アカウント削除</h1>
	<p>ご予約中のテニスコートや、主催/参加予定のイベントはございませんか？</p>
	<form action="" method="post">
		<p>
			<input type="submit" value="退会する">
		</p>
		<p>
			<a href="listUserEvent">戻る</a>
		</p>
		<script>
			var adminDiv =
		<%=session.getAttribute("adminDiv")%>
			;
			document.getElementById("backLink").addEventListener("click",
					function() {
						if (adminDiv === 1) {
							window.location.href = "listAdminUser";//ユーザー一覧画面を作ってそこに飛ぶように設定する
						} else {
							window.location.href = "login";
						}
					});
		</script>
	</form>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script>
		$(document).ready(function() {
			$("form").submit(function() {
				return confirm("退会手続きを完了する");
			});
		});
	</script>
</body>
</html>