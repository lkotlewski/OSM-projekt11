package application;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
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

public class UserApplication extends Application {

	/**
	 * Przyciks button odpowiedzialny za...
	 */
    Menu menu = new Menu("Aplikacja");
    MenuBar menuBar = new MenuBar();
	MenuItem menuItem = new MenuItem("Wyjœcie");
    TableView<UserData> table  = new TableView<UserData>();
	Model model = new Model();
	Controller controller = new Controller();
    BorderPane mainLayout = new BorderPane();
    BorderPane rightLayout = new BorderPane(); // opakowanie na tablice z danymi
    BorderPane leftLayout = new BorderPane(); // Opakowanie na dwa formularze do wprowadzania danych
    GridPane formGrid = new GridPane();    // Czesc odpowiedzialna za formularz z danymi
    GridPane examGrid = new GridPane();
    FlowPane rightBox = new FlowPane(); // prawa czesc okna, wzgledem, ktorej wyznaczane sa marginesy
    ScrollPane scrollCont = new ScrollPane();
    Label patientData = new Label("Dane Pacjentów");
	Button addButton = new Button("Dodaj");
    Button deleteButton = new Button("Usuñ");
    Text formTitle = new Text("Dane Pacjenta");
    Label name = new Label("Imiê:");
    TextField nameTextField = new TextField();
    Label surname = new Label("Nazwisko:");
    TextField surnameTextField = new TextField();
    Label pesel = new Label("PESEL:");
    TextField peselTextField = new TextField();
    Label sex = new Label("P³eæ:");
    RadioButton femaleCheck = new RadioButton("Kobieta");
    RadioButton maleCheck = new RadioButton("Mê¿czyzna");
    ToggleGroup sexGroup = new ToggleGroup();
    Label insurance = new Label("Ubezpieczenie");
    ComboBox<String> insuranceType = new ComboBox<String>();
    Button saveButton = new Button("Zapisz");
    Button clearButton = new Button("Anuluj");
    Label error = new Label("");
    Label exam = new Label("Badanie");
    Label date = new Label("Data");
    DatePicker calendar =  new DatePicker();
    Label antigenHBS = new Label("Antygen HBS");
    CheckBox checkHBS = new CheckBox();
    Label antiBodiesHCV = new Label("Przeciwcia³a HCV");
    CheckBox checkHCV = new CheckBox();
    Label bilirubin = new Label("Poziom Bilirubiny");
    TextField bilirubinLevel = new TextField();
    Label unit = new Label("mg/dl");
    Button saveButtonExam = new Button("Zapisz");
    Button clearButtonExam = new Button("Anuluj");
    
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
			
			rightBox.getChildren().add(patientData);
			
			createTable();
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
	        formGrid.add(name, 0, 1, 2, 1);
	        formGrid.add(nameTextField, 2, 1, 2, 1);
	        formGrid.add(surname, 0, 2, 2, 1);
	        formGrid.add(surnameTextField, 2, 2, 2, 1);
	        formGrid.add(pesel, 0, 3, 2, 1);
	        formGrid.add(peselTextField, 2, 3, 2, 1);
	        formGrid.add(sex, 0, 4, 2, 1);
	        formGrid.add(femaleCheck, 2, 4);
	        formGrid.add(maleCheck, 3, 4);
	        formGrid.add(insurance, 0, 5, 2, 1);
	        formGrid.add(insuranceType, 3, 5, 2, 1);
	        formGrid.add(saveButton, 0, 6);
	        formGrid.add(clearButton, 1, 6);
	        formGrid.add(error, 1, 7);
	        
	        femaleCheck.setToggleGroup(sexGroup);
	        maleCheck.setToggleGroup(sexGroup);
	        
	        insuranceType.getItems().addAll("NFZ","Prywatne","Brak");
	        insuranceType.getSelectionModel().selectFirst();

			leftLayout.setTop(formGrid);
			
			
			examGrid.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
					CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			examGrid.setMinSize(260, 200);
			examGrid.setPadding(new Insets(20, 20, 20 ,20));
	        examGrid.setAlignment(Pos.TOP_CENTER);
	        examGrid.setHgap(20);
	        examGrid.setVgap(10);
	        
	        examGrid.add(exam, 0, 0, 2, 1);
	        examGrid.add(date, 0, 1, 2, 1);
	        examGrid.add(calendar, 2, 1, 3, 1);
	        examGrid.add(antigenHBS, 0, 2, 2, 1);
	        examGrid.add(checkHBS, 2, 2);
	        examGrid.add(antiBodiesHCV, 0, 3, 2, 1);
	        examGrid.add(checkHCV, 2, 3);
	        examGrid.add(bilirubin, 0, 4, 2, 1);
	        bilirubinLevel.setPrefWidth(60);
	        examGrid.add(bilirubinLevel, 2, 4);
	        examGrid.add(unit, 3, 4);
	        examGrid.add(saveButtonExam, 0, 5);
	        examGrid.add(clearButtonExam, 1, 5);
	        examGrid.setDisable(true);
	        
	        leftLayout.setBottom(examGrid);
	        
	        /**
	         * ustawienia odnoszace sie do calego okna programu
	         */
	        Scene scene = new Scene(mainLayout, 1400, 600);
			primaryStage.setTitle("Rejestracja wyników badañ");
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			
	        /**
	         * Obsluga zdarzen z calego programu
	         */
	        
