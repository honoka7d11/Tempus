<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>
<form action="submit.jsp" method="post">
        <label for="selectBox">選択してください:</label>
        <select name="selectBox" id="selectBox">
        	<option value=0 hidden>-選択してください-</option>
         <c:forEach var="admin" items="${list}">
         	<option value="${admin.id}">${admin.name}</option>
         </c:forEach>
        </select>
        <button type="submit">検索</button>
</form>

<a href="Admin.action?id=2" class="button">社員情報編集</a>
<a href="Return.action" class="button">戻る</a>
<%@include file="../footer.html" %>