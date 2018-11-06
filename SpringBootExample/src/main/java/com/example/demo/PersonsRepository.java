package com.example.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository; 
import com.example.demo.model.*;

@Repository
public interface PersonsRepository extends CrudRepository<Persons, Long> {
	
	 //public List<Persons> findById(long Id);
	

}