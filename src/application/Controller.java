package application;

import javafx.collections.ObservableList;

public class Controller 
{

	/**
	 * Obiekt klasy Model
	 */
	Model model = new Model();
	
	/**
	 * Pole typu UserApplication
	 */
	UserApplication userApplication;
	
	/**
	 * Konstruktor klasy Controller
	 * @param userApplication
	 */
	public Controller(UserApplication userApplication) {
		super();
		this.userApplication = userApplication;
	}
	
	/**
	 * Funkcja obslugujaca zapisywanie danych o badaniach
	 */
	public void saveButtonExamFunction()
	{
		FormExamData formExamData = userApplication.getExam();
		int selectedIndex = userApplication.getSelectedIndex();
		boolean response = Model.saveExamResults(formExamData.getBilirubinLevel(),  formExamData.isAntiBodiesHCV(),
				formExamData.isAntigenHBS(), formExamData.getExamDate(), selectedIndex);
				
				if(!response)
				{
					userApplication.ExamSaveError();	  
				}
				else
				{
					userApplication.clearExamGrid();
					userApplication.DisableForms(true);
					userApplication.ExamSaved();					
			    }
	}
	
	/**
	 * Funkcja obslugujaca zapisywanie danych uzytkownika
	 */
	public void saveButtonFunction()
	{
	   FormUserData formUserData = userApplication.getPatient();
	   int selectedIndex =  userApplication.getSelectedIndex();
	   boolean response = true;
		
	   boolean isButtonAddPressed = userApplication.isButtonAddPressed();
	   
	   if(isButtonAddPressed){
		   response = model.saveUser(formUserData.getName(), formUserData.getSurname(), 
				   formUserData.getId(), formUserData.getSex(), formUserData.getInsurance(), -1);	
	   }
	   else if(!userApplication.isButtonAddPressed() && selectedIndex != -1){
		   response = model.saveUser(formUserData.getName(), formUserData.getSurname(), 
				   formUserData.getId(), formUserData.getSex(), formUserData.getInsurance(), selectedIndex);
	   }
	   
	   if(!response){
		   userApplication.PatientSaveError();
		   
	   }
	   else{
		   userApplication.setButtonAddPressed(false);
		   userApplication.clearFormGrid();
		   userApplication.DisableForms(true);
		   userApplication.PatientSaved();
	   }
	    	
	}
	
	/**
	 * Funkcja zarzadzajaca wydarzeniami zwiazanymi z tablica
	 */
	public void actionTable()
	{
		if (userApplication.getSelectedIndex() == -1)
				return;
		userApplication.DisableForms(false);
		int selectedRow = userApplication.getSelectedIndex();
		UserData userData = Model.userObservableList.get(selectedRow);
		userApplication.setPatientView(userData);
		if(userData.getExamination())
		{
			ExamData examData = Model.patientExamMap.get(userData.getId());
			userApplication.setExamView(examData);
		}
		else
		{
			userApplication.clearExamGrid();
		}
	}
	
	/**
	 * Funkcja obslugujaca usuniecie pacjenta
	 */
	public void deleteButtonFunction()
	{
		int selectedIndex = userApplication.getSelectedIndex();
		model.deletePatient(selectedIndex);
		userApplication.clearFormGrid();
		userApplication.clearExamGrid();
		userApplication.DisableForms(true);

	}
	
	/**
	 * Funkcja obslugujaca wyjscie z programu
	 */
	public void primaryStageClose()
	{
		model.serializeData();
	}

	/**
	 * Funkcja zwracajaca liste z danymi pacjentow
	 * @return
	 */
	ObservableList<UserData> getPatientData()
	{
		model.deserializeData();
		return Model.userObservableList;
	}
	
}