package de.hsalbsig.inf.dea.model;

import java.util.Collection;

import javax.ejb.Remote;

@Remote
public interface InfrastructureRemote {

	public BuyingList getBuyingList(long id) throws NoSuchRowException;

	public long saveBuyingList(BuyingList list);

	public void removeBuyingList(long id) throws NoSuchRowException;
	
	public long saveUser(User user);
	
	public User getUser(String name, String password) throws NoSuchRowException;
	
	public User getUser(String name) throws NoSuchRowException;
	
	public long saveArticle(Article article);

	public void removeArticle(long id) throws NoSuchRowException;
}
