package conta;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MenuPage {

	JFrame frame;
	JPanel panel;
	JMenuBar menuBar;
	JMenu registracionMenu, accountingMenu;
	JMenuItem newItem, viewItem, openItem, allEmployeesItem;

	public MenuPage() {

	}

	public void menu() {

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setLayout(null);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setResizable(false);
		frame.setVisible(true);

		menuBar = new JMenuBar();
		registracionMenu = new JMenu("Registracion");
		registracionMenu.setFont(new Font("Calibri", Font.BOLD, 20));
		registracionMenu.setForeground(Color.black);
		accountingMenu = new JMenu("Accounting");
		accountingMenu.setFont(new Font("Calibri", Font.BOLD, 20));
		accountingMenu.setForeground(Color.black);
		menuBar.add(registracionMenu);
		menuBar.add(accountingMenu);

		newItem = new JMenuItem("New");
		newItem.setFont(new Font("Calibri", Font.BOLD, 17));
		viewItem = new JMenuItem("View");
		viewItem.setFont(new Font("Calibri", Font.BOLD, 17));
		registracionMenu.add(newItem);
		registracionMenu.add(viewItem);

		openItem = new JMenuItem("Calculate salary");
		openItem.setFont(new Font("Calibri", Font.BOLD, 17));
		accountingMenu.add(openItem);
		allEmployeesItem = new JMenuItem("All employees");
		allEmployeesItem.setFont(new Font("Calibri", Font.BOLD, 17));
		accountingMenu.add(allEmployeesItem);

		frame.setJMenuBar(menuBar);

		newItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == newItem) {
					NewPage registracion = new NewPage();
					registracion.registracion();// opens the NewPage -employee registracion"
				}

			}

		});
		viewItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == viewItem) {
					ViewPage viewReg = new ViewPage();
					viewReg.viewRegistracions();// opens the ViewPage - all employees registracions

				}
			}

		});
		openItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == openItem) {
					CalculateSalaryPage open = new CalculateSalaryPage();
					open.openAccounting();// opens the CalculateSalaryPage were we calculate the accounting for eatch
											// employee
				}
			}

		});
		allEmployeesItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == allEmployeesItem) {
					AllEmployeesPage employees = new AllEmployeesPage();
					employees.showEmployees();
				}
			}

		});

	}
}
