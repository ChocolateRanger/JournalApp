package com.example.Practice.Controller.Controller;
import com.example.Practice.Controller.Entity.Journal;
import com.example.Practice.Controller.Repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
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


    //Post entries using Post mapping
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
}
