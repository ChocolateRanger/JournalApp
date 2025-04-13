package com.example.Practice.Controller.Repository;
import com.example.Practice.Controller.Entity.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
Here whatever new method we declare will be creating a query.
I did a mistake by keeping random name of methods which I need to take care
as every word in the method means something.
For understanding more, read more about JPA.
 */
public interface JournalRepository extends JpaRepository<Journal, Integer> {
    List<Journal> findAllByNameInIgnoreCase(List<String> names);
    List<Journal> findAllByName(String name);
    List<Journal> findAllById(Integer id);

}
