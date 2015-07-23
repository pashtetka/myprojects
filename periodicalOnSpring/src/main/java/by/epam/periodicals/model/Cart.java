package by.epam.periodicals.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "cart_periodical",
			   joinColumns = @JoinColumn(name = "cart_id"),
			   inverseJoinColumns = @JoinColumn(name = "periodical_id"))
	private List<Periodical> periodicals;
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public List<Periodical> getPeriodicals() {
		return periodicals;
	}


	public void setPeriodicals(List<Periodical> periodicals) {
		this.periodicals = periodicals;
	}

}
