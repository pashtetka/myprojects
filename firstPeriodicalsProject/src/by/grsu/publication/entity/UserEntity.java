package by.grsu.publication.entity;

public class UserEntity {

	private int id;
	private String login;
	private String password;
	private String mail;
	private String userType;
	private String userName;
	private String userSurname;

	protected UserEntity() {
	}

	public UserEntity(int id, String login, String password, String mail,
			String userType, String userName, String userSurname) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.mail = mail;
		this.userType = userType;
		this.userName = userName;
		this.userSurname = userSurname;
	}

	public UserEntity(int id, String login, String userType) {
		this.id = id;
		this.login = login;
		this.userType = userType;
	}

	public int getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSurname() {
		return userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
