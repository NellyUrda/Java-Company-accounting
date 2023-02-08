package conta;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {

	JFrame frame;
	JPanel panel;
	JLabel titleL, ussernameL, passwordL;
	JTextField ussernameTF;
	JPasswordField passPF;
	JButton loginB;

	HashMap<String, String> login = new HashMap<String, String>();

	public Login() {

	}

	public void login() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Login Page");
		frame.setSize(800, 600);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);

		panel = new JPanel();
		panel.setSize(800, 600);
		panel.setBackground(Color.DARK_GRAY);
		panel.setLayout(null);

		titleL = new JLabel("Company's accounting");
		titleL.setFont(new Font("Calibri", Font.BOLD, 30));
		titleL.setBounds(200, 130, 300, 100);
		titleL.setForeground(Color.orange);

		ussernameL = new JLabel("Ussername");
		ussernameL.setFont(new Font("Calibri", Font.BOLD, 20));
		ussernameL.setBounds(100, 200, 100, 100);
		ussernameL.setForeground(Color.black);

		passwordL = new JLabel("Password");
		passwordL.setFont(new Font("Calibri", Font.BOLD, 20));
		passwordL.setBounds(100, 250, 100, 100);
		passwordL.setForeground(Color.black);

		ussernameTF = new JTextField();
		ussernameTF.setBounds(220, 230, 100, 30);

		passPF = new JPasswordField(10);
		passPF.setBounds(220, 280, 100, 30);

		loginB = new JButton("Login");
		loginB.setBounds(220, 330, 100, 40);
		loginB.setBackground(Color.orange);
		loginB.setForeground(Color.black);

		frame.add(panel);
		panel.add(titleL);
		panel.add(ussernameL);
		panel.add(passwordL);
		panel.add(ussernameTF);
		panel.add(passPF);
		panel.add(loginB);

		loginB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				login.put("maria", "M2345");
				login.put("jhon", "J1234");

				String ussername = ussernameTF.getText();
				String password = passPF.getText();
				MenuPage menu = new MenuPage();

				if (e.getSource() == loginB) {

					Boolean autentification = false;
					for (Entry<String, String> entry : login.entrySet()) {
						if (entry.getKey().equals(ussername) && entry.getValue().equals(password)) {
							autentification = true;
							menu.menu();// opens the "Menu page"
							frame.dispose();
						}
					}
					if (autentification == false) {

						JOptionPane.showMessageDialog(null, "Wrong username or password !", "Message",
								JOptionPane.INFORMATION_MESSAGE);

					}

				}

			}

		});
	}
}