			menuItem.setOnAction(e->
			{
				primaryStage.close();
				
			});
			
			saveButton.setOnMouseClicked(e-> 
			{
				handleOptions(femaleCheck, maleCheck, insuranceType,
				nameTextField, surnameTextField, peselTextField, error);
			});
			
			clearButton.setOnMouseClicked(e->
			{
				clearFormGrid();
			});
			
			table.setOnMouseClicked(e->
			{
				examGrid.setDisable(false);
				int selectedRow = table.getSelectionModel().getSelectedIndex();
				UserData userData = Model.userObservableList.get(selectedRow);
				nameTextField.setText(Model.userObservableList.get(selectedRow).getName());
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
				
				if(userData.isExamination()){
					ExamData examData = Model.patientExamMap.get(userData.getId());
					System.out.println(examData.getExamDate());
					calendar.setValue(examData.getExamDate());
					System.out.println(examData.isAntigenHBS());
					checkHBS.setSelected(examData.isAntigenHBS());
					checkHCV.setSelected(examData.isAntiBodiesHCV());
					bilirubinLevel.setText(String.valueOf(examData.getBilirubinLevel()));
				}
				else{
					clearExamGrid();
				}
			});
			
			addButton.setOnMouseClicked(e->
			{
				clearFormGrid();
				clearExamGrid();
			});
			
			clearButtonExam.setOnMouseClicked(e->
			{
				clearExamGrid();
			});
			
			saveButtonExam.setOnMouseClicked(e->{
				controller.saveExamResults(bilirubinLevel.getText(), checkHCV.isSelected(),
				checkHBS.isSelected(), calendar.getValue(),table.getSelectionModel().getSelectedIndex());
				clearExamGrid();
			});
			
			formGrid.setOnMouseClicked(e->
			{
				examGrid.setDisable(true);
				table.getSelectionModel().select(null);
				e.consume();
			});
			
			primaryStage.setOnCloseRequest(e-> model.serializeData());
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	private void clearFormGrid(){
		nameTextField.clear();
		surnameTextField.clear();
		peselTextField.clear();
		femaleCheck.setSelected(false);
		maleCheck.setSelected(false);
		insuranceType.getSelectionModel().selectFirst();
		error.setText("");
	}
	private void clearExamGrid(){
		calendar.setValue(null);
		bilirubinLevel.clear();
		checkHBS.setSelected(false);
		checkHCV.setSelected(false);
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
	private void handleOptions(RadioButton femaleCheck, RadioButton maleCheck, ComboBox<String> insuranceType,
			TextField  nameTextField, TextField  surnameTextField, TextField peselTextField, Label error)
	{
		String name = nameTextField.getText();
		String surname = surnameTextField.getText();
		String pesel = peselTextField.getText();
		error.setText("");
		String sex = "";
		String insurance = "";
		boolean response = true;
		
		if(femaleCheck.isSelected()){
			sex = "K";
		}
		else if(maleCheck.isSelected()){
			sex = "M";
		}
		else{
			sex = null;
		}   
	  
		String value = insuranceType.getValue();	
		if(value.equals("NFZ")){
			insurance = "NFZ";
		}
		else if(value.equals("Prywatne")){
			insurance = "Prywatne";
		}
		else{
			insurance = "Brak";
		}
	    response = controller.saveUser(name, surname, pesel, sex, insurance);	   
		  
	   if(!response)
	   {
		   error.setText("B³¹d !");
		   error.setTextFill(Color.RED);
	   }
	   else
	   {
	   
		table.setItems(Model.userObservableList);
	   	nameTextField.clear();
		surnameTextField.clear();
		peselTextField.clear();
		femaleCheck.setSelected(false);
		maleCheck.setSelected(false);
		insuranceType.getSelectionModel().selectFirst();
		error.setText("");
		
	   }
	 	
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Funkcja tworzaca tabele
	 */
	private void createTable()
	{
		@SuppressWarnings("rawtypes")
		TableColumn examinationColumn = new TableColumn("Badanie");
	/**
	 * Callback to interfejs z dwoma typami generycznymi - typem, z ktorym wywolywana jest
	 * funcja call i typem, ktory zwraca funkcja call	
	 */
		examinationColumn.setCellValueFactory(new Callback<CellDataFeatures<UserData, CheckBox>, ObservableValue<CheckBox>>() 
		{
			
             @Override
             public ObservableValue<CheckBox> call(CellDataFeatures<UserData, CheckBox> arg0) 
             {
            	          		
                 CheckBox checkBox = new CheckBox();
                 checkBox.setDisable(true);
                 
                 UserData userData = arg0.getValue();
       
                     if(userData.isExamination())
                     {
                         checkBox.selectedProperty().setValue(Boolean.TRUE);
                     }
               
               

                 return new SimpleObjectProperty<CheckBox>(checkBox);
        		}        
         });
		
		model.deserializeData();
		
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
  
        table.setItems(Model.userObservableList);
        Model.userObservableList.toString();
        table.getColumns().addAll(fullNameColumn, sexColumn, idColumn, insuranceColumn, examinationColumn );
        table.setPrefHeight(200);
        table.setTableMenuButtonVisible(true);
         
    }
	
	public static void main(String[] args)
	{
		launch(args);
	}

}