<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.html" %>
<form action="submit.jsp" method="post">
        <label for="selectBox">選択してください:</label>
        <select name="selectBox" id="selectBox">
            <% 
                // リクエストスコープからデータを取得
                List<AdminAction.Users> users = (List<SelectBoxServlet.Item>) request.getAttribute("items");
                if (list != null) {
                    for (SelectBoxServlet.Item item : list) {
            %>
                        <option value="<%= item.getValue() %>"><%= item.getText() %></option>
            <%
                    }
                }
            %>
        </select>
        <button type="submit">送信</button>
</form>

<%@include file="../footer.html" %>