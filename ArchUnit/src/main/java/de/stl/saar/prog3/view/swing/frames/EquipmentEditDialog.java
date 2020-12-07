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
import de.stl.saar.prog3.model.interfaces.Equipment;
import de.stl.saar.prog3.service.interfaces.EquipmentService;
import de.stl.saar.prog3.utils.StringUtils;

public class EquipmentEditDialog {
	private JDialog theDialog;
	private JPanel pnlRoot;
	private EquipmentService equipmentService;
	private JTextField txtEquipmentName;
	private JTextField txtWeight;
	
	private static final int DIALOG_WIDTH = 600;
	private static final int DIALOG_HEIGHT = 200;
	private static final boolean MODAL = true;
	
	public EquipmentEditDialog(final Equipment equipment, final JDialog owner) {
		equipmentService = ServiceFactory.createEquipmentService();
		initFrame(equipment, owner);
		initForm(equipment);
		initButtons(equipment);
		theDialog.setVisible(true);
	}
	
	private void initFrame(final Equipment equipment, final JDialog owner) {
		String title = StringUtils.EMPTY_STRING;
		if (equipment != null) {
			title = "Ausrüstung ändern";
		} else {
			title = "Ausrüstung anlegen";
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
	
	private void initForm(final Equipment equipment) {
		final JLabel lblEquipmentName = new JLabel("Bezeichnung");
		final JLabel lblEmployeeCount = new JLabel("Gewicht");
		txtEquipmentName = new JTextField();
		txtWeight = new JTextField();
		
		if (equipment != null) {
			txtEquipmentName.setText(equipment.getEquipmentName());
			final double weight = equipment.getWeight(); 
			txtWeight.setText(String.valueOf(weight));
		}
		
		final JPanel pnlForm = new JPanel();
		pnlForm.setLayout(new GridLayout(2, 2));
		pnlForm.add(lblEquipmentName);
		pnlForm.add(txtEquipmentName);
		pnlForm.add(lblEmployeeCount);
		pnlForm.add(txtWeight);
		pnlRoot.add(pnlForm);
	}
	
	private void initButtons(final Equipment equipment) {
		final JButton btnOk = new JButton("Ok");
		final JButton btnCancel = new JButton("Abbrechen");
		final JPanel pnlButtons = new JPanel();
		pnlButtons.add(btnOk);
		pnlButtons.add(btnCancel);
		
		btnOk.addActionListener(event -> {
			saveEquipment(equipment);
			theDialog.dispose();
		});
		
		btnCancel.addActionListener(event-> theDialog.dispose());
		
		pnlRoot.add(pnlButtons);
	}
	
	private void saveEquipment(final Equipment equipment) {
		final String equipmentName = txtEquipmentName.getText();
		final double weight = Double.parseDouble(txtWeight.getText());
		if (equipment != null) {
			equipment.setEquipmentName(equipmentName);
			equipment.setWeight(weight);
			equipmentService.upateEquipment(equipment);
		} else {
			final Equipment newEquipment = ModelFactory.createEquipment(equipmentName, weight);
			equipmentService.saveNewEquipment(newEquipment);
		}
	}
}
