package fr.romainmoreau.epaper.client.common.table;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import fr.romainmoreau.epaper.client.api.EPaperValidationException;
import fr.romainmoreau.epaper.client.api.table.Border;
import fr.romainmoreau.epaper.client.api.table.Cell;
import fr.romainmoreau.epaper.client.api.table.Column;
import fr.romainmoreau.epaper.client.api.table.Row;
import fr.romainmoreau.epaper.client.api.table.Table;

public class Tables {
	public static DrawableTable getDrawableTable(int width, int height, int topLeftX, int topLeftY, Table table) {
		DrawableTable drawableTable = new DrawableTable();
		List<Integer> columnWidths = getSizes(width, table.getVerticalBorders(), table.getColumns(), Column::getWidth);
		List<Border> verticalBorders = table.getVerticalBorders().subList(0, columnWidths.size() + 1);
		List<Integer> rowHeights = getSizes(height, table.getHorizontalBorders(), table.getRows(), Row::getHeight);
		List<Border> horizontalBorders = table.getHorizontalBorders().subList(0, rowHeights.size() + 1);
		NavigableMap<Integer, NavigableMap<Integer, List<Cell>>> cells = table.getCells().stream()
				.filter(c -> c.getColumnIndex() < columnWidths.size() && c.getRowIndex() < rowHeights.size())
				.collect(Collectors.groupingBy(Cell::getColumnIndex, TreeMap::new,
						Collectors.groupingBy(Cell::getRowIndex, TreeMap::new, Collectors.toList())));
		addDrawableCells(topLeftX, topLeftY, drawableTable, columnWidths, verticalBorders, rowHeights,
				horizontalBorders, cells);
		addVerticalDrawableBorders(height, topLeftX, topLeftY, drawableTable, columnWidths, verticalBorders);
		addHorizontalDrawableBorders(width, topLeftX, topLeftY, drawableTable, rowHeights, horizontalBorders);
		return drawableTable;
	}

	public static void validateTable(int width, int height, Table table) throws EPaperValidationException {
		if (table == null) {
			throw new EPaperValidationException("Empty table");
		}
		if (table.getColumns().isEmpty()) {
			throw new EPaperValidationException("No columns");
		}
		if (table.getVerticalBorders().size() != table.getColumns().size() + 1) {
			throw new EPaperValidationException("Not enough vertical borders");
		}
		if (table.getRows().isEmpty()) {
			throw new EPaperValidationException("No rows");
		}
		if (table.getHorizontalBorders().size() != table.getRows().size() + 1) {
			throw new EPaperValidationException("Not enough horizontal borders");
		}
		for (Column column : table.getColumns()) {
			validateColumn(column, width, height, table);
		}
		for (Border border : table.getVerticalBorders()) {
			validateBorder(border, width, height, table);
		}
		if (table.getVerticalBorders().subList(0, 2).stream().mapToInt(Border::getSize).sum() >= width) {
			throw new EPaperValidationException("Not enough width for the vertical borders");
		}
		for (Row row : table.getRows()) {
			validateRow(row, width, height, table);
		}
		for (Border border : table.getHorizontalBorders()) {
			validateBorder(border, width, height, table);
		}
		if (table.getHorizontalBorders().subList(0, 2).stream().mapToInt(Border::getSize).sum() >= height) {
			throw new EPaperValidationException("Not enough height for the horizontal borders");
		}
	}

