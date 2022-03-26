package fr.romainmoreau.epaper.jaxb.api.table;

import fr.romainmoreau.epaper.client.api.Color;
import fr.romainmoreau.epaper.client.api.FontSize;
import fr.romainmoreau.epaper.client.api.HorizontalAlignment;
import fr.romainmoreau.epaper.client.api.VerticalAlignment;
import fr.romainmoreau.epaper.client.api.table.TextCellContent;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;

public class Text implements CellContent {
	private Color textColor;

	private Color backgroundColor;

	private FontSize fontSize;

	private int lineSpacing;

	private HorizontalAlignment horizontalAlignment;

	private VerticalAlignment verticalAlignment;

	private String text;

	@Override
	public fr.romainmoreau.epaper.client.api.table.CellContent toCellContent() {
		return new TextCellContent(textColor, backgroundColor, fontSize, lineSpacing, horizontalAlignment,
				verticalAlignment, text);
	}

	@XmlAttribute(required = true)
	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	@XmlAttribute(required = true)
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	@XmlAttribute(required = true)
	public FontSize getFontSize() {
		return fontSize;
	}

	public void setFontSize(FontSize fontSize) {
		this.fontSize = fontSize;
	}

	@XmlAttribute(required = true)
	public int getLineSpacing() {
		return lineSpacing;
	}

	public void setLineSpacing(int lineSpacing) {
		this.lineSpacing = lineSpacing;
	}

	@XmlAttribute(required = true)
	public HorizontalAlignment getHorizontalAlignment() {
		return horizontalAlignment;
	}

	public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
		this.horizontalAlignment = horizontalAlignment;
	}

	@XmlAttribute(required = true)
	public VerticalAlignment getVerticalAlignment() {
		return verticalAlignment;
	}

	public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
		this.verticalAlignment = verticalAlignment;
	}

	@XmlValue
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
