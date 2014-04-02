package ERP_classes;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import contacts.Contact;
import contacts.Customer;

import java.util.List;

public class DataLinkLayer {
  private Statement statement = null;
  //private PreparedStatement preparedStatement = null; //für update bzw delete statements
  private ResultSet resultSet = null;
  private Connection connect = null;

      

  private void writeMetaData(ResultSet resultSet) throws SQLException {
    // now get some metadata from the database
    System.out.println("The columns in the table are: ");
    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
    }
  }

  private List<Contact> getContactsFromResultSet(ResultSet resultSet) throws SQLException {
    // resultSet is initialised before the first data set
	  List<Contact> contacts = new ArrayList<Contact>();
	  
    while (resultSet.next()) {
      // it is possible to get the columns via name
      // also possible to get the columns via the column number
      // which starts at 1
      // e.g., resultSet.getSTring(2);
    	Customer contact = new Customer();
      contact.set_name(resultSet.getString("name"));
      contact.set_title(resultSet.getString("title"));
      contact.set_suffix(resultSet.getString("suffix"));
      contact.set_surname(resultSet.getString("surname"));
      contact.set_lastname(resultSet.getString("lastname"));
      contact.set_dateOfBirth(resultSet.getDate("dateofbirth").toString());
      contact.set_employedAt(resultSet.getString("employedAt"));
      contact.set_address(resultSet.getString("address"));
      contact.set_plz(resultSet.getInt("plz"));
      contact.set_city(resultSet.getString("city"));
      System.out.println("Username: " + contact.get_name());
      System.out.println("Website: " + contact.get_title());
      System.out.println("Summary: " + contact.get_lastname());
      System.out.println("Date: " + contact.get_dateOfBirth());
      System.out.println("Comment: " + contact.get_plz());
      
      contacts.add(contact);
    }
	return contacts;
  }

  public List<Contact> searchContacts(String text, 
	    boolean onlyActive, 
	    boolean onlyDeleted, 
	    int openInvoicesFrom, 
	    int openInvoicesTo) {
	  
	  List<Contact> contacts = new ArrayList<Contact>();
	
			try {
				 // this will load the MySQL driver, each DB has its own driver
				Class.forName("com.mysql.jdbc.Driver");
				connect = DriverManager
				      .getConnection("jdbc:mysql://localhost/mikroerp?"
				          + "user=Stefan&password=ziffer");
				
				statement = connect.createStatement();
			     resultSet = statement.executeQuery("select * from CUSTOMER");
			     writeMetaData(resultSet);
			     contacts = getContactsFromResultSet(resultSet);
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	
			return contacts;
	}
}




//preparedStatements can use variables and are more efficient
/*preparedStatement = connect
    .prepareStatement("insert into  CUSTOMER values (default, ?, ?, ?, ? , ?, ?, ?, ?, ?, ?)");
 "uid,name,title,suffix,surname,lastname,dateofbirth,employedAt,address, plz, city from CUSTOMER");
 parameters start with 1
 
preparedStatement.setString(1, "Testuser");
preparedStatement.setString(3, "Herr");
preparedStatement.setDate(6, new java.sql.Date(1990, 12, 11));
preparedStatement.executeUpdate();

preparedStatement = connect
    .prepareStatement("SELECT * from CUSTOMER");
resultSet = preparedStatement.executeQuery();
writeResultSet(resultSet);

// remove again the insert comment
preparedStatement = connect
.prepareStatement("delete from CUSTOMER where myuser= ? ; ");
preparedStatement.setString(1, "Testuser");
preparedStatement.executeUpdate();

resultSet = statement
.executeQuery("select * from FEEDBACK.COMMENTS");
writeMetaData(resultSet);

} catch (Exception e) {
throw e;
} finally {
close();
*/