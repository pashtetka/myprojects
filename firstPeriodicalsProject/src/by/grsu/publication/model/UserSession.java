package by.grsu.publication.model;

public class UserSession {

	private int userId;
	private String userName;
	private String userRole;

	public UserSession(int userId, String userName, String userRole) {
		this.setUserId(userId);
		this.userName = userName;
		this.userRole = userRole;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
