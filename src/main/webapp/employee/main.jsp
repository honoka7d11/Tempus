<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="../header.html" %>

<div class=btn> 
	<form action="Attendance.action?id=1" method="post">
	<button type="submit">出勤</button>
	</form>
	
	<form action="Attendance.action?id=2" method="post">
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
        <a href="Admin.action?id=1" class="button">管理者ページ</a>
        <a href="logout.html" class="button">ログアウト</a>
    <% 
        } else { 
    %>
        <a href="logout.html" class="button">ログアウト</a>
    <% 
        } 
    %>

<%@include file="../footer.html" %>
