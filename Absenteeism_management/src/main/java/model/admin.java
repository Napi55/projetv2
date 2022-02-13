package model;

public class admin {
	private int id;
	private String firstname;
	private String lastname;
	private String email;
	private String username;
	private String password;
	public admin() {}
		public admin( String firstname, String lastname, String email, String username, String password) {
			
			this.firstname = firstname;
			this.lastname = lastname;
			this.email = email;
			this.username = username;
			this.password = password;
		}
	public admin(int id, String firstname, String lastname, String email, String username, String password) {
			this.id=id;
			this.firstname = firstname;
			this.lastname = lastname;
			this.email = email;
			this.username = username;
			this.password = password;
		}
	public int getId() {
		return id;
	}
	public void setId(int id2) {
		this.id = id2;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	
}
