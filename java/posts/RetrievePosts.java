package posts;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/getIt")
public class RetrievePosts extends HttpServlet
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String dbURL = "jdbc:mysql://localhost/livebook";
    private String dbUser = "root";
    private String dbPass = "";
    
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		System.out.println("retrieving");
		
		Connection conn = null;
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
        	conn = DriverManager.getConnection(dbURL,dbUser, dbPass);
        	
            String sql = "SELECT * FROM posts WHERE id=20";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            
			Blob b;
			Blob blob;
            
            while(rs.next())
            {

            	b=rs.getBlob(2);
	    		byte barr[]=b.getBytes(1, (int)b.length()); 
	    		resp.setContentType("image/gif");
	    		
	    		OutputStream os = resp.getOutputStream();
	    		os.write(barr);
	    		
            }
            
		} 
		catch (Exception e) 
		{
			
		}
		
	}

}