package application;

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
			   
		   model.saveInfo(name, surname, id, sex, insurance);
		}
				
		return info;
	}
	
	
	
	public static void main(String[] args) 
	{
		
	}

}