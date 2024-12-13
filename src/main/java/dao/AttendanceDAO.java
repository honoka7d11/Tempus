package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Attend;



public class AttendanceDAO extends DAO {
	
	//	出勤記録の登録
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
	
	//	今日出勤しているかの確認
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
	
	//	既に出勤記録のある行に退勤記録を追加
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
	
	//	勤怠記録の検索
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
			attend.setUserId(id);
			attend.setDate( rs.getString("date"));
			attend.setAttend(rs.getString("attend"));
			attend.setAtTime(rs.getString("at_time"));
			attend.setLeave(rs.getString("leave"));
			attend.setLeTime(rs.getString("le_time"));
			attend.setMonth(date);
			list.add(attend);
		}
		
		st.close();
		con.close();
		
		return list;
	}
	
	
	//	指定した行のデータを取得
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
	
	//	社員番号に相当する社員名を取得
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
	
//	勤怠記録のデータを取得
	public ArrayList<Object> searchData(int id) throws Exception{
		Connection con=getConnection();
		ArrayList<Object> list=new ArrayList<>();
		
		PreparedStatement st;
		st=con.prepareStatement("SELECT * FROM attend WHERE id = ?;");
		st.setInt(1, id);
		ResultSet rs=st.executeQuery();
		
			while (rs.next()) {
				list.add(rs.getString("user_id"));
				list.add(rs.getString("date"));
			}
			
			st.close();
			con.close();
			
			return list;
	}
	
	
	//	出勤記録の更新
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
	
	
	//	退勤記録の更新
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
	
	
	//	勤怠記録の削除
	public void delete(int id) throws Exception{
		
		Connection con=getConnection();
		
		PreparedStatement st;
		st=con.prepareStatement("DELETE FROM attend WHERE id = ?;");
		st.setInt(1, id);
		
		st.executeUpdate();
		
		st.close();
		con.close();
		
	}

}
