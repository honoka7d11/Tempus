package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.Attend;



public class AttendanceDAO extends DAO {
	public void insert(Attend at) throws Exception {
		Connection con=getConnection();
		
		PreparedStatement st=con.prepareStatement(
			"INSERT INTO attend (user_id, attend, date, at_time) VALUES (?, '出勤', ?, ?);");
		st.setInt(1, at.getUserId());
		st.setString(2, at.getDate());
		st.setString(3, at.getAtTime());
		
		
		st.executeUpdate();

		st.close();
		con.close();
		
	}
	
	public ArrayList<Object> search(int id, String date) throws Exception{
		ArrayList<Object> list=new ArrayList<>();
		
		Connection con=getConnection();
		
		PreparedStatement st;
		st=con.prepareStatement("SELECT * FROM attend WHERE user_id = ? and date = ? and attend = '出勤';");
		st.setInt(1, id);
		st.setString(2, date);
		ResultSet rs=st.executeQuery();
		
		
		while (rs.next()) {
			list.add(rs.getInt("user_id"));
		}
		
		st.close();
		con.close();
		
		return list;
	}
	
	public void leave(Attend at) throws Exception {
		Connection con=getConnection();
		
		PreparedStatement st=con.prepareStatement(
			"UPDATE attend SET leave = '退勤', le_time = ? where user_id = ? and date = ?;");
		st.setString(1, at.getLeTime());
		st.setInt(2, at.getUserId());
		st.setString(3, at.getDate());
		
		
		st.executeUpdate();

		st.close();
		con.close();
		
	}

}
