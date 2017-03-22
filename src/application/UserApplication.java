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
	public MenuItem menuItem = new MenuItem("Wyj�cie");
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
			createTable();
			BorderPane mainLayout = new BorderPane();
			mainLayout.setPadding(new Insets(0,20,0,0)); // margines z prawej strony
			
			VBox rightLayout = new VBox(); //opakowuje tablice
			rightLayout.getChildren().add(table);
			mainLayout.setRight(rightLayout);
			
			BorderPane leftLayout = new BorderPane(); // opakowuje dwa obszary po lewej stronie
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
	        //formGrid.setGridLinesVisible(true);
	        
	        Text formTitle = new Text("Dane Pacjenta");
	        formGrid.add(formTitle, 0, 0, 2, 1);
	    

	        Label name = new Label("Imi�:");
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
	        
	        Label sex = new Label("P�e�:");
	        formGrid.add(sex, 0, 4, 2, 1);
	        
	       
	        RadioButton femaleCheck = new RadioButton("Kobieta");
	        RadioButton maleCheck = new RadioButton("M�czyzna");
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
	        
			leftLayout.setTop(formGrid);
			menuItem.setOnAction(e->
			{
				
				primaryStage.close();
				
			});
			
			menu.getItems().add(menuItem);
			menuBar.getMenus().add(menu);
			
			mainLayout.setTop(menuBar);
		
			Scene scene = new Scene(mainLayout, 1200, 500);
			primaryStage.setTitle("Rejestracja wynik�w bada�");
			primaryStage.setScene(scene);
			primaryStage.show();		
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
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
                 
                 for (int i = 0; i < model.userObservableList.size(); i++)
                 {
                     if(userData.getExamination())
                     {
                         checkBox.selectedProperty().setValue(Boolean.TRUE);
                     }
               
                 }

                 return new SimpleObjectProperty<CheckBox>(checkBox);
        		}
         
         
         });
		
				
		TableColumn<UserData, String> fullNameColumn = new TableColumn<>("Imi� i nazwisko");
		fullNameColumn.setMinWidth(200);
		fullNameColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("fullName"));
		
		TableColumn<UserData, String> sexColumn = new TableColumn<>("P�e�");
		sexColumn.setMinWidth(50);
		sexColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("sex"));
		
		TableColumn<UserData, String> idColumn = new TableColumn<>("PESEL");
		idColumn.setMinWidth(200);
		idColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("id"));
		
		TableColumn<UserData, String> insuranceColumn = new TableColumn<>("Ubezpieczenie");
		insuranceColumn.setMinWidth(200);
		insuranceColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("insurance"));
  
         table.setItems(model.userObservableList);
         table.getColumns().addAll(fullNameColumn, sexColumn, idColumn, insuranceColumn, examinationColumn );
         table.setPrefHeight(200);
         table.setTableMenuButtonVisible(true);
    
         
         table.setOnMouseClicked(e->
         {
        	 
                UserData userData =   table.getSelectionModel().getSelectedItem();
               // content.setText(user.getUserName());
              
        });
    }
	
	public static void main(String[] args)
	{
		launch(args);
	}

}
