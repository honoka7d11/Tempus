package employee;

import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Attend;
import bean.Users;
import dao.AdminDAO;
import tool.Action;


public class SearchAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session=request.getSession();
		
		int userId = Integer.parseInt(request.getParameter("selectName"));
		String month = request.getParameter("selectMonth");
		
		LocalDate today = LocalDate.now();
        int year = today.getYear();
        
        String date = year+ "-" +month+ "%";
		
		AdminDAO dao=new AdminDAO();
		List<Attend> list2=dao.searchAttend(userId, date);
		String name=dao.searchName(userId);
		
		List<Users> list=dao.search();
		
		if (list2 == null || list2.size() == 0) {
			name = null;
			request.setAttribute("name", name);
			session.setAttribute("list", list);
			session.removeAttribute("list2");
			RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
		     dispatcher.forward(request, response);
			return "admin.jsp";
		}else {
		
			request.setAttribute("name", name);
			session.setAttribute("list", list);
			session.setAttribute("list2", list2);
			RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
		     dispatcher.forward(request, response);
			return "admin.jsp";
		}
	}

}
