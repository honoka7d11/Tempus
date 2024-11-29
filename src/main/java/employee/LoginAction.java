package employee;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Users;
import dao.LoginoutDAO;
import tool.Action;

public class LoginAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		
		HttpSession session = request.getSession();
		
		int id = Integer.parseInt(request.getParameter("id"));
		String password = request.getParameter("password");
		
		
		LoginoutDAO dao = new LoginoutDAO();
		Users users = dao.serch(id, password);
		
		
		
		if (users != null) {
			Boolean admin=users.getAdmin();
			request.setAttribute("admin", admin);
			session.setAttribute("users", users);
			 RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
		     dispatcher.forward(request, response);
			return "main.jsp";
		}
		
		
		return "login.jsp";
	}

}
