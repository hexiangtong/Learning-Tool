package learning;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class WordTest {

	private Word word;

	@Before
	public void setUp() throws Exception {
		word = new Word();
	}
    

	@Test
	public void testHashCode() {
		word.hashCode();
	}
    
	@Ignore
	@Test
	public void testIsNewWord() {
		word.isNewWord();
	}

	@Ignore
	@Test
	public void testSetNewWord() {
		word.setNewWord(true);
	}

	@Test
	public void testGetWord() {
		word.getWord();
	}
    
//	@Ignore
//	@Test
//	public void testSetWord() {
//		word.setWord(word);
//	}

	@Test
	public void testGetPro() {
		word.getPro();
	}

//	@Test
//	public void testSetPro() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGetMeaning() {
		word.getMeaning();
	}

//	@Test
//	public void testSetMeaning() {
//		fail("Not yet implemented");
//	}


	@Test
	public void testIsHasLearned() {
		word.isHasLearned();
	}

//	@Test
//	public void testSetHasLearned() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testEqualsObject() {
		word.getClass();
	}

}
