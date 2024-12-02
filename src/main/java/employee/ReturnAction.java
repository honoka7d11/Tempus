package employee;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Users;
import tool.Action;

public class ReturnAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		
		HttpSession session = request.getSession();
		session.removeAttribute("list2");
		
		Users users = null;
		users = (Users) session.getAttribute("users");
		


		
		if (users != null) {
			System.out.println(users.getId());
			Boolean admin=users.getAdmin();
			request.setAttribute("admin", admin);
			 RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
		     dispatcher.forward(request, response);
			return "main.jsp";
		}
		
		
		return "login.jsp";
	}

}
