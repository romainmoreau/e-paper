package fr.romainmoreau.epaper.client.common.table;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import fr.romainmoreau.epaper.client.api.EPaperValidationException;
import fr.romainmoreau.epaper.client.api.table.Cell;
import fr.romainmoreau.epaper.client.api.table.Column;
import fr.romainmoreau.epaper.client.api.table.Row;
import fr.romainmoreau.epaper.client.api.table.Table;

public class Tables {
	public static DrawableTable getDrawableTable(int width, int height, int topLeftX, int topLeftY, Table table) {
		DrawableTable drawableTable = new DrawableTable();
		List<Integer> columnWidths = getSizes(width, table.getBorderSize(), table.getColumns(), Column::getWidth);
		List<Integer> rowHeights = getSizes(height, table.getBorderSize(), table.getRows(), Row::getHeight);
		NavigableMap<Integer, NavigableMap<Integer, List<Cell>>> cells = table.getCells().stream()
				.filter(c -> c.getColumnIndex() < columnWidths.size() && c.getRowIndex() < rowHeights.size())
				.collect(Collectors.groupingBy(Cell::getColumnIndex, TreeMap::new,
						Collectors.groupingBy(Cell::getRowIndex, TreeMap::new, Collectors.toList())));
		addDrawableCells(topLeftX, topLeftY, table, drawableTable, columnWidths, rowHeights, cells);
		if (table.getBorderSize() != 0) {
			addVerticalDrawableBorders(height, topLeftX, topLeftY, table, drawableTable, columnWidths);
			addHorizontalDrawableBorders(width, topLeftX, topLeftY, table, drawableTable, rowHeights);
		}
		return drawableTable;
	}

	public static void validateTable(int width, int height, Table table) throws EPaperValidationException {
		if (table == null) {
			throw new EPaperValidationException("Empty table");
		}
		if (table.getColumns().isEmpty()) {
			throw new EPaperValidationException("No columns");
		}
		if (table.getRows().isEmpty()) {
			throw new EPaperValidationException("No rows");
		}
		for (Column column : table.getColumns()) {
			validateColumn(column, width, height, table);
		}
		for (Row row : table.getRows()) {
			validateRow(row, width, height, table);
		}
		for (Cell cell : table.getCells()) {
			validateCell(cell, width, height, table);
		}
		if (table.getBorderSize() < 0) {
			throw new EPaperValidationException("Invalid border size");
		}
		if (table.getBorderSize() > 0 && table.getBorderColor() == null) {
			throw new EPaperValidationException("Empty border color");
		}
		if (table.getBorderSize() * 2 >= width) {
			throw new EPaperValidationException("Not enough width for the border");
		}
		if (table.getBorderSize() * 2 >= height) {
			throw new EPaperValidationException("Not enough height for the border");
		}
	}

	private static <T> List<Integer> getSizes(int size, int borderSize, List<T> tList, Function<T, Double> sizeGetter) {
		List<Integer> tSizes = new ArrayList<>();
		int bordersSize = (tList.size() + 1) * borderSize;
		int sizeMinusBordersSize = size - bordersSize;
		int tsSize = 0;
		for (T t : tList) {
			double tDefinedSize = sizeGetter.apply(t);
			int tSize;
			if (tDefinedSize > 1) {
				tSize = (int) tDefinedSize;
			} else {
				tSize = (int) (sizeMinusBordersSize * tDefinedSize);
			}
			tSizes.add(tSize);
			tsSize += tSize;
		}
		while (tsSize != sizeMinusBordersSize) {
			int sizeDelta = sizeMinusBordersSize - tsSize;
			int lastIndex = tSizes.size() - 1;
			if (sizeDelta < 0) {
				int lastTSize = tSizes.get(lastIndex);
				int lastTSizePlusSizeDelta = lastTSize + sizeDelta;
				if (lastTSizePlusSizeDelta > 0) {
					tSizes.set(lastIndex, lastTSizePlusSizeDelta);
					tsSize += sizeDelta;
				} else {
					return getSizes(size, borderSize, tList.subList(0, tList.size() - 1), sizeGetter);
				}
			} else {
				tSizes.set(lastIndex, tSizes.get(lastIndex) + sizeDelta);
				tsSize += sizeDelta;
			}
		}
		return tSizes;
	}

