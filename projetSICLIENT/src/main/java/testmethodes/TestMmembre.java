package testmethodes;

import eventsDAO.DAOException;
import methodes.Mmembre;

public class TestMmembre {

	public static void main(String[] args) throws DAOException {
		// TODO Auto-generated method stub
		Mmembre mm = new Mmembre();
		
		System.out.println("tojson : " + mm.MembretoJson());

	}

}
