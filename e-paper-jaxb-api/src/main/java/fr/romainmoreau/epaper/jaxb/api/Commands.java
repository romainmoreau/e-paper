package fr.romainmoreau.epaper.jaxb.api;

import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Commands {
	private List<Command> commands;

	@XmlElements({ @XmlElement(name = "clear", type = Clear.class),
			@XmlElement(name = "setDrawingColors", type = SetDrawingColors.class),
			@XmlElement(name = "setDisplayDirection", type = SetDisplayDirection.class),
			@XmlElement(name = "setFontSize", type = SetFontSize.class),
			@XmlElement(name = "drawLine", type = DrawLine.class),
			@XmlElement(name = "drawRectangle", type = DrawRectangle.class),
			@XmlElement(name = "drawPoint", type = DrawPoint.class),
			@XmlElement(name = "drawTable", type = DrawTable.class),
			@XmlElement(name = "fillRectangle", type = FillRectangle.class),
			@XmlElement(name = "displayText", type = DisplayText.class),
			@XmlElement(name = "displayTextRectangle", type = DisplayTextRectangle.class),
			@XmlElement(name = "refreshAndUpdate", type = RefreshAndUpdate.class) })
	public List<Command> getCommands() {
		return commands;
	}

	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}
}
