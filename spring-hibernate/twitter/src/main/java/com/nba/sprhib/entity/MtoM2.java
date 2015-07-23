package com.nba.sprhib.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "mtom2")
public class MtoM2 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToMany(mappedBy = "listMtoM2")
	private List<MtoM1> listMtoM1;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<MtoM1> getListMtoM1() {
		return listMtoM1;
	}

	public void setListMtoM1(List<MtoM1> listMtoM1) {
		this.listMtoM1 = listMtoM1;
	}
}
