package de.hsalbsig.inf.dea.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

// @SuppressWarnings("serial")
@Entity
@Table(name = "benutzer")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private long id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "passwort")
	private String password;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JsonManagedReference
	private Collection<BuyingList> allBuyingLists;
	
	public User() {
	}

	public User(Long id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public Collection<BuyingList> getAllBuyingLists() {
		return allBuyingLists;
	}

	public void setAllBuyingLists(Collection<BuyingList> allBuyingLists) {
		this.allBuyingLists = allBuyingLists;
	}

	public void addOneBuyingList(BuyingList buyingList) {
		if (allBuyingLists == null)
			allBuyingLists = new ArrayList<BuyingList>();
		
		System.out.println( "User:: addOneBuyingList:: buyingList = "+ buyingList );

		allBuyingLists.add(buyingList);
	}
	

	public void copyData(User copy) {
		this.id = copy.id;
		this.name = copy.name;
		this.password = copy.password;
		this.allBuyingLists = copy.allBuyingLists;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + "]";
	}

}
