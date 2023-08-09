package com.edu.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "User")
public class User {

	// or String, UUID etc. based on your application
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phoneNumber;
	// Note: Always save hashed version of password, not plain text
	// e.g., "admin", "user", "guest"
	private String accountType;
	
	

	public User(int userId, String username, String firstName, String lastName, String email, String password,
			String phoneNumber, String accountType) {
		super();
		this.userId = userId;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.accountType = accountType;
	}

	public User(String username, String firstName, String lastName, String email, String password, String phoneNumber,
			String accountType) {
		super();
		this.phoneNumber = phoneNumber;
		this.lastName = lastName;
		this.password = password;
		this.accountType = accountType;
		this.email = email;
		this.firstName = firstName;
		this.username = username;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", username=" + username + ", password=" + password
				+ ", accountType=" + accountType + "]";
	}

	public void saveUser(User user, String username, String firstname, String lastname, String email, String password,
			String number, String accountType) {
		this.username = username;
		this.firstName = firstname;
		this.lastName = lastname;
		this.email = email;
		this.password = password;
		this.phoneNumber = number;
		this.accountType = accountType;
	}

}
