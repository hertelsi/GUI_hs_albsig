package de.hsalbsig.inf.dea.model;

import java.util.Collection;

import javax.ejb.Remote;

@Remote
public interface InfrastructureRemote {
	public Collection<BuyingList> getAllBuyingLists();

	public BuyingList getBuyingList(long id) throws NoSuchRowException;

	public long saveBuyingList(BuyingList list);

	public void removeBuyingList(long id) throws NoSuchRowException;
	
	public long saveUser(User user);

	public void removeUser(long primaryKey) throws NoSuchRowException;
	
	public User getUser(String name, String password) throws NoSuchRowException;
	
	public User getUser(String name) throws NoSuchRowException;
	
	public User getUser(long id) throws NoSuchRowException;
	
	public Collection<Article> getAllArticles();

	public long getCountArticles();

	public Article getArticle(long id) throws NoSuchRowException;
	
	public long saveArticle(Article article);

	public void removeArticle(long primaryKey) throws NoSuchRowException;
}
