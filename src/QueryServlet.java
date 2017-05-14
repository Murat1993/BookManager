import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class QueryServlet extends HttpServlet {
    // The doGet() runs once per HTTP GET request to this servlet.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set the MIME type for the response message
        resp.setContentType("text/html");
        // Get a output writer to write the response message into the network socket
        PrintWriter out = resp.getWriter();

        Connection conn = null;
        Statement stmt = null;
        try {
            // Step 1: Create a database "Connection" object
            // For MySQL
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ebookshop", "murat", "muratbek"); // <<== Check

            // Step 2: Create a "Statement" object inside the "Connection"
            stmt = conn.createStatement();

            // Step 3: Execute a SQL SELECT query
            String sqlStr = "SELECT * FROM books WHERE author = "
                    + "'" + req.getParameter("author") + "'";

            // Print an HTML page as output of query
            out.println("<html><head><title>Query Results</title></head><body>");
            out.println("<h2>Thank you for your query.</h2>");
            out.println("<p>You query is: " + sqlStr + "</p>"); // Echo for debugging
            ResultSet rset = stmt.executeQuery(sqlStr); // Send the query to the server

            // Step 4: Process the query result
            int count = 0;
            while (rset.next()) {
                // Print a paragraph <p>...</p> for each row
                out.println("<p>" + rset.getString("author")
                            + ", " + rset.getString("title")
                            + ", $" + rset.getDouble("price") + "</p>");
                ++count;
            }
            out.println("<p>==== " + count + " records found ====</p>");
            out.println("</body></html>");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            out.close();
            try {
                // Step 5: Close the Statement and Connection
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
