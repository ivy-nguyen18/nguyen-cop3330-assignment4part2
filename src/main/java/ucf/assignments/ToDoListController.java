/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ivy Nguyen
 */
package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class ToDoListController {

    //configure the table
    @FXML private TableView<Item> itemTableView = new TableView<>();
    @FXML private TableColumn<Item, CheckBox> isCompletedColumn;
    @FXML private TableColumn<Item, String> descriptionColumn;
    @FXML private TableColumn<Item, LocalDate> dueDateColumn;

    //Instance variables to create new Item objects
    @FXML private TextField descriptionTextField;
    @FXML private DatePicker dueDatePicker;

    @FXML private ComboBox<String> filterComboBox;
    @FXML private ObservableList<Item> itemObservableList = FXCollections.observableArrayList();


    private Stage primaryStage;
    private File selectedFile;


    @FXML
    public void addItemButtonClicked(ActionEvent actionEvent) {
        //all addItem with user inputted info
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
        //get the choice from the Combo box
        String choice = filterComboBox.getValue();

        //initialize filtered list
        FilteredList<Item> displayList;

        //switch case to for choice
        switch (choice) {
            case "View Completed Only" -> {
                //call viewCompletedOnly, put resulting list in displayList
                displayList = viewCompletedOnly(itemObservableList);
                //call displayItems to display the items in List
                displayItems(displayList);
            }
            case "View Uncompleted Only" -> {
                //call viewUncompletedOnly, put resulting list in displayList
                displayList = viewUncompletedOnly(itemObservableList);
                //call displayItems to display the items in List
                displayItems(displayList);
            }
            default -> {
                //set itemTableView to itemObservableList to display all items
                itemTableView.setItems(itemObservableList);
            }
        }
    }

    @FXML
    public void editDescription(TableColumn.CellEditEvent edittedCell){
        //go into the table, get the new changes
        Item itemSelected = itemTableView.getSelectionModel().getSelectedItem();
        //set the changes to item's description
        itemSelected.setDescription(edittedCell.getNewValue().toString());
    }

    @FXML
    public void clearListClicked(ActionEvent actionEvent) {
        //call clearList
        clearList(itemObservableList);
    }

    @FXML
    public void refreshButtonClicked(ActionEvent actionEvent) {
        //use refresh button for tableview
        itemTableView.refresh();
    }

    @FXML
    public void saveOptionClicked(){
        //initialize FileFunctions with object
        FileFunctions file = new FileFunctions();
        file.setItemObservableList(itemObservableList);

        //call saveFile
        file.saveFile(itemTableView.getItems(), selectedFile);
    }

    @FXML
    public void saveAsOptionClicked(){
        //initialize FileFunctions with object
        FileFunctions file = new FileFunctions();
        file.setItemObservableList(itemObservableList);

        //set new file
        this.selectedFile = file.saveAs();

        //call saveFile
        file.saveFile(itemTableView.getItems(), selectedFile);
    }

    @FXML
    public void openOptionClicked() {
        //initialize FileFunctions with object
        FileFunctions file = new FileFunctions();
        file.setItemObservableList(itemObservableList);

        //set new file
        this.selectedFile = file.openFile();

        //update itemObservableList
        itemObservableList = file.loadFromPrevious(selectedFile);
    }

    public void initialize(){
        //set up table
        initTable();

        //load observableListItems to make list viewable
        itemTableView.setItems(itemObservableList);

        //set up filter
        filterComboBox.getItems().add("View All");
        filterComboBox.getItems().addAll("View Completed Only", "View Uncompleted Only");

        //set selection to single
        itemTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //make table editable
        editColumns();

    }

    private void initTable() {
        //initialize columns in table to instance variables using setCellValueFactory
        isCompletedColumn.setCellValueFactory(new PropertyValueFactory<Item, CheckBox>("isComplete"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("description"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<Item, LocalDate>("dueDate"));
    }

    private void editColumns() {
        //set the table to be editable (true)
        itemTableView.setEditable(true);

        //allow for description to be editable
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        //allow for due date to be editable
        editDueDateView();
    }


    private void clearList(ObservableList<Item> list) {
        //use list's clear function
        list.clear();
    }

    public FilteredList<Item> viewUncompletedOnly(ObservableList<Item> allItems){
        //for unCompleteList use lambda expression to set the predicate that for each Item, call isCompleted and compare to false
        return new FilteredList<>(allItems, i -> !i.getIsComplete().isSelected());
    }

    public FilteredList<Item> viewCompletedOnly(ObservableList<Item> allItems){
        //for completedList use lambda expression to set the predicate that for each Item, call isCompleted and compare to true
        return new FilteredList<>(allItems, i -> i.getIsComplete().isSelected());
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

        //return selected list
        return selectedList;
    }


    public ObservableList<Item> deleteItems(Item selectedItem, ObservableList<Item> allItemsList){
        //remove item from list
        allItemsList.remove(selectedItem);

        //return allItems list
        return allItemsList;
    }

    public void setPrimaryStage(Stage primaryStage){
        //set the primary stage to be ToDoList
        this.primaryStage = primaryStage;
    }


    public void editDueDateView(){
        //set the mouse click event to be take in double clicks
        itemTableView.setOnMouseClicked((MouseEvent event) -> {

            //if mouse is left - double clicked
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                try {
                    //load the DueDate.fxml
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("DueDateView.fxml"));
                    AnchorPane dueDateViewParent = loader.load();

                    //set the stage
                    Stage stage = new Stage();

                    //set due date stage to be within primary window
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(primaryStage);
                    stage.setTitle("Edit Due Date");

                    //set the scene
                    Scene dueDateViewScene = new Scene(dueDateViewParent);
                    stage.setScene(dueDateViewScene);

                    //get the DueDateViewController and load it to stage
                    DueDateViewController controller = loader.getController();
                    controller.setDueDateStage(stage);
                    //get the selected item
                    Item selectedItem = itemTableView.getSelectionModel().getSelectedItem();
                    //initialize selected item into DueDateController
                    controller.initData(selectedItem);

                    //keep window open until closed
                    stage.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
