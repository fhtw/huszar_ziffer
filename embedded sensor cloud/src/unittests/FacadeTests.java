package unittests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ Test_getDateFromKey.class, Test_getDoubleFromKey.class,
		Test_getValueFromKey.class })
public class FacadeTests {

}
