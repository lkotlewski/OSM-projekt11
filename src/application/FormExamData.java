package application;

import java.time.LocalDate;

public class FormExamData {
	
	/**
	 * Pole typu String, przechowujace stezenie bilirubiny
	 */
	String bilirubinLevel;
	
	/**
	 * Pole typu boolean, informujace czy wykryto przeciwcia³a HCV
	 */
	boolean antiBodiesHCV;
	
	/**
	 * Pole typu boolean, informujace czy wykryto antygen HBS
	 */
	boolean antigenHBS;
	
	/**
	 * Obiekt typu LocalDate, przechowujace date badania
	 */
	LocalDate examDate;
	
	/**
	 * Konstruktor klasy FormExamData
	 * @param bilirubinLevel
	 * @param antiBodiesHCV
	 * @param antigenHBS
	 * @param examDate
	 */
	public FormExamData(String bilirubinLevel, boolean antiBodiesHCV, boolean antigenHBS, LocalDate examDate) {
		super();
		this.bilirubinLevel = bilirubinLevel;
		this.antiBodiesHCV = antiBodiesHCV;
		this.antigenHBS = antigenHBS;
		this.examDate = examDate;
		
	}

	/**
	 * Funkcja zwracajaca stezenie bilirubiny
	 * @return
	 */
	public String getBilirubinLevel() {
		return bilirubinLevel;
	}

	/**
	 * Funkcja sprawdzajaca czy zostaly wykryte przeciwciala HCV
	 * @return
	 */
	public boolean isAntiBodiesHCV() {
		return antiBodiesHCV;
	}

	/**
	 * Funkcja sprawdzajaca czy zostal wykryty Antygen HBS
	 * @return
	 */
	public boolean isAntigenHBS() {
		return antigenHBS;
	}

	/**
	 * Funkcja zwracajaca date badania
	 * @return
	 */
	public LocalDate getExamDate() {
		return examDate;
	}
	
}