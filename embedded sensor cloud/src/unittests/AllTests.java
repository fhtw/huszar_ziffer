package unittests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BusinessLayerTests.class, DataLinkLayerTests.class,
		FacadeTests.class })
public class AllTests {

}
