package employee;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Attend;
import bean.Users;
import dao.AdminDAO;
import dao.AttendanceDAO;
import tool.Action;


public class UpdateAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session=request.getSession();
		
//		対象のデータのidを取得
		int id = Integer.parseInt(request.getParameter("id"));
		
//		入力されている日付を取得		
		String date=request.getParameter("date");
		
//		出勤の記録か退勤の記録か
		int attend = Integer.parseInt(request.getParameter("attend"));
		
//		入力された時間を取得
		String hour = request.getParameter("selectHour");
		String min = request.getParameter("selectMin");
		String time = hour+ ":" + min;
		
//		それぞれのデータを格納
		Attend at = new Attend();
		at.setId(id);
		at.setDate(date);
		at.setAtTime(time);
		at.setLeTime(time);
		
		if (attend == 1) { //出勤記録の更新の場合
			AdminDAO dao=new AdminDAO();
			AttendanceDAO dao2=new AttendanceDAO();
			
//			出勤記録の更新
			dao2.updateAttend(at);
			
//			社員リストと更新した記録のデータをセット
			List<Users> list=dao.search();
			List<Attend> list2=dao2.searchRow(id);
			session.setAttribute("list", list);
			session.setAttribute("list2", list2);
			return "admin.jsp";
			
		}else if (attend == 2){ //退勤記録の更新の場合
			AdminDAO dao=new AdminDAO();
			AttendanceDAO dao2=new AttendanceDAO();
			
//			退勤記録の更新
			dao2.updateLeave(at);
			
//			社員リストと更新した記録のデータをセット
			List<Users> list=dao.search();
			List<Attend> list2=dao2.searchRow(id);
			session.setAttribute("list", list);
			session.setAttribute("list2", list2);
			return "admin.jsp";
		}else {
			return "admin.jsp";
		}
		

	}
}
