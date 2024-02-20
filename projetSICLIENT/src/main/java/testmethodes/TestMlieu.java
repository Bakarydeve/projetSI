package testmethodes;

import eventsDAO.DAOException;
import methodes.Mlieu;

public class TestMlieu {

	public static void main(String[] args) throws DAOException {
		// TODO Auto-generated method stub
		Mlieu ml = new Mlieu();
		
		System.out.println("tojson : " + ml.LieutoJson());

	}

}
