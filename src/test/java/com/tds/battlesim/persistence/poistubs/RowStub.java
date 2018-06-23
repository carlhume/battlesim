package com.tds.battlesim.persistence.poistubs;

import java.util.Iterator;
import java.util.LinkedList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class RowStub implements Row {

	private LinkedList<Cell> cells;
	
	public RowStub() {
		cells = new LinkedList<Cell>();		
	}
	
	@Override
	public Iterator<Cell> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cell createCell(int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cell createCell(int column, int type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cell createCell(int column, CellType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeCell(Cell cell) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setRowNum(int rowNum) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getRowNum() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Cell getCell(int cellnum) {
		return cells.get( cellnum );
	}

	@Override
	public Cell getCell(int cellnum, MissingCellPolicy policy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public short getFirstCellNum() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public short getLastCellNum() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPhysicalNumberOfCells() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setHeight(short height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setZeroHeight(boolean zHeight) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getZeroHeight() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setHeightInPoints(float height) {
		// TODO Auto-generated method stub

	}

	@Override
	public short getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getHeightInPoints() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isFormatted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CellStyle getRowStyle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRowStyle(CellStyle style) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterator<Cell> cellIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sheet getSheet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getOutlineLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setCellAt( CellStub cell, int i ) {
		while( cells.size() <= i ) {
			cells.add( new CellStub() );
		}
		cells.set( i, cell );
	}

}
