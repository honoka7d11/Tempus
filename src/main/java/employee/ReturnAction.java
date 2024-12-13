package employee;

import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Users;
import dao.AdminDAO;
import tool.Action;

public class ReturnAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		
		
		
		if (id == 1) {// 管理者ページからメイン画面に戻る操作
			Users users = null;
			users = (Users) session.getAttribute("users");
			
			
			if (users != null) {
				Boolean admin=users.getAdmin();
				request.setAttribute("admin", admin);
				 RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
			     dispatcher.forward(request, response);
				return "main.jsp";
			}else {
				return "login.jsp";
			}
			
		}else if(id == 2) {// 社員情報編集ページから管理者ページに戻る操作
			session.removeAttribute("list2");
			AdminDAO dao = new AdminDAO();
			List<Users> list=dao.search();
			
			session.setAttribute("list", list);
			
			return "admin.jsp";
		}
		
		return "login.jsp";
	}

}
