package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	// need an autoincrement field
	// alter table users change idusers idusers int (11) AUTO_INCREMENT NOT NULL;
	private int idusers;

	private String name;

	private String surename;

	public User() {
	}
	
	

	public User(int idusers, String name, String surename) {
		super();
		this.idusers = idusers;
		this.name = name;
		this.surename = surename;
	}



	public User(String name, String surename) {
		super();
		this.name = name;
		this.surename = surename;
	}



	public int getIdusers() {
		return this.idusers;
	}

	public void setIdusers(int idusers) {
		this.idusers = idusers;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurename() {
		return this.surename;
	}

	public void setSurename(String surename) {
		this.surename = surename;
	}

}