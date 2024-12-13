package employee;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Users;
import dao.LoginoutDAO;
import tool.Action;


public class PassChangeAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		Users users = null;
		HttpSession session = request.getSession(false);
		
//		ログインしているユーザーの情報を確認
		 if (session != null && (users = (Users) session.getAttribute("users")) == null) {
		      session = null;
		    }

		    if (session == null) {
		      return "login.jsp";
		    }

//		ユーザーの社員番号を取得
		 int id = users.getId();
		 
//		 変更後のパスワードを取得
		 String pass = request.getParameter("password");
		 
//		 パスワード変更
		 LoginoutDAO dao = new LoginoutDAO();
		 dao.update(id, pass);
		 
//		 管理者権限の有無を確認し、メイン画面へ
		 Boolean admin=users.getAdmin();
		request.setAttribute("admin", admin);
		RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
		 dispatcher.forward(request, response);
		 
		 return "main.jsp";
	}
	
}