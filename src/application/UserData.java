package application;

import java.io.Serializable;

public class UserData implements Serializable 
{

	private static final long serialVersionUID = 1L;
	private String name;
	private String surname;
	private String fullName;
	private String id;
	private String sex;
	private String insurance;
	private boolean examination;
	
	private UserData() 
	{
		this.name = "";
		this.surname = "";
		this.fullName = "";
		this.id = "0";
		this.sex = "";
		this.insurance = "";
		this.examination = false;
	}
	
	public UserData(String name, String surname, String id, String sex, String insurance, boolean examination)
	{
		this.name = name;
		this.surname = surname;
		this.fullName = name + " " + surname;
		this.id = id;
		this.sex = sex;
		this.insurance = insurance;
		this.examination = examination;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public boolean getExamination() {
		return examination;
	}

	public void setExamination(boolean examination) {
		this.examination = examination;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UserData [firstName=" + name + ", secondName=" + surname + ", id=" + id + ", sex=" + sex
				+ ", insurance=" + insurance + ", examination=" + examination + "]";
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}
}