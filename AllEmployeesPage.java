package conta;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AllEmployeesPage {

	JFrame frame;
	JPanel panel;
	JScrollPane scrollPane, scrollPane2;
	JTable employee_table, search_employee_table;
	JButton search;
	JTextField searchTF;

	public AllEmployeesPage() {

	}

	public void showEmployees() {

		frame = new JFrame();
		frame.setSize(800, 600);
		frame.setTitle("Accounting/All employees page");
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);

		panel = new JPanel();
		panel.setSize(800, 600);
		panel.setBackground(Color.DARK_GRAY);
		panel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 50, 600, 350);
		employee_table = new JTable();
		scrollPane.setViewportView(employee_table);

		scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(100, 50, 600, 350);
		search_employee_table = new JTable();
		scrollPane2.setViewportView(search_employee_table);

		search = new JButton("Search employee");
		search.setBounds(150, 430, 150, 40);
		search.setBackground(Color.orange);
		search.setForeground(Color.black);

		searchTF = new JTextField("by name");
		searchTF.setBounds(320, 435, 120, 30);

		frame.add(panel);
		panel.add(scrollPane);
		panel.add(scrollPane2);
		panel.add(search);
		panel.add(searchTF);

		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "");
			Statement stm = con.createStatement();

			String sql = "SELECT employees.name, employees.jobTitle, accounting.mounth, accounting.salary\r\n"
					+ "FROM employees\r\n" + "INNER JOIN accounting on employees.employeeID=accounting.employeeID;";
			ResultSet rs = stm.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) employee_table.getModel();

			int colNr = rsmd.getColumnCount();
			String[] colName = new String[colNr];
			for (int i = 0; i < colNr; i++) {
				colName[i] = rsmd.getColumnName(i + 1);
				model.setColumnIdentifiers(colName);
			}
			while (rs.next()) {
				String name, jobTitle, mounth, salary;
				name = rs.getString(1);
				jobTitle = rs.getString(2);
				mounth = rs.getString(3);
				salary = rs.getString(4);

				String[] row = { name, jobTitle, mounth, salary };
				model.addRow(row);

			}
			rs.next();
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);

		}
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String searchName = searchTF.getText();

				if (e.getSource() == search) {
					try {

						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "");
						Statement stm = con.createStatement();

						String sql = "SELECT employees.name, employees.jobTitle, accounting.mounth, accounting.salary\r\n"
								+ "FROM employees\r\n"
								+ "INNER JOIN accounting on employees.employeeID=accounting.employeeID where employees.name= '"
								+ searchName + "';";
						ResultSet rs = stm.executeQuery(sql);
						ResultSetMetaData rsmd = rs.getMetaData();
						DefaultTableModel model = (DefaultTableModel) search_employee_table.getModel();

						int colNr = rsmd.getColumnCount();
						String[] colName = new String[colNr];
						for (int i = 0; i < colNr; i++) {
							colName[i] = rsmd.getColumnName(i + 1);
							model.setColumnIdentifiers(colName);
						}
						while (rs.next()) {
							String name, jobTitle, mounth, salary;
							name = rs.getString(1);
							jobTitle = rs.getString(2);
							mounth = rs.getString(3);
							salary = rs.getString(4);

							String[] row = { name, jobTitle, mounth, salary };
							model.addRow(row);

						}
						rs.next();
						con.close();
					} catch (Exception ex) {
						System.out.println(ex);

					}

				}
			}

		});

	}
}
