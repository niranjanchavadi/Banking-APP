

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ResetFP extends HttpServlet 
{
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		HttpSession session = req.getSession();
		String newpwd = req.getParameter("newpwd");
		String toEmail = (String) session.getAttribute("toEmail");
		
		Model m = new Model();
		m.setNewpwd(newpwd);
		m.setEmail(toEmail);
		
		int x = m.forgotPwd();
		
		if(x==0)
		{
			res.sendRedirect("/BankApp/failureRFP.html");
		}
		else
		{
			res.sendRedirect("/BankApp/successRFP.html");
		}
	}
}





