package ua.prettl.downloading.model;

public class Employee {
	
	public static final String COL_NAME = "colName";
	public static final String COL_AUTHORIZATION_NAME = "colAuthorizationCode";
	public static final String COL_EMPLOYEE_NUMBER = "colTabNumber";
	public static final String COL_SURNAME = "colSurname";
	public static final String COL_MIDDLE_NAME = "colMiddleName";
		
	private String authorizationCode;
	private String employeeNumber;
	private String surName;
	private String name;
	private String middleName;
	
	public Employee() {}
	
	public Employee(String authorizationCode, String employeeNumber, String surName, String name, String middleName) {
		super();
		this.authorizationCode = authorizationCode;
		this.employeeNumber = employeeNumber;
		this.surName = surName;
		this.name = name;
		this.middleName = middleName;
	}
	public String getAuthorizationCode() {
		return authorizationCode;
	}
	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorizationCode == null) ? 0 : authorizationCode.hashCode());
		result = prime * result + ((employeeNumber == null) ? 0 : employeeNumber.hashCode());
		result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surName == null) ? 0 : surName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (authorizationCode == null) {
			if (other.authorizationCode != null)
				return false;
		} else if (!authorizationCode.equals(other.authorizationCode))
			return false;
		if (employeeNumber == null) {
			if (other.employeeNumber != null)
				return false;
		} else if (!employeeNumber.equals(other.employeeNumber))
			return false;
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surName == null) {
			if (other.surName != null)
				return false;
		} else if (!surName.equals(other.surName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Employee [authorizationCode=" + authorizationCode + ", employeeNumber=" + employeeNumber + ", surName="
				+ surName + ", name=" + name + ", middleName=" + middleName + "]";
	}
	
	
	
	
	

}
