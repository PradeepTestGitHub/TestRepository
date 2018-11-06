package com.example.demo;

import com.example.demo.model.Persons;  
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;



@Service 
public class Personsservice {
	 
	    @Autowired  
	    private static PersonsRepository userRepository;  
	    
	    public List<Persons> getAllUsers(){  
	        List<Persons>userRecords = new ArrayList<>();  
	        userRepository.findAll().forEach(userRecords::add);  
	        return userRecords;  
	    }  
	    public Optional<Persons> getUser(Long id){  
	        return userRepository.findById(id);  
	    }  
	    public void addUser(Persons userRecord){  
	    	userRepository.save(userRecord);  
	    }  
	    public void delete(Long id){  
	    	userRepository.deleteById(id);  
	    }  
	}  

