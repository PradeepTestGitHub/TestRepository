package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;  
 


@Entity
public class Persons {

	/**
	 * 
	 */
	@Id
	private Long id;
	private String firstName;
	private String lastName;
	private int contactNo;
	private String email;
	private String gender;
	private String aboutMe;
	
	public Persons(){
	}

	public Persons(Long id, String firstName, String lastName, int contactNo, String email, String gender,
			String aboutMe) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNo = contactNo;
		this.email = email;
		this.gender = gender;
		this.aboutMe = aboutMe;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getContactNo() {
		return contactNo;
	}
	public void setContactNo(int contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAboutMe() {
		return aboutMe;
	}
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
	
	@Override
	public String toString() {
		return "Persons [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", contactNo=" + contactNo
				+ ", email=" + email + ", gender=" + gender + ", aboutMe=" + aboutMe + "]";
	}
	
}
