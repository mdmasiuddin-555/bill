import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class Bill1
 */
public class Demo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		//Getting bill number from user
		int bill_number=Integer.parseInt(request.getParameter("bill_number"));
		int units=Integer.parseInt(request.getParameter("bill_units"));
		//for output printing
		PrintWriter pw=response.getWriter();
		float bill=0;
		if(units<=30)
			bill=units*1.90f;
		else if(units>30 && units<=75)
			bill=units*3.00f;
		else if(units>75 && units<=125)
			bill=units*4.50f;
		else if(units>125 && units<=225)
			bill=units*6.00f;
		else if(units>225 && units<=400)
			bill=units*8.75f;
		else
			bill=units*9.75f;
		
		// JDBC connection variables
        Connection connection=null;
        PreparedStatement preparedStatement = null;

        try {
            // Load JDBC driver explicitly using the Class.forName() method
        	Class.forName("com.mysql.jdbc.Driver");

            // Establish connection
            connection = DriverManager.getConnection("jdbc:mysql://database-1.cwbkuyc2ih2q.us-east-1.rds.amazonaws.com:3306/cv01", "admin", "Cloud247.ai");

            // Create SQL insert statement
            String sql = "INSERT INTO bill_manage (bill_number, units, amount, generated_at) VALUES (?, ?, ?, ?)";

            // Prepare statement
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bill_number);
            preparedStatement.setInt(2, units);
            preparedStatement.setFloat(3, bill);
            preparedStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis())); // current timestamp

            // Execute insert query
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
		
		
		request.setAttribute("bill_number",bill_number);
		request.setAttribute("units",units);
		request.setAttribute("bill",bill);
		
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}

}
