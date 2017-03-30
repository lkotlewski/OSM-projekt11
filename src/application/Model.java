package application;


import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model 
{
	
	/**
	 * Nowy, statyczny obiekt typu ObservableList<UserData>
	 */
	public static ObservableList<UserData> userObservableList =  FXCollections.observableArrayList();
    public static Map<String,ExamData> patientExamMap = new HashMap<String,ExamData>();
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
	public void savePatientData(String name, String surname, String id, String sex, String insurance)
	{
		UserData userData = new UserData(name, surname, id, sex, insurance, false);
		userObservableList.add(userData);
	}
	
	public void saveExamData(float bilirubinLevel, boolean antiBodiesHCV, boolean antigenHBS, 
			LocalDate examDate, String id)
	{
		ExamData examData = new ExamData(bilirubinLevel,antiBodiesHCV,antigenHBS, examDate, id);
		patientExamMap.put(id, examData); // jesli istnialo pole o takim kluczu zostanie nadpisane
		System.out.println(patientExamMap.get(id));
	}
	public void serializeData(){
		try 
		{
			ObjectOutputStream dataOutputStream = new ObjectOutputStream(new FileOutputStream("Data.ser"));	
			ObjectOutputStream examResultsOutputStream = new ObjectOutputStream(new FileOutputStream("ExamResults.ser"));
			for(int i = 0; i < userObservableList.size(); i++)
			{
				UserData userData = userObservableList.get(i);
				dataOutputStream.writeObject(userData);
				System.out.println(userData.isExamination());
				if(userData.isExamination()){
					System.out.println("jestem w srodku" + userData.getId());
					ExamData examData = patientExamMap.get(userData.getId());
					System.out.println("bilirubina : " + examData.getBilirubinLevel());
					examResultsOutputStream.writeObject(examData);
				}
				
			}
			dataOutputStream.close();	
			examResultsOutputStream.close();			
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
			ObjectInputStream dataInputStream = new ObjectInputStream(new FileInputStream("Data.ser"));	
			ObjectInputStream examInputStream = new ObjectInputStream(new FileInputStream("ExamResults.ser"));
			while(true)
			{
				try
				{
					userObservableList.add((UserData)dataInputStream.readObject());
					
				}
				catch(EOFException e)
				{
					dataInputStream.close();
				}
				
				try
				{
					ExamData examData = (ExamData)examInputStream.readObject();
					patientExamMap.put(examData.getId(), examData);
					System.out.println(examData.getExamDate());
					
				}
				catch(EOFException e)
				{
					examInputStream.close();
				}
			}	
			
			
						
		}
		catch (Exception ex) 
		{
			//ex.printStackTrace();
		}
	}
	
	public void saveExamResults(float bilirubinLevel, boolean antiBodiesHCV, boolean antigenHBS, boolean firstResults){
		
	}
	
	
	public static void main(String[] args) 
	
	{

		
	}


}
