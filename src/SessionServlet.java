import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class SessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set the response message's MIME type
        resp.setContentType("text/html;charset=UTF-8");
        // Allocate a output writer to write the response message into network socket
        PrintWriter out = resp.getWriter();

        // Return the existing session if there is one. Create a new session otherwise.
        HttpSession session = req.getSession();
        Integer accessCount;
        synchronized (session) {
            accessCount = (Integer) session.getAttribute("accessCount");
            if (accessCount == null) {
                accessCount = 0; // autobox int to Integer
            } else {
                accessCount += 1;
            }
            session.setAttribute("accessCount", accessCount);
        }


        // Write the response message, in an HTML page
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
            out.println("<title>Session Test Servlet</title></head><body>");
            out.println("<h2>You have access this site " + accessCount + " times in this session.</h2>");
            out.println("<p>(Session ID is " + session.getId() + ")</p>");

            out.println("<p>(Session creation time is " +
                    new Date(session.getCreationTime()) + ")</p>");
            out.println("<p>(Session last access time is " +
                    new Date(session.getLastAccessedTime()) + ")</p>");
            out.println("<p>(Session max inactive interval  is " +
                    session.getMaxInactiveInterval() + " seconds)</p>");

            out.println("<p><a  href='" + req.getRequestURI() +  "'>Refresh</a>");
            out.println("<p><a  href='" + resp.encodeURL(req.getRequestURI())  +
                    "'>Refresh with  URL rewriting</a>");
            out.println("</body></html>");
        } finally {
            out.close();  // Always close the output writer
        }
    }
}
