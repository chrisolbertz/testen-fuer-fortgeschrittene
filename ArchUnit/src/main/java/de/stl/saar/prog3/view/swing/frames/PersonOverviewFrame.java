package de.stl.saar.prog3.view.swing.frames;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import de.stl.saar.prog3.facades.ServiceFacade;
import de.stl.saar.prog3.factories.ServiceFactory;
import de.stl.saar.prog3.model.interfaces.Person;
import de.stl.saar.prog3.service.interfaces.PersonService;
import de.stl.saar.prog3.view.swing.tablemodels.PersonTableModel;

public class PersonOverviewFrame {
	private JFrame theFrame;
	private ServiceFacade serviceFacade;
	private List<Person> persons;
	private JPanel pnlRoot;
	private Person selectedPerson;
	private JTable tblPersons;
	
	private JButton initBtnDeletePerson() {
		final JButton btnDeletePerson = new JButton("Löschen");
		btnDeletePerson.addActionListener(event -> {
			serviceFacade.deletePerson(selectedPerson);
			tblPersons.tableChanged(null);
			theFrame.repaint();
		});
		
		return btnDeletePerson;
	}
	
	public PersonOverviewFrame() {
		serviceFacade = ServiceFacade.getInstance();
		initFrame();
		initTable();
		initButtons();
		initMainMenu();
		theFrame.pack();
	}
	
	private void initMainMenu() {
		JMenuBar jMenuBar = new JMenuBar();
		JMenu mnuFile = new JMenu("Datei");
		JMenuItem mnuFileClose = new JMenuItem("Beenden");
		mnuFileClose.addActionListener(event -> theFrame.dispose());
		JMenu mnuAdministration = new JMenu("Verwaltung");
		JMenuItem mnuAdministrationCompanies = new JMenuItem("Firmen");
		JMenuItem mnuAdministrationEquipment = new JMenuItem("Ausrüstung");
		
		mnuAdministrationCompanies.addActionListener(event -> new CompanyOverviewDialog());
		mnuAdministrationEquipment.addActionListener(event -> new EquipmentOverviewDialog());
		
		mnuAdministration.add(mnuAdministrationEquipment);
		mnuAdministration.add(mnuAdministrationCompanies);
		mnuFile.add(mnuFileClose);
		jMenuBar.add(mnuFile);
		jMenuBar.add(mnuAdministration);
		theFrame.setJMenuBar(jMenuBar);
	}

	private void initFrame() {
		theFrame = new JFrame();
		pnlRoot = new JPanel();
		pnlRoot.setLayout(new BoxLayout(pnlRoot, BoxLayout.Y_AXIS));
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		theFrame.setVisible(true);
		theFrame.setSize(500, 500);
		theFrame.getContentPane().add(pnlRoot);
	}
	
	private void initTable() {
		tblPersons = new JTable();
		final JPanel pnlTable = new JPanel();
		persons = serviceFacade.findAllPersons();
		pnlTable.setLayout(new BoxLayout(pnlTable, BoxLayout.X_AXIS));
		
		final TableModel personsTableModel = new PersonTableModel(persons);
		tblPersons.setModel(personsTableModel);
		
		tblPersons.getSelectionModel().addListSelectionListener(event -> {
			final int selectedRow = tblPersons.getSelectedRow();
			if (selectedRow >= 0) {
				selectedPerson = persons.get(selectedRow);
			}
		});
		final JScrollPane scroller = new JScrollPane(tblPersons);
		pnlTable.add(scroller);
		pnlRoot.add(pnlTable);
	}
	
	private void initButtons() {
		final JPanel pnlButtons = new JPanel();
		pnlButtons.setLayout(new FlowLayout());
		
		pnlButtons.add(initBtnNewPerson());
		pnlButtons.add(initBtnEditPerson());
		pnlButtons.add(initBtnDeletePerson());
		pnlRoot.add(pnlButtons);
	}
	
	private JButton initBtnNewPerson() {
		final JButton btnNewPerson = new JButton("Neu");
		btnNewPerson.addActionListener(event -> {
			new PersonEditDialog(null, theFrame);
			tblPersons.tableChanged(null);
			theFrame.repaint();
		});
		
		return btnNewPerson;
	}
	
	private JButton initBtnEditPerson() {
		final JButton btnEditPerson = new JButton("Ändern");
		btnEditPerson.addActionListener(event -> {
			new PersonEditDialog(selectedPerson, theFrame);
			tblPersons.tableChanged(null);
			theFrame.repaint();
		});
		
		return btnEditPerson;
	}
}
