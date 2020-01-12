

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TransferMoney extends HttpServlet 
{
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String raccno = req.getParameter("raccno");
		String amt = req.getParameter("amt");
		HttpSession session =req.getSession();
		String accno = (String) session.getAttribute("accno");
		
		Model m = new Model();
		m.setRaccno(raccno);
		m.setAmt(amt);
		m.setAccno(accno);	
		
		int x = m.transfer();
		
		if(x==0)
		{
			res.sendRedirect("/BankApp/failureTransfer.html");
		}
		else
		{
			res.sendRedirect("/BankApp/successTransfer.html");
		}
	}
	
}









