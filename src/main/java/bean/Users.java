package bean;

public class Users implements java.io.Serializable {
	private int id;
	private String name;
	private String password;
	private boolean admin;
	
	
	//ゲッター
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}
	public boolean getAdmin() {
		return admin;
	}

	//セッター
	public void setId(int id) {
		this.id=id;
	}
	public void setName(String name) {
		this.name=name;
	}

	public void setPassword(String password) {
		this.password=password;
	}
	public void setAdmin(boolean admin) {
		this.admin=admin;
	}


}

