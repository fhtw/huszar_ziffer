package ERP_classes;

import invoice.Invoice;
import invoice.InvoiceElement;
import invoice.InvoiceList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import contacts.Customer;
import contacts.CustomerList;


public class DataLinkLayer {
	private PreparedStatement preparedStatement = null;
	//private PreparedStatement preparedStatement = null; //für update bzw delete statements
	private ResultSet resultSet = null;
	private Connection connect = null;      
	
	private CustomerList getContactsFromResultSet(ResultSet resultSet) throws SQLException {
	  // resultSet is initialised before the first data set
		CustomerList contacts = new CustomerList();
		  
		try{
			while (resultSet.next()) {
			    // also possible to get the columns via the column number
			    // which starts at 1
			    // e.g., resultSet.getSTring(2);
		    	Customer contact = new Customer();
		    	contact.set_id(resultSet.getInt("id"));
		    	contact.set_name(resultSet.getString("name"));
		    	contact.set_uid(resultSet.getString("uid"));
			    contact.set_title(resultSet.getString("title"));
			    contact.set_suffix(resultSet.getString("suffix"));
			    contact.set_surname(resultSet.getString("surname"));
			    contact.set_lastname(resultSet.getString("lastname"));
			    if(resultSet.getDate("dateOfBirth") != null){
			    	contact.set_dateOfBirth(resultSet.getDate("dateOfBirth").toString());
			    }
			    contact.set_employedAt(resultSet.getString("employedAt"));
			    contact.set_address(resultSet.getString("address"));
			    contact.set_plz(resultSet.getInt("plz"));
			    contact.set_city(resultSet.getString("city"));
		      
			    contacts.add(contact);
		    }
			return contacts;
		} catch(NullPointerException e){
			e.printStackTrace();
		}
		return contacts;
	}
	
	private ArrayList<Invoice> getInvoicesFromResultSet(ResultSet set) throws SQLException {
		  // resultSet is initialised before the first data set
			ArrayList<Invoice> invoices = new ArrayList<Invoice>();
			  
			try{
				while (set.next()) {
				    // also possible to get the columns via the column number
				    // which starts at 1
				    // e.g., resultSet.getSTring(2);
			    	int invoiceId = set.getInt("id");
			    	ArrayList<InvoiceElement> articles = new ArrayList<InvoiceElement>();
			    	Invoice invoice = new Invoice();
			    	invoice.set_id(set.getInt("id"));
			    	invoice.set_invoiceNumber(set.getInt("invoiceNumber"));
			    	invoice.set_isOutgoing(set.getBoolean("isOutgoing"));
				    invoice.set_creationDate(set.getString("creationDate"));
				    invoice.set_expirationDate(set.getString("expirationDate"));
				    invoice.set_comment(set.getString("comment"));
				    invoice.set_message(set.getString("message"));
				    invoice.set_customerName(set.getString("customerName").toString());
				    invoice.set_ust(set.getDouble("ust"));
				    invoice.set_gross(set.getDouble("gross"));
				    invoice.set_net(set.getDouble("net"));
				    invoice.set_shippingAddress(set.getString("shippingAddress"));
				    invoice.set_invoiceAddress(set.getString("invoiceAddress"));		    
				    
				    // get invoice articles from db
					Class.forName("com.mysql.jdbc.Driver");
					connect = DriverManager.getConnection("jdbc:mysql://localhost/mikroerp?"
					    		+ "user=root&password=!eps1loN");
					//preparedStatements can use variables and are more efficient
					PreparedStatement preparedStatement2 = connect
						    .prepareStatement("SELECT * FROM INVOICES2ARTICLES "
						    		+ "WHERE fkInvoiceId=?");

					preparedStatement2.setInt(1, invoiceId);
				
					ResultSet resultSet2 = preparedStatement2.executeQuery();
					while(resultSet2.next()){//has to be called!cause you need the cursor point on the id
						InvoiceElement elem = new InvoiceElement();
						elem.set_name(getNameFromArticle(resultSet2.getInt("fkArticleId")));
						elem.set_amount(resultSet2.getInt("amount"));
						elem.set_price(getPriceFromArticle(resultSet2.getInt("fkArticleId")));
						articles.add(elem);
					}
					invoice.set_articles(articles);					   
					invoices.add(invoice);
				}
				return invoices;
			} catch(SQLException e){
				e.printStackTrace();
				return invoices;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return invoices;
			}			    
		}
	
