package employee;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Users;
import dao.AdminDAO;
import tool.Action;


public class UserAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session=request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		
		if (id == 1) {
			int userId = Integer.parseInt(request.getParameter("selectName"));
			int admin = Integer.parseInt(request.getParameter("selectAdmin"));
			if (userId == 0 || admin == 0) {
				AdminDAO dao = new AdminDAO();
				List<Users> list=dao.search();
				
				session.setAttribute("list", list);
				
				return "users.jsp";
			}else if (admin == 1) {
				AdminDAO dao = new AdminDAO();
				dao.update(true, userId);
				List<Users> list=dao.search();
				
				session.setAttribute("list", list);
				
				return "users.jsp";
			}else if (admin == 2) {
				AdminDAO dao = new AdminDAO();
				dao.update(false, userId);
				List<Users> list=dao.search();
				
				session.setAttribute("list", list);
				
				return "users.jsp";
			}else {
				AdminDAO dao = new AdminDAO();
				List<Users> list=dao.search();
				
				session.setAttribute("list", list);
				return "users.jsp";
			}
			
		}else if (id == 2) {
			int userId = Integer.parseInt(request.getParameter("selectEmp"));
			
			if (userId == 0) {
				AdminDAO dao = new AdminDAO();
				List<Users> list=dao.search();
				
				session.setAttribute("list", list);
				
				return "users.jsp";
			}
			
			AdminDAO dao = new AdminDAO();
			dao.delete(userId);
			List<Users> list=dao.search();
			
			session.setAttribute("list", list);
				
			return "users.jsp";
			
			
		}else if (id == 3){
			int userId = Integer.parseInt(request.getParameter("user_id"));
			String name = request.getParameter("user_name");
			String password = request.getParameter("password");
			int adminNum = Integer.parseInt(request.getParameter("admin"));
			
			if (adminNum == 0) {
				Users users = new Users();
				users.setId(userId);
				users.setName(name);
				users.setPassword(password);
				users.setAdmin(false);
	
				AdminDAO dao = new AdminDAO();
				dao.insert(users);
				List<Users> list=dao.search();
				session.setAttribute("list", list);
			}else {
				Users users = new Users();
				users.setId(userId);
				users.setName(name);
				users.setPassword(password);
				users.setAdmin(true);
	
				AdminDAO dao = new AdminDAO();
				dao.insert(users);
				List<Users> list=dao.search();
				session.setAttribute("list", list);
			}
			
			return "users.jsp";
		}else {
			return "login.jsp";
		}
		
		
	}

}