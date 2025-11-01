package com.example.Practice.Controller.Repository;

import com.example.Practice.Controller.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Integer> {

}
