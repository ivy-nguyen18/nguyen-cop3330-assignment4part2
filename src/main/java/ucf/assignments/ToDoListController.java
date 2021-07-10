/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ivy Nguyen
 */
package ucf.assignments;

import com.google.gson.Gson;
import com.sun.javafx.collections.ObservableListWrapper;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
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

    private ArrayList<Item> observableListToArrayList(ObservableList<Item> observableList){
        //copy elements in ObservableList to ArrayList
        return new ArrayList<Item>(observableList);
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

        try{
            Gson gson = new Gson();

            Reader reader = Files.newBufferedReader(Paths.get(String.valueOf(file)));
            List<SerItem> serItems = Arrays.asList(gson.fromJson(reader, SerItem[].class));

            makeListDeserializable(serItems);

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void refreshButtonClicked(ActionEvent actionEvent) {
        itemTableView.refresh();
    }

    @FXML
    public void saveOptionClicked(){
        //call observableListToArrayList
        //serialize using java serialization
        //write to current list file
        saveFile(itemTableView.getItems(), selectedFile);
    }

    @FXML
    public void saveAsOptionClicked(){
        FileChooser fileChooser = new FileChooser();
        Stage fileStage = new Stage();
        fileStage.setTitle("Save As");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        //make sure file is saved as .ser to be serializable
        FileChooser.ExtensionFilter exFilter = new FileChooser.ExtensionFilter("Json files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(exFilter);

        File selectedFile = fileChooser.showSaveDialog(fileStage);
        this.selectedFile = selectedFile;
        saveFile( itemTableView.getItems() , selectedFile);

    }

    private void saveFile(ObservableList<Item> allItems, File selectedFile){
        Gson gson = new Gson().newBuilder().setPrettyPrinting().create();

        try {
            FileWriter writer = new FileWriter(selectedFile);
            gson.toJson(makeListSerializable(allItems), writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<SerItem> makeListSerializable(ObservableList<Item> allItems){
        ArrayList<Item> observList = observableListToArrayList(allItems);
        ArrayList<SerItem> serList = new ArrayList<>();
        for(Item item: observList){
           serList.add(makeItemSerializable(item));
        }
        return serList;
    }
    private SerItem makeItemSerializable(Item item){
        return new SerItem(item.getDescription(), item.getDueDate().toString(), item.getIsComplete().isSelected());
    }

    private ObservableList<Item> makeListDeserializable(List<SerItem> serItemList){
        itemObservableList.clear();
        for(SerItem serItem: serItemList){
            itemObservableList.add(turnSerItemToItem(serItem));
        }
        return itemObservableList;
    }

    private Item turnSerItemToItem(SerItem serItem){
        LocalDate serDate = dateFormatter(serItem.getSerDueDate());
        Item item = new Item(serItem.getSerDescription(), serDate);
        item.getIsComplete().setSelected(serItem.isSerIsCompleted());
        return item;
    }

    private LocalDate dateFormatter(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }


    @FXML
    public void openOptionClicked(ActionEvent actionEvent) {
        openFile();
    }

    private void openFile(){
        FileChooser fileChooser = new FileChooser();
        Stage fileStage = new Stage();
        fileStage.setTitle("Open");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        //make sure file is saved as .ser to be serializable
        FileChooser.ExtensionFilter exFilter = new FileChooser.ExtensionFilter("Json files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(exFilter);

        File selectedFile = fileChooser.showOpenDialog(fileStage);
        this.selectedFile = selectedFile;
        loadFromPrevious(selectedFile);
    }

}
