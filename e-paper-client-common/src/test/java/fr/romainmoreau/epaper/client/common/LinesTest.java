package fr.romainmoreau.epaper.client.common;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import fr.romainmoreau.epaper.client.api.EPaperValidationException;
import fr.romainmoreau.epaper.client.api.FontSize;

public class LinesTest {
	@Test(expected = EPaperValidationException.class)
	public void validateTextTest1() throws EPaperValidationException {
		Lines.validateText("é");
	}

	@Test
	public void validateTextTest2() throws EPaperValidationException {
		String validCharacters = getValidCharacters();
		Assert.assertEquals(97, validCharacters.length());
		Lines.validateText(validCharacters);
	}

	@Test(expected = EPaperValidationException.class)
	public void validateTextTest3() throws EPaperValidationException {
		String text = getValidCharacters() + "é";
		Assert.assertEquals(98, text.length());
		Lines.validateText(text);
	}

	@Test(expected = EPaperValidationException.class)
	public void validateTextTest4() throws EPaperValidationException {
		String text = getValidCharacters() + "\r";
		Assert.assertEquals(98, text.length());
		Lines.validateText(text);
	}

	@Test(expected = EPaperValidationException.class)
	public void getMaxLinesTest1() throws EPaperValidationException {
		Lines.getMaxLines(31, FontSize.DOTS_32, 1);
	}

	@Test
	public void getMaxLinesTest2() throws EPaperValidationException {
		Assert.assertEquals(1, Lines.getMaxLines(32, FontSize.DOTS_32, 1));
	}

	@Test
	public void getMaxLinesTest3() throws EPaperValidationException {
		Assert.assertEquals(1, Lines.getMaxLines(64, FontSize.DOTS_32, 1));
	}

	@Test
	public void getMaxLinesTest4() throws EPaperValidationException {
		Assert.assertEquals(2, Lines.getMaxLines(65, FontSize.DOTS_32, 1));
	}

	@Test(expected = EPaperValidationException.class)
	public void getMaxLinesTest5() throws EPaperValidationException {
		Lines.getMaxLines(31, FontSize.DOTS_32, 0);
	}

	@Test
	public void getMaxLinesTest6() throws EPaperValidationException {
		Assert.assertEquals(1, Lines.getMaxLines(32, FontSize.DOTS_32, 0));
	}

	@Test
	public void getMaxLinesTest7() throws EPaperValidationException {
		Assert.assertEquals(1, Lines.getMaxLines(63, FontSize.DOTS_32, 0));
	}

	@Test
	public void getMaxLinesTest8() throws EPaperValidationException {
		Assert.assertEquals(2, Lines.getMaxLines(64, FontSize.DOTS_32, 0));
	}

	@Test
	public void getWordsTest1() throws EPaperValidationException {
		Assert.assertEquals(Collections.emptyList(), Lines.getWords("    "));
	}

	@Test
	public void getWordsTest2() throws EPaperValidationException {
		Assert.assertEquals(Arrays.asList("   "), Lines.getWords("\u00A0\u00A0\u00A0"));
	}

	@Test
	public void getWordsTest3() throws EPaperValidationException {
		Assert.assertEquals(Arrays.asList("hello", "world: hello", "world"),
				Lines.getWords("hello world:\u00A0hello world"));
	}

	@Test
	public void constructLinesTest1() throws EPaperValidationException {
		List<Line> lines = Lines.constructLines(5, 32, FontSize.DOTS_32, 1, "i");
		Assert.assertEquals(Arrays.asList("i"), lines.stream().map(Line::getText).collect(Collectors.toList()));
	}

	@Test
	public void constructLinesTest2() throws EPaperValidationException {
		List<Line> lines = Lines.constructLines(5, 32, FontSize.DOTS_32, 1, "i i");
		Assert.assertEquals(Arrays.asList("i"), lines.stream().map(Line::getText).collect(Collectors.toList()));
	}

	@Test
	public void constructLinesTest3() throws EPaperValidationException {
		List<Line> lines = Lines.constructLines(5, 97, FontSize.DOTS_32, 1, "i i i");
		Assert.assertEquals(Arrays.asList("i", "i"), lines.stream().map(Line::getText).collect(Collectors.toList()));
	}

	@Test
	public void constructLinesTest4() throws EPaperValidationException {
		List<Line> lines = Lines.constructLines(5, 98, FontSize.DOTS_32, 1, "i i i");
		Assert.assertEquals(Arrays.asList("i", "i", "i"),
				lines.stream().map(Line::getText).collect(Collectors.toList()));
	}

	private String getValidCharacters() {
		StringBuilder stringBuilder = new StringBuilder();
		Arrays.asList(PrintableCharacter.values()).stream().map(PrintableCharacter::getCharacter)
				.forEach(stringBuilder::append);
		stringBuilder.append(Lines.NO_BREAK_SPACE);
		stringBuilder.append(Lines.LINE_FEED);
		return stringBuilder.toString();
	}
}
