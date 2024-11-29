<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.html" %>

<div style="text-align:center;margin-top:250px">
	<form action="Login.action" method="post" >
	<p>社員番号<input type="text" name="id"></p>
	<p>パスワード<input type="password" name="password"></p>
	<p><button type="submit">ログイン</button>
	</form>
</div>

<%@include file="../footer.html" %>