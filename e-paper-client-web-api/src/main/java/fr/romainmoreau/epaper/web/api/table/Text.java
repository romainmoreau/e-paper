package fr.romainmoreau.epaper.web.api.table;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

import fr.romainmoreau.epaper.client.api.FontSize;
import fr.romainmoreau.epaper.client.api.HorizontalAlignment;
import fr.romainmoreau.epaper.client.api.VerticalAlignment;
import fr.romainmoreau.epaper.client.api.table.TextCellContent;

public class Text implements CellContent {
	private FontSize fontSize;

	private int lineSpacing;

	private HorizontalAlignment horizontalAlignment;

	private VerticalAlignment verticalAlignment;

	private String text;

	@Override
	public fr.romainmoreau.epaper.client.api.table.CellContent toCellContent() {
		return new TextCellContent(fontSize, lineSpacing, horizontalAlignment, verticalAlignment, text);
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
