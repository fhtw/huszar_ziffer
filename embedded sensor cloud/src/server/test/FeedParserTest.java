package server.test;

import static org.junit.Assert.*;

import org.junit.Test;

import server.FeedParser;

public class FeedParserTest {

	@Test
	public void testFeedParser() {
		FeedParser parser = new FeedParser("http://feeds.reuters.com/reuters/sportsNews");
		return;
	}

	@Test
	public void testReadFeed() {
		FeedParser parser = new FeedParser("http://feeds.reuters.com/reuters/sportsNews");
		assertTrue(null != parser.readFeed());
		return;
	}

}
