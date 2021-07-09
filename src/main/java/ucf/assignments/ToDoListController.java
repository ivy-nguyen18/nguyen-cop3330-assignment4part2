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
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.time.LocalDate;
import java.util.ArrayList;

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

    @FXML ComboBox<String> filterComboBox;
    @FXML private ObservableList<Item> itemObservableList = FXCollections.observableArrayList();


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
    public void saveChangesButtonClicked(ActionEvent actionEvent) {
        //call saveChanges
    }


    @FXML
    public void viewFilterComboBox(){
        String choice = filterComboBox.getValue().toString();
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
                displayList = viewAll(itemObservableList);
                displayItems(displayList);
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
        isCompletedColumn.setCellFactory(tc -> new CheckBoxTableCell<>());
    }

    public FilteredList<Item> viewUncompletedOnly(ObservableList<Item> allItems){
        //for unCompleteList use lambda expression to set the predicate that for each Item, call isCompleted and compare to false
        FilteredList<Item> unCompleteList = new FilteredList<>(allItems, i -> !i.getIsComplete().isSelected());
        return unCompleteList;
    }

    public FilteredList<Item> viewCompletedOnly(ObservableList<Item> allItems){
        //for completedList use lambda expression to set the predicate that for each Item, call isCompleted and compare to true
        FilteredList<Item> completeList = new FilteredList<>(allItems, i -> i.getIsComplete().isSelected());
        return completeList;
    }

    public FilteredList<Item> viewAll(ObservableList<Item> allItems){
        //for entireList use lambda expression to set the predicate that for each Item, call isCompleted and compare to true or false
        FilteredList<Item> entireList = new FilteredList<>(allItems, i -> i.getIsComplete().isSelected() || !i.getIsComplete().isSelected());
        return entireList;
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
    }

    private void editDueDate(){
        //make the DueDate column editable
        //dueDateColumn.setEditable(true);
        //go into the table, get the new changes
        //dueDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        //Item itemSelected = itemTableView.getSelectionModel().getSelectedItem();
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
