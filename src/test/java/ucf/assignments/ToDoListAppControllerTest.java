/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ivy Nguyen
 */
package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListAppControllerTest {

    @Test
    void able_to_add_a_new_todo_list() {
        //create a ToDoList object
        //set a list name
        //create a list of ToDoList of type ObservableList with some lists set

        //set actual value of type integer to addNewListToLists.size
        //set expected value of type integer to size of list given + 1

        //assert that actual and expected are equal
    }

    @Test
    void able_to_remove_existing_todo_list() {
        //create an ObservableList of lists with some lists set
        //create a list of type ObservableList of lists to be deleted

        //set actual value of type integer to deleteLists.size
        //set expected value of type integer to size of list given - (however many lists to be deleted)

        //assert that actual and expected are equal
    }

    @Test
    void able_to_edit_title_of_existing_todo_list() {
        //create a ToDoList object with a list name set
        //call changeListName and pass a new name

        //set actual to the name returned by list name getter
        //set expected to the new name

        //assert that actual and expected are equal
    }

    @Test
    void able_to_save_all_items_across_all_todo_lists_to_external_storage() {
        //create an ObservableList of type ToDoList, setting the name, items array, file location, and items file
        //call saveAllLists send in the ObservableList and the file that will save the lists
        //read back in serialized file to a list

        //set actual of integer to the size of the list that was read in
        //set expected of type integer to actual size of list

        //assert that actual and expected are equal
    }

    @Test
    void able_to_load_single_to_do_list_previously_saved_from_external_storage() {
        //create a serialized file with one list in list manager folder

        //set actual of type integer to loadFromPrevious.size
        //set expected of type integer to 1

        //assert that actual and expected are equal
    }

    @Test
    void able_to_load_multiple_to_do_list_previously_saved_from_external_storage() {
        //create a serialized file with n number lists in list manager folder

        //set actual of type integer to loadFromPrevious.size
        //set expected of type integer to n

        //assert that actual and expected are equal
    }
}