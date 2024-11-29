package employee;

import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.Admin;
import dao.AdminDAO;
import tool.Action;


public class AdminAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		
		
		AdminDAO dao = new AdminDAO();
		List<Admin> list=dao.search();
		
		request.setAttribute("list", list);

        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
        dispatcher.forward(request, response);
		
		return "admin.jsp";
	}

}
