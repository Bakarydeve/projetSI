

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import methodes.Mlieu;
import methodes.Mmembre;

import java.io.IOException;
import java.io.PrintWriter;

import eventsDAO.DAOException;

/**
 * Servlet implementation class LieuServlet
 */

public class LieuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Mlieu mlieu;
       
    /**
     * @throws DAOException 
     * @see HttpServlet#HttpServlet()
     */
    public LieuServlet() throws DAOException {
        super();
        // TODO Auto-generated constructor stub
        this.mlieu = new Mlieu();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String operation = request.getParameter("operation");
		
		PrintWriter out = response.getWriter();
		
		if (operation.equals("Lieu"))	{
			response.setContentType("application/json");
			
			try {
				String lieus = this.mlieu.LieutoJson();
				//if(lieus != null)	{
					out.println(lieus);
				//}
				
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
