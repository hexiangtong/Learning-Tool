package learning;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException{

		
		File f =  FileTools.chooseFile();
		String tempPath = f.getAbsolutePath() + ".temp";
		File tempFile = new File(tempPath);
		if(tempFile.exists()){
			try {
				FileTools.loadTempFile(tempFile);
			} catch (Exception e) {
				tempFile.delete();
				//main(args);
			}
		}
		else{			
			List<Word> words = FileTools.loadTextFile(f);
			new MainFrame(words);
			new Log();
					
		}
	}
}
