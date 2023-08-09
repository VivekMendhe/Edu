package com.edu.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Department {

	@Id
	private int DEPTNO;
	private String DNAME;
	private String LOCATION;

	@OneToOne(mappedBy = "dept")
	@JsonBackReference
	private Employees emp;

	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Department(int dEPTNO, String dNAME, String lOCATION) {
		super();
		DEPTNO = dEPTNO;
		DNAME = dNAME;
		LOCATION = lOCATION;
	}

	public int getDEPTNO() {
		return DEPTNO;
	}

	public void setDEPTNO(int dEPTNO) {
		DEPTNO = dEPTNO;
	}

	public String getDNAME() {
		return DNAME;
	}

	public void setDNAME(String dNAME) {
		DNAME = dNAME;
	}

	public String getLOCATION() {
		return LOCATION;
	}

	public void setLOCATION(String lOCATION) {
		LOCATION = lOCATION;
	}

	public Employees getEmp() {
		return emp;
	}

	public void setEmp(Employees emp) {
		this.emp = emp;
	}

	@Override
	public String toString() {
		return "Department [DEPTNO=" + DEPTNO + ", DNAME=" + DNAME + ", LOCATION=" + LOCATION + ", emp=" + emp + "]";
	}

}