	private static void addDrawableCells(int topLeftX, int topLeftY, Table table, DrawableTable drawableTable,
			List<Integer> columnWidths, List<Integer> rowHeights,
			NavigableMap<Integer, NavigableMap<Integer, List<Cell>>> cells) {
		int cx0 = topLeftX + table.getBorderSize();
		int cx1;
		for (int i = 0; i < columnWidths.size(); i++) {
			NavigableMap<Integer, List<Cell>> columnCells = cells.get(i);
			int columnWidth = columnWidths.get(i);
			cx1 = cx0 + columnWidth - 1;
			if (columnCells != null) {
				int cy0 = topLeftY + table.getBorderSize();
				int cy1;
				for (int j = 0; j < rowHeights.size(); j++) {
					List<Cell> cellCells = columnCells.get(j);
					int rowHeight = rowHeights.get(j);
					cy1 = cy0 + rowHeight - 1;
					if (cellCells != null) {
						drawableTable.getDrawableCells()
								.add(new DrawableCell(cx0, cy0, cx1, cy1,
										cellCells.stream()
												.sorted((c1, c2) -> Integer.compare(c1.getZIndex(), c2.getZIndex()))
												.map(Cell::getCellContent).collect(Collectors.toList())));
					}
					cy0 = cy1 + table.getBorderSize();
				}
			}
			cx0 = cx1 + table.getBorderSize();
		}
	}

	private static void addHorizontalDrawableBorders(int width, int topLeftX, int topLeftY, Table table,
			DrawableTable drawableTable, List<Integer> rowHeights) {
		int hx0 = topLeftX;
		int hx1 = hx0 + width - 1;
		int hy0 = topLeftY;
		int hy1 = hy0 + table.getBorderSize() - 1;
		drawableTable.getDrawableBorders().add(new DrawableBorder(hx0, hy0, hx1, hy1));
		for (int i = 0; i < rowHeights.size(); i++) {
			int rowHeight = rowHeights.get(i);
			hy0 = hy1 + rowHeight + 1;
			hy1 = hy0 + table.getBorderSize() - 1;
			drawableTable.getDrawableBorders().add(new DrawableBorder(hx0, hy0, hx1, hy1));
		}
	}

	private static void addVerticalDrawableBorders(int height, int topLeftX, int topLeftY, Table table,
			DrawableTable drawableTable, List<Integer> columnWidths) {
		int vx0 = topLeftX;
		int vx1 = vx0 + table.getBorderSize() - 1;
		int vy0 = topLeftY;
		int vy1 = vy0 + height - 1;
		drawableTable.getDrawableBorders().add(new DrawableBorder(vx0, vy0, vx1, vy1));
		for (int i = 0; i < columnWidths.size(); i++) {
			int columnWidth = columnWidths.get(i);
			vx0 = vx1 + columnWidth + 1;
			vx1 = vx0 + table.getBorderSize() - 1;
			drawableTable.getDrawableBorders().add(new DrawableBorder(vx0, vy0, vx1, vy1));
		}
	}

	private static void validateColumn(Column column, int width, int height, Table table)
			throws EPaperValidationException {
		if (column.getWidth() < 0d) {
			throw new EPaperValidationException("Invalid column width");
		}
	}

	private static void validateRow(Row row, int width, int height, Table table) throws EPaperValidationException {
		if (row.getHeight() < 0d) {
			throw new EPaperValidationException("Invalid row height");
		}
	}

	private static void validateCell(Cell cell, int width, int height, Table table) throws EPaperValidationException {
		if (cell.getColumnIndex() < 0 || cell.getColumnIndex() > table.getColumns().size()) {
			throw new EPaperValidationException("Invalid column index: " + cell.getColumnIndex());
		}
		if (cell.getRowIndex() < 0 || cell.getRowIndex() > table.getRows().size()) {
			throw new EPaperValidationException("Invalid row index: " + cell.getRowIndex());
		}
		if (cell.getCellContent() == null) {
			throw new EPaperValidationException("Empty cell content");
		}
	}
}
