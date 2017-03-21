package application;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class UserApplication extends Application {

	/**
	 * Przyciks button odpowiedzialny za...
	 */
	public Button button;
	public Label label;
	public TextField text;
	public Menu menu = new Menu("Aplikacja");
	public MenuBar menuBar = new MenuBar();
	public Controller controller = new Controller();
	public TableView<UserData> table  = new TableView<UserData>();
	public Model model = new Model();
	

	private void createTable()
	{
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
		
				
		TableColumn<UserData, String> fullNameColumn = new TableColumn<>("Imiê i nazwisko");
		fullNameColumn.setMinWidth(300);
		fullNameColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("fullName"));
		
		TableColumn<UserData, String> sexColumn = new TableColumn<>("P³eæ");
		sexColumn.setMinWidth(50);
		sexColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("sex"));
		
		TableColumn<UserData, String> idColumn = new TableColumn<>("PESEL");
		idColumn.setMinWidth(300);
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
			VBox layout = new VBox(10); // layout z ekspozycja w srodku
			layout.setPadding(new Insets(10, 10, 10 ,10));
			button = new Button("Start"); // moze byc tez setText()
			layout.getChildren().add(button);
			
			text = new TextField();
			layout.getChildren().add(text);
			
			label = new Label("");
			label.setWrapText(true);
			layout.getChildren().add(label);
			
			mainLayout.setCenter(layout);

			MenuItem menuItem = new MenuItem("Wyjœcie");
			menuItem.setOnAction(e->
			{
				
				primaryStage.close();
				
			});
			
			menu.getItems().add(menuItem);
			menuBar.getMenus().add(menu);
			
			mainLayout.setTop(menuBar);

			button.setOnAction(e->
			{
				
				label.setText(controller.getData(text.getText()));
				
			});
	
		
			
			/*table.setItems(getTable());
			table.getColumns().addAll(fullNameColumn, sexColumn, idColumn, insuranceColumn, examinationColumn);*/
			
			VBox vBoxLayout = new VBox(); 
			vBoxLayout.getChildren().add(table);
			mainLayout.setRight(vBoxLayout);

			
		
			Scene scene = new Scene(mainLayout, 1200, 450);
			
			primaryStage.setTitle("Rejestracja wyników badañ");
			primaryStage.setScene(scene);
			primaryStage.show();		
			
		} catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/*public ObservableList<Table> getTable()
	{
		ObservableList<Table> table = FXCollections.observableArrayList();
		table.add(new Table("Tomasz Wa³êsa", 'M', "68051307545", "NFZ", true));
		return table;
	}*/
	
	public static void main(String[] args)
	{
		launch(args);
	}

}
