package project_database.model;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import project_database.controller.PostController;

public class PostTest {
	private PostModel model;
	private PostController controller;
	
	@Before
	public void setUp() {
		controller = new PostController();
		model = new PostModel();
	}
	
	@Test
	public void testSetTitle() {
		model.setTitle("Title");
		assert(model.getTitle()=="Title");
		
		model.setTitle("Title2");
		assert(model.getTitle()=="Title2");
		
		model.setTitle("Title2!$#");
		assert(model.getTitle()=="Title2!$#");
	}
	
	@Test
	public void testSetTitle2() {
		model.setTitle("Title ");
		assert(model.getTitle()=="Title ");
		
		model.setTitle("Title_-_'");
		assert(model.getTitle()=="Title_-_'");
		
		model.setTitle("Title!$#()()");
		assert(model.getTitle()=="Title!$#()()");
	}
	
	@Test
	public void testSetBody() {
		model.setBody("TestBody");
		assert(model.getBody()=="TestBody");
		
		model.setBody("Test Body");
		assert(model.getBody()=="Test Body");
		
		model.setBody("Test Body!$#");
		assert(model.getBody()=="Test Body!$#");
	}
	
	@Test
	public void testSetBody2() {
		model.setBody("TestBody1559");
		assert(model.getBody()=="TestBody1559");
		
		model.setBody("Test Body"
				+ "");
		assert(model.getBody()=="Test Body"
				+ "");
		
		model.setBody("Test Body!$#"
				+ ""
				+ ""
				+ "");
		assert(model.getBody()=="Test Body!$#"
				+ ""
				+ ""
				+ "");
	}
	
	@Test
	public void testSetPostID() {
		model.setPostID(1000);
		assert(model.getPostID() == 1000);
		model.setPostID(10);
		assert(model.getPostID() == 10);
		model.setPostID(23);
		assert(model.getPostID() == 23);
	}
	
	@Test
	public void testSetPostID2() {
		model.setPostID(1000);
		assert(model.getPostID() == 1000);
		model.setPostID(0);
		assert(model.getPostID() == 0);
		model.setPostID(26);
		assert(model.getPostID() == 26);
	}
	
	@Test
	public void testSetUserID() {
		model.setUserID(1000);
		assert(model.getUserID() == 1000);
		model.setUserID(10);
		assert(model.getUserID() == 10);
		model.setUserID(23);
		assert(model.getUserID() == 23);
	}
	
	@Test
	public void testSetUserID2() {
		model.setUserID(1000);
		assert(model.getUserID() == 1000);
		model.setUserID(0);
		assert(model.getUserID() == 0);
		model.setUserID(26);
		assert(model.getUserID() == 26);
	}
	
	
	@Test
	public void testSetUsername() {
		model.setUsername("testewew");
		assert(model.getUsername().equals("testewew"));
		model.setUsername("test=2");
		assert(model.getUsername().equals("test=2"));
		model.setUsername("test___3!$#");
		assert(model.getUsername().equals("test___3!$#"));
	}
	
	@Test
	public void testSetUsername2() {
		model.setUsername("test9887");
		assert(model.getUsername().equals("test9887"));
		model.setUsername("test opop");
		assert(model.getUsername().equals("test opop"));
		model.setUsername("R Snyder");
		assert(model.getUsername().equals("R Snyder"));
	}
	
	@Test
	public void testSetUsername3() {
		model.setUsername("ooga");
		assert(model.getUsername().equals("ooga"));
		model.setUsername("booga");
		assert(model.getUsername().equals("booga"));
		model.setUsername("test 3!$#");
		assert(model.getUsername().equals("test 3!$#"));
	}
	
	@Test
	public void testSetUsername4() {
		model.setUsername("username");
		assert(model.getUsername().equals("username"));
		model.setUsername("tank-");
		assert(model.getUsername().equals("tank-"));
		model.setUsername("kino115");
		assert(model.getUsername().equals("kino115"));
	}
	
	@Test
	public void testSetUsername5() {
		model.setUsername("test''");
		assert(model.getUsername().equals("test''"));
		model.setUsername("test 2\"");
		assert(model.getUsername().equals("test 2\""));
		model.setUsername("test <>3!$#");
		assert(model.getUsername().equals("test <>3!$#"));
	}
	
	
	@Test
	public void testSetDateJoined() {
		model.setDateCreated("05022001");
		assert(model.getDateCreated().equals("05022001"));
		
	}
	
	@Test
	public void testSetDateJoined2() {
		model.setDateCreated("05102021");
		assert(model.getDateCreated().equals("05102021"));
		
	}
	
	@Test
	public void testSetDateJoined3() {
		model.setDateCreated("12311919");
		assert(model.getDateCreated().equals("12311919"));
		
	}
	
	@Test
	public void testSetDateJoined4() {
		model.setDateCreated("09081954");
		assert(model.getDateCreated().equals("09081954"));
		
	}
	
	@Test
	public void testSetTextStyle() {
		model.setTextStyle("Light");
		assert(model.getTextStyle().equals("Light"));
		
	}
	
	@Test
	public void testSetTextStyle2() {
		model.setTextStyle("Dark");
		assert(model.getTextStyle().equals("Dark"));
		
	}
	
	@Test
	public void testSetTextStyle3() {
		model.setTextStyle("Gold");
		assert(model.getTextStyle().equals("Gold"));
		
	}
	
	@Test
	public void testSetTextStyle4() {
		model.setTextStyle("Fire");
		assert(model.getTextStyle().equals("Fire"));
		
	}
	
	@Test
	public void testSetBackgroundColor() {
		model.setBackgroundStyle("Light");
		assert(model.getBackgroundStyle().equals("Light"));
		
	}
	
	@Test
	public void testSetBackgroundColor2() {
		model.setBackgroundStyle("Dark");
		assert(model.getBackgroundStyle().equals("Dark"));
		
	}
	
	@Test
	public void testSetBackgroundColor3() {
		model.setBackgroundStyle("Gold");
		assert(model.getBackgroundStyle().equals("Gold"));
		
	}
	
	@Test
	public void testSetBackgroundColor4() {
		model.setBackgroundStyle("Fire");
		assert(model.getBackgroundStyle().equals("Fire"));
		
	}
	
	@Test
	public void testSetLinkStyle() {
		model.setLinkStyle("Light");
		assert(model.getLinkStyle().equals("Light"));
		
	}
	
	@Test
	public void testSetLinkStyle2() {
		model.setLinkStyle("Dark");
		assert(model.getLinkStyle().equals("Dark"));
		
	}
	
	@Test
	public void testSetLinkStyle3() {
		model.setLinkStyle("Gold");
		assert(model.getLinkStyle().equals("Gold"));
		
	}
	
	@Test
	public void testSetLinkStyle4() {
		model.setLinkStyle("Fire");
		assert(model.getLinkStyle().equals("Fire"));
		
	}
	
	@Test
	public void testSetTitleStyle() {
		model.setTitleStyle("Light");
		assert(model.getTitleStyle().equals("Light"));
		
	}
	
	@Test
	public void testSetTitleStyle2() {
		model.setTitleStyle("Dark");
		assert(model.getTitleStyle().equals("Dark"));
		
	}
	
	@Test
	public void testSetTitleStyle3() {
		model.setTitleStyle("Gold");
		assert(model.getTitleStyle().equals("Gold"));
		
	}
	
	@Test
	public void testSetTitleStyle4() {
		model.setTitleStyle("Fire");
		assert(model.getTitleStyle().equals("Fire"));
		
	}
}
