package application;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class Controller 
{

	/**
	 * Nowy obiekt klasy Model
	 */
	Model model = new Model();
	
	/**
	 * Funkcja zwraca prawde, gdy string jest liczba calkowita
	 * @param string
	 * @return
	 */
	private boolean isInt(String string) 
	{
		boolean info = true;
		
		try
		{
			
		Long.parseLong(string);
		
		}
		catch(NumberFormatException e)
		{
			info = false;
		}
		
		return info;
	}
	
	
	/**
	 * Zwraca prawde gdy string zawiera tylko duze i male litery
	 * @param value
	 * @return
	 */
	public boolean isName(String value)
	{
		boolean info = true;
		
		if(!Pattern.matches("[A-Za-zøüÊÒÛ≥ÍπúØè∆•å £”—]*", value))
			info = false;
			
			return info;
	}
	
	public boolean isFloat(String value){
boolean info = true;
		
		if(!Pattern.matches("([0-9]*[.])?[0-9]+", value))
			info = false;
			
			return info;
	}
	
	/**
	 * Funkcja sluzaca do zapisu uzytkownika, zwraca prawde gdy dane sa poprawnie wprowadzone
	 * @param name
	 * @param surname
	 * @param id
	 * @param sex
	 * @param insurance
	 */
	boolean saveUser(String name, String surname, String id, String sex, String insurance)
	{
		boolean info = true;
		if(!(isName(name) && isName(surname) && isInt(id) && model.checkId(id)&& id.length() == 11 && sex != null))
		{
			info = false;
		}
		else
		{  
		   model.savePatientData(name, surname, id, sex, insurance);
		}
		return info;
	}
	
	boolean saveExamResults(String bilirubinLevel, boolean antiBodiesHCV, boolean antigenHBS,
			LocalDate examDate, int row)
	{
		boolean info = true;
		if(examDate==null || !isFloat(bilirubinLevel)){
			info = false;
		}
		else{
		model.saveExamData(Float.valueOf(bilirubinLevel), antiBodiesHCV, antigenHBS, examDate, 
				          Model.userObservableList.get(row).getId());
		Model.userObservableList.get(row).setExamination(true);
		}
		return info;
	}
	
	public static void main(String[] args) 
	{
		
	}

}