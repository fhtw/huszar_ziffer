package server.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import server.PluginManager;

public class PluginManagerTest {

	@Test
	public void testListPlugins() {
		PluginManager tester = new PluginManager();
		String testString = tester.listPlugins();
		assertNotNull(testString);
	}
}
