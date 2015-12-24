package learning;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;


class MainFrame extends JFrame implements Serializable,Cloneable{

	private static final long serialVersionUID = 8290863009353919652L;

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		//MainFrame.main(args);
		MainFrame frameTabel = new MainFrame();
	}
	
	private final JToolBar toolBarTop = new JToolBar();
	private List<Word> words;
	private Index idx;
	private enum Act{LAST,NEXT};
	private JCheckBox chckbxShowPro = new JCheckBox("Pro");//显示音标
	private Component horizontalGlue = Box.createHorizontalGlue();		
	private JLabel lbMeaning = new JLabel("Meaning");
	private Component verticalStrut = Box.createVerticalStrut(30);		
	private JLabel lbPro = new JLabel("Pronunciation");		
	private JLabel lbWord = new JLabel("Word");		
	private JPanel panelContent = new JPanel();		
	private Component horizontalGlue_1 = Box.createHorizontalGlue();
	private JButton btnLast = new JButton("last");
	private JPanel panel = new JPanel();		
	private JButton btnNewWord = new JButton("review marked words");
	private JButton btnExam = new JButton("exam");
	private JCheckBox chckbxNewWord = new JCheckBox("mark");
	private JRadioButton rdbtnClose = new JRadioButton("close");
	private JRadioButton rdbtn2s = new JRadioButton("2sec");		
	private JRadioButton rdbtn10s = new JRadioButton("10sec");
	private JRadioButton rdbtn5s = new JRadioButton("5sec");
	private JLabel labelSpeed = new JLabel("auto");
	private JToolBar toolBarBottom = new JToolBar();
	private JButton btnNext = new JButton("next");
	private File tempFile;
	private static Timer timer;
	private static boolean isCanceled = true;
	
	public MainFrame(List<Word> words) { 
		this.words = words;
		//this.tempFile = tempFile;
		this.idx = new Index(words.size());
//		System.out.println(words.size());
		MainFrame();
		this.setVisible(true);
	}
	

	public MainFrame() {
	}


	public void setVisible(boolean value){
		super.setVisible(value);
	}
	
	/**
	 * 重写监听器，来实现关闭后自动保存
	 */
	
	@Override
	protected void processWindowEvent(WindowEvent e) {
		if(e.getID() == WindowEvent.WINDOW_CLOSING){
			try {
				this.setVisible(false);
				FileTools.saveTempFile(tempFile,this);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void MainFrame() {
		this.setBounds(100, 100, 550, 260);
		this.setTitle("Learning Tool");
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		this.getContentPane().add(toolBarBottom, BorderLayout.SOUTH);
		
		toolBarBottom.add(labelSpeed);
		
		rdbtnClose.setSelected(true);
		
		toolBarBottom.add(rdbtnClose);
		toolBarBottom.add(rdbtn2s);
		toolBarBottom.add(rdbtn5s);
		toolBarBottom.add(rdbtn10s);
		
		toolBarBottom.add(chckbxNewWord);
		this.getContentPane().add(toolBarTop, BorderLayout.NORTH);

		toolBarTop.add(btnNewWord);
		
		toolBarTop.add(btnExam);

		this.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		

		chckbxShowPro.setSelected(true);
		toolBarBottom.add(chckbxShowPro);
		panel.add(btnLast);
		panel.add(horizontalGlue_1);
		panel.add(panelContent);
		panelContent.setLayout(new BoxLayout(panelContent, BoxLayout.Y_AXIS));

		lbWord.setFont(new Font("consolas", Font.PLAIN, 45));
		panelContent.add(lbWord);

		lbPro.setFont(new Font("consolas", Font.ITALIC, 18));
		panelContent.add(lbPro);
		panelContent.add(verticalStrut);
		lbMeaning.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		panelContent.add(lbMeaning);
		
		panel.add(horizontalGlue);
		panel.add(btnNext);
		
		this.addListeners();
		freshUI();
	}
	
	public void addListeners() {
		this.btnLast.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showWord(Act.LAST);
			}
		});
		
		this.btnNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showWord(Act.NEXT);
			}
			});
		
		this.chckbxNewWord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.words.get(MainFrame.this.idx.getIndex()).setNewWord(MainFrame.this.chckbxNewWord.isSelected());
				MainFrame.this.freshUI();
			}
		});
		
        this.chckbxShowPro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.freshUI();
			}
		});
        
        class SpeedChangeListener implements ActionListener{
        	private long speed;
        	public SpeedChangeListener(long speed) {
        		this.speed = speed;
        	}

			@Override
			public void actionPerformed(ActionEvent e) {
				rdbtnClose.setSelected(false);
				rdbtn2s.setSelected(false);
				rdbtn5s.setSelected(false);
				rdbtn10s.setSelected(false);
				
				((JRadioButton)(e.getSource())).setSelected(true);
				
				if (!isCanceled){
					timer.cancel();
					isCanceled = true;
				}
				if (speed != 0){
					timer = new Timer(true);
					timer.schedule(new TimerTask() {

						@Override
						public void run() {
							try {
								SwingUtilities.invokeAndWait(()->{
									showWord(Act.NEXT);
								});
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}						
						}						
					}, speed, speed);
					isCanceled = false;
				}
			}       	
        }
        
        this.rdbtnClose.addActionListener(new SpeedChangeListener(0));
		this.rdbtn2s.addActionListener(new SpeedChangeListener(2000));
		this.rdbtn5s.addActionListener(new SpeedChangeListener(5000));
		this.rdbtn10s.addActionListener(new SpeedChangeListener(10000));
		this.btnNewWord.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!MainFrame.isCanceled){
					timer.cancel();
					isCanceled = true;
				}
				rdbtnClose.setSelected(false);
				rdbtn2s.setSelected(false);
				rdbtn5s.setSelected(false);
				rdbtn10s.setSelected(false);
				rdbtnClose.setSelected(true);
				new NewWordFrame(words);
			}});
		    this.btnExam.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new ExamFrame(words);
				}	    	
		    });

		}
	
	public void showWord(Act act){
		switch (act) {
		case LAST:
			idx.minusThenReturn();
			break;
		case NEXT:
			idx.plusThenReturn();
			break;
		default:
			break;
		}
		freshUI();
	}

	private void freshUI() {
		Word word = words.get(idx.getIndex());
		word.setHasLearned(true);
		this.lbWord.setText(word.getWord());
		this.lbPro.setText(word.getPro());
		this.lbMeaning.setText(word.getMeaning());
		
		if (word.isNewWord()) {
			this.chckbxNewWord.setSelected(true);
			this.chckbxNewWord.setText("mark unknown words");
		} else {
			this.chckbxNewWord.setSelected(false);
			this.chckbxNewWord.setText("mark unknown words");
		}
		this.lbPro.setVisible(chckbxShowPro.isSelected());
	}
	public void resume(){
		setVisible(true);
		addListeners();
		rdbtnClose.setSelected(false);
		rdbtn2s.setSelected(false);
		rdbtn5s.setSelected(false);
		rdbtn10s.setSelected(false);
		rdbtnClose.setSelected(true);
	}
	
}
