package employee;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Users;
import dao.AdminDAO;
import tool.Action;


public class UserAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session=request.getSession();
		
//		管理者の登録・削除、社員情報の削除、新規社員の登録のうちどの処理か
		int id = Integer.parseInt(request.getParameter("id"));
		

		if (id == 1) {	//管理者の登録・削除のとき
			int userId = Integer.parseInt(request.getParameter("selectName"));
			int admin = Integer.parseInt(request.getParameter("selectAdmin"));
			if (userId == 0 || admin == 0) { //何も選択されていない場合
				AdminDAO dao = new AdminDAO();
				List<Users> list=dao.search();
				
				session.setAttribute("list", list);
				
				return "users.jsp";
			}else if (admin == 1) {	//管理者へ追加
				AdminDAO dao = new AdminDAO();
				dao.update(true, userId);
				List<Users> list=dao.search();
				
				session.setAttribute("list", list);
				
				return "users.jsp";
			}else if (admin == 2) {	//管理者登録の解除
				AdminDAO dao = new AdminDAO();
				dao.update(false, userId);
				List<Users> list=dao.search();
				
				session.setAttribute("list", list);
				
				return "users.jsp";
			}else {	//それ以外の場合
				AdminDAO dao = new AdminDAO();
				List<Users> list=dao.search();
				
				session.setAttribute("list", list);
				return "users.jsp";
			}
			
		}else if (id == 2) {	//	社員情報の削除
//			対象の社員の社員番号
			int userId = Integer.parseInt(request.getParameter("selectEmp"));
			
			if (userId == 0) {	//何も選択されていない場合
				AdminDAO dao = new AdminDAO();
				List<Users> list=dao.search();
				
				session.setAttribute("list", list);
				
				return "users.jsp";
			}
			
//			社員情報の削除
			AdminDAO dao = new AdminDAO();
			dao.delete(userId);
			List<Users> list=dao.search();
			
			session.setAttribute("list", list);
				
			return "users.jsp";
			
			
		}else if (id == 3){	//新規社員の登録
			int userId = Integer.parseInt(request.getParameter("user_id"));
			String name = request.getParameter("user_name");
			String password = request.getParameter("password");
			int adminNum = Integer.parseInt(request.getParameter("admin"));
			
			if (adminNum == 0) {	//管理者権限がない場合
				Users users = new Users();
				users.setId(userId);
				users.setName(name);
				users.setPassword(password);
				users.setAdmin(false);
	
				AdminDAO dao = new AdminDAO();
				dao.insert(users);
				List<Users> list=dao.search();
				session.setAttribute("list", list);
			}else {	//管理者権限がある場合
				Users users = new Users();
				users.setId(userId);
				users.setName(name);
				users.setPassword(password);
				users.setAdmin(true);
	
				AdminDAO dao = new AdminDAO();
				dao.insert(users);
				List<Users> list=dao.search();
				session.setAttribute("list", list);
			}
			
			return "users.jsp";
		}else {
			return "login.jsp";
		}
		
		
	}

}