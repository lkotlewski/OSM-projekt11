package application;

import javafx.collections.ObservableList;

public class Controller 
{

	/**
	 * Nowy obiekt klasy Model
	 */
	Model model = new Model();
	UserApplication userApplication;
	
	
	public Controller(UserApplication userApplication) {
		super();
		this.userApplication = userApplication;
	}

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
	
	public void menuItemFunction()
	{
		model.serializeData();
	}
	
	/**
	 * Funkcja do obslugi przycisku "Zapisz" w panelu danych pacjenta
	 * @param femaleCheck
	 * @param maleCheck
	 * @param insuranceType
	 * @param name
	 * @param surname
	 * @param pesel
	 */
	public void saveButtonFunctions()
	{
	   FormUserData formUserData = userApplication.getPatient();
	   int selectedIndex =  userApplication.getSelectedIndex();
	   boolean response = true;
		
	   boolean isButtonAddPressed = userApplication.isButtonAddPressed();
	   if(isButtonAddPressed){
		   response = model.saveUser(formUserData.getName(), formUserData.getSurname(), 
				   formUserData.getId(), formUserData.getSex(), formUserData.getInsurance(), -1);	
		   userApplication.setButtonAddPressed(false);
	   }
	   else if(!userApplication.isButtonAddPressed() && selectedIndex != -1){
		   response = model.saveUser(formUserData.getName(), formUserData.getSurname(), 
				   formUserData.getId(), formUserData.getSex(), formUserData.getInsurance(), selectedIndex);
		   userApplication.setButtonAddPressed(false);
	   }
		  
	   if(!response){
		   userApplication.PatientSaveError();
		   
	   }
	   else{
		   userApplication.clearFormGrid();
		   userApplication.DisableForms(true);
		   userApplication.PatientSaved();
	   }
	    	
	}
	/**
	 * funkcja zarzadzajaca wydarzeniami zwiazanymi z tablic¹
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
	
	public void deleteButtonFunction()
	{
		int selectedIndex = userApplication.getSelectedIndex();
		model.deletePatient(selectedIndex);
		userApplication.clearFormGrid();
		userApplication.clearExamGrid();
		userApplication.DisableForms(true);

	}
	
	public void primaryStageClose()
	{
		model.serializeData();
	}
		

	/**
	 * Funkcja zwracajaca listê z danymi pacjentow
	 * @return
	 */
	ObservableList<UserData> getPatientData()
	{
		model.deserializeData();
		return Model.userObservableList;
	}
	

	public static void main(String[] args) 
	{
		
	}

}