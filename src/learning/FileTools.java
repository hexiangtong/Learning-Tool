package learning;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

class FileTools {
	
	public static List<Word> loadTextFile(File file) throws IOException{
		List<Word> list = Collections.synchronizedList(new LinkedList<Word>());
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), 
				Charset.forName(getCharSet(file)))); 
		
		String line;
		
		while((line = br.readLine()) != null){
			line = line.trim();
			String[] strs = line.split("\t");
			Word word = new Word(strs[0],strs[2],strs[1]);
			list.add(word);
		}
		br.close();
		return list;
	}
	
	private static String getCharSet(File file) throws IOException {
		FileInputStream in = new FileInputStream(file);
		CharsetDetector decoder = new CharsetDetector();
		String [] strs = decoder.detectAllCharset(in);
		in.close();
		for(String str:strs){
			System.out.println(str);
		}
		return strs[0];
	}
	
	public static File chooseFile(){
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				// TODO 自动生成的方法
				return "*.txt";
			}

			@Override
			public boolean accept(File f) {
				if(f.isDirectory()){
					return true;
				}
				if(f.getName().endsWith(".txt")){
					return true;
				}
				return false;
			}
		});
		
		File file = null;
		chooser.setDialogTitle("choosing file:");
		chooser.showOpenDialog(null);
		if(chooser.getSelectedFile() == null||!chooser.getSelectedFile().exists()){
			int a = JOptionPane.showOptionDialog(null, "no selected file!\n continue to select？", "hint",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, 
					(Object[]) new String[]{"continue","exit"}, 0);
			if(a == 0){
				file = FileTools.chooseFile();
			}else{
				System.exit(0);
			}
		}else{
			file = chooser.getSelectedFile();
		}
		return file;
	}
	
	public static Object loadTempFile(File f) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
		MainFrame frame = (MainFrame) ois.readObject();
		frame.setVisible(true);
		//frame.resume();
		ois.close();
		return frame;
	}
	
	public static void saveTempFile(File f,Object obj) throws FileNotFoundException, IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
		oos.writeObject(obj);
		oos.close();
		System.exit(0);
	}

}
