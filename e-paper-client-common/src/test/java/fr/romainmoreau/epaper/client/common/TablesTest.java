package fr.romainmoreau.epaper.client.common;

import org.junit.Assert;
import org.junit.Test;

import fr.romainmoreau.epaper.client.api.Color;
import fr.romainmoreau.epaper.client.api.EPaperValidationException;
import fr.romainmoreau.epaper.client.api.table.Column;
import fr.romainmoreau.epaper.client.api.table.Row;
import fr.romainmoreau.epaper.client.api.table.Table;
import fr.romainmoreau.epaper.client.common.table.DrawableTable;
import fr.romainmoreau.epaper.client.common.table.Tables;

public class TablesTest {
	@Test(expected = EPaperValidationException.class)
	public void validateTableTest1() throws EPaperValidationException {
		Table table = new Table(200, Color.BLACK);
		table.getColumns().add(new Column(0.25));
		table.getColumns().add(new Column(0.25));
		table.getColumns().add(new Column(0.25));
		table.getColumns().add(new Column(0.25));
		table.getRows().add(new Row(0.33));
		table.getRows().add(new Row(0.33));
		table.getRows().add(new Row(0.33));
		Tables.validateTable(400, 600, table);
	}

	@Test
	public void validateTableTest2() throws EPaperValidationException {
		Table table = new Table(199, Color.BLACK);
		table.getColumns().add(new Column(0.25));
		table.getColumns().add(new Column(0.25));
		table.getColumns().add(new Column(0.25));
		table.getColumns().add(new Column(0.25));
		table.getRows().add(new Row(0.33));
		table.getRows().add(new Row(0.33));
		table.getRows().add(new Row(0.33));
		Tables.validateTable(400, 600, table);
	}

	@Test(expected = EPaperValidationException.class)
	public void validateTableTest3() throws EPaperValidationException {
		Table table = new Table(200, Color.BLACK);
		table.getColumns().add(new Column(0.25));
		table.getColumns().add(new Column(0.25));
		table.getColumns().add(new Column(0.25));
		table.getColumns().add(new Column(0.25));
		table.getRows().add(new Row(0.33));
		table.getRows().add(new Row(0.33));
		table.getRows().add(new Row(0.33));
		Tables.validateTable(600, 400, table);
	}

	@Test
	public void validateTableTest4() throws EPaperValidationException {
		Table table = new Table(199, Color.BLACK);
		table.getColumns().add(new Column(0.25));
		table.getColumns().add(new Column(0.25));
		table.getColumns().add(new Column(0.25));
		table.getColumns().add(new Column(0.25));
		table.getRows().add(new Row(0.33));
		table.getRows().add(new Row(0.33));
		table.getRows().add(new Row(0.33));
		Tables.validateTable(600, 400, table);
	}

	@Test
	public void getDrawableTableTest1() {
		Table table = new Table(1, Color.BLACK);
		table.getColumns().add(new Column(0.25));
		table.getColumns().add(new Column(0.25));
		table.getColumns().add(new Column(0.25));
		table.getColumns().add(new Column(0.25));
		table.getRows().add(new Row(0.33));
		table.getRows().add(new Row(0.33));
		table.getRows().add(new Row(0.33));
		DrawableTable drawableTable = Tables.getDrawableTable(400, 300, 0, 0, table);
		Assert.assertEquals(9, drawableTable.getDrawableBorders().size());
	}

	@Test
	public void getDrawableTableTest2() {
		Table table = new Table(1, Color.BLACK);
		table.getColumns().add(new Column(0.5));
		table.getColumns().add(new Column(0.5));
		table.getColumns().add(new Column(0.25));
		table.getColumns().add(new Column(0.25));
		table.getRows().add(new Row(0.66));
		table.getRows().add(new Row(0.66));
		table.getRows().add(new Row(0.33));
		DrawableTable drawableTable = Tables.getDrawableTable(400, 300, 0, 0, table);
		Assert.assertEquals(6, drawableTable.getDrawableBorders().size());
	}

	@Test
	public void getDrawableTableTest3() {
		Table table = new Table(1, Color.BLACK);
		table.getColumns().add(new Column(400));
		table.getColumns().add(new Column(0.25));
		table.getColumns().add(new Column(0.25));
		table.getColumns().add(new Column(0.25));
		table.getRows().add(new Row(300));
		table.getRows().add(new Row(0.33));
		table.getRows().add(new Row(0.33));
		DrawableTable drawableTable = Tables.getDrawableTable(400, 300, 0, 0, table);
		Assert.assertEquals(4, drawableTable.getDrawableBorders().size());
	}
}
