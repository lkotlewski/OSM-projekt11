package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {
	
	public ObservableList<UserData> userObservableList =  FXCollections.observableArrayList(
            new UserData("Tomasz Wa³êsa", 'M', "68051307545", "NFZ", true),
            new UserData("Anna Nowak", 'K', "75112275345", "Brak", false),
            new UserData("Adam Jêdrzejewski", 'M', "95022075373", "NFZ", true),
            new UserData("Anna Mierzejewska", 'K', "87230210536", "NFZ", true)

            );
    
    
	/**
	 * Funkcja dodaje 1 do wartosci wejsciowej
	 * @return
	 */
	public static String addOne(String i)
	{
		
		return Integer.toString(Integer.parseInt(i) + 1);
		
	}
	
	public static void main(String[] args) 
	
	{

		
	}


}
