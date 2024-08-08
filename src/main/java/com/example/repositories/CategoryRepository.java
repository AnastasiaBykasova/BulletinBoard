package com.example.repositories;

//import com.example.models.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

//
//import com.example.models.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//public interface UserRepository extends CrudRepository<User, Long> {
//}

import com.example.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}