/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ivy Nguyen
 */
package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoListControllerTest {

    @Test
    void able_to_add_new_item_to_existing_todo_list() {
        //create object ToDoListFunctions
        ToDoListFunctions toDoListFunctions = new ToDoListFunctions();

        //create a list of type Item with items already in list
        List<SerItem> list = new ArrayList<>();
        SerItem item1 = new SerItem("2", LocalDate.of(2021, 7, 1).toString(), true);
        SerItem item2 = new SerItem("3", LocalDate.of(2022, 8, 3).toString(), false);
        SerItem item3 = new SerItem("4", LocalDate.of(2023, 6, 22).toString(), true);
        list.add(item1);
        list.add(item2);
        list.add(item3);

        //add the item to list
        toDoListFunctions.addItem("1", LocalDate.of(2020, 9, 18).toString(), false, list);

        //set actual of type integer to list.size
        int actual = list.size();
        //set expected of type integer to what size of list should be
        int expected = 4;

        //assert that actual and expected are equal
        assertEquals(actual, expected);
    }

    @Test
    void able_to_remove_item_to_existing_todo_list() {
        //create object ToDoListFunctions
        ToDoListFunctions toDoListFunctions = new ToDoListFunctions();

        //create a list of type List of items
        List<SerItem> list = new ArrayList<>();
        SerItem item1 = new SerItem("2", LocalDate.of(2021, 7, 1).toString(), true);
        SerItem item2 = new SerItem("3", LocalDate.of(2022, 8, 3).toString(), false);
        SerItem item3 = new SerItem("4", LocalDate.of(2023, 6, 22).toString(), true);
        list.add(item1);
        list.add(item2);
        list.add(item3);

        //call delete item function
        toDoListFunctions.deleteItems(item1, list);

        //set actual of type integer to list.size
        int actual = list.size();
        //set expected of type integer to what size the remaining list should be
        int expected = 2;

        //assert that actual and expected are equal
        assertEquals(actual, expected);
    }

    @Test
    void able_to_clear_list_of_all_items(){
        //create object ToDoListFunctions
        ToDoListFunctions toDoListFunctions = new ToDoListFunctions();

        //create the list
        List<SerItem> list = new ArrayList<>();
        SerItem item1 = new SerItem("2", LocalDate.of(2021, 7, 1).toString(), true);
        SerItem item2 = new SerItem("3", LocalDate.of(2022, 8, 3).toString(), false);
        SerItem item3 = new SerItem("4", LocalDate.of(2023, 6, 22).toString(), true);
        list.add(item1);
        list.add(item2);
        list.add(item3);

        //call clear list
        toDoListFunctions.clearList(list);

        //set actual of type integer to list.size
        int actual = list.size();
        //set expected of type integer to what size the remaining list should be
        int expected = 0;

        assertEquals(actual, expected);
    }

    @Test
    void able_to_edit_description_of_item_within_existing_todo_list() {
        //create item of type SerItem with a description
        SerItem item = new SerItem("1", LocalDate.of(2020, 9, 18).toString(), false);

        //change the description with setter
        item.setSerDescription("2");

        //set actual of type String to getter of the item description
        String actual = item.getSerDescription();
        //set expected of type String to given new description
        String expected = "2";

        //assert that actual and expected are equal
        assertEquals(actual, expected);
    }

    @Test
    void able_to_edit_due_date_of_item_within_existing_todo_list() {
        //create item of type SerItem with a due date
        SerItem item = new SerItem("1", LocalDate.of(2020, 9, 18).toString(), false);

        //change due date with setter
        item.setSerDueDate(LocalDate.of(2022, 9, 18).toString());

        //set actual of type String to getter of the item due date
        String actual = item.getSerDueDate();
        //set expected of type String to given new due date
        String expected = "2022-09-18";

        //assert that actual and expected are equal
        assertEquals(actual,expected);
    }

    @Test
    void able_to_mark_item_in_todo_list_as_complete() {
        //create item of type SerItem with status unComplete
        SerItem item = new SerItem("1", LocalDate.of(2020, 9, 18).toString(), false);
        //call setter and pass in status of complete
        item.setSerIsCompleted(true);

        //set actual to getter of the item status
        boolean actual = item.getSerIsCompleted();
        //set expected to complete
        boolean expected = true;

        //assert that actual and expected are equal
        assertEquals(actual, expected);

    }

    @Test
    void able_to_display_all_items_in_todo_list() {
        //create ToDoListFunctions object
        ToDoListFunctions toDoListFunctions = new ToDoListFunctions();
        //create a list of items
        List<SerItem> list = new ArrayList<>();
        SerItem item1 = new SerItem("2", LocalDate.of(2021, 7, 1).toString(), true);
        SerItem item2 = new SerItem("3", LocalDate.of(2022, 8, 3).toString(), false);
        SerItem item3 = new SerItem("4", LocalDate.of(2023, 6, 22).toString(), true);
        list.add(item1);
        list.add(item2);
        list.add(item3);

        //set actual of type integer to viewAll.size
        int actual = toDoListFunctions.viewAll(list).size();
        //set expected of type integer to the number of items in list
        int expected = 3;

        //assert that actual and expected are equal
        assertEquals(actual, expected);
    }

    @Test
    void able_to_display_only_completed_items_in_todo_list() {
        //create ToDoListFunctions object
        ToDoListFunctions toDoListFunctions = new ToDoListFunctions();
        //create a list of items with completed and uncompleted
        List<SerItem> list = new ArrayList<>();
        SerItem item1 = new SerItem("2", LocalDate.of(2021, 7, 1).toString(), true);
        SerItem item2 = new SerItem("3", LocalDate.of(2022, 8, 3).toString(), false);
        SerItem item3 = new SerItem("4", LocalDate.of(2023, 6, 22).toString(), true);
        list.add(item1);
        list.add(item2);
        list.add(item3);

        //set actual of type integer to filteredView.size
        int actual = toDoListFunctions.filteredView(list, true).size();
        //set expected of type integer to the number of completed items in list
        int expected = 2;

        //assert that actual and expected are equal
        assertEquals(actual, expected);
    }

    @Test
    void able_to_display_only_uncompleted_items_in_todo_list() {
        //create ToDoListFunctions object
        ToDoListFunctions toDoListFunctions = new ToDoListFunctions();
        //create a list of items with completed and uncompleted
        List<SerItem> list = new ArrayList<>();
        SerItem item1 = new SerItem("2", LocalDate.of(2021, 7, 1).toString(), true);
        SerItem item2 = new SerItem("3", LocalDate.of(2022, 8, 3).toString(), false);
        SerItem item3 = new SerItem("4", LocalDate.of(2023, 6, 22).toString(), true);
        list.add(item1);
        list.add(item2);
        list.add(item3);

        //set actual of type integer to filteredView.size
        int actual = toDoListFunctions.filteredView(list, false).size();
        //set expected of type integer to the number of uncompleted items in list
        int expected = 1;

        //assert that actual and expected are equal
        assertEquals(actual, expected);
    }

    @Test
    void able_to_save_all_items_to_external_storage() throws IOException {
        //create FileFunctions object
        FileFunctions fileFunctions = new FileFunctions();

        //create list
        ArrayList<SerItem> list = new ArrayList<>();
        SerItem item1 = new SerItem("2", LocalDate.of(2021, 7, 1).toString(), true);
        SerItem item2 = new SerItem("3", LocalDate.of(2022, 8, 3).toString(), false);
        SerItem item3 = new SerItem("4", LocalDate.of(2023, 6, 22).toString(), true);
        list.add(item1);
        list.add(item2);
        list.add(item3);

        //create file in project directory
        File file = File.createTempFile("TEST1", ".json", null);
        //call saveFile
        fileFunctions.saveFile(list, file);

        //set actual of type integer to the size of the list of items read in
        boolean actual = file.exists();
        file.deleteOnExit();
        //set expected of type integer to the actual number of items given
        boolean expected = true;

        //assert that actual and expected are equal
        assertEquals(actual,expected);

    }

    @Test
    void able_to_load_list_from_external_storage() throws IOException {
        //create FileFunctions object
        FileFunctions fileFunctions = new FileFunctions();

        //create list
        ArrayList<SerItem> list = new ArrayList<>();
        SerItem item1 = new SerItem("2", LocalDate.of(2021, 7, 1).toString(), true);
        SerItem item2 = new SerItem("3", LocalDate.of(2022, 8, 3).toString(), false);
        SerItem item3 = new SerItem("4", LocalDate.of(2023, 6, 22).toString(), true);
        list.add(item1);
        list.add(item2);
        list.add(item3);

        //create file in project directory
        File file = File.createTempFile("TEST1", ".json", null);
        //call saveFile
        fileFunctions.saveFile(list, file);

        //load file where items were saved
        List<SerItem> loadedList = fileFunctions.loadFromPrevious(file);

        //set actual of type integer to the size of the list of items read in
        int actual = loadedList.size();
        file.deleteOnExit();
        //set expected of type integer to the actual number of items given
        int expected = 3;

        //assert that actual and expected are equal
        assertEquals(actual,expected);
    }

}