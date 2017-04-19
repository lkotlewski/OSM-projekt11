package application;

public class FormUserData {
	
	/**
	 * Pole typu String przechowujace imie 
	 */
	private String name;
	
	/**
	 * Pole typu String przechowujace nazwisko 
	 */
	private String surname;
	
	/**
	 * Pole typu String przechowujace PESEL 
	 */
	private String id;
	
	/**
	 * Pole typu String przechowujace plec 
	 */
	private String sex;
	
	/**
	 * Pole typu String przechowujace typ ubezpieczenia 
	 */
	private String insurance;
	
	/**
	 * Konstruktor klasy FormUserData
	 * @param name
	 * @param surname
	 * @param id
	 * @param sex
	 * @param insurance
	 */
	public FormUserData(String name, String surname, String id, String sex, String insurance) {
		super();
		this.name = name;
		this.surname = surname;
		this.id = id;
		this.sex = sex;
		this.insurance = insurance;
	}
	
	/**
	 * Funkcja zwracajaca imie
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Funkcja zwracajaca nazwisko
	 * @return
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * Funkcja zwracajaca PESEl
	 * @return
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Funkcja zwracajaca plec
	 * @return
	 */
	public String getSex() {
		return sex;
	}
	
	/**
	 * Funkcja zwracajaca typ ubezpieczenia
	 * @return
	 */
	public String getInsurance() {
		return insurance;
	}
	
	
}
