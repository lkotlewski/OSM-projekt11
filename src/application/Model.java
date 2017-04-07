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
	 * Statyczny obiekt typu ObservableList<UserData>
	 */
	public static ObservableList<UserData> userObservableList =  FXCollections.observableArrayList();
	
	
	/**
	 * Statyczny obiekt typu  Map<String,ExamData>
	 */
    public static Map<String,ExamData> patientExamMap = new HashMap<String,ExamData>();
    
   
    /**
     * Nazwa pliku do ktorego sa zapisywane dane uzytkownika
     */
    String nameOfUserDataFile = "Data.ser";
    
    
    /**
     * Nazwa pliku do ktorego sa zapisywane dane obadaniach uzytkownika
     */
    String nameOfExamDataFile = "ExamResults.ser";
   
    
   /**
    * Funkcja zapisujaca dane pacjenta, lub aktualizujaca juz istniejacego, gdy row = -1. W przypadku zmiany PESEL-u nastepuje rowniez aktualizacja w patientExamMap
    * @param name
    * @param surname
    * @param id
    * @param sex
    * @param insurance
    * @param row
    */
	public void savePatientData(String name, String surname, String id, String sex, String insurance, int row)
	{
		if(row == -1)
		{
			UserData userData = new UserData(name, surname, id, sex, insurance, false);
			userObservableList.add(userData);
			
		}
		else
		{
			if(userObservableList.get(row).getExamination() && !userObservableList.get(row).getId().equals(id))
			{
				ExamData examData = patientExamMap.get(userObservableList.get(row).getId());
				examData.setId(id);
				patientExamMap.remove(userObservableList.get(row).getId());
				patientExamMap.put(id, examData);	
			}
			
			userObservableList.get(row).setName(name);
			userObservableList.get(row).setSurname(surname);
			userObservableList.get(row).setFullName(name + " " + surname);
			userObservableList.get(row).setId(id);
			userObservableList.get(row).setSex(sex);
			userObservableList.get(row).setInsurance(insurance);
			
		}
		
	}
	
	
	/**
	 * Funkcja zapisujaca dane badan uzytkownika
	 * @param bilirubinLevel
	 * @param antiBodiesHCV
	 * @param antigenHBS
	 * @param examDate
	 * @param id
	 */
	public void saveExamData(float bilirubinLevel, boolean antiBodiesHCV, boolean antigenHBS, 
			LocalDate examDate, String id)
	{
		ExamData examData = new ExamData(bilirubinLevel,antiBodiesHCV,antigenHBS, examDate, id);
		patientExamMap.put(id, examData); // jesli istnialo pole o takim kluczu zostanie nadpisane
	}
	
	
	/**
	 * Funkcja usuwajaca uzytkownika i jesli istnieje to stowarzyszone z nim badania
	 * @param index
	 */
	public void deletePatient(int index){
		
		if(userObservableList.get(index).getExamination()){
			patientExamMap.remove(userObservableList.get(index).getId());
		}
		userObservableList.remove(index);
			
	}
	
	/**
	 * Funkcja serializujaca dane w plikach zewnetrznych
	 */
	public void serializeData(){
		try 
		{
			ObjectOutputStream dataOutputStream = new ObjectOutputStream(new FileOutputStream(nameOfUserDataFile, false));	
			ObjectOutputStream examResultsOutputStream = new ObjectOutputStream(new FileOutputStream(nameOfExamDataFile, false));
			for(int i = 0; i < userObservableList.size(); i++)
			{
				UserData userData = userObservableList.get(i);
				dataOutputStream.writeObject(userData);
				if(userData.getExamination()){
					
					ExamData examData = patientExamMap.get(userData.getId());
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
	 * Funkcja przeprowadza desrializacje danych z plikow zewnetrznych
	 */
	public void deserializeData()
	{

		try 
		{
			ObjectInputStream dataInputStream = new ObjectInputStream(new FileInputStream(nameOfUserDataFile));	
			ObjectInputStream examInputStream = new ObjectInputStream(new FileInputStream(nameOfExamDataFile));
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

	
	public static void main(String[] args) 
	
	{

		
	}


}