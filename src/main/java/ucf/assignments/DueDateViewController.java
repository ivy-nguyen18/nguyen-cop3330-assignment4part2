package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.time.LocalDate;

public class DueDateViewController {

    private Item selectedItem;
    private Stage dueDateStage;

    @FXML private DatePicker newDueDatePicker;


    @FXML
    public void saveChangesButtonClicked(ActionEvent actionEvent) {
        //get the new value and call saveChanges
        LocalDate newDueDate = newDueDatePicker.getValue();
        //check if newDueDate is entered
        if(newDueDate != null){
            saveChanges(newDueDate);
        }
        //exit
        dueDateStage.close();
    }

    public void saveChanges(LocalDate newDueDate){
        //set new due date for selected item
        selectedItem.setDueDate(newDueDate);
    }

    public void setDueDateStage(Stage dueDateStage){
        //set the stage
        this.dueDateStage = dueDateStage;
    }

    public void initData(Item item){
        //initialize the selected item
        selectedItem = item;
    }

}
