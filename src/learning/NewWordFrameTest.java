package learning;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NewWordFrameTest {

    private NewWordFrame newwordframe;
    

	@Before
	public void setUp() throws Exception {
		newwordframe = new NewWordFrame();
	}


	@Test
	public void testNewWordFrame() {
		newwordframe.setVisible(true);
	}

}
