package application;

public class FormUserData {
	private String name;
	private String surname;
	private String id;
	private String sex;
	private String insurance;
	public FormUserData(String name, String surname, String id, String sex, String insurance) {
		super();
		this.name = name;
		this.surname = surname;
		this.id = id;
		this.sex = sex;
		this.insurance = insurance;
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public String getId() {
		return id;
	}
	public String getSex() {
		return sex;
	}
	public String getInsurance() {
		return insurance;
	}
	
	
}
