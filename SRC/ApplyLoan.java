

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ApplyLoan extends HttpServlet 
{
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String accno = (String) session.getAttribute("accno");
		
		Model m = new Model();
		m.setAccno(accno);
		m.applyLoan();
		String name = m.getName();
		String email = m.getEmail();
		
		session.setAttribute("name", name);
		session.setAttribute("email", email);
		
		res.sendRedirect("/BankApp/loanDetails.jsp");
	}
}







