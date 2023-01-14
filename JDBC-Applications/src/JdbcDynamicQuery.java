import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class JdbcDynamicQuery {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		BufferedReader br = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
			st = con.createStatement();
			br = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("SQL Query: ");
			String query = br.readLine();
			
			boolean b = st.execute(query);
			if(b == true) {
				rs = st.getResultSet();
				ResultSetMetaData rmd = rs.getMetaData();
				int colCount = rmd.getColumnCount();
				
				for(int i = 1; i <= colCount; i++) {
					System.out.print(rmd.getColumnName(i) + "\t");
				}
				System.out.println();
				System.out.println("-------------------------------------");
				
				while(rs.next()) {
					for(int i = 1; i <= colCount; i++) {
						System.out.print(rs.getString(i) + "\t");
					}
					System.out.println();
				}
			}
			else {
				int rowCount = st.getUpdateCount();
				System.out.println("RowCount : " + rowCount);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
