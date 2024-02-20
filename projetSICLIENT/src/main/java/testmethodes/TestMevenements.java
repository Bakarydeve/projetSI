package testmethodes;

import eventsDAO.DAOException;
import methodes.Mevenement;

public class TestMevenements {

	public static void main(String[] args) throws DAOException {
		// TODO Auto-generated method stub
		Mevenement me = new Mevenement();
		
		System.out.println("tojson : " + me.AvenirtoJson());

	}

}
