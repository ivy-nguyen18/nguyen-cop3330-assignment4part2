/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ivy Nguyen
 */
package ucf.assignments;

import com.sun.javafx.scene.control.DatePickerContent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class ToDoListController {

    //configure the table
    @FXML private TableView<Item> itemTableView;
    @FXML private TableColumn<Item, CheckBox> isCompletedColumn;
    @FXML private TableColumn<Item, String> descriptionColumn;
    @FXML private TableColumn<Item, LocalDate> dueDateColumn;

    //Instance variables to create new Item objects
    @FXML private CheckBox isComplete;
    @FXML private TextArea descriptionTextArea;
    @FXML private DatePicker dueDatePicker;

    @FXML private ObservableList<Item> itemObservableList = FXCollections.observableArrayList();


    @FXML
    public void addItemButtonClicked(ActionEvent actionEvent) {
        addItem(descriptionTextArea.getText(), dueDatePicker.getValue(), itemObservableList);
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
    public void saveChangesButtonClicked(ActionEvent actionEvent) {
        //call saveChanges
    }

    @FXML
    public void backToListManagerButtonClicked(ActionEvent actionEvent) {
        //if the event is clicked twice
            //create parent node and load the ListManager.fxml from Resources
            //Initialize Scene and set Scene to show parent
            //Set stage
            //Set scene onto window
            //show the window
    }

    @FXML
    public void viewCompletedOnlyClicked(ActionEvent actionEvent) {
        //call viewCompletedOnly
        //call displayItems
    }

    @FXML
    public void viewUncompletedOnlyButtonClicked(ActionEvent actionEvent) {
        //call viewUncompletedOnly
        //call displayItems
    }

    @FXML
    public void viewAllClicked(ActionEvent actionEvent) {
        //call viewAll
        //call displayItems
    }

    @FXML
    private void editColumns() {
        //set the table to be editable (true)
        //allow for due date to be editable
        //allow for description to be editable
        //allow for status to be editable

        //allow for multiple selection
    }

    public void initialize(){
        //set up table
        initTable();

        //load from memory

        //load observableListItems to make list viewable
        itemTableView.setItems(itemObservableList);

        //allow for multiple selection
        itemTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public FilteredList<Item> viewUncompletedOnly(FilteredList<Item> unCompleteList){
        //for unCompleteList use lambda expression to set the predicate that for each Item, call isCompleted and compare to false
        return unCompleteList;
    }

    public FilteredList<Item> viewCompletedOnly(FilteredList<Item> completeList){
        //for completedList use lambda expression to set the predicate that for each Item, call isCompleted and compare to true
        return completeList;
    }

    public FilteredList<Item> viewAll(FilteredList<Item> entireList){
        //for entireList use lambda expression to set the predicate that for each Item, call isCompleted and compare to true or false
        //set ToDoList arrayList of items to this entireList
        return entireList;
    }

    private void displayItems(FilteredList<Item> displayList){
        //set itemTableView to displayList
    }


    public ObservableList<Item> addItem(String description, LocalDate dueDate, ObservableList<Item> selectedList){
        //Create and initialize new item object
        Item newItem = new Item(description, dueDate);

        //add the new item to the list of items in the table
        itemTableView.getItems().add(newItem);

        return selectedList;
    }

    public void saveChanges(){
        //call observableListToArrayList
        //serialize using java serialization
        //write to current list file
    }

    private ArrayList<Item> observableListToArrayList(){
        ArrayList<Item> observableList = new ArrayList<>();
        //copy elements in ObservableList to ArrayList
        return observableList;
    }

    public ObservableList<Item> deleteItems(Item selectedItem, ObservableList<Item> allItemsList){
        //remove item from list
        allItemsList.remove(selectedItem);

        return allItemsList;
    }

    private ObservableList<Item> ObservableListItems(){
        //iterate through observable list
            //add the attributes of the Item object to list
        //set the table

        return itemObservableList;
    }

    private void initTable() {
        //initialize columns in table to instance variables using setCellValueFactory
        isCompletedColumn.setCellValueFactory(new PropertyValueFactory<Item, CheckBox>("isComplete"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("description"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<Item, LocalDate>("dueDate"));

        //make columns editable
        itemTableView.setEditable(true);
    }

    private void editDueDate(){
        //make the DueDate column editable
        dueDateColumn.setEditable(true);
        //go into the table, get the new changes
        dueDateColumn.setOnEditCommit(event -> event.getRowValue().setDueDate(event.getNewValue()));
    }

    private void editDescription(){
        //make the Description column editable
        descriptionColumn.setEditable(true);
        //go into the table, get the new changes
        descriptionColumn.setOnEditCommit(event -> event.getRowValue().setDescription(event.getNewValue()));
    }

    private void editStatus(){
        //make the status column editable
        isCompletedColumn.setEditable(true);
        //go into the table, get the new changes
        isCompletedColumn.setOnEditCommit(event -> event.getRowValue().setIsComplete(event.getNewValue()));
    }

    private void dateConverter(){
        //set the String pattern to yyyy-MM-dd
        //set datePicker to the PromptText to get original format
        //setConverter for datePicker to new pattern using "nested" Override functions
            //initialize DateTimeFormatter using DateTimeFormatter.ofPattern function
            //override the toString method for LocalDate
                //if there a date is inputted
                    //return formatted date
                //else
                    //return empty string
            //override the fromString method for Strings
                //if the string is not empty
                    //parse the string into new pattern
                //if the string is empty
                    //return null
    }

    private void loadFromPrevious(){
        //read this list file
        //read into a list of type Item
        //copy list to current ObservableList
    }

    public void clearListClicked(ActionEvent actionEvent) {
    }
}
