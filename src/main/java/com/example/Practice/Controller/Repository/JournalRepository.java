package com.example.Practice.Controller.Repository;
import com.example.Practice.Controller.Entity.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<Journal, Integer> {
}