	private static <T> List<Integer> getSizes(int size, List<Border> borders, List<T> tList,
			Function<T, Double> sizeGetter) {
		List<Integer> tSizes = new ArrayList<>();
		int bordersSize = borders.stream().mapToInt(Border::getSize).sum();
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
					return getSizes(size, borders.subList(0, borders.size() - 1), tList.subList(0, tList.size() - 1),
							sizeGetter);
				}
			} else {
				tSizes.set(lastIndex, tSizes.get(lastIndex) + sizeDelta);
				tsSize += sizeDelta;
			}
		}
		return tSizes;
	}

	private static void addDrawableCells(int topLeftX, int topLeftY, DrawableTable drawableTable,
			List<Integer> columnWidths, List<Border> verticalBorders, List<Integer> rowHeights,
			List<Border> horizontalBorders, NavigableMap<Integer, NavigableMap<Integer, List<Cell>>> cells) {
		int cx0 = topLeftX + verticalBorders.get(0).getSize();
		int cx1;
		for (int i = 0; i < columnWidths.size(); i++) {
			NavigableMap<Integer, List<Cell>> columnCells = cells.get(i);
			int columnWidth = columnWidths.get(i);
			cx1 = cx0 + columnWidth - 1;
			if (columnCells != null) {
				int cy0 = topLeftY + horizontalBorders.get(0).getSize();
				int cy1;
				for (int j = 0; j < rowHeights.size(); j++) {
					List<Cell> cellCells = columnCells.get(j);
					int rowHeight = rowHeights.get(j);
					cy1 = cy0 + rowHeight - 1;
					if (cellCells != null) {
						Optional<Cell> lastBackgroundColorNullCellOptional = cellCells.stream()
								.sorted((c1, c2) -> Integer.compare(c2.getZIndex(), c1.getZIndex()))
								.filter(c -> c.getBackgroundColor() != null).findFirst();
						List<Cell> drawableCellCells = cellCells.stream()
								.sorted((c1, c2) -> Integer.compare(c1.getZIndex(), c2.getZIndex()))
								.collect(Collectors.toList());
						if (lastBackgroundColorNullCellOptional.isPresent()
								&& drawableCellCells.stream().filter(c -> c.getBackgroundColor() != null).count() > 1) {
							drawableCellCells
									.subList(0, drawableCellCells.indexOf(lastBackgroundColorNullCellOptional.get()))
									.clear();
						}
						drawableTable.getDrawableCells()
								.add(new DrawableCell(cx0, cy0, cx1, cy1,
										lastBackgroundColorNullCellOptional.map(Cell::getBackgroundColor).orElse(null),
										drawableCellCells.stream().map(Cell::getCellContent).filter(Objects::nonNull)
												.collect(Collectors.toList())));
					}
					cy0 = cy1 + horizontalBorders.get(j + 1).getSize() + 1;
				}
			}
			cx0 = cx1 + verticalBorders.get(i + 1).getSize() + 1;
		}
	}

	private static void addHorizontalDrawableBorders(int width, int topLeftX, int topLeftY, DrawableTable drawableTable,
			List<Integer> rowHeights, List<Border> horizontalBorders) {
		Border border = horizontalBorders.get(0);
		int hx0 = topLeftX;
		int hx1 = hx0 + width - 1;
		int hy0 = topLeftY;
		int hy1 = hy0 + border.getSize() - 1;
		if (border.getSize() != 0) {
			drawableTable.getDrawableBorders().add(new DrawableBorder(hx0, hy0, hx1, hy1, border.getColor()));
		}
		for (int i = 0; i < rowHeights.size(); i++) {
			int rowHeight = rowHeights.get(i);
			border = horizontalBorders.get(i + 1);
			hy0 = hy1 + rowHeight + 1;
			hy1 = hy0 + border.getSize() - 1;
			if (border.getSize() != 0) {
				drawableTable.getDrawableBorders().add(new DrawableBorder(hx0, hy0, hx1, hy1, border.getColor()));
			}
		}
	}

	private static void addVerticalDrawableBorders(int height, int topLeftX, int topLeftY, DrawableTable drawableTable,
			List<Integer> columnWidths, List<Border> verticalBorders) {
		Border border = verticalBorders.get(0);
		int vx0 = topLeftX;
		int vx1 = vx0 + border.getSize() - 1;
		int vy0 = topLeftY;
		int vy1 = vy0 + height - 1;
		if (border.getSize() != 0) {
			drawableTable.getDrawableBorders().add(new DrawableBorder(vx0, vy0, vx1, vy1, border.getColor()));
		}
		for (int i = 0; i < columnWidths.size(); i++) {
			int columnWidth = columnWidths.get(i);
			border = verticalBorders.get(i + 1);
			vx0 = vx1 + columnWidth + 1;
			vx1 = vx0 + border.getSize() - 1;
			if (border.getSize() != 0) {
				drawableTable.getDrawableBorders().add(new DrawableBorder(vx0, vy0, vx1, vy1, border.getColor()));
			}
		}
	}

	private static void validateColumn(Column column, int width, int height, Table table)
			throws EPaperValidationException {
		if (column.getWidth() < 0d) {
			throw new EPaperValidationException("Invalid column width");
		}
	}

	private static void validateBorder(Border border, int width, int height, Table table)
			throws EPaperValidationException {
		if (border.getSize() < 0) {
			throw new EPaperValidationException("Invalid border size");
		}
		if (border.getSize() > 0 && border.getColor() == null) {
			throw new EPaperValidationException("Empty border color");
		}
	}

	private static void validateRow(Row row, int width, int height, Table table) throws EPaperValidationException {
		if (row.getHeight() < 0d) {
			throw new EPaperValidationException("Invalid row height");
		}
	}
}
