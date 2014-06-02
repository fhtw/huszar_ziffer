package unittests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ Test_createCustomer.class, Test_createInvoice.class,
		Test_getArticles.class, Test_getArticlesFromResultSet.class,
		Test_getContactsFromResultSet.class, Test_getIdFromArticle.class,
		Test_getIdFromName.class, Test_getInvoicesFromResultSet.class,
		Test_getNameFromArticle.class, Test_getPriceFromArticle.class,
		Test_searchContacts.class, Test_searchInvoices.class })
public class DataLinkLayerTests {

}
