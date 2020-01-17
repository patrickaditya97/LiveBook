package posts;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/postIt")
public class CreatePost extends HttpServlet
{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		
		System.out.println("here");
		
		StringBuilder buffer = new StringBuilder();
	    BufferedReader reader = req.getReader();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        buffer.append(line);
	    }
	    String data = buffer.toString();
	    
		System.out.println(data);
	}
	
}
