<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.html" %>

<div style="text-align:center;margin-top:250px">
	<form action="PassChange.action" method="post" >
	<p>変更後のパスワード</p>
	<p><input type="password" name="password" oninput="this.value = this.value.replace(/[^a-zA-Z0-9]/g, '')"></p>
	<p><button type="submit">変更</button>
	</form>
</div>

<div class="url">
<a href="Return.action?id=1" class="button">戻る</a>
</div>

<%@include file="../footer.html" %>