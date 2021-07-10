/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ivy Nguyen
 */
package ucf.assignments;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.CheckBox;

import java.time.LocalDate;

public class Item implements java.io.Serializable{

    private CheckBox isComplete;
    private SimpleStringProperty description;
    private LocalDate dueDate;

    public Item (String description, LocalDate dueDate) {
        this.isComplete = new CheckBox();
        this.description = new SimpleStringProperty(description);
        this.dueDate = dueDate;
    }

    public CheckBox getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(CheckBox isComplete) {
        this.isComplete = isComplete;
    }


    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
        //this.description = new SimpleStringProperty(description);
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

}
