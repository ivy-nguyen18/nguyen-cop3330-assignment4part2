/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ivy Nguyen
 */
package ucf.assignments;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

import java.time.LocalDate;

public class Item implements java.io.Serializable{

    private CheckBox isComplete;
    private SimpleStringProperty description;
    private LocalDate dueDate;

    public Item (String description, LocalDate dueDate) {
        //initialize instances
        this.isComplete = new CheckBox();
        this.description = new SimpleStringProperty(description);
        this.dueDate = dueDate;
    }

    public CheckBox getIsComplete() {
        //return checkbox
        return isComplete;
    }

    public void setIsComplete(CheckBox isComplete) {
        //set checkbox
        this.isComplete = isComplete;
    }


    public String getDescription() {
        //return description
        return description.get();
    }

    public void setDescription(String description) {
        //set description
        this.description.set(description);
    }

    public LocalDate getDueDate() {
        //return due date
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        //set due date
        this.dueDate = dueDate;
    }

}
