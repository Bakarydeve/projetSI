

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import eventsDAO.DAOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import methodes.Mevenement;

/**
 * Servlet implementation class EvenementServlet
 */
@WebServlet("/Evenement/*")
public class EvenementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Mevenement mevenement;
       
    /**
     * @throws DAOException 
     * @see HttpServlet#HttpServlet()
     */
    public EvenementServlet() throws DAOException {
        super();
        mevenement = new Mevenement();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//   response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String operation = request.getParameter("operation");
		
		PrintWriter out = response.getWriter();
		
		if (operation.equals("Events"))	{
		
				response.setContentType("application/json");
				try {
					String events = mevenement.AvenirtoJson();
					System.out.println("json : " + mevenement.AvenirtoJson());
					System.out.println("json events : " + events);
					out.println(events);
					//System.out.println("authentification ok ");
					
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		}

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String operation = request.getParameter("operation");
		
		if(operation.equals("Inscription"))	{
			response.setContentType("application/json");
			String nom = request.getParameter("nom");
		    String pseudo = request.getParameter("pseudo");
		    
		    System.out.println("nom : " + nom + " pseudo : " + pseudo);
		    
			try {
				mevenement.Inscription(pseudo,nom);
				response.getWriter().println("Inscription réussie !");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				response.getWriter().println(e.getMessage());
				e.printStackTrace();
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				response.getWriter().println(e.getMessage());
				e.printStackTrace();
			}
			
		}
		if(operation.equals("Desinscription"))	{
			response.setContentType("application/json");
			String nom = request.getParameter("nom");
		    String pseudo = request.getParameter("pseudo");
		    
		    System.out.println("nom : " + nom + " pseudo : " + pseudo);
		    
			try {
				mevenement.Desinscription(pseudo, nom);
				response.getWriter().println("Desinscription réussie !");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				response.getWriter().println(e.getMessage());
				e.printStackTrace();
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				response.getWriter().println(e.getMessage());
				e.printStackTrace();
			}
			
		}
		if (operation.equals("logout")) {
	        HttpSession session = request.getSession(false); 
	        if (session != null) {
	            session.invalidate(); 
	            response.setStatus(HttpServletResponse.SC_OK); 
	        } else {
	            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); 
	        }
	    }
	}

}
