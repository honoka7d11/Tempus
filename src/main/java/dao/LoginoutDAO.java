package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Users;

public class LoginoutDAO extends DAO {
	
	// ユーザーログイン情報をSELECTする分
	public Users serch(int id, String password)
	throws Exception {
		
		Users users = null;
		
		Connection con = getConnection();
		
		PreparedStatement st;
		
		st = con.prepareStatement(
				"select * from users where id = ? and password = ?");
		st.setInt(1, id);
		st.setString(2, password);
		ResultSet rs = st.executeQuery();
		
		while (rs.next()) {
			users = new Users();
			users.setId(rs.getInt("id"));
			users.setName(rs.getString("name"));
			users.setPassword(rs.getString("password"));
			users.setAdmin(rs.getBoolean("admin"));
		}
		
		st.close();
		con.close();
		return users;
		
	}	
	
}