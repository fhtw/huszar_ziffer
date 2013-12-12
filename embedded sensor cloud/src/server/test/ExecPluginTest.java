package server.test;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;
 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class ExecPluginTest {
	
	
	@Parameters
	 public static Collection<Object[]> data() {
	   Object[][] data = new Object[][] { { "Temperatur" }, { null }, { "StaticData" } };
	   return Arrays.asList(data);
	 }

	@Test
	public void testExecPlugin() {
		
	}

}
