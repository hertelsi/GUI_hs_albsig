package de.hsalbsig.inf.dea.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

// @SuppressWarnings("serial")
@Entity
@Table(name = "einkaufsliste")
public class BuyingList implements Serializable {
	//private static final long serialVersionUID = -352914445303824595L;
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "erstellungsDatum")
	private Date creationDate;
	
	@Column(name = "einkaufsDatum")
	private Date buyingDate;

	@OneToMany(mappedBy = "buyingList", fetch=FetchType.EAGER, cascade=CascadeType.REMOVE)
	@JsonManagedReference
	private Collection<Article> allArticles;

	public BuyingList() {
	}

	public BuyingList(String name, Date creationDate, Date buyingDate) {
		this.name = name;
		this.creationDate = creationDate;
		this.buyingDate = buyingDate;
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

	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date date) {
		this.creationDate = date;
	}
	
	
	public Date getBuyingDate() {
		return buyingDate;
	}

	public void setBuyingDate(Date date) {
		this.buyingDate = date;
	}

	
	public Collection<Article> getAllArticles() {
		return allArticles;
	}

	public void setAllArticles(Collection<Article> allArticles) {
		this.allArticles = allArticles;
	}

	public void addOneArticle(Article article) {
		if (allArticles == null)
			allArticles = new ArrayList<Article>();
		article.setBuyingList(this);

		allArticles.add(article);
	}
	

	public void copyData(BuyingList copy) {
		this.id = copy.id;
		this.name = copy.name;
		this.creationDate= copy.creationDate;
		this.buyingDate = copy.buyingDate;
		this.allArticles = copy.allArticles;
	}

	@Override
	public String toString() {
		return "EinkaufsListe [id=" + id + ", name=" + name + ", creationDate=" + creationDate+ ", buyingDate=" + buyingDate+ "]";
	}

}
