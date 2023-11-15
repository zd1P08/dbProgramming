<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>テニスイベント管理</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<style>
body {
	background-image: url('images/tennisCourt.jpg'); /* 画像のファイルパスを指定 */
	background-size: cover; /* 画像を画面全体に広げる */
	background-repeat: no-repeat; /* 画像の繰り返しを無効化 */
	background-attachment: fixed; /* 画像を固定背景として設定 */
	background-position: center center; /* 画像を中央に配置 */
}
/* ナビゲーションバー内の要素を右に配置 */
.navbar-nav {
	margin-left: auto;
}
/* ドロップダウンメニューのリンクの色を白に設定 */
.navbar .navbar-nav .nav-link {
	color: white;
}

.dropdown-menu {
	right: auto;
	light: 0;
}

h4 {
	background-color: #333;
	color: white;
	margin: 0;
}

table {
	background-color: #fff;
	border-collapse: collapse;
	width: 100%;
	border-color: #000 !important;
}

th {
	background-color: #999 !important;
	font-weight: bold; /* フォントを太字に設定 */
	vertical-align: middle; /* テキストを垂直方向に中央に配置 */
}
/* 垂直方向の中央に配置 */
td {
	vertical-align: middle;
}
</style>
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<div class="container">
				<a class="navbar-brand" href="listEvent">テニスイベント掲示板</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNav"
					aria-controls="navbarNav" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav ml-auto">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="user-dropdown-toggle" role="button" data-bs-toggle="dropdown"
							aria-expanded="false"> <%=(String) request.getSession().getAttribute("userName")%>
						</a>
							<ul class="dropdown-menu" aria-labelledby="user-dropdown-toggle">
								<li><a class="dropdown-item" href="updateUser">プロフィールの編集</a></li>
								<li><a class="dropdown-item" href="listUserEvent">予定（主催・参加イベント一覧）</a></li>
								<li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item" href="logout">ログアウト</a></li>
								<!-- 他のリンクをここに追加 -->
							</ul></li>
					</ul>
				</div>
			</div>
		</nav>
		<!-- スペースを追加 -->
		<div style="height: 60px;"></div>
		<h4>イベント参加</h4>
		<form action="" method="post">
			<table class="table table-bordered">
				<tr>
					<th>開始時間</th>
					<td><c:out value="${eventStartDate}" /></td>
				</tr>
				<tr>
					<th>終了時間</th>
					<td><c:out value="${eventEndDate}" /></td>
				</tr>
				<tr>
					<th>場所</th>
					<td><c:out value="${locationName}" /></td>
				</tr>
				<tr>
					<th>金額</th>
					<td><c:out value="${amount}" />円</td>
				</tr>
				<tr>
					<th>募集人数</th>
					<td><c:out value="${capacity}" /></td>
				</tr>
				<tr>
					<th>現在の参加人数</th>
					<td><c:out value="${participantsNumber}" /></td>
				</tr>
				<tr>
					<th>参加者のレベル</th>
					<td><c:out value="${levelName}" /></td>
				</tr>
				<tr>
					<th>イベント内容</th>
					<td><c:out value="${content}" /></td>
				</tr>
			</table>
			<p>
				<input type="hidden" name="eventId" value="${eventId}"> <input
					type="hidden" name="userId" value="${userId}">
				<button type="submit">イベントに参加</button>
			</p>
		</form>
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
		<script>
			$(document).ready(function() {
				$("form").submit(function() {
					return confirm("参加を確定しますか？");
				});
			});
		</script>
		<script src="js/bootstrap.bundle.min.js"></script>
		<script>
			// ユーザー名をクリックしたときにドロップダウンメニューを表示
			$(document).ready(function() {
				$(".user-dropdown-toggle").click(function() {
					$("#user-dropdown").toggleClass("show");
				});
			});
		</script>
</body>
</html>