	private ArrayList<InvoiceElement> getArticlesFromResultSet(ResultSet resultSet) throws SQLException {
		  // resultSet is initialised before the first data set
			ArrayList<InvoiceElement> articles = new ArrayList<InvoiceElement>();
			  
			try{
				while (resultSet.next()) {
				    // also possible to get the columns via the column number
				    // which starts at 1
				    // e.g., resultSet.getSTring(2);
			    	InvoiceElement article = new InvoiceElement();
			    	article.set_name(resultSet.getString("name"));
			    	article.set_price(resultSet.getDouble("price"));
				  			      
				    articles.add(article);
			    }
				return articles;
			} catch(NullPointerException e){
				e.printStackTrace();
			}
			return articles;
		}
	
	
	
	  public CustomerList searchContacts(String surname, 
		    String lastname, 
		    String businessname) {
		  
		  CustomerList contacts = new CustomerList();
		  System.out.println("surname: " + surname + " lastname: " + lastname + " businessname : " + businessname);
		  try {
			  // this will load the MySQL driver, each DB has its own driver
			  Class.forName("com.mysql.jdbc.Driver");
			  connect = DriverManager.getConnection("jdbc:mysql://localhost/mikroerp?"
				    		+ "user=root&password=!eps1loN");
			
			  preparedStatement = connect
					    .prepareStatement("SELECT * from CUSTOMER where (surname RLIKE ? "
					    		+ "AND lastname RLIKE ?) "
					    		+ "OR name RLIKE ?");
			  if(!(businessname == null || businessname.equals("null") || businessname.equals(""))){
				  preparedStatement.setString(1, " ");
				  preparedStatement.setString(2, " ");
				  preparedStatement.setString(3, businessname);
				  System.out.println(preparedStatement);
					resultSet = preparedStatement.executeQuery();
				  
				  //writeMetaData(resultSet);
				  return contacts = getContactsFromResultSet(resultSet);
			  }else {
				  if(surname == null || surname.equals("null") || surname.equals("")){//if name was not set
				  
				  preparedStatement.setString(1, ".*");
				}else{
					preparedStatement.setString(1, surname);//weiß nicht wie mans besser lösen kann!!
				}
			  	
				if(lastname == null || lastname.equals("null") || lastname.equals("")){
					preparedStatement.setString(2, ".*");
				}else{
					preparedStatement.setString(2, lastname);
				}
				
				if((businessname == null || businessname.equals("null") || businessname.equals("")) && (!(lastname == null || lastname.equals("null") || lastname.equals(""))
						|| !(surname == null || surname.equals("null") || surname.equals("")))){
					preparedStatement.setString(3, surname + " " + lastname);
				}else if((businessname == null || businessname.equals("null") || businessname.equals("")) && (lastname == null || lastname.equals("null") || lastname.equals(""))
						&& (surname == null || surname.equals("null") || surname.equals(""))){					
					preparedStatement.setString(3, ".*");
				}else{
					preparedStatement.setString(3, businessname);
				}			  
			  System.out.println(preparedStatement);
				resultSet = preparedStatement.executeQuery();
			  
			  //writeMetaData(resultSet);
			  return contacts = getContactsFromResultSet(resultSet);
			 }		   
		  } catch (SQLException e) {
			  e.printStackTrace();
		  } catch (ClassNotFoundException e) {
			  e.printStackTrace();
		  }
		  return contacts;
	  }
	
