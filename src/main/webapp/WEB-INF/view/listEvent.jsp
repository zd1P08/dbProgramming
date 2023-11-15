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
/* header {
	background-color: #003366; /* 濃い緑色に変更 
	color: white; /* ヘッダーのテキストを白色に設定 
	padding: 10px; /* ヘッダーの余白を設定 
	overflow: hidden; /* フロート要素の影響を防ぐ 
}

h1 {
	float: left;
	margin: 0;
}
/* h3要素内のリンクの色を白色に設定 
h3 a {
	float: right;
	color: white;
} */
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
	background-color: #333; /* 濃い緑色に変更 */
	color: white; /* テキストを白色に設定 */
	margin: 0; /* h4要素の上下マージンを0に設定 */
}

table {
	background-color: #fff; /* テーブルの背景色を白色に設定 */
	border-collapse: collapse; /* テーブルのセルを結合 */
	width: 100%; /* テーブルの幅100%に設定 */
	border-color: #000 !important; /* 枠線を濃い緑に変更 */
}
/* 奇数行の背景色を水色に設定 */
tr:nth-child(odd) {
	background-color: #ADD8E6;
}
/* テーブルヘッダーのスタイル */
th {
	background-color: #999 !important;
	font-weight: bold; /* フォントを太字に設定 */
	vertical-align: middle; /* テキストを垂直方向に中央に配置 */
}
/* 垂直方向の中央に配置 */
td {
	vertical-align: middle;
}
/* 開始時間と終了時間の幅を調整 */
td:nth-child(2), td:nth-child(3) {
	width: 8%;
}
/* 定員と参加人数の行を狭く設定 */
td:nth-child(6), td:nth-child(7) {
	width: 4%;
	text-align: center; /* 文字を中央寄せ */
}
/* レベルの幅を調整 */
td:nth-child(8) {
	width: 5.5%;
}
/* 主催者の行を広く設定 */
td:nth-child(1) {
	width: 10%;
}
/* 新規作成ボタンのスタイル */
a.button {
	display: inline-block;
	padding: 5px 10px; /* ボタンの内側の余白を設定 */
	background-color: #388E3C; /* ボタンの背景色を緑に設定 */
	color: #fff; /* ボタンの文字色を白に設定 */
	text-decoration: none; /* 下線を削除 */
	border-radius: 5px; /* 角丸を設定 */
	transition: background-color 0.3s, color 0.3s;
	/* 背景色と文字色の変化にアニメーションを追加 */
}
/* マウスを乗せた際のスタイル */
a.button:hover {
	background-color: #006400; /* マウスを乗せた際の背景色を濃い緑に変更 */
	color: #fff;
	/*
	ホバー時のテキスト色を白色に変更
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
		<%-- <header>
			<h1>テニスイベント掲示板</h1>
		</header>
			<div class="dropdown user-dropdown">
				<button class="btn btn-secondary dropdown-toggle" type="button"
					id="user-dropdown-toggle" data-bs-toggle="dropdown"
					aria-expanded="false">
					<%=(String) request.getSession().getAttribute("userName")%>
				</button>
				<ul class="dropdown-menu" aria-labelledby="user-dropdown-toggle">
					<li><a class="dropdown-item" href="listUserEvent">リンク1</a></li>
					<li><a class="dropdown-item" href="logout">リンク2</a></li>
					<!-- 他のリンクをここに追加 -->
				</ul>
			</div> --%>
		<!-- スペースを追加 -->
		<div style="height: 20px;"></div>
		<p>
			<a class="button" href="addEvent">イベントの新規作成</a>
		</p>
		<!-- <p>&nbsp;</p>
		空文字を入れてスペースを作る -->
		<h4>イベント一覧</h4>
		<table class="table table-bordered">
			<tr>
				<th>主催者</th>
				<th>開始時間</th>
				<th>終了時間</th>
				<th>コート名</th>
				<th>金額</th>
				<th>定員</th>
				<th>参加人数</th>
				<th>レベル</th>
				<th>イベント内容</th>
				<th colspan="2"></th>
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
					<td style="text-align: center; vertical-align: middle;">
						<form action="participateEvent" method="GET">
							<input type="hidden" name="id" value="${event.eventId}" />
							<button type="submit" class="btn btn-success">参加</button>
						</form>
					</td>
					<%-- <td><a
						href="participateEvent?id=<c:out value="${event.eventId}" />">参加
					</a></td> --%>
				</tr>
			</c:forEach>
		</table>
		<!-- <p>
			<a href="listUserEvent">マイページ</a>
		</p> -->
		<!-- <form action="logout" method="GET">
			<button type="submit">ログアウト</button>
		</form> -->
	</div>
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