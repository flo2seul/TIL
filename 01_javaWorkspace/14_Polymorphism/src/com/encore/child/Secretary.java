package com.encore.child;

import com.encore.parent.Employee;
import com.encore.parent.MyDate;

public class Secretary extends Employee {
	public String bossOfName;
	
	public Secretary(String name, MyDate birthday, double salary, String bossOfName) {
		super(name, birthday, salary);
		this.bossOfName = bossOfName;
	}
	
	public String getDetails() {
		
		return super.getDetails()+","+bossOfName;
	}
	
	public void changebossOfName(String bossOfName) {
		this.bossOfName = bossOfName;
	}

}
