/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ivy Nguyen
 */
package ucf.assignments;

public class SerItem {

    private String serDescription;
    private String serDueDate;
    private boolean serIsCompleted;

    public SerItem(String serDescription, String serDueDate, boolean serIsCompleted) {
        //instantiate variables
        this.serDescription = serDescription;
        this.serDueDate = serDueDate;
        this.serIsCompleted = serIsCompleted;
    }

    public void setSerDescription(String serDescription) {
        //set description
        this.serDescription = serDescription;
    }

    public void setSerDueDate(String serDueDate) {
        //set due date
        this.serDueDate = serDueDate;
    }

    public void setSerIsCompleted(boolean serIsCompleted) {
        //set status
        this.serIsCompleted = serIsCompleted;
    }


    public String getSerDescription() {
        //return serializable description
        return serDescription;
    }

    public String getSerDueDate() {
        //return serializable due date
        return serDueDate;
    }

    public boolean getSerIsCompleted() {
        //return serializable isCompleted checkbox
        return serIsCompleted;
    }

}
