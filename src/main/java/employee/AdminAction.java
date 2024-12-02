package employee;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Users;
import dao.AdminDAO;
import tool.Action;


public class AdminAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session=request.getSession();
		session.removeAttribute("list2");
		int id = Integer.parseInt(request.getParameter("id"));
		
		if (id == 1) {
			AdminDAO dao = new AdminDAO();
			List<Users> list=dao.search();
			
			
			session.setAttribute("list", list);
			
			return "admin.jsp";
		}else if (id == 2) {
			AdminDAO dao = new AdminDAO();
			List<Users> list=dao.search();
			
			
			session.setAttribute("list", list);
			
			return "users.jsp";
		}else {
			return "login.jsp";
		}
		
		
	}

}
