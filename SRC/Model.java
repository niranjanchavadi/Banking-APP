import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.OracleDriver;

public class Model 
{
	private String un;
	private String name;
	private String pwd;
	private String accno;
	private String balance;
	private String email;
	private String raccno;
	private String amt;
	private String oldpwd;
	private String newpwd;
	
	ArrayList al1 = new ArrayList();
	ArrayList al2 = new ArrayList();
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res;
	public void setName(String name) {
		this.name = name;
	}
	public void setUn(String un) {
		this.un = un;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public void setAccno(String accno) {
		this.accno = accno;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public String getUn() {
		return un;
	}
	public String getPwd() {
		return pwd;
	}
	public String getAccno() {
		return accno;
	}
	public String getBalance() {
		return balance;
	}
	public String getEmail() {
		return email;
	}
	
	Model()
	{
		try 
		{
			DriverManager.registerDriver(new OracleDriver());
			con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE","system", "root");
			System.out.println("Connection established");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//________________Login_________________
	
	int login()
	{
		try
		{
			String s = "SELECT * FROM BANKAPP WHERE ACCNO=? AND PWD=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, accno);
			pstmt.setString(2, pwd);
			res = pstmt.executeQuery();
			
			if(res.next()==true)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	
	//______________CheckBalance____________
	
	void checkBalance()
	{
		try
		{
			String s = "SELECT * FROM BANKAPP WHERE ACCNO=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, accno);
			
			res = pstmt.executeQuery();
			while(res.next()==true)
			{
				balance = res.getString("BALANCE");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public String getRaccno() {
		return raccno;
	}
	public void setRaccno(String raccno) {
		this.raccno = raccno;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	
	//__________________Transfer Money____________
	
	int transfer()
	{
		try
		{
			String s1 = "SELECT * FROM BANKAPP WHERE ACCNO=?";
			pstmt = con.prepareStatement(s1);
			pstmt.setString(1, raccno);
			res = pstmt.executeQuery();
			if(res.next()==true)
			{
				//raacno exist
				
				String s2 = "UPDATE BANKAPP SET BALANCE=BALANCE+? WHERE ACCNO=?";
				pstmt = con.prepareStatement(s2);
				pstmt.setString(1, amt);
				pstmt.setString(2, raccno);
				int x = pstmt.executeUpdate();
				if(x==1)
				{
					String s3 = "UPDATE BANKAPP SET BALANCE=BALANCE-? WHERE ACCNO=?";
					pstmt = con.prepareStatement(s3);
					pstmt.setString(1, amt);
					pstmt.setString(2, accno);
					x = pstmt.executeUpdate();
					if(x==1)
					{
						String s4 = "INSERT INTO GETSTATEMENT VALUES(?,?,?)";
						pstmt = con.prepareStatement(s4);
						pstmt.setString(1, accno);
						pstmt.setString(2, amt);
						pstmt.setString(3, raccno);
						x =pstmt.executeUpdate();
						return x;
					}   
				}
				else
				{
					return 0;
				}
			}
			else
			{
				return 0;
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	public String getOldpwd() {
		return oldpwd;
	}
	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}
	public String getNewpwd() {
		return newpwd;
	}
	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}
	
	//______________RESET PWD_______________
	
	int resetPwd()
	{
		try
		{
			String s1 = "SELECT * FROM BANKAPP WHERE ACCNO=? AND PWD=?";
			pstmt = con.prepareStatement(s1);
			pstmt.setString(1, accno);
			pstmt.setString(2, oldpwd);
			
			res = pstmt.executeQuery();
			
			if(res.next()==true)
			{
				String s2 = "UPDATE BANKAPP SET PWD=? WHERE ACCNO=?";
				pstmt = con.prepareStatement(s2);
				pstmt.setString(1, newpwd);
				pstmt.setString(2, accno);
				
				int x = pstmt.executeUpdate();
				return x;
			}
			else
			{
				return 0;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	
	//_______________ Apply Loan_________
	
	void applyLoan()
	{
		try
		{
			String s ="SELECT * FROM BANKAPP WHERE ACCNO=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, accno);
			res = pstmt.executeQuery();
			
			while(res.next()==true)
			{
				name = res.getString("NAME");
				email = res.getString("EMAIL");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
		
		//_______________ Get Statement Details_________
		
	void getStatement()
	{
		try
		{
			String s = "SELECT * FROM GETSTATEMENT WHERE ACCNO=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, accno);
			res = pstmt.executeQuery();
			
			while(res.next()==true)
			{
				al1.add(res.getString("AMT"));
				al2.add(res.getString("RACCNO"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//______________Reset FP________________-
	
	int forgotPwd()
	{
		try
		{
			String s ="UPDATE BANKAPP SET PWD=? WHERE EMAIL=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, newpwd);
			pstmt.setString(2, email);
			
			int x = pstmt.executeUpdate();
			return x;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
}











