package ucf.assignments;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class DueDateViewController {

    private Item selectedItem;
    private Stage dueDateStage;

    @FXML private DatePicker newDueDatePicker;
    @FXML private Label itemDescriptionLabel;


    @FXML
    public void saveChangesButtonClicked(ActionEvent actionEvent) {
        saveChanges(newDueDatePicker.getValue());
        dueDateStage.close();
    }

    public void saveChanges(LocalDate newDueDate){
        selectedItem.setDueDate(newDueDate);
    }

    public void setDueDateStage(Stage dueDateStage){
        this.dueDateStage = dueDateStage;
    }

    public void initData(Item item){
        selectedItem = item;
        itemDescriptionLabel.setText(selectedItem.getDescription());
    }

}
