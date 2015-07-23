package by.grsu.publication.model;

public interface IUser {

	public int getId();

	public String getLogin();

	public String getPassword();

	public void setPassword(String password);

	public String getUserType();

	public void setUserType(String userType);

	public String getUserName();

	public void setUserName(String userName);

	public String getUserSurname();

	public void setUserSurname(String userSurname);
}
