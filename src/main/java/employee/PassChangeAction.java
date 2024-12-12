package employee;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Users;
import dao.LoginoutDAO;
import tool.Action;

/**
 * Servlet implementation class PassChangeAction
 */
@WebServlet("/PassChangeAction")
public class PassChangeAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		Users users = null;
		HttpSession session = request.getSession(false);
		
		 if (session != null && (users = (Users) session.getAttribute("users")) == null) {
		      session = null;
		    }

		    if (session == null) {
		      return "login.jsp";
		    }

		 int id = users.getId();
		 String pass = request.getParameter("password");
		 
		 LoginoutDAO dao = new LoginoutDAO();
		 dao.update(id, pass);
		 
		 Boolean admin=users.getAdmin();
		request.setAttribute("admin", admin);
		RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
		 dispatcher.forward(request, response);
		 
		 return "main.jsp";
	}
	
}