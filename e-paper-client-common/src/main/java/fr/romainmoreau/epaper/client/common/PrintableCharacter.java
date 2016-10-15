package fr.romainmoreau.epaper.client.common;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import fr.romainmoreau.epaper.client.api.FontSize;

public enum PrintableCharacter {
	SPACE(' ', 7, 7, 7), //
	EXCLAMATION_MARK('!', 7, 7, 8), //
	QUOTATION_MARK('"', 9, 23, 23), //
	NUMBER_SIGN('#', 14, 23, 28), //
	DOLLAR_SIGN('$', 14, 23, 23), //
	PERCENT_SIGN('%', 23, 23, 31), //
	AMPERSAND('&', 17, 23, 31), //
	APOSTROPHE('\'', 4, 8, 8), //
	LEFT_PARENTHESIS('(', 8, 15, 16), //
	RIGHT_PARENTHESIS(')', 8, 15, 17), //
	ASTERISK('*', 10, 23, 28), //
	PLUS_SIGN('+', 15, 23, 29), //
	COMMA(',', 7, 10, 10), //
	HYPHEN_MINUS('-', 8, 23, 29), //
	FULL_STOP('.', 7, 10, 10), //
	SOLIDUS('/', 7, 23, 30), //
	DIGIT_ZERO('0', 14, 23, 27), //
	DIGIT_ONE('1', 14, 23, 19), //
	DIGIT_TWO('2', 14, 23, 26), //
	DIGIT_THREE('3', 14, 23, 25), //
	DIGIT_FOUR('4', 14, 23, 29), //
	DIGIT_FIVE('5', 14, 23, 25), //
	DIGIT_SIX('6', 14, 23, 27), //
	DIGIT_SEVEN('7', 14, 23, 25), //
	DIGIT_EIGHT('8', 14, 23, 27), //
	DIGIT_NINE('9', 14, 23, 27), //
	COLON(':', 7, 7, 9), //
	SEMICOLON(';', 7, 7, 7), //
	LESS_THAN_SIGN('<', 15, 23, 25), //
	EQUALS_SIGN('=', 15, 23, 29), //
	GREATER_THAN_SIGN('>', 15, 23, 25), //
	QUESTION_MARK('?', 14, 23, 27), //
	COMMERCIAL_AT('@', 26, 23, 29), //
	LATIN_CAPITAL_LETTER_A('A', 17, 23, 31), //
	LATIN_CAPITAL_LETTER_B('B', 17, 23, 31), //
	LATIN_CAPITAL_LETTER_C('C', 19, 23, 31), //
	LATIN_CAPITAL_LETTER_D('D', 19, 23, 31), //
	LATIN_CAPITAL_LETTER_E('E', 17, 23, 31), //
	LATIN_CAPITAL_LETTER_F('F', 16, 23, 31), //
	LATIN_CAPITAL_LETTER_G('G', 20, 23, 31), //
	LATIN_CAPITAL_LETTER_H('H', 18, 23, 31), //
	LATIN_CAPITAL_LETTER_I('I', 7, 23, 31), //
	LATIN_CAPITAL_LETTER_J('J', 12, 23, 31), //
	LATIN_CAPITAL_LETTER_K('K', 17, 23, 31), //
	LATIN_CAPITAL_LETTER_L('L', 14, 23, 31), //
	LATIN_CAPITAL_LETTER_M('M', 22, 23, 31), //
	LATIN_CAPITAL_LETTER_N('N', 18, 23, 31), //
	LATIN_CAPITAL_LETTER_O('O', 20, 23, 31), //
	LATIN_CAPITAL_LETTER_P('P', 16, 23, 31), //
	LATIN_CAPITAL_LETTER_Q('Q', 20, 23, 31), //
	LATIN_CAPITAL_LETTER_R('R', 19, 23, 31), //
	LATIN_CAPITAL_LETTER_S('S', 17, 23, 31), //
	LATIN_CAPITAL_LETTER_T('T', 15, 23, 31), //
	LATIN_CAPITAL_LETTER_U('U', 18, 23, 31), //
	LATIN_CAPITAL_LETTER_V('V', 16, 23, 31), //
	LATIN_CAPITAL_LETTER_W('W', 27, 23, 31), //
	LATIN_CAPITAL_LETTER_X('X', 16, 23, 31), //
	LATIN_CAPITAL_LETTER_Y('Y', 17, 23, 31), //
	LATIN_CAPITAL_LETTER_Z('Z', 16, 23, 31), //
	LEFT_SQUARE_BRACKET('[', 7, 15, 20), //
	REVERSE_SOLIDUS('\\', 7, 23, 31), //
	RIGHT_SQUARE_BRACKET(']', 7, 15, 20), //
	CIRCUMFLEX_ACCENT('^', 11, 11, 23), //
	LOW_LINE('_', 14, 23, 31), //
	GRAVE_ACCENT('`', 8, 12, 18), //
	LATIN_SMALL_LETTER_A('a', 14, 22, 31), //
	LATIN_SMALL_LETTER_B('b', 14, 21, 31), //
	LATIN_SMALL_LETTER_C('c', 13, 19, 27), //
	LATIN_SMALL_LETTER_D('d', 14, 21, 30), //
	LATIN_SMALL_LETTER_E('e', 14, 18, 27), //
	LATIN_SMALL_LETTER_F('f', 6, 21, 30), //
	LATIN_SMALL_LETTER_G('g', 14, 21, 30), //
	LATIN_SMALL_LETTER_H('h', 14, 23, 31), //
	LATIN_SMALL_LETTER_I('i', 5, 16, 23), //
	LATIN_SMALL_LETTER_J('j', 5, 17, 23), //
	LATIN_SMALL_LETTER_K('k', 13, 22, 31), //
	LATIN_SMALL_LETTER_L('l', 5, 16, 23), //
	LATIN_SMALL_LETTER_M('m', 21, 23, 35), //
	LATIN_SMALL_LETTER_N('n', 14, 23, 31), //
	LATIN_SMALL_LETTER_O('o', 14, 21, 29), //
	LATIN_SMALL_LETTER_P('p', 14, 22, 31), //
	LATIN_SMALL_LETTER_Q('q', 14, 21, 30), //
	LATIN_SMALL_LETTER_R('r', 8, 21, 30), //
	LATIN_SMALL_LETTER_S('s', 13, 19, 26), //
	LATIN_SMALL_LETTER_T('t', 7, 19, 27), //
	LATIN_SMALL_LETTER_U('u', 14, 23, 31), //
	LATIN_SMALL_LETTER_V('v', 12, 22, 31), //
	LATIN_SMALL_LETTER_W('w', 18, 25, 35), //
	LATIN_SMALL_LETTER_X('x', 11, 21, 30), //
	LATIN_SMALL_LETTER_Y('y', 13, 21, 30), //
	LATIN_SMALL_LETTER_Z('z', 12, 18, 26), //
	LEFT_CURLY_BRACKET('{', 8, 12, 17), //
	VERTICAL_LINE('|', 5, 4, 7), //
	RIGHT_CURLY_BRACKET('}', 8, 12, 17), //
	TILDE('~', 15, 22, 30);

