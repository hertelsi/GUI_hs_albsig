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

	@SuppressWarnings("unchecked")
	@Override
	public Collection<BuyingList> getAllBuyingLists() {
		return em.createQuery("SELECT b FROM BuyingList b").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Article> getAllArticles() {
		return em.createQuery("SELECT a FROM Article a").getResultList();
	}

	@Override
	public long getCountArticles() {
		return (long) em.createQuery("SELECT count(*) FROM Article a").getSingleResult();
	}

	@Override
	public Article getArticle(long id) throws NoSuchRowException {
		Article article = em.find(Article.class, id);
		if (article != null)
			return article;
		else
			throw new NoSuchRowException();
	}
	
	@Override
	public long saveArticle(Article article) {
		Article a = em.find(Article.class, article.getId());

		if (a != null) {
			a.copyData(article); // a := article (deep copy)
			em.merge(a);
			return a.getId();
		} else
			em.persist(article);
		
		return article.getId();
	}
	
	@Override
	public void removeArticle(long primaryKey) throws NoSuchRowException {
		Article a = em.find(Article.class, primaryKey);
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
			b.copyData(buyingList); // b := building (deep copy)
			em.merge(b);
			return b.getId();
		} else
			em.persist(buyingList);
		
		return buyingList.getId();
	}
	

	@Override
	public void removeBuyingList(long id) throws NoSuchRowException {
		//em.createQuery("Delete FROM BuyingList where id=?1").setParameter(1, id);
		BuyingList b  = em.find(BuyingList.class, id);
		if (b != null) {
			System.out.println("Buyinglist found");
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
			System.out.print("-----" + user);
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
	public User getUser(long id) throws NoSuchRowException {
		User user= em.find(User.class, id);
		if (user != null)
			return user;
		else
			throw new NoSuchRowException();
	}
	
	@Override
	public long saveUser(User user) {
		User u= em.find(User.class, user.getId());

		if (u != null) {
			u.copyData(user); // u := user (deep copy)
			em.merge(u);
			return u.getId();
		} else
			em.persist(user);
		
		return user.getId();
	}
	
	@Override
	public void removeUser(long primaryKey) throws NoSuchRowException {
		User u = em.find(User.class, primaryKey);
		if (u != null)
			em.remove(u);
		else
			throw new NoSuchRowException();
	}

}
