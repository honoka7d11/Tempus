<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>
<div class="userform">
	<form action="Search.action" method="post">
	 	<p>
	        <label for="selectName">社員を選択してください:</label>
		    <select name="selectName" id="selectName">
		         <c:forEach var="admin" items="${list}">
		         <option value="${admin.id}">${admin.id}　${admin.name}</option>
		         </c:forEach>
		    </select>
		</p>
		<p>       
		    <label for="selectMonth">検索したい月を選択してください:</label>
		    <select name="selectMonth" id="selectMonth">
		        <option value="01">1月</option>
		        <option value="02">2月</option>
		        <option value="03">3月</option>
		        <option value="04">4月</option>
		        <option value="05">5月</option>
		        <option value="06">6月</option>
		        <option value="07">7月</option>
		        <option value="08">8月</option>
		        <option value="09">9月</option>
		        <option value="10">10月</option>
		        <option value="11">11月</option>
		        <option value="12">12月</option>
	
		    </select>
		</p>
		
	    <button type="submit">検索</button>
	</form>
</div>
<div style="text-align:center;">
	 <% 
        String name = (String) request.getAttribute("name");
        if (name != null) { 
    %>
        <p><%=name %>さんの勤怠</p>
    <% 
        } else if (name == null){ 
    %>
    	<p>検索結果はありません</p>
	<% 
        }else{
    %>
    	<p></p>
    <% 
        }
    %>
 </div>
 

<div>
	<table id="list_table">
	<tr>
		<th style="display:none;">    </th>
		<th>日付</th>
		<th>出勤</th>
		<th>出勤時間</th>
		<th>退勤</th>
		<th>退勤時間</th>
		<th>更新</th>
		<th>削除</th>
		
	</tr>
	<c:forEach var="attend" items="${list2}">
		<tr class="list_table">
		<td style="display:none;">${attend.id}</td>
		<td>${attend.date}</td>
		<td>${attend.attend}</td>
		<td>${attend.atTime}</td>
		<td>${attend.leave}</td>
		<td>${attend.leTime}</td>
		<td><button type="submit" id="update_button" 
		 onclick="
			let row = this.parentElement.parentElement;
			getElementById('update_id').value=row.cells[0].firstChild.data;
			getElementById('update_date').value=row.cells[1].firstChild.data;
			var dialog = getElementById('updateDialog');
			dialog.style.left=((window.innerWidth - 500) / 2) + 'px';
			dialog.style.display = 'block';">更新</button>
		</td>
		<td>
		<form action="" method="post">
		<button style="width:40%;margin:auto;">削除</button>
		</form>
		</td>
	</c:forEach>
	</tr>
	</table>
</div>

<div id="updateDialog" style="display:none;background-color:#FFFFFF;border:2px double;width:500px;height:500px;
position:fixed;top:120px;z-index:9999;">
	<div class="userform">
		<h2>勤怠状況の編集</h2>
		<form action="Update.action">
			<input id="update_id" name="id" type="hidden" />
			<label>日付</label>
			<input id="update_date" name="date" type="date" />
			<label>勤怠</label>
			<select name = attend>
			<option value=1>出勤</option>
			<option value=2>退勤</option>
			</select>
			<label>時刻</label>
			<p><select name="selectHour" id="selectHour">
		        <option value="00">00</option>
		        <option value="01">01</option>
		        <option value="02">02</option>
		        <option value="03">03</option>
		        <option value="04">04</option>
		        <option value="05">05</option>
		        <option value="06">06</option>
		        <option value="07">07</option>
		        <option value="08">08</option>
		        <option value="09">09</option>
		     	<option value="10">10</option>
		        <option value="11">11</option>
		        <option value="12">12</option>
		        <option value="13">13</option>
		        <option value="14">14</option>
		        <option value="15">15</option>
		        <option value="16">16</option>
		        <option value="17">17</option>
		        <option value="18">18</option>
		        <option value="19">19</option>
		        <option value="20">20</option>
		        <option value="21">21</option>
		        <option value="22">22</option>
		        <option value="23">23</option>
		    </select>:
		    <select name="selectMin" id="selectMin">
		    	<option value="00">00</option>
		        <option value="01">01</option>
		        <option value="02">02</option>
		        <option value="03">03</option>
		        <option value="04">04</option>
		        <option value="05">05</option>
		        <option value="06">06</option>
		        <option value="07">07</option>
		        <option value="08">08</option>
		        <option value="09">09</option>
		    <% 
                for (int i = 10; i <= 59; i++) {
            %>
                <option value="<%= i %>"><%= i %></option>
            <%
                }
            %>
            </select></p>
            
			<div>
				<button type="submit">更新</button>
				<button type="reset" onclick="getElementById('updateDialog').style.display='none';">
				キャンセル</button>
			</div>
		</form>
	</div>
</div>


<a href="Admin.action?id=2" class="button">社員情報編集</a>
<a href="Return.action" class="button">戻る</a>
<%@include file="../footer.html" %>