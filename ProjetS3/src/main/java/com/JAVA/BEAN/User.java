package com.JAVA.BEAN;

public class User {
	public String id;
	public String name;
	public String firstName;
	public String email;
	private String password;
	
	public User(String name, String fname, String email, String passwd) {
		this.name = name;
		this.firstName = fname;
		this.email = email;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
    // Setter pour l'id
    public void setId(String id) {
        this.id = id;
    }
	
    public String getId() {
		return this.id = id;
	}

    
    public void seConnecter() {
      
    }

    public void seDeconnecter() {
    }

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
		
	}
	
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String fname) {
		this.firstName = fname;
		
	}

	public String getEmail() {
		return this.email;
	}

	
	public void setEmail(String email) {
		this.email = email;
		
	}

	public void setPassword(String passwd) {
		this.password = passwd;
		
	}
	
	public String getPassword() {
        return this.password;
    }


}
