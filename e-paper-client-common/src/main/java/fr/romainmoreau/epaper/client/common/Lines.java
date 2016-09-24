package fr.romainmoreau.epaper.client.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Lines {
	public static final char NO_BREAK_SPACE = '\u00A0';

	public static final String LINE_FEED = "\n";

	private static final Pattern VALID_TEXT_PATTERN = Pattern.compile("[[\\p{Print}&&\\p{ASCII}]\u00A0\n]+");

	public static void validateFontSize(FontSize fontSize) throws EPaperValidationException {
		if (fontSize == null) {
			throw new EPaperValidationException("Empty font size");
		}
	}

	public static void validateSpacing(int spacing) throws EPaperValidationException {
		if (spacing < 0) {
			throw new EPaperValidationException("Negative spacing");
		}
	}

	public static void validateHorizontalAlignment(HorizontalAlignment horizontalAlignment)
			throws EPaperValidationException {
		if (horizontalAlignment == null) {
			throw new EPaperValidationException("Empty horizontal alignment");
		}
	}

	public static void validateVerticalAlignment(VerticalAlignment verticalAlignment) throws EPaperValidationException {
		if (verticalAlignment == null) {
			throw new EPaperValidationException("Empty vertical alignment");
		}
	}

	public static void validateText(String text) throws EPaperValidationException {
		Matcher matcher = VALID_TEXT_PATTERN.matcher(text);
		if (!matcher.matches()) {
			throw new EPaperValidationException("Invalid character");
		}
	}

	public static int getMaxLines(int height, FontSize fontSize, int spacing) throws EPaperValidationException {
		int maxLines = (height + spacing) / (fontSize.getHeight() + spacing);
		if (maxLines < 1) {
			throw new EPaperValidationException("Not enough height");
		}
		return maxLines;
	}

	public static List<String> getWords(String text) {
		List<String> words = Arrays.asList(text.split(" ")).stream()
				.map(w -> w.replace(NO_BREAK_SPACE, PrintableCharacter.SPACE.getCharacter()))
				.collect(Collectors.toList());
		for (int i = 0; i < words.size(); i++) {
			String word = words.get(i);
			String[] newWords = word.split(LINE_FEED, -1);
			if (newWords.length > 1) {
				words.remove(i);
				for (int j = newWords.length - 1; j > -1; j--) {
					String newWord = newWords[j];
					if (!newWord.isEmpty()) {
						words.add(i, newWord);
					}
					if (j > 0) {
						words.add(i, LINE_FEED);
					}
				}
			}
		}
		return words;
	}

	public static List<Line> constructLines(int width, int height, FontSize fontSize, int spacing, String text)
			throws EPaperValidationException {
		int maxLines = Lines.getMaxLines(height, fontSize, spacing);
		List<String> words = Lines.getWords(text);
		List<Line> lines = new ArrayList<>();
		if (!words.isEmpty()) {
			Line line = new Line(width, fontSize);
			lines.add(line);
			for (String word : words) {
				boolean newLine = word.equals(LINE_FEED);
				if (newLine || !line.addWord(word)) {
					if (lines.size() == maxLines) {
						break;
					} else {
						line = new Line(width, fontSize);
						lines.add(line);
					}
					if (!newLine) {
						line.addWord(word);
					}
				}
			}
		}
		return lines;
	}

	public static void alignLines(int width, int height, int topLeftX, int topLeftY, FontSize fontSize, int spacing,
			HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment, List<Line> lines) {
		int linesHeight = lines.size() * (fontSize.getHeight() + spacing) - spacing;
		int y = topLeftY;
		switch (verticalAlignment) {
		case BOTTOM:
			y += height - linesHeight;
			break;
		case CENTER:
			y += (height - linesHeight) / 2;
			break;
		case TOP:
			break;
		default:
			throw new IllegalStateException("Invalid vertical alignment");
		}
		for (Line line : lines) {
			switch (horizontalAlignment) {
			case CENTER:
				line.setX(topLeftX + (width - line.getWidth()) / 2);
				break;
			case LEFT:
				line.setX(topLeftX);
				break;
			case RIGHT:
				line.setX(topLeftX + width - line.getWidth());
				break;
			default:
				throw new IllegalStateException("Invalid horizontal alignment");
			}
			line.setY(y);
			y += fontSize.getHeight() + spacing;
		}
	}

	public static List<Line> getLines(int width, int height, int topLeftX, int topLeftY, FontSize fontSize, int spacing,
			HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment, String text)
			throws EPaperValidationException {
		List<Line> lines = constructLines(width, height, fontSize, spacing, text);
		alignLines(width, height, topLeftX, topLeftY, fontSize, spacing, horizontalAlignment, verticalAlignment, lines);
		return lines;
	}
}
