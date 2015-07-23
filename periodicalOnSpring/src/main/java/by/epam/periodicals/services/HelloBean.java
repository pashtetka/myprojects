package by.epam.periodicals.services;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Component;

@ManagedBean(name = "helloBean")
@SessionScoped
@Component("helloBean")
public class HelloBean {
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
