import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.Color;


public class ATMFrame {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ATMFrame window = new ATMFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ATMFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 625, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel KeyPad = new JPanel();
		KeyPad.setBackground(SystemColor.activeCaption);
		KeyPad.setBounds(85, 335, 285, 329);
		frame.getContentPane().add(KeyPad);
		KeyPad.setLayout(new GridLayout(4, 3, 10, 10));
		
		JButton btnNewButton = new JButton("7");
		
		btnNewButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		KeyPad.add(btnNewButton);
		JButton btnNewButton_1 = new JButton("8");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		KeyPad.add(btnNewButton_1);
		JButton btnNewButton_2 = new JButton("9");
		btnNewButton_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		KeyPad.add(btnNewButton_2);
		JButton btnNewButton_3 = new JButton("4");
		btnNewButton_3.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		KeyPad.add(btnNewButton_3);
		JButton btnNewButton_4 = new JButton("5");
		btnNewButton_4.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		KeyPad.add(btnNewButton_4);
		JButton btnNewButton_5 = new JButton("6");
		btnNewButton_5.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		KeyPad.add(btnNewButton_5);
		JButton btnNewButton_6 = new JButton("1");
		btnNewButton_6.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		KeyPad.add(btnNewButton_6);
		JButton btnNewButton_7 = new JButton("2");
		btnNewButton_7.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		KeyPad.add(btnNewButton_7);
		JButton btnNewButton_8 = new JButton("3");
		btnNewButton_8.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		KeyPad.add(btnNewButton_8);
		JButton btnNewButton_9 = new JButton("0");
		btnNewButton_9.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		KeyPad.add(btnNewButton_9);
		JButton btnNewButton_10 = new JButton(".");
		btnNewButton_10.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		KeyPad.add(btnNewButton_10);
		
		JButton clearButton = new JButton("CE");
		clearButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		KeyPad.add(clearButton);
		
		textField = new JTextField();
		textField.setBounds(85, 281, 285, 43);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JPanel SelectionPad = new JPanel();
		SelectionPad.setBackground(SystemColor.activeCaption);
		SelectionPad.setBounds(428, 335, 110, 329);
		frame.getContentPane().add(SelectionPad);
		SelectionPad.setLayout(new GridLayout(4, 1, 10, 10));
		
		JButton btnA = new JButton("A");
		btnA.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		SelectionPad.add(btnA);
		
		JButton btnB = new JButton("B");
		btnB.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		SelectionPad.add(btnB);
		
		JButton btnC = new JButton("C");
		btnC.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		SelectionPad.add(btnC);
		
		JButton btnD = new JButton("D");
		btnD.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		SelectionPad.add(btnD);
		
		JPanel DisplayPanel = new JPanel();
		DisplayPanel.setBackground(SystemColor.activeCaption);
		FlowLayout fl_DisplayPanel = (FlowLayout) DisplayPanel.getLayout();
		fl_DisplayPanel.setAlignment(FlowLayout.LEFT);
		DisplayPanel.setBounds(81, 24, 457, 220);
		frame.getContentPane().add(DisplayPanel);
		
		JTextArea textArea = new JTextArea(8, 50);
		textArea.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		DisplayPanel.add(textArea);
		
		
		
		
		
	}
}
