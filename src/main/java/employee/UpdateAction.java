package employee;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Attend;
import bean.Users;
import dao.AdminDAO;
import tool.Action;


public class UpdateAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session=request.getSession();
		
		int id = Integer.parseInt(request.getParameter("id"));
		String date=request.getParameter("date");
		int attend = Integer.parseInt(request.getParameter("attend"));
		String hour = request.getParameter("selectHour");
		String min = request.getParameter("selectMin");
		String time = hour+ ":" + min;
		
		Attend at = new Attend();
		at.setId(id);
		at.setDate(date);
		at.setAtTime(time);
		
		if (attend == 1) {
			AdminDAO dao=new AdminDAO();
			dao.updateAttend(at);
			List<Users> list=dao.search();
			List<Attend> list2=dao.searchRow(id);
			session.setAttribute("list", list);
			session.setAttribute("list2", list2);
			return "admin.jsp";
			
		}else {
			AdminDAO dao=new AdminDAO();
			dao.updateLeave(at);
			List<Users> list=dao.search();
			List<Attend> list2=dao.searchRow(id);
			session.setAttribute("list", list);
			session.setAttribute("list2", list2);
			return "admin.jsp";
		}
		

	}
}
