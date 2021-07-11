package ucf.assignments;

import com.google.gson.Gson;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
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

public class FileFunctions {

    private ObservableList<Item> itemObservableList;

    public void setItemObservableList(ObservableList<Item> itemObservableList){
        this.itemObservableList = itemObservableList;
    }

    public ObservableList<Item> loadFromPrevious(File file){

        try{
            //new Gson object
            Gson gson = new Gson();

            //read file into a list of SerItem
            Reader reader = Files.newBufferedReader(Paths.get(String.valueOf(file)));
            List<SerItem> serItems = Arrays.asList(gson.fromJson(reader, SerItem[].class));

            //call makeListDeserializable to turn serItems to Item list
            itemObservableList = makeListDeserializable(serItems);

            //close reader
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemObservableList;
    }

    private ArrayList<Item> observableListToArrayList(ObservableList<Item> observableList){
        //copy elements in ObservableList to ArrayList
        return new ArrayList<Item>(observableList);
    }

    public void saveFile(ObservableList<Item> allItems, File selectedFile){
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

    public ObservableList<Item> makeListDeserializable(List<SerItem> serItemList){
        //clear whatever is already in the itemObservableList
        itemObservableList.clear();

        //add items from serItemList to itemObservableList
        for(SerItem serItem: serItemList){
            itemObservableList.add(turnSerItemToItem(serItem));
        }

        //return itemObservableList
        return itemObservableList;
    }

    public File saveAs(){
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
        //return file chosen
        return fileChooser.showSaveDialog(fileStage);
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

    public File openFile(){
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

        //change instance of selected file to selected file in FileChooser
        return fileChooser.showOpenDialog(fileStage);

        //call loadFromPrevious
        //loadFromPrevious(selectedFile);
    }

}
