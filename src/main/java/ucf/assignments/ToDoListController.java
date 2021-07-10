/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ivy Nguyen
 */
package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ToDoListController {

    //configure the table
    @FXML private TableView<Item> itemTableView = new TableView<>();
    @FXML private TableColumn<Item, CheckBox> isCompletedColumn;
    @FXML private TableColumn<Item, String> descriptionColumn;
    @FXML private TableColumn<Item, LocalDate> dueDateColumn;

    //Instance variables to create new Item objects
    @FXML private CheckBox isComplete;
    @FXML private TextField descriptionTextField;
    @FXML private DatePicker dueDatePicker;

    @FXML private ComboBox<String> filterComboBox;
    @FXML private ObservableList<Item> itemObservableList = FXCollections.observableArrayList();

    private Stage primaryStage;


    @FXML
    public void addItemButtonClicked(ActionEvent actionEvent) {
        addItem(descriptionTextField.getText(), dueDatePicker.getValue(), itemObservableList);
    }

    @FXML
    public void deleteItemsButtonClicked(ActionEvent actionEvent) {
        //create ObservableList of type Item for all Items
        ObservableList<Item> allItems = itemTableView.getItems();

        //create ObservableList of type Item for items to remove
        Item selectedItem = itemTableView.getSelectionModel().getSelectedItem();

        //call deleteItems
        deleteItems(selectedItem, allItems);
    }


    @FXML
    public void viewFilterComboBox(){
        String choice = filterComboBox.getValue();
        FilteredList<Item> displayList;
        switch (choice) {
            case "View Completed Only" -> {
                displayList = viewCompletedOnly(itemObservableList);
                displayItems(displayList);
            }
            case "View Uncompleted Only" -> {
                displayList = viewUncompletedOnly(itemObservableList);
                displayItems(displayList);
            }
            default -> {
                itemTableView.setItems(itemObservableList);
            }
        }
    }

    @FXML
    public void editColumns() {
        //set the table to be editable (true)
        //allow for due date to be editable
        //allow for description to be editable
        //allow for status to be editable

        //allow for multiple selection
    }

    @FXML
    public void clearListClicked(ActionEvent actionEvent) {
        clearList(itemObservableList);
    }

    private void clearList(ObservableList<Item> list) {
        list.clear();
    }

    public void initialize(){
        //set up table
        initTable();

        //load from memory

        //load observableListItems to make list viewable
        itemTableView.setItems(itemObservableList);

        //set up filter
        filterComboBox.getItems().add("View All");
        filterComboBox.getItems().addAll("View Completed Only", "View Uncompleted Only");

        //allow for multiple selection
        itemTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //make columns editable
        itemTableView.setEditable(true);
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        editDueDateView();
    }

    public FilteredList<Item> viewUncompletedOnly(ObservableList<Item> allItems){
        //for unCompleteList use lambda expression to set the predicate that for each Item, call isCompleted and compare to false
        return new FilteredList<>(allItems, i -> !i.getIsComplete().isSelected());
    }

    public FilteredList<Item> viewCompletedOnly(ObservableList<Item> allItems){
        //for completedList use lambda expression to set the predicate that for each Item, call isCompleted and compare to true
        return new FilteredList<>(allItems, i -> i.getIsComplete().isSelected());
    }

    public FilteredList<Item> viewAll(ObservableList<Item> itemObservableList){
        //for entireList use lambda expression to set the predicate that for each Item, call isCompleted and compare to true or false
        return new FilteredList<>(itemObservableList, b -> true);
    }

    private void displayItems(FilteredList<Item> displayList){
        //set itemTableView to displayList
        itemTableView.setItems(displayList);
    }


    public ObservableList<Item> addItem(String description, LocalDate dueDate, ObservableList<Item> selectedList){
        //Create and initialize new item object
        Item newItem = new Item(description, dueDate);

        //add the new item to the list of items in the table
        itemTableView.getItems().add(newItem);

        return selectedList;
    }

    private ArrayList<Item> observableListToArrayList(){
        //copy elements in ObservableList to ArrayList
        ArrayList<Item> observableList = new ArrayList<Item>(itemTableView.getItems());
        return observableList;
    }

    public ObservableList<Item> deleteItems(Item selectedItem, ObservableList<Item> allItemsList){
        //remove item from list
        allItemsList.remove(selectedItem);

        return allItemsList;
    }

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    @FXML
    public void editDueDateView(){
        itemTableView.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("DueDateView.fxml"));
                    AnchorPane dueDateViewParent = loader.load();

                    Stage stage = new Stage();
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(primaryStage);
                    stage.setTitle("Edit Due Date");
                    Scene dueDateViewScene = new Scene(dueDateViewParent);
                    stage.setScene(dueDateViewScene);

                    DueDateViewController controller = loader.getController();
                    controller.setDueDateStage(stage);
                    Item selectedItem = itemTableView.getSelectionModel().getSelectedItem();
                    controller.initData(selectedItem);

                   stage.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    private void initTable() {
        //initialize columns in table to instance variables using setCellValueFactory
        isCompletedColumn.setCellValueFactory(new PropertyValueFactory<Item, CheckBox>("isComplete"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("description"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<Item, LocalDate>("dueDate"));
    }


    @FXML
    public void editDescription(TableColumn.CellEditEvent edittedCell){
        //go into the table, get the new changes
        Item itemSelected = itemTableView.getSelectionModel().getSelectedItem();
        //set the changes to item's description
        itemSelected.setDescription(edittedCell.getNewValue().toString());
    }

    @FXML
    public void editStatus(TableColumn.CellEditEvent edittedCell){
        //make the status column editable
        Item itemSelected = itemTableView.getSelectionModel().getSelectedItem();
        //go into the table, get the new changes
        itemSelected.getIsComplete().isSelected();
    }


    private void loadFromPrevious(File file){
//        //read back in file
//        //deserialize file
//        ObjectInputStream ois = null;
//        try{
//            FileInputStream fis = new FileInputStream(file);
//            ois = new ObjectInputStream(fis);
//
//            List<Item> itemList = (List<Item>)ois.readObject();
//
//            //copy list to current ObservableList
//            itemObservableList = FXCollections.observableArrayList(itemList);
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    @FXML
    public void refreshButtonClicked(ActionEvent actionEvent) {
        itemTableView.refresh();
    }

    @FXML
    public void aboutOptionClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void saveOptionClicked(){
        //call observableListToArrayList
        //serialize using java serialization
        //write to current list file
    }

    @FXML
    public void saveAsOptionClicked(){
        FileChooser fileChooser = new FileChooser();
        Stage fileStage = new Stage();
        fileStage.setTitle("Save As");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        //make sure file is saved as .ser to be serializable
        FileChooser.ExtensionFilter exFilter = new FileChooser.ExtensionFilter("SER files (*.ser)", "*.ser");
        fileChooser.getExtensionFilters().add(exFilter);

        if(itemObservableList.isEmpty()){
            fileStage.initOwner(primaryStage);
            Alert emptyTableAlert = new Alert(Alert.AlertType.ERROR, "EMPTY TABLE", ButtonType.OK);
            emptyTableAlert.setContentText("Nothing new to save");
            emptyTableAlert.initModality(Modality.APPLICATION_MODAL);
            emptyTableAlert.initOwner(primaryStage);
            emptyTableAlert.showAndWait();
        }else{
            File selectedFile = fileChooser.showSaveDialog(fileStage);
            if(selectedFile != null){
                saveFile(itemTableView.getItems(), selectedFile);
            }
        }
    }

    private void saveFile(ObservableList<Item> allItems, File selectedFile){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try{
            fos = new FileOutputStream(selectedFile);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(observableListToArrayList());

            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openOptionClicked(ActionEvent actionEvent) {
    }
}
