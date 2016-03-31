import java.lang.Runtime;
import java.io.*;

public class Procedure extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
                        {
        res.setContentType("text/html");
        ServletOutputStream out = res.getOutputStream();
        out.println("<HTML><HEAD><TITLE>Test</TITLE></HEAD><BODY><blockquote><pre>");
        
        String user = req.getParameter("user");
        if(user != null) {
            try {
                 String[] args = { "/bin/sh", "-c", "finger " + user };
                Process p = Runtime.getRuntime().exec(args);
                BufferedReader fingdata = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while((line = fingdata.readLine()) != null)
                    out.println(line);
                p.waitFor();
                catch(Exception e) {
                    throw new ServletException(e);
                
                    else {
                        out.println("specify a user");
            
        
                        out.println("</pre></blockquote></BODY></HTML>");
                        out.close();
        
                    }
                }
            }
        }
    }
}
