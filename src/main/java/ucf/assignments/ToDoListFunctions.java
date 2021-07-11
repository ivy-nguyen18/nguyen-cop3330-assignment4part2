/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ivy Nguyen
 */
package ucf.assignments;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ToDoListFunctions {

    public ObservableList<Item> addItem(String description, LocalDate dueDate, ObservableList<Item> selectedList){
        //Create and initialize new item object
        Item newItem = new Item(description, dueDate);

        //add the new item to the list of items in the table
        selectedList.add(newItem);

        //return selected list
        return selectedList;
    }

    public List<SerItem> addItem(String description, String dueDate, boolean isCompleted, List<SerItem> selectedList){
        //Create and initialize new item object
        SerItem newItem = new SerItem(description, dueDate, isCompleted);

        //add the new item to the list of items
        selectedList.add(newItem);

        //return selected list
        return selectedList;
    }

    public void clearList(ObservableList<Item> list) {
        //use list's clear function
        list.clear();
    }

    public void clearList(List<SerItem> list) {
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

    public List<SerItem> viewAll(List<SerItem> allItems){
        //returns list passed
        return allItems;
    }

    public List<SerItem> filteredView(List<SerItem> allItems, boolean filterBy){
        List<SerItem> filteredList = new ArrayList<>();
        for(SerItem serItem: allItems){
            //add item to list based on status
            if((serItem.getSerIsCompleted()) == filterBy){
                filteredList.add(serItem);
            }
        }
        return filteredList;
    }

    public ObservableList<Item> deleteItems(Item selectedItem, ObservableList<Item> allItemsList){
        //remove item from list
        allItemsList.remove(selectedItem);

        //return allItems list
        return allItemsList;
    }

    public List<SerItem> deleteItems(SerItem selectedItem, List<SerItem> allItemsList){
        //remove item from list
        allItemsList.remove(selectedItem);

        //return allItems list
        return allItemsList;
    }
}
