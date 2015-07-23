package by.grsu.publication.model;

public class Comments {
	
	private int id;
	private int periodicalId;
	private String userLogin;
	private String comm;
	
	public Comments(int id, int periodicalId, String userLogin, String comm) {
		this.id = id;
		this.periodicalId = periodicalId;
		this.userLogin = userLogin;
		this.comm = comm;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getPeriodicalId() {
		return periodicalId;
	}
	public void setPeriodicalId(int periodicalId) {
		this.periodicalId = periodicalId;
	}
	public String getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	public String getComm() {
		return comm;
	}
	public void setComm(String comm) {
		this.comm = comm;
	}

}