	private static final Map<Character, Integer> CHARACTER_WIDTH32_MAP;

	private static final Map<Character, Integer> CHARACTER_WIDTH48_MAP;

	private static final Map<Character, Integer> CHARACTER_WIDTH64_MAP;

	private final char character;

	private final int width32;

	private final int width48;

	private final int width64;

	static {
		CHARACTER_WIDTH32_MAP = Arrays.asList(PrintableCharacter.values()).stream()
				.collect(Collectors.toMap(PrintableCharacter::getCharacter, PrintableCharacter::getWidth32));
		CHARACTER_WIDTH48_MAP = Arrays.asList(PrintableCharacter.values()).stream()
				.collect(Collectors.toMap(PrintableCharacter::getCharacter, PrintableCharacter::getWidth48));
		CHARACTER_WIDTH64_MAP = Arrays.asList(PrintableCharacter.values()).stream()
				.collect(Collectors.toMap(PrintableCharacter::getCharacter, PrintableCharacter::getWidth64));
	}

	private PrintableCharacter(char character, int width32, int width48, int width64) {
		this.character = character;
		this.width32 = width32;
		this.width48 = width48;
		this.width64 = width64;
	}

	public static Map<Character, Integer> getCharacterWidthMap(FontSize fontSize) {
		switch (fontSize) {
		case DOTS_32:
			return CHARACTER_WIDTH32_MAP;
		case DOTS_48:
			return CHARACTER_WIDTH48_MAP;
		case DOTS_64:
			return CHARACTER_WIDTH64_MAP;
		default:
			throw new IllegalStateException("Unsupported font size");

		}
	}

	public char getCharacter() {
		return character;
	}

	public int getWidth32() {
		return width32;
	}

	public int getWidth48() {
		return width48;
	}

	public int getWidth64() {
		return width64;
	}

	public int getWidth(FontSize fontSize) {
		switch (fontSize) {
		case DOTS_32:
			return width32;
		case DOTS_48:
			return width48;
		case DOTS_64:
			return width64;
		default:
			throw new IllegalStateException("Unsupported font size");
		}
	}
}
