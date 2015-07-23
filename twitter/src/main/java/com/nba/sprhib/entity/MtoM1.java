package com.nba.sprhib.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "mtom1")
public class MtoM1 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToMany
	@JoinTable(name = "mtom1_mtom2", joinColumns = { @JoinColumn(name = "listMtoM1_id") }, inverseJoinColumns = { 
			@JoinColumn(name = "listMtoM2_id") })
	private List<MtoM2> listMtoM2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<MtoM2> getListMtoM() {
		return listMtoM2;
	}

	public void setListMtoM(List<MtoM2> listMtoM2) {
		this.listMtoM2 = listMtoM2;
	}

}
