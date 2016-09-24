package fr.romainmoreau.epaper.client.common;

import org.junit.Assert;
import org.junit.Test;

import fr.romainmoreau.epaper.client.common.EPaperValidationException;
import fr.romainmoreau.epaper.client.common.FontSize;
import fr.romainmoreau.epaper.client.common.Line;

public class LineTest {
	@Test(expected = EPaperValidationException.class)
	public void test1() throws EPaperValidationException {
		Line line = new Line(0, FontSize.DOTS_32);
		line.addWord("foo");
	}

	@Test(expected = EPaperValidationException.class)
	public void test2() throws EPaperValidationException {
		Line line = new Line(4, FontSize.DOTS_32);
		line.addWord("i");
	}

	@Test
	public void test3() throws EPaperValidationException {
		Line line = new Line(5, FontSize.DOTS_32);
		Assert.assertTrue(line.addWord("i"));
		Assert.assertEquals(5, line.getWidth());
		Assert.assertEquals("i", line.getText());
	}

	@Test
	public void test4() throws EPaperValidationException {
		Line line = new Line(6, FontSize.DOTS_32);
		Assert.assertTrue(line.addWord("i"));
		Assert.assertEquals(5, line.getWidth());
		Assert.assertEquals("i", line.getText());
	}

	@Test
	public void test5() throws EPaperValidationException {
		Line line = new Line(5, FontSize.DOTS_32);
		Assert.assertTrue(line.addWord("ii"));
		Assert.assertEquals(5, line.getWidth());
		Assert.assertEquals("i", line.getText());
	}

	@Test(expected = EPaperValidationException.class)
	public void test6() throws EPaperValidationException {
		Line line = new Line(4, FontSize.DOTS_32);
		line.addWord("ii");
	}

	@Test
	public void test7() throws EPaperValidationException {
		Line line = new Line(5, FontSize.DOTS_32);
		Assert.assertTrue(line.addWord("i"));
		Assert.assertFalse(line.addWord("i"));
		Assert.assertEquals(5, line.getWidth());
		Assert.assertEquals("i", line.getText());
	}

	@Test
	public void test8() throws EPaperValidationException {
		Line line = new Line(18, FontSize.DOTS_32);
		Assert.assertTrue(line.addWord("i"));
		Assert.assertFalse(line.addWord("i"));
		Assert.assertEquals(5, line.getWidth());
		Assert.assertEquals("i", line.getText());
	}

	@Test
	public void test9() throws EPaperValidationException {
		Line line = new Line(19, FontSize.DOTS_32);
		Assert.assertTrue(line.addWord("i"));
		Assert.assertTrue(line.addWord("i"));
		Assert.assertEquals(19, line.getWidth());
		Assert.assertEquals("i i", line.getText());
	}
}
