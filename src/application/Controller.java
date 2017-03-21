package application;

public class Controller {
	/**
	 * Publiczna metoda do pobierania informacji
	 * @param data
	 */
	public String getData(String data)
	{
		String info = "";
		
		if(isInt(data))
		{
			info = Model.addOne(data);
		}
		else
		{
			info = "B³êdna dana";
		}
		
		return info;
	}

	
	/**
	 * Funkcja sprawdza czy string jest liczba calkowita
	 * @param string
	 * @return
	 */
	private boolean isInt(String string) 
	{
		boolean info = true;
		
		try
		{
			
		Integer.parseInt(string);
		
		}
		catch(NumberFormatException e)
		{
			info = false;
		}
		
		return info;
	}
	
	
	
	public static void main(String[] args) 
	{
		
	}

}
