package com.example.Practice.Controller.Controller;
import com.example.Practice.Controller.Dto.JournalEntryDto;
import com.example.Practice.Controller.Entity.Journal;
import com.example.Practice.Controller.Repository.JournalRepository;
import com.example.Practice.Controller.Service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private JournalService journalService;

    //Post-entries using Post mapping
    @PostMapping
    public String setAllEntries(@RequestBody JournalEntryDto myEntry){
        return journalService.saveEntry(myEntry);
        }

    //Get All objects through Get Call
    @GetMapping
    public List<Journal> getAllEntries(){
        return journalService.getAllEntries();
    }

//    //If we want to find list of integer by Id
//    //I can pass multiple ids here Ex - http://localhost:9090/journal/Id?ids=1&ids=2
    @GetMapping("/Id")
    public ResponseEntity<?> getEntryByIds(@RequestParam List<Integer> ids){
        return journalService.getElementById(ids);
    }

    //If I want to see Journal with a list of names
    @GetMapping("/Names")
    public ResponseEntity<?> getJournalsByNames(@RequestParam List<String> names) {
        return journalService.getEntriesByNames(names);
    }

    //Finding Name with a particular name
    //I will take it as a Path variable as we will only find for one name at a time
    @GetMapping("/Name/{name}")
    public ResponseEntity<?> getEntryByOneName(@PathVariable String name) {
        return journalService.getEntryByOneNameOnly(name);
    }
//
//    @GetMapping("/Names/Id")
//    public ResponseEntity<?> getEntryByNameAndId(@RequestParam String name,int id){
//        if(journalRepository.findAllByNameAndId(name,id).isEmpty()){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Name and Id doesn't exist !");
//        }
//        else
//        {
//            return ResponseEntity.ok().body(journalRepository.findAllByNameAndId(name,id));
//        }
//    };
//
//    //Now I want to add Update Mapping
//    //One way is to do using Post mapping
//    //Other is to do using Put Mapping
//    //Here I have not allowed duplicate call on Post mapping, so I will use Put to update if any.
//
    //I can also allow to save with relevant message but I only want to update
    //If Id exists.
    @PutMapping("Update")
    public ResponseEntity<?> updateJournalById(@RequestBody Journal myEntry){
        return journalService.updateJournalById(myEntry);
    }

//    http://localhost:9090/journal?ids=21

    @DeleteMapping
    public ResponseEntity<?> deleteEntryById(@RequestParam List<Integer> ids){
        return journalService.deleteEntryById(ids);
    }
}
