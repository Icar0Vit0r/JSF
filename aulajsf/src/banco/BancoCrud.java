package banco;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BancoCrud {
	
	private Connection con = null;
	
	private String userName = null;
	private String password = null;
	private String url = null;
	private String jdbcDriver = null;


	public BancoCrud() {
	   jdbcDriver = "org.firebirdsql.jdbc.FBDriver";
	   userName = "SYSDBA";
	   password = "masterkey";
	   url = "jdbc:firebirdsql:localhost:c:\\Program Files (x86)\\Firebird\\Firebird_2_5\\CRUDJSF.GDB";
	}

	public Connection getConnection() {
		
		Runtime.getRuntime().gc();
		try {
			if (con == null) {
				Class.forName(jdbcDriver);
				con = DriverManager.getConnection(url
						+ "?defaultResultSetHoldable=True", userName, password);
			} else if (con.isClosed()) {
				con = null;
				return getConnection();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public void closeConnection() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}