package employee;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Attend;
import bean.Users;
import dao.AttendanceDAO;
import tool.Action;


public class AttendanceAction extends Action {
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
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		Attend at = new Attend();
		
		Calendar now = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(now.getTime());
		
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String nowtime = currentTime.format(formatter);
        
        if (id == 1) {
			at.setUserId(users.getId());
			at.setDate(today);
			at.setAtTime(nowtime);
			
			AttendanceDAO dao = new AttendanceDAO();
			dao.insert(at);
			
			System.out.println(users.getId());
        }else if(id == 2) {
        	
        	AttendanceDAO dao = new AttendanceDAO();
        	ArrayList<Object> list=dao.search(users.getId(), today);
        	
        	if (list == null || list.size() == 0) {
        		boolean admin=users.getAdmin();
        		boolean error=false;
                request.setAttribute("admin", admin);
                request.setAttribute("error", error);
        		RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
        	    dispatcher.forward(request, response);
        		return "main.jsp";
        	}else{
	        	at.setUserId(users.getId());
				at.setAttend("退勤");
				at.setDate(today);
				at.setLeTime(nowtime);
				
				
				dao.leave(at);
        	}
		
        }
        
        
        boolean admin=users.getAdmin();
        request.setAttribute("admin", admin);
		RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
	    dispatcher.forward(request, response);
        
		return "main.jsp";
      
	}

}
