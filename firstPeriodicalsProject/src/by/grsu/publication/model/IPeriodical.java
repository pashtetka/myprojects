package by.grsu.publication.model;

public interface IPeriodical {

	public String getPeriodicalName();

	public void setPeriodicalName(String name);

	public int getCost();

	public void setCost(int cost);

	public int getOutputsInMonth();

	public void setOutputsInMonth(int outputsInMonth);

	public int getId();

	public String getTopic();

	public void setTopic(String topic);
}
