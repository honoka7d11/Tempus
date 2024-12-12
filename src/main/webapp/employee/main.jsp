<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="../header.html" %>
<h1>勤怠システム─Tempus─</h1>
<div class=btn> 
	<form action="Attendance.action?id=1" method="post" style="width:25%;">
	<button type="submit">出勤</button>
	</form>
	
	<form action="Attendance.action?id=2" method="post" style="width:25%;">
	<button type="submit">退勤</button>
</form>
</div>
	
	 <% 
        Boolean error = (Boolean) request.getAttribute("error");
        if (error != null && error == false) { 
    %>
        <p>本日は出勤していません</p>
    <% 
        } else { 
    %>
    	<p></p>
	<% 
        } 
    %>


     <% 
        Boolean admin = (Boolean) request.getAttribute("admin");
        if (admin != null && admin) { 
    %>
    	<div class="url">
    	<a href="passchange.jsp" class="button">パスワード変更画面</a>
        <a href="Admin.action?id=1" class="button">管理者ページ</a>
        <a href="Logout.action" class="button">ログアウト</a>
        </div>
    <% 
        } else { 
    %>	<div class="url">
    <a href="passchange.jsp" class="button">パスワード変更画面</a>
        <a href="Logout.action" class="button">ログアウト</a>
        </div>
    <% 
        } 
    %>

<%@include file="../footer.html" %>
