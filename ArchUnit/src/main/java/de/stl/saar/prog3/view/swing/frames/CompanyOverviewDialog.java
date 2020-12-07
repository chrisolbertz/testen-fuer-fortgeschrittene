package de.stl.saar.prog3.view.swing.frames;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.TableModel;

import de.stl.saar.prog3.factories.ServiceFactory;
import de.stl.saar.prog3.model.interfaces.Company;
import de.stl.saar.prog3.service.interfaces.CompanyService;
import de.stl.saar.prog3.view.swing.tablemodels.CompanyTableModel;

public class CompanyOverviewDialog {
	private JDialog theDialog;
	private CompanyService companyService;
	private List<Company> companys;
	private JPanel pnlRoot;
	private Company selectedCompany;
	private JTable tblCompanys;
	
	public CompanyOverviewDialog() {
		companyService = ServiceFactory.createCompanyService();
		initFrame();
		initTable();
		initButtons();
		theDialog.pack();
	}
	
	private void initFrame() {
		theDialog = new JDialog();
		pnlRoot = new JPanel();
		pnlRoot.setLayout(new BoxLayout(pnlRoot, BoxLayout.Y_AXIS));
		theDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		theDialog.setVisible(true);
		theDialog.setSize(500, 500);
		theDialog.getContentPane().add(pnlRoot);
	}
	
	private void initTable() {
		tblCompanys = new JTable();
		final JPanel pnlTable = new JPanel();
		companys = companyService.findAllCompanies();
		pnlTable.setLayout(new BoxLayout(pnlTable, BoxLayout.X_AXIS));
		
		final TableModel companysTableModel = new CompanyTableModel(companys);
		tblCompanys.setModel(companysTableModel);
		
		tblCompanys.getSelectionModel().addListSelectionListener(event -> {
			final int selectedRow = tblCompanys.getSelectedRow();
			if (selectedRow >= 0) {
				selectedCompany = companys.get(selectedRow);
			}
		});
		final JScrollPane scroller = new JScrollPane(tblCompanys);
		pnlTable.add(scroller);
		pnlRoot.add(pnlTable);
	}
	
	private void initButtons() {
		final JPanel pnlButtons = new JPanel();
		pnlButtons.setLayout(new FlowLayout());
		
		pnlButtons.add(initBtnNewCompany());
		pnlButtons.add(initBtnEditCompany());
		pnlButtons.add(initBtnDeleteCompany());
		pnlRoot.add(pnlButtons);
	}
	
	private JButton initBtnNewCompany() {
		final JButton btnNewCompany = new JButton("Neu");
		btnNewCompany.addActionListener(event -> {
			new CompanyEditDialog(null, theDialog);
			tblCompanys.tableChanged(null);
			theDialog.repaint();
		});
		
		return btnNewCompany;
	}
	
	private JButton initBtnEditCompany() {
		final JButton btnEditCompany = new JButton("Ändern");
		btnEditCompany.addActionListener(event -> {
			new CompanyEditDialog(selectedCompany, theDialog);
			tblCompanys.tableChanged(null);
			theDialog.repaint();
		});
		
		return btnEditCompany;
	}
	
	private JButton initBtnDeleteCompany() {
		final JButton btnDeleteCompany = new JButton("Löschen");
		btnDeleteCompany.addActionListener(event -> {
			companyService.deleteCompany(selectedCompany);
			tblCompanys.tableChanged(null);
			theDialog.repaint();
		});
		
		return btnDeleteCompany;
	}
}
