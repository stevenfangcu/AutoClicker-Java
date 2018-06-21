import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Random;



public class autoClickapp extends Thread{
	
	private JFrame frame;
	private volatile static JTextField textField;	// min
	private volatile static JTextField textField_1; // max	
	
	private static Robot robot; 
	private static int delay; // delay counter 
	private volatile static boolean onOff = false; // on/off boolean value
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					autoClickapp window = new autoClickapp();
					Thread t = new Thread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							while(true) {
								int min = Integer.parseInt(textField.getText());
								int max = Integer.parseInt(textField_1.getText());
								leftClick(InputEvent.BUTTON1_MASK,min,max);
							}
						}
						
					});
					t.start();
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
	public autoClickapp() {
		//initializing the window
		initialize();
		//initializing the robot
		try {
			robot = new Robot();
		}catch (Exception e) {
			e.printStackTrace();
		}
		// default delay just a random number
		delay = 300; 
	}
// functions for the auto clicker portion
	public static void leftClick(int button, int min, int max){
		Random rand = new Random();
		try {
			while(onOff){
			//for(int i = 0; i < 1 ; i++) {
				delay = rand.nextInt((max - min) + 1) + min;
				robot.mousePress(button);
				robot.delay(delay);
				robot.mouseRelease(button);
				System.out.println("a1");
				Thread.sleep(1000); 
				if(onOff == false){
					break;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
// when to turn on and off the auto clicker
	public void setOnOff() {
		// just switching the boolean without having to worry about it in the other class
		onOff = !onOff;
		System.out.println(onOff);
	}
	/**
	 * Initialize the contents of the frame.
	 */
// all of the gui
	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 548, 289);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Random delays between clicks in milliseconds");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		
		textField = new JTextField();
		textField.setText("0");
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("1");
		textField_1.setColumns(10);
		
		frame.addKeyListener(new KeyAdapter() {
			String input = "";
			private char keypress = 'a';
			@Override
			public void keyPressed(KeyEvent e) {
				keypress = e.getKeyChar();
				input = textField.getText();
				if(keypress == '*') {
					System.out.println(input);
				}
			}
		});
		
		frame.getContentPane().addKeyListener(new KeyAdapter() {
			String input = "";
			private char keypress = 'a';
			@Override
			public void keyPressed(KeyEvent e) {
				keypress = e.getKeyChar();
				input = textField.getText();
				if(keypress == '*') {
					System.out.println(input);
				}
			}
		});
		
		JButton btnRegisterKey = new JButton("Press * key or click to start");
		btnRegisterKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnRegisterKey.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("start");
			}
		});
		
		btnRegisterKey.addKeyListener(new KeyAdapter() {
			private char keypress = 'a';
			@Override
			public void keyPressed(KeyEvent e) {
				keypress = e.getKeyChar();
				if(keypress == '*') {
					setOnOff();
				}
			}
		});
		
		
		JLabel lblTo = new JLabel("to");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(169)
					.addComponent(btnRegisterKey, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
					.addGap(158))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(137, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblTo)
							.addGap(46)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
					.addGap(125))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(48)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTo)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
					.addComponent(btnRegisterKey)
					.addGap(32))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
