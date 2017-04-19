package application;

import java.util.regex.Pattern;

public class Tools 
{
	
	/**
	 * Funkcja zwraca prawde, gdy string jest liczba calkowita
	 * @param string
	 * @return
	 */
	public static boolean isInt(String string) 
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
	 * Funkcja zwraca prawde gdy string zawiera tylko duze i male litery
	 * @param value
	 * @return
	 */
	public static boolean isName(String value)
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
	public static boolean isFloat(String value)
	{
		boolean info = true;
		
		if(!Pattern.matches("([0-9]*[.])?[0-9]+", value))
			info = false;
			
			return info;
	}
	
	/**
	 * Funkcja zwracajaca prawde, gdy nie istnieje podany PESEL w bazie
	 * @param id
	 * @return
	 */
	public static boolean checkId(String id)
	{
		boolean info = true;
		
		for(int i = 0; i < Model.userObservableList.size(); i++)
		{
			if(Model.userObservableList.get(i).getId().equals(id))
			{
				info = false;
				break;
			}
		}
		
	return info;
		
	}
	
}
