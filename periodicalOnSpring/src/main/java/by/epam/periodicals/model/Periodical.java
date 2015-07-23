package by.epam.periodicals.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.joda.time.DateTime;
import org.springframework.data.domain.Auditable;

import by.epam.periodicals.model.enume.PeriodicalTopic;

@Entity
@Audited
@Table(name = "periodicals")
public class Periodical implements Auditable<User, Long>, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "periodical_name")
	private String periodicalName;

	@Column(name = "cost")
	private int cost;

	@Column(name = "outputs_in_month")
	private int outputsInMonth;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "topic")
	private PeriodicalTopic topic;

	@OneToMany(mappedBy = "periodical")
	@NotAudited
	private List<Comment> comments;

	@ManyToOne
	@JoinColumn(name = "CREATED_BY")
	@NotAudited
	private User createdBy;

	@Column(name = "CREATED_DATE", columnDefinition = "LONGBLOB")
	private DateTime createdDate;

	@ManyToOne
	@JoinColumn(name = "LAST_MODIFIED_BY")
	@NotAudited
	private User lastModifiedBy;

	@Column(name = "LAST_MODIFIED_DATE", columnDefinition = "LONGBLOB")
	private DateTime lastModifiedDate;

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getId() {
		return id;
	}

	public PeriodicalTopic getTopic() {
		return topic;
	}

	public void setTopic(PeriodicalTopic topic) {
		this.topic = topic;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public DateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(DateTime createdDate) {
		this.createdDate = createdDate;
	}

	public User getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(User lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public DateTime getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(DateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	@Transient
	public boolean isNew() {
		if (id == null) {
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		return "Contact - Id: " + id + ", First name: " + periodicalName
				+ ", Create by: " + createdBy + ", Create date: " + createdDate
				+ ", Modified by: " + lastModifiedBy + ", Modified date: "
				+ lastModifiedDate;
	}

}
