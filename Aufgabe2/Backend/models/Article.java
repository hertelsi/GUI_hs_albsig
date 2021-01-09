package de.hsalbsig.inf.dea.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

// @SuppressWarnings("serial")
@Entity
@Table(name = "artikel")
public class Article implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private long id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "mengeEinheit")
	private String unit;
	
	@Column(name = "menge")
	private long amount;

	@ManyToOne
	@JoinColumn(name = "einkaufslisteId")
	@JsonBackReference
	private BuyingList buyingList;

	public Article() {
	}

	public Article(String name, BuyingList buyingList, String unit, long amount) {
		this.name = name;
		this.buyingList = buyingList;
		this.unit = unit;
		this.amount = amount;
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

	public void copyData(Article copy) {
		this.id = copy.id;
		this.name = copy.name;
	}

	public BuyingList getBuyingList() {
		return buyingList;
	}

	public void setBuyingList(BuyingList buyingList) {
		this.buyingList = buyingList;
	}
	

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", name=" + name + ", unit=" + unit + ", amount=" + amount + ", buyingList="
				+ buyingList + "]";
	}

}