	public InvoiceList searchInvoices(String name,
			java.sql.Date fromDate, 
			java.sql.Date toDate,
			double fromAmount,
			double toAmount){
		
		InvoiceList result = new InvoiceList();
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			  connect = DriverManager.getConnection("jdbc:mysql://localhost/mikroerp?"
				    		+ "user=root&password=!eps1loN");
			ArrayList<Integer> customerIds = getIdFromName(name);
			//preparedStatements can use variables and are more efficient
			preparedStatement = connect
			    .prepareStatement("SELECT * from INVOICES where fkCustomerId RLIKE ?"
			    		+ " AND creationDate BETWEEN ? AND ? "
			    		+ "AND gross BETWEEN ? AND ?");
			/*System.out.println("name: " + name);
			System.out.println("fromDate: " + fromDate);
			System.out.println("toDate: " + toDate);
			System.out.println("fromAmount: " + fromAmount);
			System.out.println("toAmount: " + toAmount);*/
			 //parameters start with 1
			for(int i=0; i<customerIds.size(); i++){
				if(customerIds.get(i) <= 0){//if name was not set
					preparedStatement.setString(1, ".*");
				}else{
					preparedStatement.setInt(1, customerIds.get(i));
				}
				if(fromDate == null){//if fromDate was not set
					preparedStatement.setString(2, ".*");
				}else{
					preparedStatement.setDate(2, fromDate);
				}
				if(toDate == null){//if toDate was not set
					preparedStatement.setString(3,"3000-01-01");
				}else{
					preparedStatement.setDate(3, toDate);
				}
				if(fromAmount <= -1){//if fromAmount was not set
					preparedStatement.setString(4, ".*");
				}else{
					preparedStatement.setDouble(4, fromAmount);
				}
				if(toAmount <= -1){//if toAmount was not set
					preparedStatement.setInt(5, Integer.MAX_VALUE);
				}else{
					preparedStatement.setDouble(5, toAmount);
				}
				System.out.println(preparedStatement);
				//preparedStatement.setDate(6, new java.sql.Date(1990, 12, 11));
				ResultSet resultSet = preparedStatement.executeQuery();
				if(resultSet != null){
					ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
					invoiceList = getInvoicesFromResultSet(resultSet);
					for(int y=0; y<invoiceList.size(); y++){
						result.add(invoiceList.get(y));
					}	
				}
			}
			System.out.println(result.getInvoices().size());
			return result;
		}catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	public String createCustomer(Customer customer) {
		if(customer.get_id() > 0){
			return updateCustomer(customer);
		}
		try{
			Class.forName("com.mysql.jdbc.Driver");
			  connect = DriverManager.getConnection("jdbc:mysql://localhost/mikroerp?"
				    		+ "user=root&password=!eps1loN");
			//preparedStatements can use variables and are more efficient
			  preparedStatement = connect
					    .prepareStatement("INSERT INTO mikroerp.customer("
					    		+ "uid,name,title,suffix,surname,lastname,"
					    		+ "dateOfBirth,employedAt,address,plz,city)"
					    		+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)");
					//1 - 11 ?
			  if(customer.get_lastname() == null){//if it is a business
				  preparedStatement.setString(1,customer.get_uid());
				  preparedStatement.setString(2,customer.get_name());
				  preparedStatement.setNull(3,java.sql.Types.VARCHAR);
				  preparedStatement.setNull(4,java.sql.Types.VARCHAR);
				  preparedStatement.setNull(5,java.sql.Types.VARCHAR);
				  preparedStatement.setNull(6,java.sql.Types.VARCHAR);
				  preparedStatement.setNull(7,java.sql.Types.VARCHAR);
				  preparedStatement.setNull(8,java.sql.Types.VARCHAR);
				  preparedStatement.setString(9,customer.get_address());
				  preparedStatement.setInt(10,customer.get_plz());
				  preparedStatement.setString(11,customer.get_city());   
			  }else{//if it is a person
				  preparedStatement.setNull(1,java.sql.Types.INTEGER);
				  preparedStatement.setNull(2,java.sql.Types.VARCHAR);
				  preparedStatement.setString(3,customer.get_title());
				  preparedStatement.setString(4,customer.get_suffix());
				  preparedStatement.setString(5,customer.get_surname());
				  preparedStatement.setString(6,customer.get_lastname());
				  preparedStatement.setString(7,customer.get_dateOfBirth());
				  preparedStatement.setString(8,customer.get_employedAt());
				  preparedStatement.setString(9,customer.get_address());
				  preparedStatement.setInt(10,customer.get_plz());
				  preparedStatement.setString(11,customer.get_city()); 
			  }			
			preparedStatement.executeUpdate();
			return "0";
			
		}catch(SQLException e){
			e.printStackTrace();
			return "1";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return "1";
		}
		
	}
	
