package com.demo.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yuhao
 * @date: 2021/5/10
 * @description:
 */
public class Employee implements Serializable {

	private int empId;
	private String empName;
	private String empTel;
	private String empEducation;
	private Date empBirthday;
	private Dept dept;

	@Override
	public String toString() {
		return "Employee{" +
				"empId=" + empId +
				", empName='" + empName + '\'' +
				", empTel='" + empTel + '\'' +
				", empEducation='" + empEducation + '\'' +
				", empBirthday=" + empBirthday +
				", dept=" + dept +
				'}';
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpTel() {
		return empTel;
	}

	public void setEmpTel(String empTel) {
		this.empTel = empTel;
	}

	public String getEmpEducation() {
		return empEducation;
	}

	public void setEmpEducation(String empEducation) {
		this.empEducation = empEducation;
	}

	public Date getEmpBirthday() {
		return empBirthday;
	}

	public void setEmpBirthday(Date empBirthday) {
		this.empBirthday = empBirthday;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}
}
