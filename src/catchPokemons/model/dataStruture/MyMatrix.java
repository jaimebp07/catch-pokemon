package catchPokemons.model.dataStruture;

import java.util.Comparator;

import javax.swing.JOptionPane;

/**
 * 
 * @author jaime
 *
 * @param <TC> tipo de dato de la columna
 * @param <C> tipo de dato de la celda
 * @param <TR> tipo de dato de la fila
 */
public class MyMatrix<TC, TR, C> {
	private SimpleList<MyHeader<TC, C>> columns;
	private SimpleList<MyHeader<TR, C>> rows;

	public MyMatrix(Comparator<MyHeader<TC, C>> comparatorColumn, Comparator<MyHeader<TR, C>> comparatorRow) {
		this.columns = new SimpleList<>(comparatorColumn);
		this.rows = new SimpleList<>(comparatorRow);
	}

	public boolean set(TC column, TR row, C info) {
		MyHeader<TC, C> columnSelected = ensureExistenceOfColumn(column);
		MyHeader<TR, C> rowSelected = ensureExistenceOfRow(row);
		return insertCell(rowSelected, columnSelected, info);
	}

	private boolean insertCell(MyHeader<TR, C> rowSelected, MyHeader<TC, C> columnSelected, C info) {
		MyCell<C> cellAuxOfColumnSelected = columnSelected.firstCell;
		MyCell<C> cellAuxOfRowSelected = rowSelected.firstCell;
		MyCell<C> cellForAdd = new MyCell<C>(info);
		if (columnSelected.firtsIsEmpty()) {
			columnSelected.firstCell = cellForAdd;
		} else {
			int positionOfInsertionColumn = findPositionOfInsertInColumn(cellAuxOfColumnSelected, rowSelected);
			if (positionOfInsertionColumn == 1) {
				columnSelected.firstCell = cellForAdd;
				columnSelected.firstCell.nextDown = cellAuxOfColumnSelected;
			} else {
				if (positionOfInsertionColumn > 0) {
					cellForAdd.nextDown = columnSelected.peekNextDown(positionOfInsertionColumn);
					columnSelected.peekNextDown(positionOfInsertionColumn - 1).nextDown = cellForAdd;
				} else {
					return false;
				}
			}
		}
		// ________________________________agregar en fila_________________________
		if (rowSelected.firtsIsEmpty()) {
			rowSelected.firstCell = cellForAdd;
		} else {
			int positionOfInsertionRow = findPositionOfInsertInRow(cellAuxOfRowSelected, columnSelected);
			if (positionOfInsertionRow == 1) {
				rowSelected.firstCell = cellForAdd;
				rowSelected.firstCell.nextRigth = cellAuxOfRowSelected;
			} else {
				if (positionOfInsertionRow > 0) {
					cellForAdd.nextRigth = rowSelected.peekNextRigth(positionOfInsertionRow);
					rowSelected.peekNextRigth(positionOfInsertionRow - 1).nextRigth = cellForAdd;
				} else {
//					System.out.println("Posicon ocupada");
					return false;
				}
			}
		}
		return true;
	}

	private int findPositionOfInsertInRow(MyCell<C> cellAuxOfRowSelected, MyHeader<TC, C> columnSelected) {
		int positionOfInsertion = 1;
		while (cellAuxOfRowSelected != null) {
			MyHeader<TC, C> columnOfLocation = findColmnOfLocation(cellAuxOfRowSelected);
			int resultOfMakeComparison = columns.makeComparison(columnSelected, columnOfLocation);
			if (resultOfMakeComparison < 0)
				return positionOfInsertion;
			if (resultOfMakeComparison == 0)
				return 0;
			positionOfInsertion++;
			cellAuxOfRowSelected = cellAuxOfRowSelected.nextRigth;
		}
		return positionOfInsertion;
	}

	private MyHeader<TC, C> findColmnOfLocation(MyCell<C> cellAuxOfRowSelected) {
		Nodo<MyHeader<TC, C>> auxColumns = columns.getHead();
		MyCell<C> cellAux = null;
		while (auxColumns != null) {
			cellAux = auxColumns.getInfo().firstCell;
			while (cellAux != null) {
				if (cellAux == cellAuxOfRowSelected)
					return auxColumns.getInfo();
				cellAux = cellAux.nextDown;
			}
			auxColumns = auxColumns.getNext();
		}
		return null;
	}

