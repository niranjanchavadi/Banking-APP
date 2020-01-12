

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ResetPwd
 */
public class ResetPwd extends HttpServlet 
{
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String oldpwd = req.getParameter("oldpwd");
		String newpwd = req.getParameter("newpwd");
		
		HttpSession session = req.getSession();
		String accno = (String) session.getAttribute("accno");
		
		Model m = new Model();
		m.setOldpwd(oldpwd);
		m.setNewpwd(newpwd);
		m.setAccno(accno);
		
		int x = m.resetPwd();
		
		if(x==1)
		{
			res.sendRedirect("/BankApp/successResetPwd.html");
		}
		else
		{
			res.sendRedirect("/BankApp/failureResetPwd.html");
		}
	}
}












