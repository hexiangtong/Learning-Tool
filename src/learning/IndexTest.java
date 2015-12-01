package learning;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class IndexTest {
	private Index index;

	@Before
	public void setUp() throws Exception {
		index = new Index();
	}

	@Test
	public void testGetIndex() {
		index.getIndex();
	}

//	@Ignore
//	@Test
//	public void testPlusThenReturn() {
//		index.plusThenReturn();
//	}
    
//	@Ignore
//	@Test
//	public void testMinusThenReturn() {
//		index.minusThenReturn();
//	}

}
