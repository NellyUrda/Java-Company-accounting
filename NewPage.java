package conta;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewPage {

	JFrame frame;
	JPanel panel;
	JLabel employeeID, name, phoneNr, adresse, jobTitle;
	JComboBox jobTitleCB;
	JTextField employeeTF, nameTF, adresseTF, phoneNrTF;
	JButton addEmployee;

	Employee employee = new Employee();

	public NewPage() {

	}

	public void registracion() {
		frame = new JFrame();
		frame.setSize(800, 600);
		frame.setTitle("Registracion/New page");
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);

		panel = new JPanel();
		panel.setSize(800, 600);
		panel.setBackground(Color.DARK_GRAY);
		panel.setLayout(null);

		employeeID = new JLabel("EmployeeID");
		employeeID.setFont(new Font("Calibri", Font.BOLD, 20));
		employeeID.setForeground(Color.white);
		employeeID.setBounds(100, 100, 100, 50);

		employeeTF = new JTextField();
		employeeTF.setBounds(220, 100, 100, 30);

		name = new JLabel("Name");
		name.setFont(new Font("Calibri", Font.BOLD, 20));
		name.setForeground(Color.white);
		name.setBounds(100, 150, 100, 50);

		nameTF = new JTextField();
		nameTF.setBounds(220, 150, 100, 30);

		phoneNr = new JLabel("PhoneNr");
		phoneNr.setFont(new Font("Calibri", Font.BOLD, 20));
		phoneNr.setForeground(Color.white);
		phoneNr.setBounds(100, 200, 100, 50);

		phoneNrTF = new JTextField();
		phoneNrTF.setBounds(220, 200, 100, 30);

		adresse = new JLabel("Adresse");
		adresse.setFont(new Font("Calibri", Font.BOLD, 20));
		adresse.setForeground(Color.white);
		adresse.setBounds(100, 250, 100, 50);

		adresseTF = new JTextField();
		adresseTF.setBounds(220, 250, 100, 30);

		jobTitle = new JLabel("Job title");
		jobTitle.setFont(new Font("Calibri", Font.BOLD, 20));
		jobTitle.setForeground(Color.white);
		jobTitle.setBounds(100, 300, 100, 50);

		String[] positions = { "accountant", "janitor", "junior java dev", "senior java dev", "manager",
				"team-leader" };
		jobTitleCB = new JComboBox(positions);
		jobTitleCB.setBounds(220, 310, 100, 30);

		addEmployee = new JButton("Add employee");
		addEmployee.setBounds(220, 360, 150, 40);
		addEmployee.setBackground(Color.orange);

		frame.add(panel);
		panel.add(employeeID);
		panel.add(name);
		panel.add(phoneNr);
		panel.add(adresse);
		panel.add(jobTitle);
		panel.add(jobTitleCB);
		panel.add(addEmployee);
		panel.add(employeeTF);
		panel.add(nameTF);
		panel.add(adresseTF);
		panel.add(phoneNrTF);

		// add new employee to the company's database
		addEmployee.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String name = nameTF.getText();
				String phone = phoneNrTF.getText();
				String adresse = adresseTF.getText();
				String employeeID = employeeTF.getText();
				String job = "";

				if (jobTitleCB.getSelectedItem().equals("accountant")) {
					job = "accountant";
				} else if (jobTitleCB.getSelectedItem().equals("janitor")) {
					job = "janitor";
				} else if (jobTitleCB.getSelectedItem().equals("junior java dev")) {
					job = "junior java dev";

				} else if (jobTitleCB.getSelectedItem().equals("senior java dev")) {
					job = "senior java dev";

				} else if (jobTitleCB.getSelectedItem().equals("manager")) {
					job = "manager";

				} else if (jobTitleCB.getSelectedItem().equals("team-leader")) {
					job = "team-leader";
				}

				if (e.getSource() == addEmployee) {

					if (employeeID.equals("") || name.equals("") || phone.equals("") || adresse.equals("")) {
						JOptionPane.showMessageDialog(null, "All fields are mandatory! ");

					} else {
						employee.setEmployeeID(employeeID);
						employee.setName(name);
						int phoneNr = Integer.parseInt(phone);
						employee.setPhoneNr(phoneNr);
						employee.setAdresse(adresse);
						employee.setJobTitle(job);

						try {
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root",
									"");
							Statement stm = con.createStatement();

							String sql = "INSERT INTO employees VALUES('" + employee.getEmployeeID() + "','"
									+ employee.getName() + "'," + employee.getPhoneNr() + ",'" + employee.getAdresse()
									+ "', '" + employee.getJobTitle() + "') ";
							stm.executeUpdate(sql);
							JOptionPane.showMessageDialog(null, "Employee registered");

						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}

		});
	}
}
