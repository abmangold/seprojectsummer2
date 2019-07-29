import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;


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
		frame.setBounds(100, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(85, 335, 285, 329);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(4, 3, 10, 10));
		
		JButton btnNewButton = new JButton("7");
		btnNewButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		panel.add(btnNewButton);
		JButton btnNewButton_1 = new JButton("8");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		panel.add(btnNewButton_1);
		JButton btnNewButton_2 = new JButton("9");
		btnNewButton_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		panel.add(btnNewButton_2);
		JButton btnNewButton_3 = new JButton("4");
		btnNewButton_3.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		panel.add(btnNewButton_3);
		JButton btnNewButton_4 = new JButton("5");
		btnNewButton_4.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		panel.add(btnNewButton_4);
		JButton btnNewButton_5 = new JButton("6");
		btnNewButton_5.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		panel.add(btnNewButton_5);
		JButton btnNewButton_6 = new JButton("1");
		btnNewButton_6.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		panel.add(btnNewButton_6);
		JButton btnNewButton_7 = new JButton("2");
		btnNewButton_7.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		panel.add(btnNewButton_7);
		JButton btnNewButton_8 = new JButton("3");
		btnNewButton_8.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		panel.add(btnNewButton_8);
		JButton btnNewButton_9 = new JButton("0");
		btnNewButton_9.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		panel.add(btnNewButton_9);
		JButton btnNewButton_10 = new JButton(".");
		btnNewButton_10.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		panel.add(btnNewButton_10);
		
		JButton clearButton = new JButton("CE");
		clearButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		panel.add(clearButton);
		
		textField = new JTextField();
		textField.setBounds(85, 255, 285, 43);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		
		
		
	}
}
