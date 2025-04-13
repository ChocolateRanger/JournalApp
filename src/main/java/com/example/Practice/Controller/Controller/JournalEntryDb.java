package com.example.Practice.Controller.Controller;
import com.example.Practice.Controller.Entity.Journal;
import com.example.Practice.Controller.Repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")

public class JournalEntryDb {

    /*
    Now Since we are using Entity, we will do the crud operations using
    JPA interface. So syntax will be changed for all the operation as we will
    use JPA methods directly
     */

    @Autowired
    private JournalRepository journalRepository;


    //Post-entries using Post mapping
    @PostMapping
    public String setAllEntries(@RequestBody Journal myEntry){
        if(journalRepository.existsById(myEntry.getId())){
            return "Id already exists !";
        }
        else {
            journalRepository.save(myEntry);
            return "Entry has been added";
        }
    }

    //Get All objects through Get Call
    @GetMapping
    public List<Journal> getAllEntries(){
        return journalRepository.findAll();
    }

    //If we want to find list of integer by Id
    //I can pass multiple ids here Ex - http://localhost:9090/journal/Id?ids=1&ids=2
    @GetMapping("/Id")
    public List<Journal> getEntryByIds(@RequestParam List<Integer> ids){
        return journalRepository.findAllById(ids);
    }

    //If I want to see Journal with a list of names
    @GetMapping("/Names")
    public ResponseEntity<?> getJournalsByNames(@RequestParam List<String> names) {
        List<Journal> journals = journalRepository.findAllByNameInIgnoreCase(names);
        if (journals.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No entries found with the given names.");
        }
        return ResponseEntity.ok(journals);
    }

    //Finding Name with a particular name
    //I will take it as a Path variable as we will only find for one name at a time
    @GetMapping("/Name/{name}")
    public ResponseEntity<?> getEntryByOneName(@PathVariable String name) {
        if (journalRepository.findAllByName(name).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Entry with this Name");
        } else {
            return ResponseEntity.ok().body(journalRepository.findAllByName(name));
        }
    }

    //Now I want to add Update Mapping
    //One way is to do using Post mapping
    //Other is to do using Put Mapping
    //Here I have not allowed duplicate call on Post mapping, so I will use Put to update if any.

    //I can also allow to save with relevant message but I only want to update
    //If Id exists.
    @PutMapping("Update")
    public ResponseEntity<?> updateJournalById(@RequestBody Journal myEntry){
        if(journalRepository.findAllById(myEntry.getId()).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID " + myEntry.getId() + " doesn't exist !");
        }
        else {
            journalRepository.save(myEntry);
            return ResponseEntity.ok().body("Id has been Updated");
        }
    }

    @DeleteMapping
    public String deleteEntryById(@RequestParam List<Integer> ids){
        if(journalRepository.findAllById(ids).isEmpty()){
            return "Id "+ids+" doesn't exist !";
        }
        else {
            journalRepository.deleteAllById(ids);
            return "Id "+ids+" has been deleted";
        }
    }
}
