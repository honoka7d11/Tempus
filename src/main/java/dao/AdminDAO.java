package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Admin;



public class AdminDAO extends DAO {
	public List<Admin> search() throws Exception{
		List<Admin> list=new ArrayList<>();
		
		Connection con=getConnection();
		
		PreparedStatement st;
		st=con.prepareStatement("SELECT * FROM users;");
		ResultSet rs=st.executeQuery();
		
		
		while (rs.next()) {
			Admin admin = new Admin();
			admin.setId(rs.getInt("user_id"));
			admin.setName( rs.getString("name"));
			list.add(admin);
		}
		
		st.close();
		con.close();
		
		return list;
	}

}
