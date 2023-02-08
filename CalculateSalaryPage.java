package conta;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CalculateSalaryPage {

	JFrame frame;

	JPanel panel;
	JLabel employeeID, mounth, hoursExtra, jobTitle, year;
	JTextField employeeTF, hoursExtraTF, jobTF, yearTF;
	JComboBox mounthCB, job_titlesCB;
	JButton calculate;
	JScrollPane scrollPane;
	JTable table;
	Employee employee;

	public CalculateSalaryPage() {

	}

	public void openAccounting() {
		frame = new JFrame();
		frame.setSize(800, 600);
		frame.setTitle("Accounting/Calculate salary page");
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

		jobTitle = new JLabel("Job title");
		jobTitle.setFont(new Font("Calibri", Font.BOLD, 20));
		jobTitle.setForeground(Color.white);
		jobTitle.setBounds(330, 100, 100, 50);

		String[] job_titles = { "accountant", "janitor", "junior java dev", "senior java dev", "manager",
				"team-leader" };
		job_titlesCB = new JComboBox(job_titles);
		job_titlesCB.setBounds(420, 100, 100, 30);

		mounth = new JLabel("Mounth");
		mounth.setFont(new Font("Calibri", Font.BOLD, 20));
		mounth.setForeground(Color.white);
		mounth.setBounds(100, 150, 100, 50);

		year = new JLabel("Year");
		year.setFont(new Font("Calibri", Font.BOLD, 20));
		year.setForeground(Color.white);
		year.setBounds(330, 150, 100, 50);

		yearTF = new JTextField();
		yearTF.setBounds(420, 150, 100, 30);

		String[] mounths = { "january", "february", "martch", "april", "may", "june", "july", "august", "september",
				"october", "november", "december" };
		mounthCB = new JComboBox(mounths);
		mounthCB.setBounds(220, 150, 100, 30);

		hoursExtra = new JLabel("Hours extra");
		hoursExtra.setFont(new Font("Calibri", Font.BOLD, 20));
		hoursExtra.setForeground(Color.white);
		hoursExtra.setBounds(100, 200, 100, 50);

		hoursExtraTF = new JTextField();
		hoursExtraTF.setBounds(220, 200, 100, 30);

		calculate = new JButton("Calculate salary");
		calculate.setBounds(220, 250, 150, 40);
		calculate.setBackground(Color.orange);
		calculate.setForeground(Color.black);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 310, 500, 100);
		table = new JTable();
		scrollPane.setViewportView(table);

		frame.add(panel);
		panel.add(employeeID);
		panel.add(mounth);
		panel.add(hoursExtra);
		panel.add(employeeTF);
		panel.add(hoursExtraTF);
		panel.add(jobTitle);
		// panel.add(jobTF);
		panel.add(mounthCB);
		panel.add(calculate);
		panel.add(scrollPane);
		panel.add(job_titlesCB);
		panel.add(year);
		panel.add(yearTF);

		// calculate the mounthly salary for each employee + hours extra if necesary and
		// update the database
		// display in a table the salary for the employee
		calculate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == calculate) {

					Employee employee = new Employee();
					String employeeID = employeeTF.getText();
					String hours = hoursExtraTF.getText();
					int hoursExtra = Integer.parseInt(hours);
					String year = yearTF.getText();
					int curent_year = Integer.parseInt(year);

					String jobTitle = "";
					String mounth = "";

					if (job_titlesCB.getSelectedItem().equals("accountant")) {
						jobTitle = "accountant";

					} else if (job_titlesCB.getSelectedItem().equals("janitor")) {
						jobTitle = "janitor";

					} else if (job_titlesCB.getSelectedItem().equals("junior java dev")) {
						jobTitle = "junior java dev";

					} else if (job_titlesCB.getSelectedItem().equals("senior java dev")) {
						jobTitle = "senior java dev";

					} else if (job_titlesCB.getSelectedItem().equals("manager")) {
						jobTitle = "manager";

					} else if (job_titlesCB.getSelectedItem().equals("team-leader")) {
						jobTitle = "team-leader";

					}

					double salary = employee.calculateSalary(jobTitle, hoursExtra);

					if (mounthCB.getSelectedItem().equals("january")) {
						mounth = "january";
					} else if (mounthCB.getSelectedItem().equals("february")) {
						mounth = "february";
					} else if (mounthCB.getSelectedItem().equals("martch")) {
						mounth = "martch";
					} else if (mounthCB.getSelectedItem().equals("april")) {
						mounth = "april";
					} else if (mounthCB.getSelectedItem().equals("may")) {
						mounth = "may";
					} else if (mounthCB.getSelectedItem().equals("june")) {
						mounth = "june";
					} else if (mounthCB.getSelectedItem().equals("july")) {
						mounth = "july";
					} else if (mounthCB.getSelectedItem().equals("august")) {
						mounth = "august";
					} else if (mounthCB.getSelectedItem().equals("september")) {
						mounth = "september";
					} else if (mounthCB.getSelectedItem().equals("october")) {
						mounth = "october";
					} else if (mounthCB.getSelectedItem().equals("november")) {
						mounth = "november";
					} else if (mounthCB.getSelectedItem().equals("december")) {
						mounth = "december";
					}

					try {

						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "");
						Statement stm = con.createStatement();

						String sql = "Insert into accounting values ('" + employeeID + "', '" + mounth + "' ,'"
								+ hoursExtra + "', " + salary + ", " + curent_year + ") ";
						stm.executeUpdate(sql);

						String sql2 = "Select * from accounting where employeeID= '" + employeeID + "'";
						ResultSet rs = stm.executeQuery(sql2);
						ResultSetMetaData rsmd = rs.getMetaData();
						DefaultTableModel model = (DefaultTableModel) table.getModel();

						int colNr = rsmd.getColumnCount();
						String[] colName = new String[colNr];
						for (int i = 0; i < colNr; i++) {
							colName[i] = rsmd.getColumnName(i + 1);
							model.setColumnIdentifiers(colName);
						}
						while (rs.next()) {
							String empID, mounthT, hoursT, salaryT, yearT;
							empID = rs.getString(1);
							mounthT = rs.getString(2);
							hoursT = rs.getString(3);
							salaryT = rs.getString(4);
							yearT = rs.getString(5);

							String[] row = { empID, mounthT, hoursT, salaryT, yearT };
							model.addRow(row);
						}
						rs.next();

					} catch (Exception ex) {
						System.out.println(ex);
					}
				}
			}

		});

	}

}
