package learning;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExamFrameTest {
	
	private ExamFrame examframe;

	
	@Before
	public void setUp() throws Exception {
		File file = File.createTempFile("test", "file");
		examframe = new ExamFrame((List<Word>) file);
	}


	@Test
	public void testExamFrame() {
		examframe.setVisible(true);
//		Object output = examframe.setVisible(true);
//		assertEquals("words",output);
	}

	@Test
	public void testShowNext() {
		examframe.showNext();
	}

}
