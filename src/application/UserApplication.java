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
	/**
	 * Obiekt klasy Controller
	 */
	Controller controller = new Controller(this);
	
	/**
	 * Obiekt typu Menu
	 */
    Menu menu = new Menu("Aplikacja");
    
    /**
     * Obiekt typu MenuBar
     */
    MenuBar menuBar = new MenuBar();
    
    /**
     * Obiekt typu MenuItem
     */
	MenuItem menuItem = new MenuItem("Wyjœcie");
	
	/**
	 * Obiekt typu TableView przechowujacy informacje o uzytkownikach
	 */
    TableView<UserData> table  = new TableView<UserData>();
    
    /**
     * Obiekt typu BorderPane, glowny typ rozlozenia grafiki 
     */
    BorderPane mainLayout = new BorderPane();
    
    /**
     * Obiekt typu BorderPane, glowny typ rozlozenia grafiki po prawej stronie okienka (tablica z danymi)
     */
    BorderPane rightLayout = new BorderPane();
    
    /**
     * Obiekt typu BorderPane, glowny typ rozlozenia grafiki po lewej stronie okienka (opakowanie na formularze do wprowadzania danych)
     */
    BorderPane leftLayout = new BorderPane();
    
    /**
     * Obiekt typu GridPane, odpowiedzialny za formularz z danymi pacjenta
     */
    GridPane formGrid = new GridPane();
    
    /**
     * Obiekt typu GridPane, odpowiedzialny za formularz z danymi o badaniach
     */
    GridPane examGrid = new GridPane();
    
    /**
     * Obiekt typu FlowPane,  prawa czesc okna, wzgledem, ktorej wyznaczane sa marginesy
     */
    FlowPane rightBox = new FlowPane(); 
    
    /**
     * Obiekt typu scrollPane, sluzacy do obslugi tablicy
     */
    ScrollPane scrollCont = new ScrollPane();
    
    /**
     * Obiekt typu Text, wyswietlajacy tytulowy prawego panelu
     */
    Text patientDataText = new Text("Dane Pacjentów");
    
    /**
     * Obiekt typu Button, sluzacy do aktywacji dodawania nowego uzytkownika
     */
	Button addButton = new Button("Dodaj");
	
	/**
	 * Obiekt typu Button, sluzacy do usuwania pacjenta
	 */
    Button deleteButton = new Button("Usuñ");
    
    /**
     * Obiekt typu Text, wyswietlajacy napis o danych pacjenta
     */
    Text formTitleText = new Text("Dane Pacjenta");
    
    /**
     * Obiekt typu Label, wyswietlajacy napis o imieniu
     */
    Label nameLabel = new Label("Imiê:");
    
    /**
     * Obiekt typu TextField, sluzacy do pobierania imienia
     */
    TextField nameTextField = new TextField();
    
    /**
     * Obiekt typu Label, wyswietlajacy napis o nazwisku
     */
    Label surnameLabel = new Label("Nazwisko:");
    
    /**
     * Obiekt typu TextField, sluzacy do pobierania nazwiska
     */
    TextField surnameTextField = new TextField();
    
    /**
     * Obiekt typu Label, wyswietlajacy napis o imieniu
     */
    Label peselLabel = new Label("PESEL:");
    
    /**
     * Obiekt typu TextField, sluzacy do pobierania PESEL-u
     */
    TextField peselTextField = new TextField();
    
    /**
     * Obiekt typu Label, wyswietlajacy napis o Plci
     */
    Label sexLabel = new Label("P³eæ:");
    
    /**
     * Obiekt typu RadioButton, sluzacy do wyboru Kobiety
     */
    RadioButton femaleCheck = new RadioButton("Kobieta");
    
    /**
     * Obiekt typu RadioButton, sluzacy do wyboru Mezszczyzny 
     */
    RadioButton maleCheck = new RadioButton("Mê¿czyzna");
    
    /**
     * Obiekt typu ToggleGroup, sluzacy do grupowania przyciskow femaleCheck i maleCheck
     */
    ToggleGroup sexGroup = new ToggleGroup();
    
    /**
     * Obiekt typu Label, wyswietlajacy napis o Ubezpieczeniu 
     */
    Label insuranceLabel = new Label("Ubezpieczenie");
    
    /**
     * Obiekt typu ComboBox, bedacy opakowaniem na typy ubezpieczen
     */
    ComboBox<String> insuranceType = new ComboBox<String>();
    
    /**
     * Obiekt typu Button, sluzacy do zapisywania informacji o uzytkowniku
     */
    Button saveButton = new Button("Zapisz");
    
    /**
     * Obiekt typu Button, sluzacy do czyszczenia wypelnionych pol z danymi uzytkownika w formatce
     */
    Button clearButton = new Button("Anuluj");
    
    /**
     * Obiekt typu Label, wyswietlajacy informacje zwrotna
     */
    Label messageFormLabel = new Label("");
    
    /**
     * Obiekt typu Text, wyswietlajacy tytul panelu z wynikami badan
     */
    Text examText = new Text("Badanie");
    
    /**
     * Obiekty typu Label, wyswietlajacy napis o dacie
     */
    Label dateLabel = new Label("Data");
    
    /**
     * Obiekt typu DatePicker, sluzacy jako kalendarz
     */
    DatePicker calendar =  new DatePicker();
    
    /**
     * Obiekty typu Label, wyswietlajacy napis o antygenie HBS
     */
    Label antigenHBSLabel = new Label("Antygen HBS");
    
    /**
     * Obiekt typu CheckBox, sluzacy do ustawiania informacji o antygenie HBS
     */
    CheckBox checkHBS = new CheckBox();
    
    /**
     * Obiekt typu Label, wyswietlajacy napis o przeciwcialach HCV
     */
    Label antiBodiesHCV = new Label("Przeciwcia³a HCV");
    
    /**
     * Obiekt typu ChceckBox, sluzacy do ustawiania informacji o przciwcialach HCV
     */
    CheckBox checkHCV = new CheckBox();
    
    /**
     * Obiekt typu Label, wyswietlajacy napis o poziomie bilirubiny
     */
    Label bilirubinLabel = new Label("Poziom Bilirubiny");
    
    /**
     * Obiekt typu TextField, sluzacy do pobierania informacji o poziomie bilirubiny
     */
    TextField bilirubinTextField = new TextField();
    
    /**
     * Obiekt typu Label, wyswietlajacy napis o stezeniu bilirubiny
     */
    Label unitLabel = new Label("mg/dl");
    
    /**
     * Obiekt typu Button, sluzacy do zapisywania wynikow badan
     */
    Button saveButtonExam = new Button("Zapisz");
    
    /**
     * Obiekt typu Button, sluzacy do czyszczenia wynikow badan w formatce
     */
    Button clearButtonExam = new Button("Anuluj");
    
    /**
     * Obiekt typu Label, sluzacy do wyswietlania informacji zwrotnej
     */
    Label messageExamLabel = new Label("");
    
    /**
     * Zmienna typu boolean, sluzaca do informowania czy przycisk Dodaj zostal wcisniety 
     */
    boolean isButtonAddPressed = false;
    
	/**
	 * Przeciazona metoda start, sluzaca do wyswietlania okienka
	 */
	public void start(Stage primaryStage)
	{
		try 
		{
			//Gora
		    menu.getItems().add(menuItem);
			menuBar.getMenus().add(menu);
			mainLayout.setTop(menuBar);
		    
			//Prawa strona
			rightLayout.setPadding(new Insets(20, 20, 20 ,20)); 
			mainLayout.setRight(rightLayout);
			
			rightBox.setPadding(new Insets(20, 20, 20 ,20));
			rightBox.setBorder(new Border(new BorderStroke(Color.INDIGO, BorderStrokeStyle.SOLID,
					CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			rightBox.setVgap(20);
			rightBox.setHgap(20);
			rightLayout.setCenter(rightBox);
			
			rightBox.getChildren().add(patientDataText);
			
			createTable(table);
			scrollCont.setContent(table);
			rightBox.getChildren().add(scrollCont);
	        rightBox.getChildren().add(addButton);
	        rightBox.getChildren().add(deleteButton);
			
	        //Lewa strona
			leftLayout.setMinSize(300,400);
			leftLayout.setPadding(new Insets(20, 20, 20 ,20));
			mainLayout.setLeft(leftLayout);
		
			formGrid.setBorder(new Border(new BorderStroke(Color.INDIGO, BorderStrokeStyle.SOLID,
					CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			formGrid.setMinSize(260, 240);
			formGrid.setPadding(new Insets(20, 20, 20 ,20));
	        formGrid.setAlignment(Pos.TOP_CENTER);
	        formGrid.setHgap(20);
	        formGrid.setVgap(10);
	        
	        formGrid.add(formTitleText, 0, 0, 2, 1);
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
			
			
			examGrid.setBorder(new Border(new BorderStroke(Color.INDIGO, BorderStrokeStyle.SOLID,
					CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			examGrid.setMinSize(260, 240);
			examGrid.setPadding(new Insets(20, 20, 20 ,20));
	        examGrid.setAlignment(Pos.TOP_CENTER);
	        examGrid.setHgap(20);
	        examGrid.setVgap(10);
	        
	        examGrid.add(examText, 0, 0, 2, 1);
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
	        
	        //ustawienia odnoszace sie do calego okna programu
	        Scene scene = new Scene(mainLayout, 1200, 640);
			primaryStage.setTitle("Rejestracja wyników badañ");
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			/**
			 * obs³uga CSS
			 */
			table.setId("table-view");
			examGrid.getStyleClass().add("grid-pane");
			formGrid.getStyleClass().add("grid-pane");
			rightBox.getStyleClass().add("grid-pane");
			examText.setId("examination-title");
			patientDataText.setId("table-title");
			formTitleText.setId("form-title");
			messageExamLabel.setId("exam-message");
			messageFormLabel.setId("form-message");
			scene.getStylesheets().add (UserApplication.class.getResource("viewStyle.css").toExternalForm());
			/**
	         * Obsluga zdarzen z calego programu
	         */
			menuItem.setOnAction(e->
			{
				controller.primaryStageClose();
				primaryStage.close();
			});
			
			saveButton.setOnMouseClicked(e-> 
			{
				controller.saveButtonFunction();
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
		
		TableColumn<UserData, String> fullNameColumn = new TableColumn<>("Imie i nazwisko");
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
        table.setPrefHeight(390);
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
	
	/**
	 * Funkcja zwracajaca wartosc zmiennej isButtonAddPressed
	 * @return
	 */
	public boolean isButtonAddPressed() {
		return isButtonAddPressed;
	}

	/**
	 * Funkcja ustawiajaca wartosc zmiennej isButtonAddPressed
	 * @param isButtonAddPressed
	 */
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
	 *Funkcja pobierajaca wprowadzone dane pacjenta 
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
	 * Funkcja pobierajaca wprowadzone wyniki badan
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
	 * Funkcja powiadamiajaca o bledzie przy zapisie danych pacjenta
	 */
	public void PatientSaveError(){
		messageFormLabel.setText("B³¹d !");
		messageFormLabel.setTextFill(Color.RED);
	}
	
	/**
	 * Funkcja powiadamiajaca o pomyslnym zapisaniu pacjenta,
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
	 * Funkcja powiadamiajaca o bledzie przy zapisie wynikow badan
	 */
	public void ExamSaveError(){
		messageExamLabel.setText("B³¹d !");
		messageExamLabel.setTextFill(Color.RED);
	}
	
	/**
	 * Funkcja powiadamiajaca o pomyslnym zapisaniu wynikow badan,
	 *  odswieza rowniez widok w tablicy 
	 */
	public void ExamSaved(){  
	    formGrid.setDisable(true);
		messageExamLabel.setText("Zapisano ");
		messageExamLabel.setTextFill(Color.GREEN);
		table.refresh(); 
	}
	
	/**
	 * Funkcja deaktywujaca formatki
	 * @param value
	 */
	public void DisableForms(boolean value){
		examGrid.setDisable(value);
		formGrid.setDisable(value);
	}
	
	/**
	 * Funkcja main
	 * @param args
	 */
	public static void main(String[] args)
	{
		launch(args);
	}

}