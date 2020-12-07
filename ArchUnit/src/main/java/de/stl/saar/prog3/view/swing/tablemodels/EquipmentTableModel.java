package de.stl.saar.prog3.view.swing.tablemodels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import de.stl.saar.prog3.model.interfaces.Equipment;
import de.stl.saar.prog3.utils.StringUtils;

public class EquipmentTableModel implements TableModel {
	private static final int COLUMN_COUNT = 2;
	private static final int INDEX_EQUIPMENT_NAME = 0;
	private static final int INDEX_WEIGHT = 1;
	
	private List<Equipment> equipments;
	/**
	 * Diese Liste enthaelt die Listener, die mit der Tabelle
	 * assoziiert sind, z.B. wenn ein Datensatz ausgewaehlt wird.
	 */
	private List<TableModelListener> listeners;
	
	public EquipmentTableModel(List<Equipment> equipments) {
		this.equipments = equipments;
		listeners = new ArrayList<>();
	}
	
	@Override
	public int getRowCount() {
		return equipments.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_COUNT;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case INDEX_EQUIPMENT_NAME: return "Bezeichnung";
		case INDEX_WEIGHT: return "Gewicht";
		default: return StringUtils.EMPTY_STRING;
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case INDEX_EQUIPMENT_NAME: return String.class;
		case INDEX_WEIGHT: return Double.class;
		default: return String.class;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		final Equipment equipment = equipments.get(rowIndex);
		switch (columnIndex) {
		case INDEX_EQUIPMENT_NAME: return equipment.getEquipmentName();
		case INDEX_WEIGHT: return equipment.getWeight();
		default: return String.class;
		}
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		
	}
	
	/**
	 * Fuegt einen neuen Ausrüstungsgegenstand in die Tabelle ein. Dabei wird ein
	 * Einfuege-Ereignis ausgeloest. Als Antwort auf dieses Ereignis
	 * wird die Liste mit den Listenern durchlaufen und alle Listener,
	 * die mit dem Einfuege-Ereignis zu tun haben, werden ausgefuehrt.
	 * @param equipment Die einzufuegende Firma.
	 */
	public void addEquipment(Equipment equipment) {		
		int index = equipments.size();
		
		TableModelEvent event = new TableModelEvent(this, index, index, 
				TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
		for(int i = 0, n = listeners.size(); i < n; i++){
			((TableModelListener)listeners.get(i)).tableChanged(event);
		}
	}

	/**
	 * Fuegt einen neuen Listener in die Liste mit den Listenern
	 * fuer die Tabelle ein. Wenn ein Ereignis ausgeloest wird,
	 * werden diese Listener durchlaufen.
	 */
	@Override
	public void addTableModelListener(TableModelListener listener) {
		listeners.add(listener);
	}

	/**
	 * Entfernt einen Listener aus der Liste dieser Tabelle. Damit
	 * wird dieser Listener beim Auftreten eines Ereignisses nicht
	 * mehr ausgefuehrt.
	 * @param listener Der Listener, der aus der Tabelle entfernt werden
	 * soll.
	 */
	@Override
	public void removeTableModelListener(TableModelListener listener) {
		listeners.remove(listener);
	}
}