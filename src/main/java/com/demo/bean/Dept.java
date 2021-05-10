package com.demo.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author yuhao
 * @date: 2021/5/10
 * @description:
 */
public class Dept implements Serializable {

	private int deptId;
	private String deptName;
	private String deptInfo;
	private Date deptCreateDate;

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptInfo() {
		return deptInfo;
	}

	public void setDeptInfo(String deptInfo) {
		this.deptInfo = deptInfo;
	}

	public Date getDeptCreateDate() {
		return deptCreateDate;
	}

	public void setDeptCreateDate(Date deptCreateDate) {
		this.deptCreateDate = deptCreateDate;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	private List<Employee> employeeList;

	@Override
	public String toString() {
		return "Dept{" +
				"deptId=" + deptId +
				", deptName='" + deptName + '\'' +
				", deptInfo='" + deptInfo + '\'' +
				", deptCreateDAte=" + deptCreateDate +
				", employeeList=" + employeeList +
				'}';
	}
}
