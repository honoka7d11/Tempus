<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.html" %>

<div style="text-align:center;margin-top:250px">
    <form action="Login.action" method="post">
        <p>
            社員番号
            <input type="number" name="id" id="employeeId" oninput="this.value = this.value.replace(/[^0-9]/g, '')">
        </p>
        <p>
            パスワード
            <input type="password" name="password" id="password" oninput="this.value = this.value.replace(/[^a-zA-Z0-9]/g, '')">
        </p>
        <p><button type="submit">ログイン</button></p>
    </form>
</div>

<%@include file="../footer.html" %>