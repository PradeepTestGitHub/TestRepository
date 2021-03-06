package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.RequestBody;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Persons;

import java.util.List;
import java.util.Optional;  
 

@RestController  
public class PersonsController {

	@Autowired  
    private Personsservice userService;   
    @RequestMapping("/")  
    public List<Persons> getAllUser(){  
        return userService.getAllUsers();  
    }     
    @RequestMapping(value="/add-user", method=RequestMethod.POST)  
    public void addUser(@RequestBody Persons userRecord){  
    	userService.addUser(userRecord);  
    }  
    @RequestMapping(value="/user/{id}", method=RequestMethod.GET)  
    public Optional<Persons> getUser(@PathVariable String id){
        return userService.getUser(Long.valueOf(id));  
    }
}
