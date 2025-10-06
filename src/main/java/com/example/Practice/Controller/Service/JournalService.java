package com.example.Practice.Controller.Service;

import com.example.Practice.Controller.Entity.Journal;
import com.example.Practice.Controller.Repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

//Controller --> Service --> Repository --> Database

@Component
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    //Post Mapping
    public String saveEntry(Journal myjournal){
        if(journalRepository.existsById(myjournal.getId())){
            return "Id already exists !!";
        }
        else {
            journalRepository.save(myjournal);
            return "Entry has been added !";
        }
    }

    //Get Mapping
    public List<Journal> getAllEntries(){
        return new ArrayList<>(journalRepository.findAll());
    }

    //Find list of Entries by Id
    public ResponseEntity<?> getElementById(List<Integer> ids){
        if(journalRepository.findAllById(ids).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id Doesn't exists !");
        }
        else {
            return ResponseEntity.ok().body(journalRepository.findAllById(ids));
        }
    }

    //If I want to see Journal with a list of names
    public ResponseEntity<?> getEntriesByNames(List<String> names){
        if(journalRepository.findAllByNameInIgnoreCase(names).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Name doesn't exist !");
        }
        else {
            return ResponseEntity.ok().body(journalRepository.findAllByNameInIgnoreCase(names));
        }
    }

    //Finding Name with a particular name
    //I will take it as a Path variable as we will only find for one name at a time

    public ResponseEntity<?> getEntryByOneNameOnly(String name) {
        if (journalRepository.findAllByName(name).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Entry with this Name");
        } else {
            return ResponseEntity.ok().body(journalRepository.findAllByName(name));
        }
    }

    public ResponseEntity<?> deleteEntryById(List<Integer> id){
        if(journalRepository.findAllById(id).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id doesn't exist !!");
        }
        else {
            journalRepository.deleteAllById(id);
            return ResponseEntity.ok().body("Id has been deleted !");
        }
    }

    public ResponseEntity<?> updateJournalById(Journal myjournal){
        if(journalRepository.findAllById(myjournal.getId()).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id doesn't exists !");
        }
        else {
            journalRepository.save(myjournal);
            return ResponseEntity.ok().body("Id has been updated !!");
        }
    }

}
