package bean;

public class Attend implements java.io.Serializable {
	private int id;
	private int userId;
	private String attend;
	private String leave;
	private String date;
	private String atTime;
	private String leTime;
	private String reason;
	private String name;
	
	
	//ゲッター
	public int getId() {
		return id;
	}
	
	public int getUserId() {
		return userId;
	}
	public String getAttend() {
		return attend;
	}

	public String getDate() {
		return date;
	}
	
	public String getAtTime() {
		return atTime;
	}
	
	public String getLeave() {
		return leave;
	}
	
	public String getLeTime() {
		return leTime;
	}
	
	public String getReason() {
		return reason;
	}
	
	public String getName() {
		return name;
	}

	//セッター
	public void setId(int id) {
		this.id=id;
	}
	
	public void setUserId(int userId) {
		this.userId=userId;
	}
	
	public void setAttend(String attend) {
		this.attend=attend;
	}

	public void setDate(String date) {
		this.date=date;
	}
	
	public void setAtTime(String atTime) {
		this.atTime=atTime;
	}
	
	public void setLeave(String leave) {
		this.leave=leave;
	}
	
	public void setLeTime(String leTime) {
		this.leTime=leTime;
	}
	
	public void setReason(String reason) {
		this.reason=reason;
	}
	
	public void setName(String name) {
		this.name=name;
	}

}
