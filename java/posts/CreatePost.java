package posts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

@WebServlet("/postIt")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
					maxFileSize = 1024 * 1024 * 10,
					maxRequestSize = 1024 * 1024 * 50)
public class CreatePost extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dbURL = "jdbc:mysql://localhost/livebook";
    private String dbUser = "root";
    private String dbPass = "";
    
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		resp.setContentType("text/html;charset=UTF-8");
		System.out.println("here");
		String caption = req.getParameter("caption");
		
		InputStream inputStream = null;
		Part filePart = req.getPart("photo");
      
        Connection conn = null;        
        
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	conn = DriverManager.getConnection(dbURL,dbUser, dbPass);
            String sql = "INSERT INTO posts (post_caption, image) values (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            
            inputStream = filePart.getInputStream();
            
            statement.setString(1, caption);
            
            if (inputStream != null) 
            {
                statement.setBlob(2, inputStream);
            }
            statement.setBinaryStream(2, (InputStream) inputStream);
            
            
            
            
            statement.executeUpdate();

        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        } 
        catch (ClassNotFoundException e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        finally 
        {
            if (conn != null) 
            {
                // closes the database connection
                try 
                {
                    conn.close();
                } 
                catch (SQLException ex) 
                {
                    ex.printStackTrace();
                }
            }
        }
        
        
	}
	
	public String extractFileName(Part part) {
		
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		
		for(String s : items)
		{
			if(s.trim().startsWith("filename"))
			{
				return s.substring(s.indexOf("")+2, s.length()-1);
			}
		}
		
		return "";
		
	}
	
}
