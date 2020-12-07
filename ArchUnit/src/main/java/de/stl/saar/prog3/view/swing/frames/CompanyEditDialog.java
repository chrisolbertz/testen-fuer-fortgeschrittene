package de.stl.saar.prog3.view.swing.frames;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import de.stl.saar.prog3.factories.ModelFactory;
import de.stl.saar.prog3.factories.ServiceFactory;
import de.stl.saar.prog3.model.interfaces.Company;
import de.stl.saar.prog3.service.interfaces.CompanyService;
import de.stl.saar.prog3.utils.StringUtils;


public class CompanyEditDialog {
	private JDialog theDialog;
	private JPanel pnlRoot;
	private CompanyService companyService;
	private JTextField txtCompanyName;
	private JTextField txtEmployeeCount;
	
	private static final int DIALOG_WIDTH = 600;
	private static final int DIALOG_HEIGHT = 200;
	private static final boolean MODAL = true;
	
	public CompanyEditDialog(final Company company, final JDialog owner) {
		companyService = ServiceFactory.createCompanyService();
		initFrame(company, owner);
		initForm(company);
		initButtons(company);
		theDialog.setVisible(true);
	}
	
	private void initFrame(final Company company, final JDialog owner) {
		String title = StringUtils.EMPTY_STRING;
		if (company != null) {
			title = "Firma ändern";
		} else {
			title = "Firma anlegen";
		}
		
		theDialog = new JDialog(owner, MODAL);
		theDialog.setTitle(title);
		pnlRoot = new JPanel();
		pnlRoot.setLayout(new BoxLayout(pnlRoot, BoxLayout.Y_AXIS));
		theDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		theDialog.setSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
		theDialog.setSize(500, 500);
		theDialog.getContentPane().add(pnlRoot);
	}
	
	private void initForm(final Company company) {
		final JLabel lblCompanyName = new JLabel("Name");
		final JLabel lblEmployeeCount = new JLabel("Mitarbeiteranzahl");
		txtCompanyName = new JTextField();
		txtEmployeeCount = new JTextField();
		
		if (company != null) {
			txtCompanyName.setText(company.getCompanyName());
			final int employeesCount = company.getEmployeesCount(); 
			txtEmployeeCount.setText(String.valueOf(employeesCount));
		}
		
		final JPanel pnlForm = new JPanel();
		pnlForm.setLayout(new GridLayout(2, 2));
		pnlForm.add(lblCompanyName);
		pnlForm.add(txtCompanyName);
		pnlForm.add(lblEmployeeCount);
		pnlForm.add(txtEmployeeCount);
		pnlRoot.add(pnlForm);
	}
	
	private void initButtons(final Company company) {
		final JButton btnOk = new JButton("Ok");
		final JButton btnCancel = new JButton("Abbrechen");
		final JPanel pnlButtons = new JPanel();
		pnlButtons.add(btnOk);
		pnlButtons.add(btnCancel);
		
		btnOk.addActionListener(event -> {
			saveCompany(company);
			theDialog.dispose();
		});
		
		btnCancel.addActionListener(event-> theDialog.dispose());
		
		pnlRoot.add(pnlButtons);
	}
	
	private void saveCompany(final Company company) {
		final String companyName = txtCompanyName.getText();
		final int employeesCount = Integer.parseInt(txtEmployeeCount.getText());
		if (company != null) {
			company.setCompanyName(companyName);
			company.setEmployeesCount(employeesCount);
			companyService.upateCompany(company);
		} else {
			final Company newCompany = ModelFactory.createCompany(companyName, employeesCount);
			companyService.saveNewCompany(newCompany);
		}
	}
}