	/**
	 * este metodo me tiene que retornar la posicion que ocupara la celda en el
	 * header de la columna, si retorna 0 sera porque la pocion ya esta ocupada
	 * 
	 * @param cellAuxOfColumnSelected
	 * @param rowSelected
	 * @return
	 */
	private int findPositionOfInsertInColumn(MyCell<C> cellAuxOfColumnSelected, MyHeader<TR, C> rowSelected) {
		int positionOfInsertion = 1;
		while (cellAuxOfColumnSelected != null) {
			MyHeader<TR, C> rowOfLocation = findRowOfLocation(cellAuxOfColumnSelected);
			int resultOfMakeComparison = rows.makeComparison(rowSelected, rowOfLocation);
			if (resultOfMakeComparison < 0)
				return positionOfInsertion;
			if (resultOfMakeComparison == 0)
				return 0;
			positionOfInsertion++;
			cellAuxOfColumnSelected = cellAuxOfColumnSelected.nextDown;
		}
		return positionOfInsertion;
	}

	/**
	 * me va a retornar en que fila esta hubicada la celda seleccionada del header
	 * 
	 * @param auxCell
	 * @return
	 */
	private MyHeader<TR, C> findRowOfLocation(MyCell<C> auxCell) {
		Nodo<MyHeader<TR, C>> auxRows = rows.getHead();
		MyCell<C> cellAux = null;
		while (auxRows != null) {
			cellAux = auxRows.getInfo().firstCell;
			while (cellAux != null) {
				if (cellAux == auxCell)
					return auxRows.getInfo();
				cellAux = cellAux.nextRigth;
			}
			auxRows = auxRows.getNext();
		}
		return null;
	}

	private MyHeader<TR, C> ensureExistenceOfRow(TR row) {
		MyHeader<TR, C> myHeaderAux = returnIfExistsInRows(new MyHeader<TR, C>(row));
		if (myHeaderAux == null) {
			if (!this.rows.isEmpty()) {
				myHeaderAux = new MyHeader<TR, C>(row);
				rows.addSort(myHeaderAux);
			} else {
				this.rows.add(new MyHeader<TR, C>(row));
				myHeaderAux = rows.getHead().getInfo();
			}
		}
		return myHeaderAux;
	}

	private MyHeader<TR, C> returnIfExistsInRows(MyHeader<TR, C> row) {
		Nodo<MyHeader<TR, C>> aux = this.rows.getHead();
		while (aux != null) {
			if (aux.getInfo().info.toString().equals(row.getInfo().toString()))
				return aux.getInfo();
			aux = aux.getNext();
		}
		return null;
	}

	private MyHeader<TC, C> ensureExistenceOfColumn(TC column) {
		MyHeader<TC, C> myHeaderAux = returnIfExistsInColumns(new MyHeader<TC, C>(column));
		if (myHeaderAux == null) {
			if (!this.columns.isEmpty()) {
				myHeaderAux = new MyHeader<TC, C>(column);
				columns.addSort(myHeaderAux);
			} else {
				this.columns.add(new MyHeader<TC, C>(column));
				myHeaderAux = columns.getHead().getInfo();
			}
		}
		return myHeaderAux;
	}

	private MyHeader<TC, C> returnIfExistsInColumns(MyHeader<TC, C> column) {
		Nodo<MyHeader<TC, C>> aux = this.columns.getHead();
		while (aux != null) {
			if (aux.getInfo().info.toString().equals(column.getInfo().toString()))
				return aux.getInfo();
			aux = aux.getNext();
		}
		return null;
	}

	public C get(TC column, TR row) {
		Nodo<MyHeader<TC, C>> auxColumn = this.columns.getHead();
		Nodo<MyHeader<TR, C>> auxRows = this.rows.getHead();
		while (auxColumn != null) {
			MyCell<C> auxCell = auxColumn.getInfo().firstCell;
			if (auxColumn.getInfo().info.equals(column)) {
				while (auxRows != null) {
					if (auxRows.getInfo().info.equals(row) && this.isExistingInRow(findRow(row), auxCell))
						return auxCell.info;
					if (isExistingInRow(auxRows.getInfo(), auxCell)) {
						auxCell = auxCell.getNextDown();
					}
					auxRows = auxRows.getNext();
				}
			}
			auxColumn = auxColumn.getNext();
		}
		return null;
	}

