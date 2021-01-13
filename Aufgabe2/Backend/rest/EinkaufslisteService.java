package rest;

import de.hsalbsig.inf.dea.model.*;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
//import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import java.sql.Date;
import java.util.Collection;

@Path("buyingList")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@RequestScoped
public class EinkaufslisteService implements java.io.Serializable {
	private static final long serialVersionUID = 1545633471206295064L;
	private static final String EJBNAME = "java:global/EinkaufslisteEJB/Infrastructure!de.hsalbsig.inf.dea.model.InfrastructureRemote";

	@EJB(mappedName = EJBNAME)
	private InfrastructureRemote infrastructureRemote;

	@POST
	@Produces({ "application/json" })
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/articles")
	public Article createArticle( 
			@FormParam("name") String name, 
			@FormParam("unit") String unit,
			@FormParam("amount") long amount,
			@FormParam("buyingListId") long buyingListId) {
	
		BuyingList b = null;
		Date d, d2;
		Article a = null;
		
		try {
			b = infrastructureRemote.getBuyingList(buyingListId);
			a = new Article(name, b, unit, amount);
			a.setId(infrastructureRemote.saveArticle(a));
			b.addOneArticle(a);
			infrastructureRemote.saveBuyingList(b);
		} catch (Exception e) {
			throw new NotFoundException();
		}
		
		return a;
	}
	
	@POST
	@Produces({ "application/json" })
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/buyingLists/user")
	public Response addUserToBuyingList(
			@FormParam("username") String name, 
			@FormParam("buyingListId") long buyingListId) throws NoSuchRowException {
		
		boolean success;
		try {
			BuyingList buyingList = infrastructureRemote.getBuyingList(buyingListId);
			User user = infrastructureRemote.getUser(name);
			success = false;
			if ((user != null) && (buyingList != null)){
				user.addOneBuyingList(buyingList);
				infrastructureRemote.saveUser(user);
				success = true;
			}
			else{
				throw new NotFoundException();
			}
		} catch (Exception e) {
			throw new NotFoundException();
		}
		
		StatusMessage msg = RestApplication.getReturnMessage(success);
		return Response.ok(msg).build();
	}
	
	@POST
	@Path("/login")
	@Produces({ "application/json" })
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public User getLogin(@FormParam("name") String name, @FormParam("password") String password) throws NoSuchRowException {
		User u = infrastructureRemote.getUser(name, password);		
		if (u == null) {
			throw new NotFoundException();
		}
		return u;
	}
	
	@POST
	@Path("/register")
	@Produces({ "application/json" })
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public User createUser(@FormParam("name") String name, @FormParam("password") String password) throws NotFoundException, NoSuchRowException {
		User user = infrastructureRemote.getUser(name);
		if (user ==null) {
			user = new User(name, password);
			user.setId(infrastructureRemote.saveUser(user));
			return user;
		}	
		throw new NotFoundException();
	}

	@POST
	@Produces({ "application/json" })
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/buyingLists")
	public BuyingList createBuyingList( 
			@FormParam("username") String name, 
			@FormParam("password") String password,
			@FormParam("name") String buyingListName,
			@FormParam("creationDate") String creationDate,
			@FormParam("buyingDate") String buyingDate, 
			@FormParam("shopName") String shopName, 
			@FormParam("shopLocation") String shopLocation) {
	
		User u;
		BuyingList b = null;
		Date d, d2;
		
		try {
			u = infrastructureRemote.getUser(name, password);
			d = new Date(Long.parseLong(creationDate));
			d2 = new Date(Long.parseLong(buyingDate));
			b = new BuyingList(buyingListName, d, d2);
			b.setId(infrastructureRemote.saveBuyingList(b));
			u.addOneBuyingList(b);
			infrastructureRemote.saveUser(u);
		} catch (Exception e) {
			throw new NotFoundException();
		}
		
		return b;
	}
	

	@DELETE
	@Path("/buyingLists/{id}")
	public Response removeBuyingList(@PathParam("id") long id) throws NoSuchRowException {
		System.out.println("\n\n---------------------------------");
		System.out.println("EinkaufsListeRestProject:: remove BuyingList id = " + id);
		System.out.println("\n\n---------------------------------");
		boolean success = false;
		try {
			success = true;
			infrastructureRemote.removeBuyingList(id);
		} catch (Exception e) {
			throw new NotFoundException();
		}
		StatusMessage msg = RestApplication.getReturnMessage(success);
		return Response.ok(msg).build();
	}
	
	@DELETE
	@Path("/articles/{id}")
	public Response removeArticle(@PathParam("id") long id) throws NoSuchRowException {
		System.out.println("\n\n---------------------------------");
		System.out.println("EinkaufsListeRestProject:: remove Article id = " + id);
		System.out.println("\n\n---------------------------------");
		boolean success = false;
		try {
			success = true;
			infrastructureRemote.removeArticle(id);
		} catch (Exception e) {
			throw new NotFoundException();
		}
		StatusMessage msg = RestApplication.getReturnMessage(success);
		return Response.ok(msg).build();
	}
}
