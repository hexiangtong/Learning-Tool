package learning;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class FileToolsTest {
	
	private FileTools filetools;
    private List<Word> words;
	
	@Before
	public void setUp() throws Exception {
		filetools = new FileTools();
	}

	@Test
	public void testLoadTextFile() throws IOException {
		File file = File.createTempFile("test", "file");
		FileWriter writer = new FileWriter(file);
		writer.write("words");
		writer.close();
		List<Word> output = filetools.loadTextFile(file);
		assertEquals(words,output);
	}
	
	@Test
	public void testChooseFile() {
		filetools.chooseFile();
		assertEquals(words,filetools.chooseFile().getAbsoluteFile());

	}

	@Ignore
	@Test
	public void testLoadTempFile() throws FileNotFoundException, ClassNotFoundException, IOException {
		filetools.loadTempFile(null);
	}
	
	@Ignore
	@Test
	public void testSaveTempFile() throws FileNotFoundException, ClassNotFoundException, IOException {
		filetools.saveTempFile(null, getClass());
	}

}
