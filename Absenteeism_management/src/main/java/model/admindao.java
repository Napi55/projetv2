package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class admindao {

	
		private String jdbcURL = "jdbc:mysql://localhost:3306/demo";
		private String jdbcUsername = "root";
		private String jdbcPassword = "penine";

		private static final String INSERT_USERS_SQL = "INSERT INTO `absenteeism management`.`admin` (`firstname`, `lastname`, `email`, `username`, `password`) VALUES (?, ?, ?, ?, ?, ?);";

		private static final String SELECT_USER_BY_ID = "SELECT * FROM `absenteeism management`.admin WHERE (`id` = ?);";
		
		private static final String SELECT_ALL_USERS = "SELECT * FROM `absenteeism management`.admin;";
	
		private static final String DELETE_USERS_SQL = "DELETE FROM `absenteeism management`.`admin` WHERE (`id` = ?);";
		private static final String UPDATE_USERS_SQL = "UPDATE `absenteeism management`.`admin` SET `firstname` = ?, `lastname` = ?, `email` = ?, `username` = ?, `password` = ? WHERE (`id` = ?);";
		
		

		protected Connection getConnection() {
			Connection connection = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return connection;
		}
		
		
		
		
	
	
	public admin checkLogin(String username, String password)
			throws InstantiationException, IllegalAccessException    {
		
		String requet;
		PreparedStatement st;
		admin admin=null;
		
	
		try {
			Connection connexion = getConnection();
			requet="SELECT * FROM prof WHERE username = ? and password = ?";
			st=connexion.prepareStatement(requet);
			st.setString(1, username);
			st.setString(2, password);
			ResultSet result = st.executeQuery();
			if(result.next()) {
				admin=new admin();
				admin.setFirstname(result.getString("firstname"));
				admin.setLastname(result.getString("lastname"));
				admin.setEmail(result.getString("email"));
				admin.setUsername(result.getString("username"));
				admin.setPassword(result.getString("password"));

			admin.setId(result.getInt("id"));
				st.close();
				System.out.println("succés !");
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		
		
		
		return admin;	
	}
	public void insertUser(admin user) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getFirstname());
			preparedStatement.setString(2, user.getLastname());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getUsername());
			preparedStatement.setString(5, user.getPassword());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public admin selectUser(int id) {
		admin user = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String email = rs.getString("email");
				String username = rs.getString("username");
				String password = rs.getString("password");
				user = new  admin(id, firstname,  lastname,  email, username,password);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}

	public List<admin> selectAllUsers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<admin> users = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String email = rs.getString("email");
				String username = rs.getString("username");
				String password = rs.getString("password");
				users.add(new admin(id,  firstname,  lastname,  email,  username,  password));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return users;
	}

	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateUser(admin user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			System.out.println("updated USer:"+statement);
			statement.setString(1, user.getFirstname());
			statement.setString(2, user.getLastname());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getUsername());
			statement.setString(5, user.getPassword());
			statement.setInt(6, user.getId());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
	
		
		
}
