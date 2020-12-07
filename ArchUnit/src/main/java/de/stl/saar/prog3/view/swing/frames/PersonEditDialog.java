package de.stl.saar.prog3.view.swing.frames;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;

import de.stl.saar.prog3.factories.ModelFactory;
import de.stl.saar.prog3.factories.ServiceFactory;
import de.stl.saar.prog3.model.interfaces.Company;
import de.stl.saar.prog3.model.interfaces.Equipment;
import de.stl.saar.prog3.model.interfaces.Person;
import de.stl.saar.prog3.service.interfaces.CompanyService;
import de.stl.saar.prog3.service.interfaces.EquipmentService;
import de.stl.saar.prog3.service.interfaces.PersonService;
import de.stl.saar.prog3.utils.StringUtils;

public class PersonEditDialog {
	private JDialog theDialog;
	private JPanel pnlRoot;
	private EquipmentService equipmentService;
	private PersonService personService;
	private CompanyService companyService;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JList<Company> lstCompanies;
	private JList<Equipment> lstEquipment;
	
	private static final int DIALOG_WIDTH = 600;
	private static final int DIALOG_HEIGHT = 500;
	private static final boolean MODAL = true;
	
	public PersonEditDialog(final Person person, final JFrame owner) {
		personService = ServiceFactory.createPersonService();
		companyService = ServiceFactory.createCompanyService();
		equipmentService = ServiceFactory.createEquipmentService();
		initFrame(person, owner);
		initForm(person);
		initButtons(person);
		theDialog.setVisible(true);
	}
	
	private void initFrame(final Person person, final JFrame owner) {
		String title = StringUtils.EMPTY_STRING;
		if (person != null) {
			title = "Person ändern";
		} else {
			title = "Person anlegen";
		}
		
		theDialog = new JDialog(owner, MODAL);
		theDialog.setTitle(title);
		pnlRoot = new JPanel();
		pnlRoot.setLayout(new BoxLayout(pnlRoot, BoxLayout.Y_AXIS));
		theDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		theDialog.setSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
		theDialog.setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
		theDialog.getContentPane().add(pnlRoot);
	}
	
	private void initForm(final Person person) {
		final JPanel pnlForm = new JPanel();
		initTextFields(pnlForm);
		initCompanyListBox(pnlForm);
		initEquipmentListBox(pnlForm);
		
		if (person != null) {
			txtFirstName.setText(person.getFirstName());
			txtLastName.setText(person.getLastName());
			lstCompanies.setSelectedValue(person.getCompany(), true);
			
			final int equipmentCount = person.getEquipment().size();
			int [] selectedEquipmentIndices = new int[equipmentCount]; 
			for (int i = 0; i < equipmentCount; i++) {
				final Equipment equipment = person.getEquipment().get(i);
				selectedEquipmentIndices[i] = (int)(equipment.getEquipmentId() - 1);
			}
			lstEquipment.setSelectedIndices(selectedEquipmentIndices);
		}
		
		pnlForm.setLayout(new GridLayout(4, 2));
		pnlRoot.add(pnlForm);
	}
	
	private void initTextFields(final JPanel pnlForm) {
		final JLabel lblFirstName = new JLabel("Vorname");
		final JLabel lblLastName = new JLabel("Nachname");
		
		txtFirstName = new JTextField();
		txtLastName = new JTextField();
		
		pnlForm.add(lblFirstName);
		pnlForm.add(txtFirstName);
		pnlForm.add(lblLastName);
		pnlForm.add(txtLastName);
	}
	
	private void initCompanyListBox(final JPanel pnlForm) {
		final List<Company> companies = companyService.findAllCompanies();
		Company[] companyArray = new Company[companies.size()];
		companies.toArray(companyArray);
		lstCompanies = new JList<>(companyArray);
		lstCompanies.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		final JLabel lblCompany = new JLabel("Firma");
		pnlForm.add(lblCompany);
		pnlForm.add(lstCompanies);
	}
	
	private void initEquipmentListBox(final JPanel pnlForm) {
		final List<Equipment> equipment = equipmentService.findAllEquipment();
		Equipment[] equipmentArray = new Equipment[equipment.size()];
		equipment.toArray(equipmentArray);
		lstEquipment = new JList<>(equipmentArray);
		lstEquipment.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		final JLabel lblEquipment = new JLabel("Ausrüstung");
		pnlForm.add(lblEquipment);
		pnlForm.add(lstEquipment);
	}
	
	private void initButtons(final Person person) {
		final JButton btnOk = new JButton("Ok");
		final JButton btnCancel = new JButton("Abbrechen");
		final JPanel pnlButtons = new JPanel();
		pnlButtons.add(btnOk);
		pnlButtons.add(btnCancel);
		
		btnOk.addActionListener(event -> {
			savePerson(person);
			theDialog.dispose();
		});
		
		btnCancel.addActionListener(event-> theDialog.dispose());
		
		pnlRoot.add(pnlButtons);
	}
	
	private void savePerson(final Person person) {
		final String firstName = txtFirstName.getText();
		final String lastName = txtLastName.getText();
		
		if (person != null) {
			person.setFirstName(firstName);
			person.setLastName(lastName);
			final Company company = lstCompanies.getSelectedValue();
			person.setCompany(company);
			final List<Equipment> selectedEquipment = lstEquipment.getSelectedValuesList();
			person.setEquipment(selectedEquipment);
			personService.upatePerson(person);
		} else {
			final Person newPerson = ModelFactory.createPerson(firstName, lastName);
			final Company company = lstCompanies.getSelectedValue();
			newPerson.setCompany(company);
			final List<Equipment> selectedEquipment = lstEquipment.getSelectedValuesList();
			newPerson.setEquipment(selectedEquipment);
			personService.saveNewPerson(newPerson);
		}
	}
}
