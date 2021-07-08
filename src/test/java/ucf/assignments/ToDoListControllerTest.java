/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ivy Nguyen
 */
package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListControllerTest {

    @Test
    void able_to_add_new_item_to_existing_todo_list() {
        //create an item of type Item
        //create a list of type Item with items already in list

        //set actual of type integer to addItem.size
        //set expected of type integer to what size of list should be

        //assert that actual and expected are equal
    }

    @Test
    void able_to_remove_item_to_existing_todo_list() {
        //create a list of type observable list of items
        //create a list of type observableList of items to be deleted

        //set actual of type integer to deleteItems.size
        //set expected of type integer to what size the remaining list should be

        //assert that actual and expected are equal
    }

    @Test
    void able_to_edit_description_of_item_within_existing_todo_list() {
        //create item of type Item with a description
        //call setChangedDescription and pass in new description

        //set actual of type String to getter of the item description
        //set expected of type String to given new description

        //assert that actual and expected are equal
    }

    @Test
    void able_to_edit_due_date_of_item_within_existing_todo_list() {
        //create item of type Item with a due date
        //call setChangedDescription and pass in new due date

        //set actual of type LocalDate to getter of the item due date
        //set expected of type LocalDate to given new due date

        //assert that actual and expected are equal

    }

    @Test
    void able_to_mark_item_in_todo_list_as_complete() {
        //create item of type Item with status unComplete
        //call setChangedStatus and pass in status of complete

        //set actual to getter of the item status
        //set expected to complete

        //assert that actual and expected are equal

    }

    @Test
    void able_to_display_all_items_in_todo_list() {
        //create a list of items

        //set actual of type integer to viewAll.size
        //set expected of type integer to the number of items in list

        //assert that actual and expected are equal
    }

    @Test
    void able_to_display_only_completed_items_in_todo_list() {
        //create a list with completed and uncompleted items

        //set actual of type integer to viewCompletedOnly.size
        //set expected of type integer to the number of completed items in list

        //assert that actual and expected are equal
    }

    @Test
    void able_to_display_only_uncompleted_items_in_todo_list() {
        //create a list with completed and uncompleted items

        //set actual of type integer to viewUncompletedOnly.size
        //set expected of type integer to the number of uncompleted items in list

        //assert that actual and expected are equal
    }

    @Test
    void able_to_save_all_items_to_external_storage() {
        //create ToDoList controller object and initialize observableList with some items
        //call saveChanges
        //load file where items were saved
        //read file into a list of items

        //set actual of type integer to the size of the list of items read in
        //set expected of type integer to the actual number of items given

        //assert that actual and expected are equal

    }


}