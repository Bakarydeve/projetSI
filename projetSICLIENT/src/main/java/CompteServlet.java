

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import donnees.Compte;
import donnees.Membre;
import eventsDAO.DAOException;
import eventsDAO.DAO_JPA;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import methodes.Mcompte;

/**
 * Servlet implementation class CompteServlet
 */
@WebServlet("/CheckSession")
public class CompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DAO_JPA<Compte> daoCompte;
	private Mcompte mcompte;
	private HttpSession maSession;
       
    /**
     * @throws DAOException 
     * @see HttpServlet#HttpServlet()
     */
    public CompteServlet() throws DAOException {
        super();
        daoCompte = new DAO_JPA<>(Compte.class);
        mcompte = new Mcompte();
        
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		response.addHeader("Access-Control-Allow-Headers",
				"X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		response.addHeader("Access-Control-Max-Age", "1728000");
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		*/
		String operation = request.getParameter("operation");
		
		PrintWriter out = response.getWriter();
		
		if (operation.equals("Session"))	{
			response.setContentType("application/json");
			JsonObject responseData = new JsonObject();
			if (maSession.getAttribute("utilisateur") != null) {
			    Membre utilisateur = (Membre) maSession.getAttribute("utilisateur");
			    responseData.addProperty("loggedIn", true);
			    responseData.addProperty("nom", utilisateur.getNom());
			    responseData.addProperty("prenom", utilisateur.getPrenom());
			    responseData.addProperty("adresse", utilisateur.getAdresse());
			    responseData.addProperty("age", utilisateur.getAge());
			    responseData.addProperty("pseudo", utilisateur.getPseudo().getPseudo());
			} else {
			    responseData.addProperty("loggedIn", false);
			}
			out.println(responseData.toString());
			
			System.out.println("tojson : " + responseData.toString());
		}
		
		

	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		/*
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		response.addHeader("Access-Control-Allow-Headers",
				"X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		response.addHeader("Access-Control-Max-Age", "1728000");
		*/
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String operation = request.getParameter("operation");
		

		PrintWriter out = response.getWriter();
		
		maSession = request.getSession(true);

		
	  	if (operation.equals("Authentification")) {
	  	// Récupérer le corps de la requête JSON
	  		BufferedReader reader = request.getReader();
	  		StringBuilder sb = new StringBuilder();
	  		String line;
	  		while ((line = reader.readLine()) != null) {
	  		    sb.append(line);
	  		}
	  		String jsonBody = sb.toString();

	  		// Parser le corps JSON pour obtenir les paramètres "login" et "mdp"
	  		JsonObject jsonObject = JsonParser.parseString(jsonBody).getAsJsonObject();
	  		String login = jsonObject.get("login").getAsString();
	  		String mdp = jsonObject.get("mdp").getAsString();
			
			
			//Compte cpt = mcompte.getEm().find(Compte.class, login);
			
			System.out.println(" login : " + login + " passwd : " + mdp);
		
			try {
				if(mcompte.Authentification(login, mdp))	{
				System.out.println("Ahahaha fetch passe00");
				if(maSession != null)	{
					try {
						maSession.setAttribute("utilisateur", mcompte.getMembre(login));
						System.out.println("session : " + maSession.getAttribute("utilisateur"));
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else	{
					System.out.println("Session vide");
				}
				
				out.println("yes");
				//RequestDispatcher dispatcher = request.getRequestDispatcher("/authenifier.jsp");
				//dispatcher.forward(request, response);
				}
				else {
					out.println("no");
				}
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}	
	  	out.close();
	}

}