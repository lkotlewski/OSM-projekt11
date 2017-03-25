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
	public Menu menu = new Menu("Aplikacja");
	public MenuBar menuBar = new MenuBar();
	public MenuItem menuItem = new MenuItem("Wyjœcie");
	public TableView<UserData> table  = new TableView<UserData>();
	public Model model = new Model();
	public Controller controller = new Controller();
	
	
	@Override
	/*
	 * (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	public void start(Stage primaryStage)
	{
		try 
		{
			
			BorderPane mainLayout = new BorderPane();
			BorderPane rightLayout = new BorderPane(); // prawa czesc okna, wzgledem, ktorej wyznaczane sa marginesy
			rightLayout.setPadding(new Insets(20, 20, 20 ,20)); 
			mainLayout.setRight(rightLayout);
			
			FlowPane rightBox = new FlowPane(); //opakowuje tablice
			rightBox.setPadding(new Insets(20, 20, 20 ,20));
			rightBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
					CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			rightBox.setVgap(20);
			rightBox.setHgap(20);
			rightLayout.setCenter(rightBox);
			
			Label patientData = new Label("Dane Pacjentów");
			rightBox.getChildren().add(patientData);
			
			ScrollPane scrollCont = new ScrollPane();
			createTable();
			scrollCont.setContent(table);
			rightBox.getChildren().add(scrollCont);
			
			Button addButton = new Button("Dodaj");
	        Button deleteButton = new Button("Usuñ");
	        rightBox.getChildren().add(addButton);
	        rightBox.getChildren().add(deleteButton);
			
			BorderPane leftLayout = new BorderPane(); //opakowuje dwa obszary po lewej stronie
			leftLayout.setMinSize(300,400);
			leftLayout.setPadding(new Insets(20, 20, 20 ,20));
			mainLayout.setLeft(leftLayout);
			
			GridPane formGrid = new GridPane();
			formGrid.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
					CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			formGrid.setMinSize(260, 240);
			formGrid.setPadding(new Insets(20, 20, 20 ,20));
	        formGrid.setAlignment(Pos.TOP_CENTER);
	        formGrid.setHgap(20);
	        formGrid.setVgap(10);
	        
	        Text formTitle = new Text("Dane Pacjenta");
	        formGrid.add(formTitle, 0, 0, 2, 1);
	    
	        Label name = new Label("Imiê:");
	        formGrid.add(name, 0, 1, 2, 1);

	        TextField nameTextField = new TextField();
	        formGrid.add(nameTextField, 2, 1, 2, 1);
	        
	        Label surname = new Label("Nazwisko:");
	        formGrid.add(surname, 0, 2, 2, 1);

	        TextField surnameTextField = new TextField();
	        formGrid.add(surnameTextField, 2, 2, 2, 1);
	        
	        Label pesel = new Label("PESEL:");
	        formGrid.add(pesel, 0, 3, 2, 1);

	        TextField peselTextField = new TextField();
	        formGrid.add(peselTextField, 2, 3, 2, 1);
	        
	        Label sex = new Label("P³eæ:");
	        formGrid.add(sex, 0, 4, 2, 1);
	        
	       
	        RadioButton femaleCheck = new RadioButton("Kobieta");
	        RadioButton maleCheck = new RadioButton("Mê¿czyzna");
	        formGrid.add(femaleCheck, 2, 4);
	        formGrid.add(maleCheck, 3, 4);
	        ToggleGroup sexGroup = new ToggleGroup();
	        femaleCheck.setToggleGroup(sexGroup);
	        maleCheck.setToggleGroup(sexGroup);
	        
	        Label insurance = new Label("Ubezpieczenie");
	        formGrid.add(insurance, 0, 5, 2, 1);
	        
	        ComboBox<String> insuranceType = new ComboBox<String>();
	        formGrid.add(insuranceType, 3, 5, 2, 1);
	        insuranceType.getItems().addAll("NFZ","Prywatne","Brak");
	        insuranceType.getSelectionModel().selectFirst();
	        
	        Button saveButton = new Button("Zapisz");
	        Button clearButton = new Button("Anuluj");
	        formGrid.add(saveButton, 0, 6);
	        formGrid.add(clearButton, 1, 6);
	        
	        Label error = new Label("");
	        formGrid.add(error, 1, 7);
	      
			leftLayout.setTop(formGrid);
			
			GridPane examGrid = new GridPane();
			examGrid.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
					CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			examGrid.setMinSize(260, 200);
			examGrid.setPadding(new Insets(20, 20, 20 ,20));
	        examGrid.setAlignment(Pos.TOP_CENTER);
	        examGrid.setHgap(20);
	        examGrid.setVgap(10);
	        
	        Label exam = new Label("Badanie");
	        examGrid.add(exam, 0, 0, 2, 1);
	        
	        Label date = new Label("Data");
	        examGrid.add(date, 0, 1, 2, 1);
	        DatePicker calendar =  new DatePicker();
	        examGrid.add(calendar, 2, 1, 3, 1);
	        
	        Label antigenHBS = new Label("Antygen HBS");
	        examGrid.add(antigenHBS, 0, 2, 2, 1);
	        CheckBox checkHBS = new CheckBox();
	        examGrid.add(checkHBS, 2, 2);
	        
	        Label antiBodiesHCV = new Label("Przeciwcia³a HCV");
	        examGrid.add(antiBodiesHCV, 0, 3, 2, 1);
	        CheckBox checkHCV = new CheckBox();
	        examGrid.add(checkHCV, 2, 3);
	        
	        Label bilirubin = new Label("Poziom Bilirubiny");
	        examGrid.add(bilirubin, 0, 4, 2, 1);
	        TextField bilirubinLevel = new TextField();
	        bilirubinLevel.setPrefWidth(60);
	        examGrid.add(bilirubinLevel, 2, 4);
	        Label unit = new Label("mg/dl");
	        examGrid.add(unit, 3, 4);
	        
	        Button saveButtonExam = new Button("Zapisz");
	        Button clearButtonExam = new Button("Anuluj");
	        examGrid.add(saveButtonExam, 0, 5);
	        examGrid.add(clearButtonExam, 1, 5);
	        
	        leftLayout.setBottom(examGrid);
	        
			menuItem.setOnAction(e->
			{
				
				primaryStage.close();
				
			});
			
			menu.getItems().add(menuItem);
			menuBar.getMenus().add(menu);
			
			mainLayout.setTop(menuBar);
			
			saveButton.setOnAction(e-> handleOptions(femaleCheck, maleCheck, insuranceType, nameTextField, surnameTextField, peselTextField, error));
			clearButton.setOnAction(e->
			{
				nameTextField.clear();
				surnameTextField.clear();
				peselTextField.clear();
				femaleCheck.setSelected(false);
				maleCheck.setSelected(false);
				insuranceType.getSelectionModel().selectFirst();
				error.setText("");
				
			});
			
			Scene scene = new Scene(mainLayout, 1400, 650);
			primaryStage.setTitle("Rejestracja wyników badañ");
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
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
		
		if(femaleCheck.isSelected())
		{
			sex = "K";
		}
		else if(maleCheck.isSelected())
		{
			sex = "M";
		}
		else
		{
			response = false;
		}
		
	    
	   if(response)
	   {
		   String value = insuranceType.getValue();
			
			if(value.equals("NFZ"))
			{
				insurance = "NFZ";
			}
			else if(value.equals("Prywatne"))
			{
				insurance = "Prywatne";
			}
			else
			{
				insurance = "Brak";
			}
			
		   response = controller.saveUser(name, surname, pesel, sex, insurance);
		   table.setItems(Model.userObservableList);
	   }
		  
	   if(!response)
	   {
		   error.setText("B³¹d !");
		   error.setTextFill(Color.RED);
	   }
	   else
	   {
	   
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
		
		examinationColumn.setCellValueFactory(new Callback<CellDataFeatures<UserData, CheckBox>, ObservableValue<CheckBox>>() 
		{
			
             @Override
             public ObservableValue<CheckBox> call(CellDataFeatures<UserData, CheckBox> arg0) 
             {
            	          		
                 CheckBox checkBox = new CheckBox();
                 checkBox.setDisable(true);
                 
                 UserData userData = arg0.getValue();
                 
                 for (int i = 0; i < Model.userObservableList.size(); i++)
                 {
                     if(userData.isExamination())
                     {
                         checkBox.selectedProperty().setValue(Boolean.TRUE);
                     }
               
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
        table.setPrefHeight(100);
        table.setTableMenuButtonVisible(true);
         
    }
	
	public static void main(String[] args)
	{
		launch(args);
	}

}
