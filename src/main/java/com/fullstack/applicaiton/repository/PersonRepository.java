package com.fullstack.applicaiton.repository;

import com.fullstack.applicaiton.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> { // {It accepts two types of entity the type of the entity and type of the primary key}
//JPA has basically pre-defined methods like findAll,save,delete,findById
}
