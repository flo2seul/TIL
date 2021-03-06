package com.encore.child;

import com.encore.parent.Employee;
import com.encore.parent.MyDate;

public class Engineer extends Employee {
	
	//자식만의 멤버 추가
	private String tech;
	private int bonus;

	
	public Engineer(String name, MyDate birthday, double salary, String tech, int bonus) {
		super(name, birthday, salary);
		this.tech = tech;
		this.bonus = bonus;
	}

	public String getDetails() {
		
		return super.getDetails()+","+tech;
	}
	
	public void changeTech(String tech) {
		this.tech = tech;
	}

	public int getBonus() {
		return bonus;
	}
	
	
	
	
	

}
