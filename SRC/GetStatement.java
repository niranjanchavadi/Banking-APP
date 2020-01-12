import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetStatement extends HttpServlet 
{
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{	
		HttpSession session = req.getSession();
		String accno = (String) session.getAttribute("accno");
		
		Model m = new Model();
		m.setAccno(accno);
		m.getStatement();
		
		ArrayList al1 = m.al1;
		ArrayList al2 = m.al2;
		
		session.setAttribute("al1", al1);
		session.setAttribute("al2", al2);
		
		res.sendRedirect("/BankApp/getStatementDetails.jsp");
	}
}








