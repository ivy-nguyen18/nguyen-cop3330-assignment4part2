/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ivy Nguyen
 */
package ucf.assignments;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ToDoListController {

    private ToDoList selectedList;

    //configure the table
    @FXML private TableView<Item> itemTableView;
    @FXML private TableColumn<Item, CheckBox> isCompletedColumn;
    @FXML private TableColumn<Item, String> itemNameColumn;
    @FXML private TableColumn<Item, String> descriptionColumn;
    @FXML private TableColumn<Item, LocalDate> dueDateColumn;

    //Instance variables to create new Item objects
    @FXML private CheckBox isComplete;
    @FXML private TextField itemNameTextField;
    @FXML private TextField descriptionTextField;
    @FXML private DatePicker dueDatePicker;

    @FXML private ObservableList<Item> itemObservableList;


    @FXML
    public void addItemButtonClicked(ActionEvent actionEvent) {
        //call addItem
    }

    @FXML
    public void deleteItemsButtonClicked(ActionEvent actionEvent) {
        //create ObservableList of type Item for the selected rows
        //get the rows that were selected
        //call deleteItems
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
        //load observableListItems to make list viewable
        //set ToDoList arrayList of items to observable list
    }

    @FXML
    public void initData(ToDoList list){
        //make passed in list equal to selectedList
        //read in list file to load previous items
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


    public ToDoList addItem(CheckBox isComplete, String item, String description, LocalDate dueDate, ToDoList selectedList){
        //Create and initialize new item object
        //add the new item to the list of items in the table
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

    public ObservableList<Item> deleteItems(ObservableList<Item> deleteItemList, ObservableList<Item> itemObservableList){
        //create Observable list of type Item to contain the entire Item list of table

        //for loop over the selected rows
            //remove the Item objects from the table

        //update selectedList
        return itemObservableList;
    }

    private ObservableList<Item> ObservableListItems(){
        //iterate through observable list
            //add the attributes of the Item object to list
        //set the table

        return itemObservableList;
    }

    private void initTable() {
        //call initColumns
    }

    private void initColumns() {
        //initialize columns in table to instance variables using setCellValueFactory
        //make columns editable
    }

    private void editDueDate(){
        //make the DueDate column editable
        //go into the table, get the new changes
        //call setChangedDueDate
    }

    public void setChangedDueDate(LocalDate newDueDate){
        //set newDueDate equal to dueDate setter
    }

    private void editDescription(){
        //make the Description column editable
        //go into the table, get the new changes
        // call setChangedDescription
    }

    public void setChangedDescription(String newDescription){
        // set newDescription equal to description setter
    }

    private void editStatus(){
        //make the status column editable
        //go into the table, get the new changes
        //call setChangedStatus
    }

    public void setChangedStatus(CheckBox newStatus){
        // new status equal to status setter
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

}
