package learning;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ExamFrame extends JFrame{
	
	private JPanel contentPane;
	private JTextField textField;
	private List<Word> words;
	private JLabel lbChinese = new JLabel("中文");
	private Index idx;
	private int total = 0;
	private int correct = 0;
	/**
	 * Create the frame.
	 */
	public ExamFrame(List<Word> words) {
		idx = new Index(words.size());
		this.words = words;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 100);
		this.setTitle("Exam");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));	
		contentPane.add(lbChinese);
		
		textField = new JTextField();
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNext = new JButton("next");
		btnNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (textField.getText().toLowerCase().equals(words.get(idx.getIndex()).getWord().trim().toLowerCase())) {
					correct++;
					total++;
				}else {
					words.get(idx.getIndex()).setNewWord(true);
					total++;
				}
				showNext();
			}			
		});
		contentPane.add(btnNext);
		
		JButton btnEnd = new JButton("End");
		btnEnd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().toLowerCase().equals(words.get(idx.getIndex()).getWord().trim().toLowerCase())) {
					correct++;
					total++;
				}else {
					words.get(idx.getIndex()).setNewWord(true);
					total++;								
			    }
			showNext();
			JOptionPane.showMessageDialog(null, "Accuracy is" + (int)(((double)correct/total)*100) + "%\n add wrong answer into newWord file ");
		    dispose();  
		    }		
		});
		contentPane.add(btnEnd); //end button
		setVisible(true);
		freshUI();
	
	}
	
	public ExamFrame() {
	}

	public void freshUI(){
		lbChinese.setText(words.get(idx.getIndex()).getMeaning());
		System.out.println(words.get(idx.getIndex()).getMeaning());
		textField.setText("");
		
	}
	public void showNext() {
		// TODO Auto-generated method stub
		idx.minusThenReturn();
		freshUI();
	}

}
