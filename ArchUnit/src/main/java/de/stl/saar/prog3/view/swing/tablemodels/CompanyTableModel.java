package de.stl.saar.prog3.view.swing.tablemodels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import de.stl.saar.prog3.model.interfaces.Company;
import de.stl.saar.prog3.utils.StringUtils;

public class CompanyTableModel implements TableModel {
	private static final int COLUMN_COUNT = 2;
	private static final int INDEX_COMPANY_NAME = 0;
	private static final int INDEX_EMPLOYEE_COUNT = 1;
	
	private List<Company> companys;
	/**
	 * Diese Liste enthaelt die Listener, die mit der Tabelle
	 * assoziiert sind, z.B. wenn ein Datensatz ausgewaehlt wird.
	 */
	private List<TableModelListener> listeners;
	
	public CompanyTableModel(List<Company> companys) {
		this.companys = companys;
		listeners = new ArrayList<>();
	}
	
	@Override
	public int getRowCount() {
		return companys.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_COUNT;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case INDEX_COMPANY_NAME: return "Firmenname";
		case INDEX_EMPLOYEE_COUNT: return "Mitarbeiteranzahl";
		default: return StringUtils.EMPTY_STRING;
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case INDEX_COMPANY_NAME: return String.class;
		case INDEX_EMPLOYEE_COUNT: return Integer.class;
		default: return String.class;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		final Company company = companys.get(rowIndex);
		switch (columnIndex) {
		case INDEX_COMPANY_NAME: return company.getCompanyName();
		case INDEX_EMPLOYEE_COUNT: return company.getEmployeesCount();
		default: return String.class;
		}
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		
	}
	
	/**
	 * Fuegt einen neue Firma in die Tabelle ein. Dabei wird ein
	 * Einfuege-Ereignis ausgeloest. Als Antwort auf dieses Ereignis
	 * wird die Liste mit den Listenern durchlaufen und alle Listener,
	 * die mit dem Einfuege-Ereignis zu tun haben, werden ausgefuehrt.
	 * @param company Die einzufuegende Firma.
	 */
	public void addCompany(Company company) {		
		int index = companys.size();
		
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