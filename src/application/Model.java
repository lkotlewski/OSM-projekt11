package application;


import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model 
{
	
	/**
	 * Nowy, statyczny oobiekt typu ObservableList<UserData>
	 */
	public static ObservableList<UserData> userObservableList =  FXCollections.observableArrayList();

    
	/**
	 * Funkcja zwraca prawde, gdy w userObservableList nie ma podanego PESEL-uS
	 * @param id
	 * @return
	 */
	public boolean checkId(String id)
	{
		boolean info = true;
		
		for(int i = 0; i < userObservableList.size(); i++)
		{
			if(userObservableList.get(i).getId().equals(id))
			{
				info = false;
			}
		}
		
	return info;
		
	}
	
	/**
	 * Funkcja zapisujaca dane do userObservableList oraz zewnetrznego pliku Data.ser
	 * @param name
	 * @param surname
	 * @param id
	 * @param sex
	 * @param insurance
	 */
	public void saveInfo(String name, String surname, String id, String sex, String insurance)
	{
		UserData userData = new UserData(name, surname, id, sex, insurance, false);
		userObservableList.add(userData);
		
		try 
		{
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Data.ser"));	
			for(int i = 0; i < userObservableList.size(); i++)
			{
				objectOutputStream.writeObject(userObservableList.get(i));
			}

			objectOutputStream.close();	
						
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	
	}
	
	/**
	 * Funkcja przeprowadza desrializacje danych z pliku Data.ser i wczytuje je do userObservableList
	 */
	public void deserializeData()
	{

		try 
		{
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Data.ser"));	
			
			while(true)
			{
				try
				{
					userObservableList.add((UserData)objectInputStream.readObject());
				}
				catch(EOFException e)
				{
					objectInputStream.close();
				}
			}	
						
		}
		catch (Exception ex) 
		{
			//ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	
	{

		
	}


}
