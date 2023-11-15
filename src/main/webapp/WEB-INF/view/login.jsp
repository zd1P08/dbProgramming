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
	<h1>ログイン</h1>
	<c:if test="${not empty error }">
		<p>ログインIDかパスワードが正しくありません。</p>
	</c:if>
	<form action="" method="post">
		<p>
			<input type="text" name="loginId" placeholder="ログインID">
		</p>
		<p>
			<input type="password" name="loginPass" placeholder="パスワード">
		</p>
		<p>
			<input type="submit" value="ログイン">
		</p>
		<p>
			<a href="addUser">アカウント新規登録</a>
		</p>
	</form>
</body>
</html>