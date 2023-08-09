package com.edu.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Employees {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long EMPNO;
	private String ENAME;
	private String JOB;
	private String MGR;
	private String HIREDATE;
	private int SAL;
	private int COMM;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private Department dept;

	public Employees() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employees(long eMPNO, String eNAME, String jOB, String mGR, String hIREDATE, int sAL, int cOMM,
			Department dept) {
		super();
		EMPNO = eMPNO;
		ENAME = eNAME;
		JOB = jOB;
		MGR = mGR;
		HIREDATE = hIREDATE;
		SAL = sAL;
		COMM = cOMM;
		this.dept = dept;
	}

	public Employees(String eNAME, String jOB, String mGR, String hIREDATE, int sAL, int cOMM, Department dept) {
		super();
		ENAME = eNAME;
		JOB = jOB;
		MGR = mGR;
		HIREDATE = hIREDATE;
		SAL = sAL;
		COMM = cOMM;
		this.dept = dept;
	}

	public long getEMPNO() {
		return EMPNO;
	}

	public void setEMPNO(long eMPNO) {
		EMPNO = eMPNO;
	}

	public String getENAME() {
		return ENAME;
	}

	public void setENAME(String eNAME) {
		ENAME = eNAME;
	}

	public String getJOB() {
		return JOB;
	}

	public void setJOB(String jOB) {
		JOB = jOB;
	}

	public String getMGR() {
		return MGR;
	}

	public void setMGR(String mGR) {
		MGR = mGR;
	}

	public String getHIREDATE() {
		return HIREDATE;
	}

	public void setHIREDATE(String hIREDATE) {
		HIREDATE = hIREDATE;
	}

	public int getSAL() {
		return SAL;
	}

	public void setSAL(int sAL) {
		SAL = sAL;
	}

	public int getCOMM() {
		return COMM;
	}

	public void setCOMM(int cOMM) {
		COMM = cOMM;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "Employees [EMPNO=" + EMPNO + ", ENAME=" + ENAME + ", JOB=" + JOB + ", MGR=" + MGR + ", HIREDATE="
				+ HIREDATE + ", SAL=" + SAL + ", COMM=" + COMM + ", dept=" + dept + "]";
	}

}