	/**
	 * verifica si una celda determinada existe en la una cieta fila
	 * 
	 * @param headerRow
	 * @param auxCell
	 * @return
	 */
	private boolean isExistingInRow(MyHeader<TR, C> headerRow, MyCell<C> auxCell) {
		MyCell<C> cell = headerRow.firstCell;
		while (cell != null) {
			if (cell == auxCell)
				return true;
			cell = cell.nextRigth;
		}
		return false;
	}

	/**
	 * verifica si una celda existe en la columna
	 * 
	 * @param headerColumn
	 * @param auxCell
	 * @return
	 */
	private boolean isExistingInColumn(MyHeader<TC, C> headerColumn, MyCell<C> auxCell) {
		MyCell<C> cell = headerColumn.firstCell;
		while (cell != null) {
			if (cell == auxCell)
				return true;
			cell = cell.nextDown;
		}
		return false;
	}

	public void remove(TC column, TR row) {
		if (get(column, row) != null) {
			MyHeader<TC, C> auxHeaderColumn = findColumn(column);
			MyHeader<TR, C> auxHeaderRow = findRow(row);
			removeInColumn(column, auxHeaderColumn, row);
			removeInRow(row, auxHeaderRow, column);
		}
	}

	private MyHeader<TR, C> findRow(TR row) {
		Nodo<MyHeader<TR, C>> auxRows = this.rows.getHead();
		while (auxRows != null) {
			if (rows.makeComparison(auxRows.getInfo(), new MyHeader<TR, C>(row)) == 0)
				return auxRows.getInfo();
			auxRows = auxRows.getNext();
		}
		return null;
	}

	private MyHeader<TC, C> findColumn(TC column) {
		Nodo<MyHeader<TC, C>> auxColumns = this.columns.getHead();
		while (auxColumns != null) {
			if (columns.makeComparison(auxColumns.getInfo(), new MyHeader<TC, C>(column)) == 0)
				return auxColumns.getInfo();
			auxColumns = auxColumns.getNext();
		}
		return null;
	}

	private void removeInRow(TR row, MyHeader<TR, C> auxHeaderRow, TC column) {
		if (auxHeaderRow.getFirstCell().nextRigth == null) {
			rows.remove(auxHeaderRow);
		} else {
			int positionRemoveColumn = findPositionInHeaderRow(this.findRow(row), column);
			if (positionRemoveColumn == 1) {
				JOptionPane.showMessageDialog(null, "en fila");
				auxHeaderRow.firstCell = auxHeaderRow.peekNextRigth(positionRemoveColumn).nextRigth;
			} else {
				if (auxHeaderRow.peekNextDown(positionRemoveColumn).nextRigth != null)
					auxHeaderRow.peekNextRigth(positionRemoveColumn - 1).nextRigth = auxHeaderRow
							.peekNextRigth(positionRemoveColumn).nextRigth;
				auxHeaderRow.peekNextRigth(positionRemoveColumn - 1).nextRigth = null;
			}
		}
	}

//	----------------------------------------------------------------------------------------------------------------------------------
	private int findPositionInHeaderRow(MyHeader<TR, C> headerRow, TC column) {
		int i = 1;
		MyCell<C> cell = headerRow.firstCell;
		Nodo<MyHeader<TC, C>> auxColumns = this.columns.getHead();
		while (auxColumns != null) {
			if (columns.makeComparison(auxColumns.getInfo(), new MyHeader<TC, C>(column)) == 0) {
				return i;
			}
			if (isExistingInColumn(auxColumns.getInfo(), cell)) {
				i++;
				cell = cell.getNextRigth();
			}
			auxColumns = auxColumns.getNext();
		}
		return 0;
	}

	/**
	 * remueve la celda en la columna
	 * 
	 * @param column
	 * @param auxHeaderColumn
	 * @param row
	 */
	private void removeInColumn(TC column, MyHeader<TC, C> auxHeaderColumn, TR row) {
		if (auxHeaderColumn.getFirstCell().nextDown == null) {
			columns.remove(auxHeaderColumn);
		} else {
			JOptionPane.showMessageDialog(null, " El header es: " + findColumn(column).getInfo());
			int positionRemoveRow = findPositionInHeaderColumn(findColumn(column), row);
			if (positionRemoveRow == 1) {
				auxHeaderColumn.firstCell = auxHeaderColumn.peekNextDown(positionRemoveRow).nextDown;
			} else {
				if (auxHeaderColumn.peekNextDown(positionRemoveRow).nextDown != null)
					auxHeaderColumn.peekNextDown(positionRemoveRow - 1).nextDown = auxHeaderColumn
							.peekNextDown(positionRemoveRow).nextDown;
				auxHeaderColumn.peekNextDown(positionRemoveRow - 1).nextDown = null;
			}
		}
	}

