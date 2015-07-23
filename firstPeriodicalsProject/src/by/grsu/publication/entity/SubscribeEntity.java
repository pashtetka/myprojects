package by.grsu.publication.entity;

public class SubscribeEntity {

	private int id;
	private int periodicalId;
	private int userId;
	private String statusSubscribe;

	public SubscribeEntity(int id, int periodicalId, int userId,
			String statusSubscribe) {
		this.id = id;
		this.periodicalId = periodicalId;
		this.userId = userId;
		this.statusSubscribe = statusSubscribe;
	}

	public int getPeriodicalId() {
		return periodicalId;
	}

	public void setPeriodicalId(int periodicalId) {
		this.periodicalId = periodicalId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getStatusSubscribe() {
		return statusSubscribe;
	}

	public void setStatusSubscribe(String statusSubscribe) {
		this.statusSubscribe = statusSubscribe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
