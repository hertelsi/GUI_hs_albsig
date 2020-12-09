package client;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.hsalbsig.inf.dea.model.Building;
import de.hsalbsig.inf.dea.model.InfrastructureRemote;



public class Main {

	public static void main(String[] args) {

		InitialContext initialContext;
		try {
			initialContext = new InitialContext();
			InfrastructureRemote infrastructureDao = (InfrastructureRemote) initialContext
					.lookup("EinkaufslisteEJB/Infrastructure!de.hsalbsig.inf.dea.model.InfrastructureRemote");
			for (BuyingList obj : infrastructureDao.getAllBuyingLists())
				System.out.println("Einkaufsliste = " + obj);
			for (Article obj : infrastructureDao.getAllArticles())
				System.out.println("Artikel = " + obj);
			for (Shop obj : infrastructureDao.getAllShops())
				System.out.println("Shop = " + obj);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
