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

public class ViewPage {

	JFrame frame;
	JPanel panel;
	JScrollPane scrollPane, scrollPane2;
	JTable table, searchTable;
	JButton search;
	JTextField searchTF;

	public ViewPage() {

	}

	public void viewRegistracions() {
		frame = new JFrame();
		frame.setSize(800, 600);
		frame.setTitle("Registracion/View page");
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 50, 600, 350);
		table = new JTable();
		scrollPane.setViewportView(table);

		scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(100, 50, 600, 350);
		searchTable = new JTable();
		scrollPane2.setViewportView(searchTable);

		search = new JButton("Search employee");
		search.setBounds(150, 430, 150, 40);
		search.setBackground(Color.orange);
		search.setForeground(Color.black);

		searchTF = new JTextField("by employee ID");
		searchTF.setBounds(320, 435, 120, 30);

		panel = new JPanel();
		panel.setSize(800, 600);
		panel.setBackground(Color.DARK_GRAY);
		panel.setLayout(null);
		panel.add(scrollPane);
		panel.add(scrollPane2);
		panel.add(search);
		panel.add(searchTF);
		frame.add(panel);

		// all company's employees are displayed in a table and can be searched by name
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "");
			Statement stm = con.createStatement();

			String sql = "Select * from employees";
			ResultSet rs = stm.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) table.getModel();

			int colNr = rsmd.getColumnCount();
			String[] colName = new String[colNr];
			for (int i = 0; i < colNr; i++) {
				colName[i] = rsmd.getColumnName(i + 1);
				model.setColumnIdentifiers(colName);
			}
			while (rs.next()) {
				String employeeID, name, phoneNr, adresse, jobTitle;
				employeeID = rs.getString(1);
				name = rs.getString(2);
				phoneNr = rs.getString(3);
				adresse = rs.getString(4);
				jobTitle = rs.getString(5);

				String[] row = { employeeID, name, phoneNr, adresse, jobTitle };
				model.addRow(row);
			}
			rs.next();

		} catch (Exception e) {
			System.out.println(e);
		}
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String eID = searchTF.getText();

				if (e.getSource() == search) {
					try {
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "");
						Statement stm = con.createStatement();

						String sql = "Select * from employees where employeeID= '" + eID + "' ";
						ResultSet rs = stm.executeQuery(sql);
						ResultSetMetaData rsmd = rs.getMetaData();
						DefaultTableModel model = (DefaultTableModel) searchTable.getModel();

						int colNr = rsmd.getColumnCount();
						String[] colName = new String[colNr];
						for (int i = 0; i < colNr; i++) {
							colName[i] = rsmd.getColumnName(i + 1);
							model.setColumnIdentifiers(colName);
						}
						while (rs.next()) {
							String employeeID, name, phoneNr, adresse, jobTitle;
							employeeID = rs.getString(1);
							name = rs.getString(2);
							phoneNr = rs.getString(3);
							adresse = rs.getString(4);
							jobTitle = rs.getString(5);

							String[] row = { employeeID, name, phoneNr, adresse, jobTitle };
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