	private String updateCustomer(Customer customer) {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/mikroerp?"
			    		+ "user=root&password=!eps1loN");
		//preparedStatements can use variables and are more efficient
		  preparedStatement = connect
				    .prepareStatement("UPDATE mikroerp.customer "
				    + "SET uid=?, name=?, title=?, suffix=?, surname=?, lastname=?, "
				    + "dateOfBirth=?, employedAt=?, address=?, plz=?, city=? "
				    + "WHERE id=?");
				//1 - 11 ?
		  if(customer.get_lastname() == null){//if it is a business
			  preparedStatement.setString(1,customer.get_uid());
			  preparedStatement.setString(2,customer.get_name());
			  preparedStatement.setNull(3,java.sql.Types.VARCHAR);
			  preparedStatement.setNull(4,java.sql.Types.VARCHAR);
			  preparedStatement.setNull(5,java.sql.Types.VARCHAR);
			  preparedStatement.setNull(6,java.sql.Types.VARCHAR);
			  preparedStatement.setNull(7,java.sql.Types.VARCHAR);
			  preparedStatement.setNull(8,java.sql.Types.VARCHAR);
			  preparedStatement.setString(9,customer.get_address());
			  preparedStatement.setInt(10,customer.get_plz());
			  preparedStatement.setString(11,customer.get_city());
			  preparedStatement.setInt(12, customer.get_id());
		  }else{//if it is a person
			  preparedStatement.setNull(1,java.sql.Types.INTEGER);
			  preparedStatement.setNull(2,java.sql.Types.VARCHAR);
			  preparedStatement.setString(3,customer.get_title());
			  preparedStatement.setString(4,customer.get_suffix());
			  preparedStatement.setString(5,customer.get_surname());
			  preparedStatement.setString(6,customer.get_lastname());
			  preparedStatement.setString(7,customer.get_dateOfBirth());
			  preparedStatement.setString(8,customer.get_employedAt());
			  preparedStatement.setString(9,customer.get_address());
			  preparedStatement.setInt(10,customer.get_plz());
			  preparedStatement.setString(11,customer.get_city());
			  preparedStatement.setInt(12, customer.get_id());
		  }	
		  preparedStatement.executeUpdate();
		  return "0";
		} catch(SQLException e){
			e.printStackTrace();
			return "1";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return "1";
		}
	}
	
	public String createInvoice(Invoice invoice) {
		
		try{ 
			Class.forName("com.mysql.jdbc.Driver");
			  connect = DriverManager.getConnection("jdbc:mysql://localhost/mikroerp?"
				    		+ "user=root&password=!eps1loN");
			//preparedStatements can use variables and are more efficient
			  int customerId = getIdFromName(invoice.get_customerName()).get(0);
			  int isOutgoing = 0;
			  int lastInsertedId = -1;
			  if(invoice.is_isOutgoing() == true){
				  isOutgoing = 1;
			  }
			  preparedStatement = connect
					    .prepareStatement("INSERT INTO mikroerp.invoices("
					    		+ "invoiceNumber,isOutgoing,creationDate,"
					    		+ "expirationDate,comment,message,customerName,"
					    		+ "shippingAddress,invoiceAddress,ust,gross,net,fkCustomerId)"
					    		+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
					//1 - 13 ?
			  preparedStatement.setInt(1,invoice.get_invoiceNumber());
			  preparedStatement.setInt(2,isOutgoing);
			  preparedStatement.setString(3,invoice.get_creationDate());
			  preparedStatement.setString(4,invoice.get_creationDate());
			  preparedStatement.setString(5,invoice.get_comment());
			  preparedStatement.setString(6,invoice.get_message());
			  preparedStatement.setString(7,invoice.get_customerName());
			  preparedStatement.setString(8,invoice.get_shippingAddress());
			  preparedStatement.setString(9,invoice.get_invoiceAddress());
			  preparedStatement.setDouble(10,invoice.get_ust());
			  preparedStatement.setDouble(11,invoice.get_gross());
			  preparedStatement.setDouble(12,invoice.get_net());
			  preparedStatement.setInt(13,customerId); 						
			
			  preparedStatement.executeUpdate();
			  
			  ResultSet rs = preparedStatement.getGeneratedKeys();
              if(rs.next())
              {
            	  lastInsertedId = rs.getInt(1);
              }
			  
			  preparedStatement = connect
					    .prepareStatement("INSERT INTO mikroerp.invoices2articles("
					    		+ "fkInvoiceId, fkArticleId, amount) "
					    		+ "VALUES(?,?,?)");
					//1 - 13 ?
			  for(int i=0; i<invoice.get_articles().size(); i++){
				  System.out.println("id: " + lastInsertedId);
				  System.out.println("idFromName: " + getIdFromArticle(invoice.get_articles().get(i).get_name()));
				  System.out.println("amount: " + invoice.get_articles().get(i).get_amount());
				  preparedStatement.setInt(1,lastInsertedId);
				  preparedStatement.setInt(2,getIdFromArticle(invoice.get_articles().get(i).get_name()));
				  preparedStatement.setInt(3,invoice.get_articles().get(i).get_amount());					
				  System.out.println(preparedStatement);
				  preparedStatement.executeUpdate();
			  }			
			  return "0";
		}catch(SQLException e){
			e.printStackTrace();
			return "1";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return "1";
		}
	}

	private ArrayList<Integer> getIdFromName(String name) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		try{
			if(name == null || "".equals(name)){
				result.add(-1);
				return result;
			}
			preparedStatement = connect
			    .prepareStatement("SELECT id from CUSTOMER where (name RLIKE ? OR surname RLIKE ? OR lastname RLIKE ?) OR (surname RLIKE ? AND lastname RLIKE ?)");
			
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, name);
			preparedStatement.setString(5, name);
			
			String[] array = name.split(" ");
			if(array.length >= 2){
				preparedStatement.setString(4, array[0]);
				preparedStatement.setString(5, array[1]);
			} else {
				preparedStatement.setString(4, name);
				preparedStatement.setString(5, name);
			}
			System.out.println(preparedStatement);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){//has to be called!cause you need the cursor point on the id
				result.add(resultSet.getInt("id"));
			}
			return result;
		}catch(SQLException e){
			e.printStackTrace();
			result.add(-1);
			return result;
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("getIdFromName: Name ist nicht vorname+nachname.");
			result.add(-1);
			return result;
		}	
	}
	
	@SuppressWarnings("unused")
	private void writeMetaData(ResultSet resultSet) throws SQLException {
	    
	    System.out.println("The columns in the table are: ");
	    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
	    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
	      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
	    }
	}

	public ArrayList<InvoiceElement> getArticles() {
		ArrayList<InvoiceElement> articles = new ArrayList<InvoiceElement>();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/mikroerp?"
			    		+ "user=root&password=!eps1loN");
			Statement stmt = connect.createStatement();
			String query = "SELECT * FROM ARTICLES";
			resultSet = stmt.executeQuery(query);

			return articles = getArticlesFromResultSet(resultSet);
		}catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return articles;
	}
	
	private int getIdFromArticle(String name) {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/mikroerp?"
			    		+ "user=root&password=!eps1loN");
			//preparedStatements can use variables and are more efficient
			PreparedStatement preparedStatement2 = connect
				    .prepareStatement("SELECT id FROM ARTICLES "
				    		+ "WHERE name=?");

			preparedStatement2.setString(1, name);
		
			resultSet = preparedStatement2.executeQuery();

			if(resultSet.next()){//has to be called!cause you need the cursor point on the id
				return resultSet.getInt(1);
			} else {
				return -1;
			}
		} catch(SQLException e){
			e.printStackTrace();
			return -1;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	private String getNameFromArticle(int id) {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/mikroerp?"
			    		+ "user=root&password=!eps1loN");
			//preparedStatements can use variables and are more efficient
			PreparedStatement preparedStatement2 = connect
				    .prepareStatement("SELECT name FROM ARTICLES "
				    		+ "WHERE id=?");

			preparedStatement2.setInt(1, id);
		
			resultSet = preparedStatement2.executeQuery();

			if(resultSet.next()){//has to be called!cause you need the cursor point on the id
				return resultSet.getString(1);
			} else {
				return null;
			}
		} catch(SQLException e){
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private double getPriceFromArticle(int id) {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/mikroerp?"
			    		+ "user=root&password=!eps1loN");
			//preparedStatements can use variables and are more efficient
			PreparedStatement preparedStatement2 = connect
				    .prepareStatement("SELECT price FROM ARTICLES "
				    		+ "WHERE id=?");

			preparedStatement2.setInt(1, id);
		
			ResultSet resultSet2 = preparedStatement2.executeQuery();

			if(resultSet2.next()){//has to be called!cause you need the cursor point on the id
				return resultSet2.getDouble(1);
			} else {
				return 0;
			}
		} catch(SQLException e){
			e.printStackTrace();
			return 0;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return 0;
		}
	}	
}

/*insert into  CUSTOMER values (default, ?, ?, ?, ? , ?, ?, ?, ?, ?, ?)");
"uid,name,title,suffix,surname,lastname,dateofbirth,employedAt,address, plz, city from CUSTOMER
*/