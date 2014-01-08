package server.test;

import static org.junit.Assert.*;

import org.junit.Test;

import server.Feed;
import server.FeedMessage;
import server.FeedParser;

public class SportnewsTest {
	String _response;

	@Test
	public void testExecPlugin() {
		FeedParser parser = new FeedParser("http://feeds.reuters.com/reuters/sportsNews");
	    Feed feed = parser.readFeed();
	    for (FeedMessage message : feed.getMessages()) {
	      _response += message;
	    }
	}

}
