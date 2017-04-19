package application;

import java.io.Serializable;

public class UserData implements Serializable 
{
	
	/**
	 * Pole typu private static final long, sluzace przy deserializacji
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Pole typu string, przechowujace imie
	 */
	private String name;
	
	/**
	 * Pole typu string, przechowujace nazwisko
	 */
	private String surname;
	
	/**
	 * Pole typu string, przechowujace imie i nazwisko
	 */
	private String fullName;
	
	/**
	 * Pole typu string, przechowujace PESEL
	 */
	private String id;
	
	/**
	 * Pole typu string, przechowujace plec
	 */
	private String sex;
	
	/**
	 * Pole typu string, przechowujace typ ubezpieczenia
	 */
	private String insurance;
	
	/**
	 * Pole typu boolean, przechowujace informacje czy dany pacjent posiada badanie/badania
	 */
	private boolean examination;
	
	/**
	 * Konstruktor klasy UserData
	 * @param name
	 * @param surname
	 * @param id
	 * @param sex
	 * @param insurance
	 * @param examination
	 */
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

	/**
	 * Funkcja zwracajaca imie 
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Funkcja ustawiajaca imie
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Funkcja zwracajaca nazwisko
	 * @return
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Funkcja ustawiajaca nazwisko
	 * @param surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Funkcja zwracajaca imie i nazwisko
	 * @return
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Funkcja ustawiajaca imie i nazwisko
	 * @param fullName
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Funkcja zwracajaca PESEL
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * Funkcja ustawiajaca PESEL
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Funkcja zwracajaca plec
	 * @return
	 */
	public String getSex() {
		return sex;
	}
	
	/**
	 * Funkcja ustawiajaca plec
	 * @param sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * Funkcja zwracajaca tyb ubezpieczenia
	 * @return
	 */
	public String getInsurance() {
		return insurance;
	}

	/**
	 * Funkcja ustawiajaca typ ubezpieczenia
	 * @param insurance
	 */
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	/**
	 * Funkcja zwracajaca informacje czy uzytkownik ma badanie/badania
	 * @return
	 */
	public boolean getExamination() {
		return examination;
	}

	/**
	 * Funkcja ustawiajaca informacje czy uzytkownik ma badanie/badania
	 */
	public void setExamination(boolean examination) {
		this.examination = examination;
	}

	/**
	 * Funkcja zwracajaca serialVersionUID
	 * @return
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Nadpisana funkcja toString
	 */
	public String toString() {
		return "UserData [firstName=" + name + ", secondName=" + surname + ", id=" + id + ", sex=" + sex
				+ ", insurance=" + insurance + ", examination=" + examination + "]";
	}

}