package com.example.Practice.Controller.Controller;


import com.example.Practice.Controller.Entity.Journal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/...journal")
public class JournalEntry {
    private Map<Integer, Journal> journalEntries = new HashMap<>();

    //Post entries using Post mapping
    @PostMapping
    public String setAllEntries(@RequestBody Journal myEntry){
        if(journalEntries.containsKey(myEntry.getId())){
            journalEntries.get(myEntry.getId()).setDescription(myEntry.getDescription());

            return ("Id "+myEntry.getId()+" has been updated");
        }
        else {
            journalEntries.put(myEntry.getId(), myEntry);
            return ("Id " + myEntry.getId()+" has been inserted");
        }
    }

    //Get All objects through Get Call
    @GetMapping
    public List<Journal> getAllEntries(){
        return new ArrayList<>(journalEntries.values());
    }

    //Adding Path variable to get entries by Id
    //Used Response Entity because i wanted to return an object for the true value and
    //relevant error message if not found
    @GetMapping("/id/{myId}")
    public ResponseEntity<?> getEntryById(@PathVariable Integer myId){
        if(journalEntries.containsKey(myId)) {
            return ResponseEntity.ok(journalEntries.get(myId));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    //Using Request Parameter (Sending id as parameter)
    @GetMapping("/id")
    public Journal getEntryByIdRequest(@RequestParam Integer id){
        return journalEntries.get(id);
    }

    //It Adds or Updates an existing object
    @PutMapping
    public String AddEntry(@RequestBody Journal myEntry){
        if (journalEntries.containsKey(myEntry.getId())){
            journalEntries.put(myEntry.getId(), myEntry);
            return "Id " + myEntry.getId() + " has been updated !";
        }
        else {
            return "Entry doesn't Exist!!";
        }
    }

    //Deleting any entry from Data using id (Request Parameter)
    @DeleteMapping("/id")
    public String deleteEntryById(@RequestParam Integer id){
        if (journalEntries.containsKey(id)) {
            journalEntries.remove(id);
            return ("id " + id + " has been removed");
        }
        else {
            return "id " + id + " doesn't exists";
        }
    }

    /*
    //Deleting any entry from Data using id
    @DeleteMapping("/id")
    public String deleteEntryByIdCheck(@RequestParam Integer id){
        journalEntries.remove(id);
        return ("id" + id + " has been removed");
    }

     */

}
