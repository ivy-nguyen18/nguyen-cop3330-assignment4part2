/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ivy Nguyen
 */
package ucf.assignments;

import javafx.beans.property.SimpleStringProperty;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

public class ToDoList {
    private ArrayList<Item> itemsArrayList;
    private SimpleStringProperty listName;
    private Path pathToList;
    private File itemsFile;

    public ArrayList<Item> getItemsArrayList() {
        return itemsArrayList;
    }

    public void setItemsArrayList(ArrayList<Item> itemsArrayList) {
        //set the arrayList;
    }

    public Path getPathToList() {
        return pathToList;
    }

    public void setPathToList(Path pathToList) {
        //set pathToList;
    }

    public File getItemsFile() {
        return itemsFile;
    }

    public void setItemsFile(File itemsFile) {
        //set ItemsFile;
    }

    public String getListName() {
        return listName.get();
    }

    public SimpleStringProperty listNameProperty() {
        return listName;
    }

    public void setListName(String listName) {
        //set list name;
    }



}
