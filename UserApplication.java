package application;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class UserApplication extends Application
{

	Controller controller = new Controller(this);
	Model model = new Model();
	
    Menu menu = new Menu("Aplikacja");
    MenuBar menuBar = new MenuBar();
	MenuItem menuItem = new MenuItem("Wyjœcie");
    TableView<UserData> table  = new TableView<UserData>();
    BorderPane mainLayout = new BorderPane();
    BorderPane rightLayout = new BorderPane(); // opakowanie na tablice z danymi
    BorderPane leftLayout = new BorderPane(); // Opakowanie na dwa formularze do wprowadzania danych
    GridPane formGrid = new GridPane();    // Czesc odpowiedzialna za formularz z danymi
    GridPane examGrid = new GridPane();
    FlowPane rightBox = new FlowPane(); // prawa czesc okna, wzgledem, ktorej wyznaczane sa marginesy
    ScrollPane scrollCont = new ScrollPane();
    Label patientDataLabel = new Label("Dane Pacjentów");
	Button addButton = new Button("Dodaj");
    Button deleteButton = new Button("Usuñ");
    Text formTitle = new Text("Dane Pacjenta");
    Label nameLabel = new Label("Imiê:");
    TextField nameTextField = new TextField();
    Label surnameLabel = new Label("Nazwisko:");
    TextField surnameTextField = new TextField();
    Label peselLabel = new Label("PESEL:");
    TextField peselTextField = new TextField();
    Label sexLabel = new Label("P³eæ:");
    RadioButton femaleCheck = new RadioButton("Kobieta");
    RadioButton maleCheck = new RadioButton("Mê¿czyzna");
    ToggleGroup sexGroup = new ToggleGroup();
    Label insuranceLabel = new Label("Ubezpieczenie");
    ComboBox<String> insuranceType = new ComboBox<String>();
    Button saveButton = new Button("Zapisz");
    Button clearButton = new Button("Anuluj");
    Label messageFormLabel = new Label("");
    Label examLabel = new Label("Badanie");
    Label dateLabel = new Label("Data");
    DatePicker calendar =  new DatePicker();
    Label antigenHBSLabel = new Label("Antygen HBS");
    CheckBox checkHBS = new CheckBox();
    Label antiBodiesHCV = new Label("Przeciwcia³a HCV");
    CheckBox checkHCV = new CheckBox();
    Label bilirubinLabel = new Label("Poziom Bilirubiny");
    TextField bilirubinTextField = new TextField();
    Label unitLabel = new Label("mg/dl");
    Button saveButtonExam = new Button("Zapisz");
    Button clearButtonExam = new Button("Anuluj");
    Label messageExamLabel = new Label("");
    boolean isButtonAddPressed = false;
    
	@Override
	/*
	 * (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	public void start(Stage primaryStage)
	{
		try 
		{
			/**
			 * Gora
			 */
		    menu.getItems().add(menuItem);
			menuBar.getMenus().add(menu);
			mainLayout.setTop(menuBar);
		    
			/**
			 * Prawa strona
			 */
			rightLayout.setPadding(new Insets(20, 20, 20 ,20)); 
			mainLayout.setRight(rightLayout);
			
			rightBox.setPadding(new Insets(20, 20, 20 ,20));
			rightBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
					CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			rightBox.setVgap(20);
			rightBox.setHgap(20);
			rightLayout.setCenter(rightBox);
			
			rightBox.getChildren().add(patientDataLabel);
			
			createTable(table);
			scrollCont.setContent(table);
			rightBox.getChildren().add(scrollCont);
	        rightBox.getChildren().add(addButton);
	        rightBox.getChildren().add(deleteButton);
			
	        /**
	         * Lewa strona
	         */
			leftLayout.setMinSize(300,400);
			leftLayout.setPadding(new Insets(20, 20, 20 ,20));
			mainLayout.setLeft(leftLayout);
		
			formGrid.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
					CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			formGrid.setMinSize(260, 240);
			formGrid.setPadding(new Insets(20, 20, 20 ,20));
	        formGrid.setAlignment(Pos.TOP_CENTER);
	        formGrid.setHgap(20);
	        formGrid.setVgap(10);
	        
	        formGrid.add(formTitle, 0, 0, 2, 1);
	        formGrid.add(nameLabel, 0, 1, 2, 1);
	        formGrid.add(nameTextField, 2, 1, 2, 1);
	        formGrid.add(surnameLabel, 0, 2, 2, 1);
	        formGrid.add(surnameTextField, 2, 2, 2, 1);
	        formGrid.add(peselLabel, 0, 3, 2, 1);
	        formGrid.add(peselTextField, 2, 3, 2, 1);
	        formGrid.add(sexLabel, 0, 4, 2, 1);
	        formGrid.add(femaleCheck, 2, 4);
	        formGrid.add(maleCheck, 3, 4);
	        formGrid.add(insuranceLabel, 0, 5, 2, 1);
	        formGrid.add(insuranceType, 3, 5, 2, 1);
	        formGrid.add(saveButton, 0, 6);
	        formGrid.add(clearButton, 1, 6);
	        formGrid.add(messageFormLabel, 0, 7);
	        formGrid.setDisable(true);
	        
	        femaleCheck.setToggleGroup(sexGroup);
	        maleCheck.setToggleGroup(sexGroup);
	        
	        insuranceType.getItems().addAll("NFZ","Prywatne","Brak");
	        insuranceType.getSelectionModel().selectFirst();

			leftLayout.setTop(formGrid);
			
			
			examGrid.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
					CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			examGrid.setMinSize(260, 240);
			examGrid.setPadding(new Insets(20, 20, 20 ,20));
	        examGrid.setAlignment(Pos.TOP_CENTER);
	        examGrid.setHgap(20);
	        examGrid.setVgap(10);
	        
	        examGrid.add(examLabel, 0, 0, 2, 1);
	        examGrid.add(dateLabel, 0, 1, 2, 1);
	        examGrid.add(calendar, 2, 1, 3, 1);
	        calendar.setEditable(false);
	        examGrid.add(antigenHBSLabel, 0, 2, 2, 1);
	        examGrid.add(checkHBS, 2, 2);
	        examGrid.add(antiBodiesHCV, 0, 3, 2, 1);
	        examGrid.add(checkHCV, 2, 3);
	        examGrid.add(bilirubinLabel, 0, 4, 2, 1);
	        bilirubinTextField.setPrefWidth(60);
	        examGrid.add(bilirubinTextField, 2, 4);
	        examGrid.add(unitLabel, 3, 4);
	        examGrid.add(saveButtonExam, 0, 5);
	        examGrid.add(clearButtonExam, 1, 5);
	        examGrid.add(messageExamLabel, 0, 6);
	        examGrid.setDisable(true);
	        leftLayout.setBottom(examGrid);
	        
	        /**
	         * ustawienia odnoszace sie do calego okna programu
	         */
	        Scene scene = new Scene(mainLayout, 1300, 700);
			primaryStage.setTitle("Rejestracja wyników badañ");
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			
	        /**
	         * Obsluga zdarzen z calego programu
	         */
			menuItem.setOnAction(e->
			{
				controller.menuItemFunction();
				primaryStage.close();
			});
			
			saveButton.setOnMouseClicked(e-> 
			{
				controller.saveButtonFunctions();
			});
			
			clearButton.setOnMouseClicked(e->
			{
				clearFormGrid();
				clearExamGrid();
				examGrid.setDisable(true);
				table.getSelectionModel().select(null);
			});
			
			table.setOnMouseClicked(e->
			{
				controller.actionTable();
			});
			
			addButton.setOnMouseClicked(e->
			{
				clearFormGrid();
				clearExamGrid();
				examGrid.setDisable(true);
				formGrid.setDisable(false);
			    isButtonAddPressed = true;
				table.getSelectionModel().select(null);
			});
			
			clearButtonExam.setOnMouseClicked(e->
			{
				clearExamGrid();
			});
			
			saveButtonExam.setOnMouseClicked(e->
			{
				controller.saveButtonExamFunction();
			});
			
			
			deleteButton.setOnMouseClicked(e->
			{
				controller.deleteButtonFunction();
			});
			
			primaryStage.setOnCloseRequest(e-> controller.primaryStageClose());
		}
		
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
     
	
	@SuppressWarnings("unchecked")
	/**
	 * Funkcja tworzaca tabele
	 */
	public void createTable(TableView<UserData> table)
	{
		@SuppressWarnings("rawtypes")
		TableColumn examinationColumn = new TableColumn("Badanie");
		
		examinationColumn.setCellValueFactory(new Callback<CellDataFeatures<UserData, CheckBox>, ObservableValue<CheckBox>>() 
		{
			
             @Override
             public ObservableValue<CheckBox> call(CellDataFeatures<UserData, CheckBox> arg0) 
             {
            	          		
                 CheckBox checkBox = new CheckBox();
                 checkBox.setDisable(true);
                 
                 UserData userData = arg0.getValue();
       
                     if(userData.getExamination())
                     {
                         checkBox.selectedProperty().setValue(Boolean.TRUE);
                     }

                 return new SimpleObjectProperty<CheckBox>(checkBox);
        		}        
         });
		
		ObservableList<UserData> userObservableList = controller.getPatientData();
		
		TableColumn<UserData, String> fullNameColumn = new TableColumn<>("Imiê i nazwisko");
		fullNameColumn.setMinWidth(200);
		fullNameColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("fullName"));
		
		TableColumn<UserData, String> sexColumn = new TableColumn<>("P³eæ");
		sexColumn.setMinWidth(50);
		sexColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("sex"));
		
		TableColumn<UserData, String> idColumn = new TableColumn<>("PESEL");
		idColumn.setMinWidth(200);
		idColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("id"));
		
		TableColumn<UserData, String> insuranceColumn = new TableColumn<>("Ubezpieczenie");
		insuranceColumn.setMinWidth(150);
		insuranceColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("insurance"));
		
        table.setItems(userObservableList);
        table.getColumns().addAll(fullNameColumn, sexColumn, idColumn, insuranceColumn, examinationColumn );
        table.setPrefHeight(200);
        table.setTableMenuButtonVisible(true);
         
    }
	
	/**
	 * Funkcja ustawiajaca widok uzytkownika
	 * * @param userData
	 */
	public void setPatientView(UserData userData)
	{
		nameTextField.setText(userData.getName());
		surnameTextField.setText(userData.getSurname());
		peselTextField.setText(userData.getId());
		femaleCheck.setSelected(userData.getSex().equals("K"));
        maleCheck.setSelected(userData.getSex().equals("M"));
		
		if(userData.getInsurance().equals("NFZ"))
			insuranceType.getSelectionModel().selectFirst();
		else if(userData.getInsurance().equals("Prywatne"))
			insuranceType.getSelectionModel().select(1);
		else if(userData.getInsurance().equals("Brak"))
			insuranceType.getSelectionModel().select(2);
		messageFormLabel.setText("");
	}
	
	public boolean isButtonAddPressed() {
		return isButtonAddPressed;
	}


	public void setButtonAddPressed(boolean isButtonAddPressed) {
		this.isButtonAddPressed = isButtonAddPressed;
	}


	/**
	 * Funkcja ustawiajaca widok badan pacjenta
	 * @param examData
	 */
	public void setExamView(ExamData examData)
	{
		
		calendar.setValue(examData.getExamDate());
		checkHBS.setSelected(examData.isAntigenHBS());
		checkHCV.setSelected(examData.isAntiBodiesHCV());
		bilirubinTextField.setText(String.valueOf(examData.getBilirubinLevel()));
		messageExamLabel.setText("");
	}
	
	/**
	 * Funkcja czyszczaca formularz danych uzytkownika
	 */
	public void clearFormGrid()
	{
		nameTextField.clear();
		surnameTextField.clear();
		peselTextField.clear();
		femaleCheck.setSelected(false);
		maleCheck.setSelected(false);
		insuranceType.getSelectionModel().selectFirst();
		messageFormLabel.setText("");
		
	}
	
	/**
	 * Funkcja czyszczaca formularz badan uzytkownika
	 */
	public void clearExamGrid()
	{
		calendar.setValue(null);
		bilirubinTextField.clear();
		checkHBS.setSelected(false);
		checkHCV.setSelected(false);
		messageExamLabel.setText("");
	}
	
	/**
	 *Funkcja pobieraj¹ca wprowadzone dane pacjenta 
	 */
	public FormUserData getPatient(){
		String name = nameTextField.getText();
		String surname = surnameTextField.getText();
		String id = peselTextField.getText();
		String sex = "";
		String insurance = insuranceType.getValue();
		if(femaleCheck.isSelected()){
			sex = "K";
		}
		else if(maleCheck.isSelected()){
			sex = "M";
		}
		else{
			sex = null;
		}   
		
		return new FormUserData(name, surname, id, sex, insurance);
		
	}
	
	/**
	 * Funkcja pobierajaca wprowadzone wyniki badañ
	 */
	public FormExamData getExam(){
		String bilirubinLevel = bilirubinTextField.getText();
		boolean antiBodiesHCV = checkHCV.isSelected();
		boolean antigenHBS = checkHBS.isSelected();
		LocalDate examDate = calendar.getValue();
		return new FormExamData(bilirubinLevel, antiBodiesHCV, antigenHBS, examDate);
	}
	
	/**
	 * Funkcja zwracajaca wybrany indeks z tabeli
	 * @return
	 */
	public int getSelectedIndex(){
		return table.getSelectionModel().getSelectedIndex();
	}
	
	/**
	 * 
	 * Funkcja powiadamiajaca o b³êdzie przy zapisie danych pacjenta
	 */
	public void PatientSaveError(){
		messageFormLabel.setText("B³¹d !");
		messageFormLabel.setTextFill(Color.RED);
	}
	
	/**
	 * Funkcja powiadamiajaca o pomyœlnym zapisaniu pacjenta,
	 *  odswieza rowniez widok w tablicy 
	 */
	public void PatientSaved(){  
	    formGrid.setDisable(true);
		messageFormLabel.setText("Zapisano ");
		messageFormLabel.setTextFill(Color.GREEN);
		table.refresh(); 
	}
	
	/**
	 * 
	 * Funkcja powiadamiajaca o b³êdzie przy zapisie wynikow badan
	 */
	public void ExamSaveError(){
		messageExamLabel.setText("B³¹d !");
		messageExamLabel.setTextFill(Color.RED);
	}
	
	/**
	 * Funkcja powiadamiajaca o pomyœlnym zapisaniu wynikow badan,
	 *  odswieza rowniez widok w tablicy 
	 */
	public void ExamSaved(){  
	    formGrid.setDisable(true);
		messageExamLabel.setText("Zapisano ");
		messageExamLabel.setTextFill(Color.GREEN);
		table.refresh(); 
	}
	
	public void DisableForms(boolean value){
		examGrid.setDisable(value);
		formGrid.setDisable(value);
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}

}