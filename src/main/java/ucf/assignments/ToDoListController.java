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
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        //call saveFile
        saveFile(itemTableView.getItems(), selectedFile);
    }

    @FXML
    public void saveAsOptionClicked(){
        //initialize FileChooser
        FileChooser fileChooser = new FileChooser();

        //set stage for fileChooser
        Stage fileStage = new Stage();
        fileStage.setTitle("Save As");

        //set default directory to be the user's home directory
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        //make sure file is saved as .ser to be serializable
        FileChooser.ExtensionFilter exFilter = new FileChooser.ExtensionFilter("Json files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(exFilter);

        //show the file stage
        File selectedFile = fileChooser.showSaveDialog(fileStage);

        //instantiate selected file
        this.selectedFile = selectedFile;

        //call saveFile to save items in List
        saveFile( itemTableView.getItems() , selectedFile);

    }

    @FXML
    public void openOptionClicked(ActionEvent actionEvent) {
        //call openFile
        openFile();
    }

    public void editColumns() {
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

    private ArrayList<Item> observableListToArrayList(ObservableList<Item> observableList){
        //copy elements in ObservableList to ArrayList
        return new ArrayList<Item>(observableList);
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


    private void initTable() {
        //initialize columns in table to instance variables using setCellValueFactory
        isCompletedColumn.setCellValueFactory(new PropertyValueFactory<Item, CheckBox>("isComplete"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("description"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<Item, LocalDate>("dueDate"));
    }

    private void loadFromPrevious(File file){

        try{
            //new Gson object
            Gson gson = new Gson();

            //read file into a list of SerItem
            Reader reader = Files.newBufferedReader(Paths.get(String.valueOf(file)));
            List<SerItem> serItems = Arrays.asList(gson.fromJson(reader, SerItem[].class));

            //call makeListDeserializable to turn serItems to Item list
            makeListDeserializable(serItems);

            //close reader
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void saveFile(ObservableList<Item> allItems, File selectedFile){
        //new Gson object
        Gson gson = new Gson().newBuilder().setPrettyPrinting().create();

        try {
            //write in list of allItems into file
            FileWriter writer = new FileWriter(selectedFile);
            gson.toJson(makeListSerializable(allItems), writer);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<SerItem> makeListSerializable(ObservableList<Item> allItems){
        //convert ObservableList to ArrayList
        ArrayList<Item> observList = observableListToArrayList(allItems);
        ArrayList<SerItem> serList = new ArrayList<>();

        //add items of observList to serList
        for(Item item: observList){
           serList.add(makeItemSerializable(item));
        }

        //return serializable list
        return serList;
    }
    private SerItem makeItemSerializable(Item item){
        //set item's description, due date, and status to serializable types in SerItem
        return new SerItem(item.getDescription(), item.getDueDate().toString(), item.getIsComplete().isSelected());
    }

    private ObservableList<Item> makeListDeserializable(List<SerItem> serItemList){
        //clear whatever is already in the itemObservableList
        itemObservableList.clear();

        //add items from serItemList to itemObservableList
        for(SerItem serItem: serItemList){
            itemObservableList.add(turnSerItemToItem(serItem));
        }

        //return itemObservableList
        return itemObservableList;
    }

    private Item turnSerItemToItem(SerItem serItem){
        //Format the date
        LocalDate serDate = dateFormatter(serItem.getSerDueDate());

        //set Item setter and constructor with serItem, with the correct types
        Item item = new Item(serItem.getSerDescription(), serDate);
        item.getIsComplete().setSelected(serItem.getSerIsCompleted());
        return item;
    }

    private LocalDate dateFormatter(String date){
        //create date pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //return parsed date string
        return LocalDate.parse(date, formatter);
    }

    private void openFile(){
        //create FileChooser
        FileChooser fileChooser = new FileChooser();

        //create stage for fileChooser
        Stage fileStage = new Stage();
        fileStage.setTitle("Open");

        //set default directory to be user's home directory
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        //make sure file is saved as .ser to be serializable
        FileChooser.ExtensionFilter exFilter = new FileChooser.ExtensionFilter("Json files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(exFilter);

        //show the file stage
        File selectedFile = fileChooser.showOpenDialog(fileStage);

        //change instance of selected file to selected file in FileChooser
        this.selectedFile = selectedFile;

        //call loadFromPrevious
        loadFromPrevious(selectedFile);
    }

}
