package fr.romainmoreau.epaper.client.common;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Line {
	private final int maxWidth;

	private final FontSize fontSize;

	private final Map<Character, Integer> characterWidthMap;

	private final StringBuilder lineStringBuilder;

	private int width;

	private int x;

	private int y;

	public Line(int maxWidth, FontSize fontSize) {
		this.maxWidth = maxWidth;
		this.fontSize = fontSize;
		this.characterWidthMap = PrintableCharacter.getCharacterWidthMap(fontSize);
		this.lineStringBuilder = new StringBuilder();
	}

	public boolean addWord(String word) throws EPaperValidationException {
		boolean empty = width == 0;
		List<Integer> wordWidths = getWidths(word);
		int wordWidth = wordWidths.stream().mapToInt(i -> i).sum()
				+ fontSize.getLetterSpacing() * (wordWidths.size() - 1);
		if (!empty) {
			wordWidth += characterWidthMap.get(PrintableCharacter.SPACE.getCharacter())
					+ 2 * fontSize.getLetterSpacing();
		}
		boolean remainingWidth = wordWidth + width <= maxWidth;
		boolean add = remainingWidth || empty;
		if (add) {
			if (!remainingWidth) {
				int i = wordWidths.size() - 1;
				while (wordWidth + width > maxWidth && i > 0) {
					wordWidth -= wordWidths.get(i) + fontSize.getLetterSpacing();
					i--;
				}
				if (wordWidth + width > maxWidth && i == 0) {
					throw new EPaperValidationException("Not enough width");
				} else {
					word = word.substring(0, i + 1);
				}
			}
			if (!empty) {
				lineStringBuilder.append(PrintableCharacter.SPACE.getCharacter());
			}
			lineStringBuilder.append(word);
			width += wordWidth;
		}
		return add;
	}

	public String getText() {
		return lineStringBuilder.toString();
	}

	public int getWidth() {
		return width;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	private List<Integer> getWidths(String word) {
		return word.chars().mapToObj(c -> characterWidthMap.get((char) c)).collect(Collectors.toList());
	}
}
