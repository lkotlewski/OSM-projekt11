package application;


import java.io.Serializable;
import java.time.LocalDate;

public class ExamData implements Serializable 
{
	/**
	 *Pole typu private static final long, sluzace przy deserializacji
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Pole typu float, informujace o stezeniu bilirubinLevel
	 */
	float bilirubinLevel;
	
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
	 * Pole typu String, przechowujace PESEL
	 */
	String id;
	
	/**
	 * Konstruktor klasy ExamData
	 * @param bilirubinLevel
	 * @param antiBodiesHCV
	 * @param antigenHBS
	 * @param examDate
	 * @param iD
	 */
	public ExamData(float bilirubinLevel, boolean antiBodiesHCV, boolean antigenHBS, LocalDate examDate, String iD)
	{
		super();
		this.bilirubinLevel = bilirubinLevel;
		this.antiBodiesHCV = antiBodiesHCV;
		this.antigenHBS = antigenHBS;
		this.examDate = examDate;
		id = iD;
	}
	
	/**
	 * Funkcja zwracajaca stezenie bilirubiny
	 * @return
	 */
	public float getBilirubinLevel()
	{
		return bilirubinLevel;
	}
	
	/**
	 * Funkcja ustawiajaca stezenie bilirubiny
	 * @param bilirubinLevel
	 */
	public void setBilirubinLevel(float bilirubinLevel)
	{
		this.bilirubinLevel = bilirubinLevel;
	}
	
	/**
	 * Funkcja sprawdzajaca czy zostaly wykryte przeciwciala HCV
	 * @return
	 */
	public boolean isAntiBodiesHCV()
	{
		return antiBodiesHCV;
	}
	
	/**
	 * Funkcja ustawiajaca czy zostaly wykryte przeciwciala HCV
	 * @param antiBodiesHCV
	 */
	public void setAntiBodiesHCV(boolean antiBodiesHCV) 
	{
		this.antiBodiesHCV = antiBodiesHCV;
	}
	
	/**
	 * Funkcja sprawdzajaca czy zostaly zostal wykryte antygen HBS
	 * @return
	 */
	public boolean isAntigenHBS()
	{
		return antigenHBS;
	}
	
	/**
	 * Funkcja ustawiajaca czy zostal wykryty antygen HBS
	 * @param antigenHBS
	 */
	public void setAntigenHBS(boolean antigenHBS) 
	{
		this.antigenHBS = antigenHBS;
	}
	
	/**
	 * Funkcja zwracajaca date badania
	 * @return
	 */
	public LocalDate getExamDate() 
	{
		return examDate;
	}
	
	/**
	 * Funkcja ustawiajaca date badania 
	 * @param examDate
	 */
	public void setExamDate(LocalDate examDate) 
	{
		this.examDate = examDate;
	}
	
	/**
	 * Funkcja zwracajaca PESEL
	 * @return
	 */
	public String getId() 
	{
		return id;
	}
	
	/**
	 * Funkcja ustawiajaca PESEL
	 * @param id
	 */
	public void setId(String id)
	{
		this.id = id;
	}
	
	/**
	 * Nadpisana funkcja toString
	 */
	public String toString() 
	{
		return "ExamData [bilirubinLevel=" + bilirubinLevel + ", antiBodiesHCV=" + antiBodiesHCV + ", antigenHBS="
				+ antigenHBS + ", examDate=" + examDate.toString() + ", Id=" + id +"]";
	}
}