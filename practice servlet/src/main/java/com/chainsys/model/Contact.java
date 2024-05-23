package com.chainsys.model;

public class Contact {
	private int id;
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	private String name;
    private String phoneNumber;
    private String email;
    private int userId;

    public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	// Constructor
    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    public Contact(String name, String phoneNumber, String email,int userId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userId=userId;
    }


    public Contact(int id, String name, String phoneNumber, String email, int userId) {
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Contact [name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email + "]";
	}

	// Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

