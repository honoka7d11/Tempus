<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>

<h1 class=title>社員情報の管理</h1>
<h2 class=title>管理者の登録・削除</h2>
<div class="userform">
	<form action="User.action?id=1" method="post">
	        <label for="selectName">管理者として登録する社員、もしくは管理者登録を解除する社員を選択してください</label>
	        <select name="selectName" id="selectName">
	        	<option value=0 hidden>-選択してください-</option>
	         <c:forEach var="admin" items="${list}">
	         	<option value="${admin.id}">${admin.id}　${admin.name}</option>
	         </c:forEach>
	        </select>
	        <label for="selectAdmin">「管理者として登録」もしくは「管理者登録を解除」を選んでください</label>
	        <select name="selectAdmin" id="selectAdmin">
	        	<option value=0 hidden>-選択してください-</option>
	         	<option value=1>管理者へ追加</option>
	         	<option value=2>管理者登録を解除</option>
	        </select>
	        <button type="submit">登録</button>
	</form>
</div>

<br><br>

<h2 class=title>社員情報の削除</h2>
<div class="userform">
	<form action="User.action?id=2" method="post">
	        <label for="selectEmp">削除する社員を選択してください</label>
	        <select name="selectEmp" id="selectEmp">
	        	<option value=0 hidden>-選択してください-</option>
	         <c:forEach var="admin" items="${list}">
	         	<option value="${admin.id}">${admin.id}　${admin.name}</option>
	         </c:forEach>
	        </select>
	        <button type="submit">削除</button>
	</form>
</div>


<br><br>

<h2 class=title>新規社員登録</h2>
<div class="userform">
	<form action="User.action?id=3" method="post">
		<label for="user_id">社員番号</label> <input id="user_id" type="text" name="user_id" oninput="this.value = this.value.replace(/[^0-9]/g, '')" required>
		<label for="user_name">氏名</label><input id="name" type="text" name="user_name" required>
		<label for="password">パスワード</label><input id="password" type="password" name="password" oninput="this.value = this.value.replace(/[^a-zA-Z0-9]/g, '')" required>
		<label for="password">管理者権限の有無を選択してください</label>
		<select name = admin>
			<option value=0>無</option>
			<option value=1>有</option>
		</select>
		<button type="submit">登録</button>
	</form>
</div>
<div class="url">
<a href="Return.action?id=2" class="button">戻る</a>
</div>
<%@include file="../footer.html" %>