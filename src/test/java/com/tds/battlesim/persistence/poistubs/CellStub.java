package com.tds.battlesim.persistence.poistubs;

import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;

public class CellStub implements Cell {
	
	private String contents;
	
	@Override
	public int getColumnIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRowIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Sheet getSheet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Row getRow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCellType(int cellType) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCellType(CellType cellType) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getCellType() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CellType getCellTypeEnum() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCachedFormulaResultType() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CellType getCachedFormulaResultTypeEnum() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCellValue(double value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCellValue(Date value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCellValue(Calendar value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCellValue(RichTextString value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCellValue(String value) {
		contents = value;
	}

	@Override
	public void setCellFormula(String formula) throws FormulaParseException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getCellFormula() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getNumericCellValue() {
		return Double.valueOf( contents );
	}

	@Override
	public Date getDateCellValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RichTextString getRichStringCellValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStringCellValue() {
		return contents;
	}

	@Override
	public void setCellValue(boolean value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCellErrorValue(byte value) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getBooleanCellValue() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public byte getErrorCellValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCellStyle(CellStyle style) {
		// TODO Auto-generated method stub

	}

	@Override
	public CellStyle getCellStyle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAsActiveCell() {
		// TODO Auto-generated method stub

	}

	@Override
	public CellAddress getAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCellComment(Comment comment) {
		// TODO Auto-generated method stub

	}

	@Override
	public Comment getCellComment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeCellComment() {
		// TODO Auto-generated method stub

	}

	@Override
	public Hyperlink getHyperlink() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setHyperlink(Hyperlink link) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeHyperlink() {
		// TODO Auto-generated method stub

	}

	@Override
	public CellRangeAddress getArrayFormulaRange() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPartOfArrayFormulaGroup() {
		// TODO Auto-generated method stub
		return false;
	}

}
