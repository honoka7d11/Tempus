package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Attend;
import bean.Users;


public class AdminDAO extends DAO {
	public List<Users> search() throws Exception{
		List<Users> list=new ArrayList<>();
		
		Connection con=getConnection();
		
		PreparedStatement st;
		st=con.prepareStatement("SELECT * FROM users ORDER BY id;");
		ResultSet rs=st.executeQuery();
		
		
		while (rs.next()) {
			Users admin = new Users();
			admin.setId(rs.getInt("id"));
			admin.setName( rs.getString("name"));
			list.add(admin);
		}
		
		st.close();
		con.close();
		
		return list;
	}
	
	public List<Attend> searchAttend(int id, String date) throws Exception{
		List<Attend> list=new ArrayList<>();
		
		Connection con=getConnection();
		
		PreparedStatement st;
		st=con.prepareStatement("SELECT * FROM attend WHERE user_id = ? and date LIKE ? ORDER BY date;");
		st.setInt(1, id);
		st.setString(2, date);
		ResultSet rs=st.executeQuery();
		
		while (rs.next()) {
			Attend attend = new Attend();
			attend.setId(rs.getInt("id"));
			attend.setDate( rs.getString("date"));
			attend.setAttend(rs.getString("attend"));
			attend.setAtTime(rs.getString("at_time"));
			attend.setLeave(rs.getString("leave"));
			attend.setLeTime(rs.getString("le_time"));
			list.add(attend);
		}
		
		st.close();
		con.close();
		
		return list;
	}
	
	public List<Attend> searchRow(int id) throws Exception{
		List<Attend> list=new ArrayList<>();
		
		Connection con=getConnection();
		
		PreparedStatement st;
		st=con.prepareStatement("SELECT * FROM attend WHERE id = ? ;");
		st.setInt(1, id);
		ResultSet rs=st.executeQuery();
		
		while (rs.next()) {
			Attend attend = new Attend();
			attend.setId(rs.getInt("id"));
			attend.setDate( rs.getString("date"));
			attend.setAttend(rs.getString("attend"));
			attend.setAtTime(rs.getString("at_time"));
			attend.setLeave(rs.getString("leave"));
			attend.setLeTime(rs.getString("le_time"));
			list.add(attend);
		}
		
		st.close();
		con.close();
		
		return list;
	}
	
	public String searchName(int id) throws Exception{
		Connection con=getConnection();
		Attend attend=null;
		
		PreparedStatement st;
		st=con.prepareStatement("SELECT * FROM users WHERE id = ?;");
		st.setInt(1, id);
		ResultSet rs=st.executeQuery();
		
			while (rs.next()) {
				attend = new Attend();
				attend.setName(rs.getString("name"));
			}
			String name = attend.getName();
			
			st.close();
			con.close();
			
			return name;
		
	}
	
	public void update(boolean admin, int id) throws Exception{
		
		Connection con=getConnection();
		
		PreparedStatement st;
		st=con.prepareStatement("UPDATE users SET admin = ? WHERE id = ?;");
		st.setBoolean(1, admin);
		st.setInt(2, id);
		
		st.executeUpdate();
		
		st.close();
		con.close();
		
	}
	
	public void updateAttend(Attend attend) throws Exception{
		
		Connection con=getConnection();
		
		PreparedStatement st;
		st=con.prepareStatement("UPDATE attend SET date = ?, at_time = ? WHERE id = ?;");
		st.setString(1, attend.getDate());
		st.setString(2, attend.getAtTime());
		st.setInt(3, attend.getId());
		st.executeUpdate();
		
		st.close();
		con.close();
		
	}
	
	public void updateLeave(Attend attend) throws Exception{
		
		Connection con=getConnection();
		
		PreparedStatement st;
		st=con.prepareStatement("UPDATE attend SET date = ?, le_time = ? WHERE id = ?;");
		st.setString(1, attend.getDate());
		st.setString(2, attend.getLeTime());
		st.setInt(3, attend.getId());
		st.executeUpdate();
		
		st.close();
		con.close();
		
	}
	
	public void delete(int id) throws Exception{
		
		Connection con=getConnection();
		
		PreparedStatement st;
		st=con.prepareStatement("DELETE FROM users WHERE id = ?;");
		st.setInt(1, id);
		
		st.executeUpdate();
		
		st.close();
		con.close();
		
	}
	
	public void insert(Users users) throws Exception{
		
		
		Connection con=getConnection();
		
		PreparedStatement st;
		st=con.prepareStatement("INSERT INTO users (id, name, admin, password) VALUES (?, ?, ?, ?);");
		st.setInt(1, users.getId());
		st.setString(2, users.getName());
		st.setBoolean(3, users.getAdmin());
		st.setString(4, users.getPassword());
		
		
		st.executeUpdate();
		
		st.close();
		con.close();
		
	}
	

}
