package by.epam.periodicals.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Entity
@Table(name = "comments")
@Audited
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "periodical_id")
	@NotAudited
	private Periodical periodical;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@NotAudited
	private User user;

	@Column(name = "comm")
	private String comm;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Periodical getPeriodical() {
		return periodical;
	}
	public void setPeriodical(Periodical periodical) {
		this.periodical = periodical;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getComm() {
		return comm;
	}
	public void setComm(String comm) {
		this.comm = comm;
	}
}
