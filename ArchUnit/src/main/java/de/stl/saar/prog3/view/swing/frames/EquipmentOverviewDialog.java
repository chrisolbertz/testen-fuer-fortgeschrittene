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
import de.stl.saar.prog3.model.interfaces.Equipment;
import de.stl.saar.prog3.service.interfaces.EquipmentService;
import de.stl.saar.prog3.view.swing.tablemodels.EquipmentTableModel;

public class EquipmentOverviewDialog {
	private JDialog theDialog;
	private EquipmentService equipmentService;
	private List<Equipment> equipments;
	private JPanel pnlRoot;
	private Equipment selectedEquipment;
	private JTable tblEquipments;
	
	public EquipmentOverviewDialog() {
		equipmentService = ServiceFactory.createEquipmentService();
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
		tblEquipments = new JTable();
		final JPanel pnlTable = new JPanel();
		equipments = equipmentService.findAllEquipment();
		pnlTable.setLayout(new BoxLayout(pnlTable, BoxLayout.X_AXIS));
		
		final TableModel equipmentsTableModel = new EquipmentTableModel(equipments);
		tblEquipments.setModel(equipmentsTableModel);
		
		tblEquipments.getSelectionModel().addListSelectionListener(event -> {
			final int selectedRow = tblEquipments.getSelectedRow();
			if (selectedRow >= 0) {
				selectedEquipment = equipments.get(selectedRow);
			}
		});
		final JScrollPane scroller = new JScrollPane(tblEquipments);
		pnlTable.add(scroller);
		pnlRoot.add(pnlTable);
	}
	
	private void initButtons() {
		final JPanel pnlButtons = new JPanel();
		pnlButtons.setLayout(new FlowLayout());
		
		pnlButtons.add(initBtnNewEquipment());
		pnlButtons.add(initBtnEditEquipment());
		pnlButtons.add(initBtnDeleteEquipment());
		pnlRoot.add(pnlButtons);
	}
	
	private JButton initBtnNewEquipment() {
		final JButton btnNewEquipment = new JButton("Neu");
		btnNewEquipment.addActionListener(event -> {
			new EquipmentEditDialog(null, theDialog);
			tblEquipments.tableChanged(null);
			theDialog.repaint();
		});
		
		return btnNewEquipment;
	}
	
	private JButton initBtnEditEquipment() {
		final JButton btnEditEquipment = new JButton("Ändern");
		btnEditEquipment.addActionListener(event -> {
			new EquipmentEditDialog(selectedEquipment, theDialog);
			tblEquipments.tableChanged(null);
			theDialog.repaint();
		});
		
		return btnEditEquipment;
	}
	
	private JButton initBtnDeleteEquipment() {
		final JButton btnDeleteEquipment = new JButton("Löschen");
		btnDeleteEquipment.addActionListener(event -> {
			equipmentService.deleteEquipment(selectedEquipment);
			tblEquipments.tableChanged(null);
			theDialog.repaint();
		});
		
		return btnDeleteEquipment;
	}
}
