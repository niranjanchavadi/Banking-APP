

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet 
{
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		String accno = req.getParameter("accno");
		String pwd = req.getParameter("pwd");
		
		Model m = new Model();
		
		m.setAccno(accno);
		m.setPwd(pwd);
		
		int x = m.login();
		if(x==0)
		{
			res.sendRedirect("/BankApp/failureLogin.html");
		}
		else
		{
			HttpSession session = req.getSession(true);
			session.setAttribute("accno", accno);
			
			res.sendRedirect("/BankApp/home.jsp");
		}
	}
}






