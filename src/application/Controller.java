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
	
	/**
	 * Funkcja zracajaca prawde gdy string jest liczba zmiennaprzecinkowa
	 * @param value
	 * @return
	 */
	public boolean isFloat(String value)
	{
		boolean info = true;
		
		if(!Pattern.matches("([0-9]*[.])?[0-9]+", value))
			info = false;
			
			return info;
	}
	
	/**
	 * Funkcja sprawdzajaca czy istnieje juz podany PESEL
	 * @param id
	 * @return
	 */
	public boolean checkId(String id)
	{
		boolean info = true;
		
		for(int i = 0; i < Model.userObservableList.size(); i++)
		{
			if(Model.userObservableList.get(i).getId().equals(id))
			{
				info = false;
			}
		}
		
	return info;
		
	}
	
	
	/**
	 * Funkcja sluzaca do zapisu danych uzytkownika, zwraca prawde i zapisuje gdy dane sa poprawnie wprowadzone. Jesli row jest rowne -1 to dodawany jest 
	 * nowy uzytkownik w przeciwnym razie nastepuje aktualizacja juz istniejacego uzytkownika. 
	 * @param name
	 * @param surname
	 * @param id
	 * @param sex
	 * @param insurance
	 */
	boolean saveUser(String name, String surname, String id, String sex, String insurance, int row)
	{
		boolean info = true;
		
		
		if(row == -1)
		{
			if(!(isName(name) && isName(surname) && isInt(id) && checkId(id)&& id.length() == 11 && sex != null))
			{
				info = false;
			}
			else
			{  
				model.savePatientData(name, surname, id, sex, insurance, row);
			}
		}
		else
		{
			if(!(isName(name) && isName(surname) && isInt(id) && id.length() == 11 && sex != null))
			{
				info = false;
			}
			else
			{  
			   model.savePatientData(name, surname, id, sex, insurance, row);
			}
		}
		
		return info;
	}
	
	/**
	 * Funkcja sluzaca do zapisu badan uzytkownika, zwraca prawde i zapisuje dane gdy zostaly one poprawnie wprowadzone
	 * @param bilirubinLevel
	 * @param antiBodiesHCV
	 * @param antigenHBS
	 * @param examDate
	 * @param row
	 * @return
	 */
	boolean saveExamResults(String bilirubinLevel, boolean antiBodiesHCV, boolean antigenHBS,
			LocalDate examDate, int row)
	{
		boolean info = true;
		
		if(examDate == null || !isFloat(bilirubinLevel))
		{
			info = false;
		}
		else
		{
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