package ERP_classes;

import invoice.Invoice;
import invoice.InvoiceList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import contacts.Customer;
import contacts.CustomerList;


public class DataLinkLayer {
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	//private PreparedStatement preparedStatement = null; //für update bzw delete statements
	private ResultSet resultSet = null;
	private Connection connect = null;      
	
	private CustomerList getContactsFromResultSet(ResultSet resultSet) throws SQLException {
	  // resultSet is initialised before the first data set
		CustomerList contacts = new CustomerList();
		  
	    while (resultSet.next()) {
		    // also possible to get the columns via the column number
		    // which starts at 1
		    // e.g., resultSet.getSTring(2);
	    	Customer contact = new Customer();
	    	contact.set_name(resultSet.getString("name"));
	    	contact.set_uid(resultSet.getString("uid"));
		    contact.set_title(resultSet.getString("title"));
		    contact.set_suffix(resultSet.getString("suffix"));
		    contact.set_surname(resultSet.getString("surname"));
		    contact.set_lastname(resultSet.getString("lastname"));
		    contact.set_dateOfBirth(resultSet.getDate("dateOfBirth").toString());
		    contact.set_employedAt(resultSet.getString("employedAt"));
		    contact.set_address(resultSet.getString("address"));
		    contact.set_shippingAddress(resultSet.getString("shippingAddress"));
		    contact.set_invoiceAddress(resultSet.getString("invoiceAddress"));
		    contact.set_plz(resultSet.getInt("plz"));
		    contact.set_city(resultSet.getString("city"));
	      
		    contacts.add(contact);
	    }
		return contacts;
	}
	
	private InvoiceList getInvoicesFromResultSet(ResultSet resultSet) throws SQLException {
		  // resultSet is initialised before the first data set
			InvoiceList invoices = new InvoiceList();
			  
		    while (resultSet.next()) {
			    // also possible to get the columns via the column number
			    // which starts at 1
			    // e.g., resultSet.getSTring(2);
		    	Invoice invoice = new Invoice();
		    	invoice.set_invoiceNumber(resultSet.getInt("invoiceNumber"));
		    	invoice.set_isOutgoing(resultSet.getBoolean("isOutgoing"));
			    invoice.set_creationDate(resultSet.getString("creationDate"));
			    invoice.set_expirationDate(resultSet.getString("expirationDate"));
			    invoice.set_comment(resultSet.getString("comment"));
			    invoice.set_message(resultSet.getString("message"));
			    invoice.set_customerName(resultSet.getString("customerName").toString());
			    invoice.set_ust(resultSet.getInt("ust"));
			    invoice.set_gross(resultSet.getInt("gross"));
			    invoice.set_net(resultSet.getInt("net"));
			    invoice.set_shippingAddress(resultSet.getString("shippingAddress"));
			    invoice.set_invoiceAddress(resultSet.getString("invoiceAddress"));
			    
			    invoices.add(invoice);
		    }
			return invoices;
		}
	
	
	
	  public CustomerList searchContacts(String surname, 
		    String lastname, 
		    String businessname) {
		  
		  CustomerList contacts = new CustomerList();
		
		  try {
			  // this will load the MySQL driver, each DB has its own driver
			  Class.forName("com.mysql.jdbc.Driver");
			  connect = DriverManager.getConnection("jdbc:mysql://localhost/mikroerp?"
				    		+ "user=root&password=!eps1loN");
			
			  preparedStatement = connect
					    .prepareStatement("SELECT * from CUSTOMER where (surname = ? "
					    		+ "AND lastname = ?) "
					    		+ "OR name = ?");
			  if(surname == null){//if name was not set
					preparedStatement.setString(1, "*");
				}else{
					preparedStatement.setString(1, surname);//weiß nicht wie mans besser lösen kann!!
				}
				if(lastname == null){//if fromDate was not set
					preparedStatement.setString(2, "*");
				}else{
					preparedStatement.setString(2, lastname);
				}
				if(businessname == null){//if toDate was not set
					preparedStatement.setString(3,"*");
				}else{
					preparedStatement.setString(3, businessname);
				}
			  
			  resultSet = preparedStatement.executeQuery();
			  //writeMetaData(resultSet);
			  contacts = getContactsFromResultSet(resultSet);				
		  } catch (SQLException e) {
			  e.printStackTrace();
		  } catch (ClassNotFoundException e) {
			  e.printStackTrace();
		  }
		  return contacts;
	  }

	@SuppressWarnings("unused")
	private void writeMetaData(ResultSet resultSet) throws SQLException {
	    
	    System.out.println("The columns in the table are: ");
	    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
	    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
	      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
	    }
	}
	
	
	public InvoiceList searchInvoices(String name,
			java.sql.Date fromDate, 
			java.sql.Date toDate,
			double fromAmount,
			double toAmount){
		
		InvoiceList invoices = new InvoiceList();
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			  connect = DriverManager.getConnection("jdbc:mysql://localhost/mikroerp?"
				    		+ "user=root&password=!eps1loN");
			int customerId = getIdFromName(name);
			//preparedStatements can use variables and are more efficient
			preparedStatement = connect
			    .prepareStatement("SELECT * from INVOICES where fkCustomerId RLIKE ?"
			    		+ " AND expirationDate BETWEEN ? AND ? "
			    		+ "AND gross BETWEEN ? AND ?");
			System.out.println("name: " + name);
			System.out.println("fromDate: " + fromDate);
			System.out.println("toDate: " + toDate);
			System.out.println("fromAmount: " + fromAmount);
			System.out.println("toAmount: " + toAmount);
			 //parameters start with 1
			System.out.println("customerId: " + customerId);
			if(customerId <= -1){//if name was not set
				preparedStatement.setString(1, ".*");
			}else{
				preparedStatement.setInt(1, customerId);//weiß nicht wie mans besser lösen kann!!
			}
			if(fromDate == null){//if fromDate was not set
				preparedStatement.setString(2, "*");
			}else{
				preparedStatement.setDate(2, fromDate);
			}
			if(toDate == null){//if toDate was not set
				preparedStatement.setString(3,"3000-01-01");
			}else{
				preparedStatement.setDate(3, toDate);
			}
			if(fromAmount <= -1){//if fromAmount was not set
				preparedStatement.setString(4, "*");
			}else{
				preparedStatement.setDouble(4, fromAmount);
			}
			if(toAmount <= -1){//if toAmount was not set
				preparedStatement.setDouble(5, Double.MAX_VALUE);
			}else{
				preparedStatement.setDouble(5, toAmount);
			}
			System.out.println(preparedStatement);
			//preparedStatement.setDate(6, new java.sql.Date(1990, 12, 11));
			resultSet = preparedStatement.executeQuery();
			
			return invoices = getInvoicesFromResultSet(resultSet);
		}catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return invoices;
		
	}

	private int getIdFromName(String name) {
		
		try{
			preparedStatement = connect
			    .prepareStatement("SELECT id from CUSTOMER where name = ? OR lastname = ?");
			
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, name);
			
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){//has to be called!cause you need the cursor point on the id
			return resultSet.getInt("id");//resultSet.getInt("id");
			}
			}catch(SQLException e){
				e.printStackTrace();
			}
		
		return -1;
	}
	
}

/*insert into  CUSTOMER values (default, ?, ?, ?, ? , ?, ?, ?, ?, ?, ?)");
"uid,name,title,suffix,surname,lastname,dateofbirth,employedAt,address, plz, city from CUSTOMER
*/