package fr.romainmoreau.epaper.client.common;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import fr.romainmoreau.epaper.client.api.FontSize;

public enum PrintableCharacter {
	SPACE(' ', 7), //
	EXCLAMATION_MARK('!', 7), //
	QUOTATION_MARK('"', 9), //
	NUMBER_SIGN('#', 14), //
	DOLLAR_SIGN('$', 14), //
	PERCENT_SIGN('%', 23), //
	AMPERSAND('&', 17), //
	APOSTROPHE('\'', 4), //
	LEFT_PARENTHESIS('(', 8), //
	RIGHT_PARENTHESIS(')', 8), //
	ASTERISK('*', 10), //
	PLUS_SIGN('+', 15), //
	COMMA(',', 7), //
	HYPHEN_MINUS('-', 8), //
	FULL_STOP('.', 7), //
	SOLIDUS('/', 7), //
	DIGIT_ZERO('0', 14), //
	DIGIT_ONE('1', 14), //
	DIGIT_TWO('2', 14), //
	DIGIT_THREE('3', 14), //
	DIGIT_FOUR('4', 14), //
	DIGIT_FIVE('5', 14), //
	DIGIT_SIX('6', 14), //
	DIGIT_SEVEN('7', 14), //
	DIGIT_EIGHT('8', 14), //
	DIGIT_NINE('9', 14), //
	COLON(':', 7), //
	SEMICOLON(';', 7), //
	LESS_THAN_SIGN('<', 15), //
	EQUALS_SIGN('=', 15), //
	GREATER_THAN_SIGN('>', 15), //
	QUESTION_MARK('?', 14), //
	COMMERCIAL_AT('@', 26), //
	LATIN_CAPITAL_LETTER_A('A', 17), //
	LATIN_CAPITAL_LETTER_B('B', 17), //
	LATIN_CAPITAL_LETTER_C('C', 19), //
	LATIN_CAPITAL_LETTER_D('D', 19), //
	LATIN_CAPITAL_LETTER_E('E', 17), //
	LATIN_CAPITAL_LETTER_F('F', 16), //
	LATIN_CAPITAL_LETTER_G('G', 20), //
	LATIN_CAPITAL_LETTER_H('H', 18), //
	LATIN_CAPITAL_LETTER_I('I', 7), //
	LATIN_CAPITAL_LETTER_J('J', 12), //
	LATIN_CAPITAL_LETTER_K('K', 17), //
	LATIN_CAPITAL_LETTER_L('L', 14), //
	LATIN_CAPITAL_LETTER_M('M', 22), //
	LATIN_CAPITAL_LETTER_N('N', 18), //
	LATIN_CAPITAL_LETTER_O('O', 20), //
	LATIN_CAPITAL_LETTER_P('P', 16), //
	LATIN_CAPITAL_LETTER_Q('Q', 20), //
	LATIN_CAPITAL_LETTER_R('R', 19), //
	LATIN_CAPITAL_LETTER_S('S', 17), //
	LATIN_CAPITAL_LETTER_T('T', 15), //
	LATIN_CAPITAL_LETTER_U('U', 18), //
	LATIN_CAPITAL_LETTER_V('V', 16), //
	LATIN_CAPITAL_LETTER_W('W', 27), //
	LATIN_CAPITAL_LETTER_X('X', 16), //
	LATIN_CAPITAL_LETTER_Y('Y', 17), //
	LATIN_CAPITAL_LETTER_Z('Z', 16), //
	LEFT_SQUARE_BRACKET('[', 7), //
	REVERSE_SOLIDUS('\\', 7), //
	RIGHT_SQUARE_BRACKET(']', 7), //
	CIRCUMFLEX_ACCENT('^', 11), //
	LOW_LINE('_', 14), //
	GRAVE_ACCENT('`', 8), //
	LATIN_SMALL_LETTER_A('a', 14), //
	LATIN_SMALL_LETTER_B('b', 14), //
	LATIN_SMALL_LETTER_C('c', 13), //
	LATIN_SMALL_LETTER_D('d', 14), //
	LATIN_SMALL_LETTER_E('e', 14), //
	LATIN_SMALL_LETTER_F('f', 6), //
	LATIN_SMALL_LETTER_G('g', 14), //
	LATIN_SMALL_LETTER_H('h', 14), //
	LATIN_SMALL_LETTER_I('i', 5), //
	LATIN_SMALL_LETTER_J('j', 5), //
	LATIN_SMALL_LETTER_K('k', 13), //
	LATIN_SMALL_LETTER_L('l', 5), //
	LATIN_SMALL_LETTER_M('m', 21), //
	LATIN_SMALL_LETTER_N('n', 14), //
	LATIN_SMALL_LETTER_O('o', 14), //
	LATIN_SMALL_LETTER_P('p', 14), //
	LATIN_SMALL_LETTER_Q('q', 14), //
	LATIN_SMALL_LETTER_R('r', 8), //
	LATIN_SMALL_LETTER_S('s', 13), //
	LATIN_SMALL_LETTER_T('t', 7), //
	LATIN_SMALL_LETTER_U('u', 14), //
	LATIN_SMALL_LETTER_V('v', 12), //
	LATIN_SMALL_LETTER_W('w', 18), //
	LATIN_SMALL_LETTER_X('x', 11), //
	LATIN_SMALL_LETTER_Y('y', 13), //
	LATIN_SMALL_LETTER_Z('z', 12), //
	LEFT_CURLY_BRACKET('{', 8), //
	VERTICAL_LINE('|', 5), //
	RIGHT_CURLY_BRACKET('}', 8), //
	TILDE('~', 15);

	private static final Map<Character, Integer> CHARACTER_WIDTH32_MAP;

	private final char character;

	private final int width32;

	static {
		CHARACTER_WIDTH32_MAP = Arrays.asList(PrintableCharacter.values()).stream()
				.collect(Collectors.toMap(PrintableCharacter::getCharacter, PrintableCharacter::getWidth32));
	}

	private PrintableCharacter(char character, int width32) {
		this.character = character;
		this.width32 = width32;
	}

	public static Map<Character, Integer> getCharacterWidthMap(FontSize fontSize) {
		switch (fontSize) {
		case DOTS_32:
			return CHARACTER_WIDTH32_MAP;
		case DOTS_48:
			// TODO: add width for 48
			throw new IllegalStateException("Not supported yet");
		case DOTS_64:
			// TODO: add width for 64
			throw new IllegalStateException("Not supported yet");
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
}
