package de.hsalbsig.inf.dea.model;

import java.util.Collection;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class Infrastructure implements InfrastructureRemote {
	@PersistenceContext(unitName = "EinkaufslisteEJB")
	private EntityManager em;

	public Infrastructure() {
	}
	
	@Override
	public long saveArticle(Article article) {
		Article a = em.find(Article.class, article.getId());

		if (a != null) {
			a.copyData(article);
			em.merge(a);
			return a.getId();
		} else
			em.persist(article);
		
		return article.getId();
	}
	
	@Override
	public void removeArticle(long id) throws NoSuchRowException {
		Article a = em.find(Article.class, id);
		if (a != null)
			em.remove(a);
		else
			throw new NoSuchRowException();
	}

	@Override
	public BuyingList getBuyingList(long id) throws NoSuchRowException {
		BuyingList buyingList = em.find(BuyingList.class, id);
		if (buyingList != null)
			return buyingList;
		else
			throw new NoSuchRowException();
	}

	@Override
	public long saveBuyingList(BuyingList buyingList) {
		BuyingList b = em.find(BuyingList.class, buyingList.getId());

		if (b != null) {
			b.copyData(buyingList);
			em.merge(b);
			return b.getId();
		} else
			em.persist(buyingList);
		
		return buyingList.getId();
	}
	

	@Override
	public void removeBuyingList(long id) throws NoSuchRowException {
		BuyingList b  = em.find(BuyingList.class, id);
		if (b != null) {
			for (Article a : b.getAllArticles()) {
				em.remove(a);
			}
			Collection<User> users = em.createQuery("Select u from User u").getResultList();
			for (User u : users) {
		        u.getAllBuyingLists().remove(b);
		        saveUser(u);
		    }
			em.remove(b);
		}
		else
			throw new NoSuchRowException();
	}
	
	
	@Override
	public User getUser(String name, String password) throws NoSuchRowException {
		User user = null;
		try {
			user= (User) em.createQuery("SELECT u FROM User u where name=?1").setParameter(1, name).getSingleResult();
			if(!user.getPassword().equals(password))
				return null;
		} catch (Exception ex)
		{
			System.out.println(ex.toString());
		}
		
		return user;
	}
	
	
	@Override
	public User getUser(String name) throws NoSuchRowException {
		User user = null;
		try {
			user= (User) em.createQuery("SELECT u FROM User u where name=?1").setParameter(1, name).getSingleResult();
		} catch (Exception ex)
		{
			System.out.println(ex.toString());
		}
		
		return user;
	}
	
	@Override
	public long saveUser(User user) {
		User u= em.find(User.class, user.getId());

		if (u != null) {
			u.copyData(user);
			em.merge(u);
			return u.getId();
		} else
			em.persist(user);
		
		return user.getId();
	}
}
