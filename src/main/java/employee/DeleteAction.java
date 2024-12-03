package employee;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Attend;
import bean.Users;
import dao.AdminDAO;
import dao.AttendanceDAO;
import tool.Action;


public class DeleteAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session=request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		
		
		AdminDAO dao=new AdminDAO();
		AttendanceDAO dao2=new AttendanceDAO();
		
		ArrayList<Object> list3=dao2.searchData(id);
		int userId = Integer.parseInt(list3.get(0).toString());
		String day = (String) list3.get(1);
		String month = day.substring(0, 7);
		String date = month+"%";
		dao2.delete(id);
		
		List<Users> list=dao.search();
		List<Attend> list2=dao2.searchAttend(userId, date);
		String name=dao2.searchName(userId);
		request.setAttribute("name", name);
		session.setAttribute("list", list);
		session.setAttribute("list2", list2);
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
	    dispatcher.forward(request, response);
		
		return "admin.jsp";
	}

}
