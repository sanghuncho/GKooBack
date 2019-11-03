package databaseUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionDB {
    private static final Logger LOGGER = LogManager.getLogger();

	private static final String POSTGRE_DB_DRIVER = "org.postgresql.Driver";
	private static final String POSTGRE_DB_CONNECTION = "jdbc:postgresql://localhost:5432/gkoo";
	private static final String POSTGRE_DB_USER = "postgres";
	private static final String POSTGRE_DB_PASSWORD = "gkooadmin";
	private static Connection conn = null;

	public ConnectionDB() {}

	public static void connectSQL() {
    	try {
			Class.forName(POSTGRE_DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return;
		}
    	
		try {
			conn = DriverManager.getConnection(
					POSTGRE_DB_CONNECTION, 
					POSTGRE_DB_USER,
					POSTGRE_DB_PASSWORD);
		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

		if (conn == null) {
		    LOGGER.error("Failed to make connection with DB!", conn);
		}   	
    }
	
	public static Connection getConnectInstance() {
		return conn;
	}
}