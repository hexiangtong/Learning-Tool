package learning;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

class NewWordFrame extends JFrame {
	
	private JPanel contentPane;

	public NewWordFrame(List<Word> words) {
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.setTitle("New Words");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		int cnt = 0;
		
		for (Word w: words) {
			if (w.isNewWord()) {
				cnt++;
			}
		}
		
		String contents[] = new String[cnt];
		int wordIdx = 0;
		for (int i = 0; i < cnt; i++){
			Word w;
			do {
				w = words.get(wordIdx);
				wordIdx++;
			}while (!w.isNewWord());
			contents[i] = w.getWord() + " " + " " + w.getMeaning();
			
//			
		}
		
		JList<String> list = new JList<String>(contents);
		JScrollPane pane = new JScrollPane(list);
		contentPane.add(pane, BorderLayout.CENTER);
		contentPane.add(list, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
}

