

import java.io.IOException;
import java.io.PrintWriter;

import eventsDAO.DAOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import methodes.Mmembre;

/**
 * Servlet implementation class MembreServlet
 */
public class MembreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Mmembre mmembre;
       
    /**
     * @throws DAOException 
     * @see HttpServlet#HttpServlet()
     */
    public MembreServlet() throws DAOException {
        super();
        this.mmembre = new Mmembre();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String operation = request.getParameter("operation");
		
		PrintWriter out = response.getWriter();
		
		if (operation.equals("Membres"))	{
			response.setContentType("application/json");
			try {
				String membres = this.mmembre.MembretoJson();
				System.out.println("json membres : " + membres);
				out.println(membres);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				out.println(e.getMessage());
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
	}

}
