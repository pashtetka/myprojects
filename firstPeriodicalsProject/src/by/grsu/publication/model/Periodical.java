package by.grsu.publication.model;

public class Periodical implements IPeriodical {

	private int id;
	private String periodicalName;
	private int cost;
	private int outputsInMonth;
	private String topic;

	protected Periodical() {
	}

	public Periodical(int id, String periodicalName, int cost,
			int outputsInMonth, String topic) {
		this.id = id;
		this.periodicalName = periodicalName;
		this.cost = cost;
		this.outputsInMonth = outputsInMonth;
		this.topic = topic;
	}

	public String getPeriodicalName() {
		return periodicalName;
	}

	public void setPeriodicalName(String name) {
		this.periodicalName = name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getOutputsInMonth() {
		return outputsInMonth;
	}

	public void setOutputsInMonth(int outputsInMonth) {
		this.outputsInMonth = outputsInMonth;
	}

	public int getId() {
		return id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

}
