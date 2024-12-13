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
import dao.AttendanceDAO;
import tool.Action;


public class SearchAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session=request.getSession();
		
//		選択された社員の社員番号と月を取得
		int userId = Integer.parseInt(request.getParameter("selectName"));
		String month = request.getParameter("selectMonth");
		
//		今年が何年か調べ、yyyy-MMの形にする
		LocalDate today = LocalDate.now();
        String year = Integer.toString(today.getYear());
        String date = year+"-"+month+"%";
        
//		該当する記録と社員名を取得
		AdminDAO dao=new AdminDAO();
		AttendanceDAO dao2=new AttendanceDAO();
		List<Attend> list2=dao2.searchAttend(userId, date);
		String name=dao2.searchName(userId);
		
//		社員リストの取得
		List<Users> list=dao.search();
		
		if (list2 == null || list2.size() == 0) { //データが何もない場合
			name = null;
			request.setAttribute("name", name);
			session.setAttribute("list", list);
			session.removeAttribute("list2");
			RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
		     dispatcher.forward(request, response);
			return "admin.jsp";
		}else {	//データがある場合
		
			request.setAttribute("name", name);
			session.setAttribute("list", list);
			session.setAttribute("list2", list2);
			RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
		     dispatcher.forward(request, response);
			return "admin.jsp";
		}
	}

}