	private int findPositionInHeaderColumn(MyHeader<TC, C> headerColumn, TR row) {
		int i = 1;
		MyCell<C> cell = headerColumn.firstCell;
		Nodo<MyHeader<TR, C>> auxRows = this.rows.getHead();
		while (auxRows != null) {
			if (rows.makeComparison(auxRows.getInfo(), new MyHeader<TR, C>(row)) == 0) {
				return i;
			}
			if (isExistingInRow(auxRows.getInfo(), cell)) {
				i++;
				cell = cell.getNextDown();
			}
			auxRows = auxRows.getNext();
		}
		return 0;
	}

	/**
	 * Buscra la fila de una celda
	 * 
	 * @param myCell la celda a buscar
	 * @return la fila correspondiente a la celda
	 */
	@SuppressWarnings("unused")
	private TR getRow(MyCell<C> myCell) {
		Nodo<MyHeader<TR, C>> auxRows = rows.getHead();
		MyCell<C> cellAux = null;
		while (auxRows != null) {
			cellAux = auxRows.getInfo().firstCell;
			while (cellAux != null) {
				if (cellAux == myCell)
					return auxRows.getInfo().info;
				cellAux = cellAux.nextRigth;
			}
			auxRows = auxRows.getNext();
		}
		return null;
	}

	/**
	 * Buscar la columna de una celda
	 * 
	 * @param myCell la celda a buscar
	 * @return la columna correspondiente a la celda
	 */
	public TC getCol(MyCell<C> myCell) {
		Nodo<MyHeader<TC, C>> auxColumns = columns.getHead();
		MyCell<C> cellAux = null;
		while (auxColumns != null) {
			cellAux = auxColumns.getInfo().firstCell;
			while (cellAux != null) {
				if (cellAux == myCell)
					return auxColumns.getInfo().info;
				cellAux = cellAux.nextDown;
			}
			auxColumns = auxColumns.getNext();
		}
		return null;
	}

	public C getStarRow(TC colmn, TR row) {
		Nodo<MyHeader<TC, C>> auxColumn = this.columns.getHead();
		;
		Nodo<MyHeader<TR, C>> auxRows = this.rows.getHead();
		while (auxRows != null) {
			MyCell<C> auxCell = auxRows.getInfo().firstCell;
			if (auxRows.getInfo().info.equals(row)) {
				while (auxColumn != null) {
					if (auxColumn.getInfo().info.equals(colmn) && this.isExistingInColumn(findColumn(colmn), auxCell))
						return auxCell.info;
					if (this.isExistingInColumn(auxColumn.getInfo(), auxCell))
						auxCell = auxCell.getNextRigth();
					auxColumn = auxColumn.getNext();
				}
			}
			auxRows = auxRows.getNext();
		}
		return null;
	}

	public SimpleList<MyHeader<TC, C>> getColumns() {
		return columns;
	}

	public SimpleList<MyHeader<TR, C>> getRows() {
		return rows;
	}

	public SimpleList<C> makeTravelRows() {
		SimpleList<C> list = new SimpleList<C>();
		Nodo<MyHeader<TR, C>> nodoTwo = rows.getHead();
		@SuppressWarnings("unused")
		MyHeader<TR, C> auxTwo = null;
		while (nodoTwo != null) {
			auxTwo = nodoTwo.getInfo();
			MyCell<C> auxCell = nodoTwo.getInfo().getFirstCell();
			while (auxCell != null) {
				list.add(auxCell.getInfo());
				auxCell = auxCell.getNextRigth();
			}
			nodoTwo = nodoTwo.getNext();
		}
		return list;
	}

	public SimpleList<C> makeTravelColumns() {
		SimpleList<C> list = new SimpleList<C>();
		Nodo<MyHeader<TC, C>> nodoTwo = columns.getHead();
		@SuppressWarnings("unused")
		MyHeader<TC, C> auxTwo = null;
		while (nodoTwo != null) {
			auxTwo = nodoTwo.getInfo();
			MyCell<C> auxCell = nodoTwo.getInfo().getFirstCell();
			while (auxCell != null) {
				list.add(auxCell.getInfo());
				auxCell = auxCell.getNextDown();
			}
			nodoTwo = nodoTwo.getNext();
		}
		return list;
	}
}