package fr.romainmoreau.epaper.client.javafx;

import java.io.IOException;
import java.util.Map;

import fr.romainmoreau.epaper.client.api.Color;
import fr.romainmoreau.epaper.client.api.DisplayDirection;
import fr.romainmoreau.epaper.client.api.DrawingColors;
import fr.romainmoreau.epaper.client.api.EPaperException;
import fr.romainmoreau.epaper.client.api.FontSize;
import fr.romainmoreau.epaper.client.common.AbstractEPaperClient;
import fr.romainmoreau.epaper.client.common.Colors;
import fr.romainmoreau.epaper.client.common.Coordinates;
import fr.romainmoreau.epaper.client.common.Lines;
import fr.romainmoreau.epaper.client.common.PrintableCharacter;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class JavaFXEPaperClient extends AbstractEPaperClient {
	private volatile DrawingColors drawingColors;

	private volatile FontSize fontSize;

	private volatile DisplayDirection displayDirection;

	private final GraphicsContext graphicsContext;

	public JavaFXEPaperClient(Stage stage) {
		this.drawingColors = new DrawingColors(Color.BLACK, Color.WHITE);
		this.fontSize = FontSize.DOTS_32;
		this.displayDirection = DisplayDirection.NORMAL;
		Group group = new Group();
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		graphicsContext = canvas.getGraphicsContext2D();
		graphicsContext.setTextBaseline(VPos.TOP);
		group.getChildren().add(canvas);
		Scene scene = new Scene(group, WIDTH, HEIGHT);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.setResizable(false);
	}

	@Override
	public synchronized void clear() throws IOException, EPaperException {
		graphicsContext.setFill(ColorsJavaFX.getColor(drawingColors.getBackground()));
		graphicsContext.fillRect(0, 0, WIDTH, HEIGHT);
	}

	@Override
	public synchronized void close() throws IOException {
	}

	@Override
	public synchronized void refreshAndUpdate() throws IOException, EPaperException {
	}

	@Override
	public synchronized void drawPoint(int x, int y) throws IOException, EPaperException {
		Coordinates.validateCoordinates(x, y);
		graphicsContext.setStroke(ColorsJavaFX.getColor(drawingColors.getForeground()));
		graphicsContext.strokeLine(x, y, x, y);
	}

	@Override
	public synchronized void drawLine(int x0, int y0, int x1, int y1) throws IOException, EPaperException {
		Coordinates.validateCoordinates(x0, y0);
		Coordinates.validateCoordinates(x1, y1);
		graphicsContext.setStroke(ColorsJavaFX.getColor(drawingColors.getForeground()));
		graphicsContext.strokeLine(x0, y0, x1, y1);
	}

	@Override
	public synchronized void drawRectangle(int x0, int y0, int x1, int y1) throws IOException, EPaperException {
		Coordinates.validateCoordinates(x0, y0);
		Coordinates.validateCoordinates(x1, y1);
		int topLeftX = Coordinates.getTopLeftX(x0, x1);
		int topLeftY = Coordinates.getTopLeftY(y0, y1);
		int width = Coordinates.getBottomRightX(x0, x1) - topLeftX + 1;
		int height = Coordinates.getBottomRightY(y0, y1) - topLeftY + 1;
		graphicsContext.setStroke(ColorsJavaFX.getColor(drawingColors.getForeground()));
		graphicsContext.strokeRect(topLeftX, topLeftY, width, height);
	}

	@Override
	public synchronized void fillRectangle(int x0, int y0, int x1, int y1) throws IOException, EPaperException {
		Coordinates.validateCoordinates(x0, y0);
		Coordinates.validateCoordinates(x1, y1);
		int topLeftX = Coordinates.getTopLeftX(x0, x1);
		int topLeftY = Coordinates.getTopLeftY(y0, y1);
		int width = Coordinates.getBottomRightX(x0, x1) - topLeftX + 1;
		int height = Coordinates.getBottomRightY(y0, y1) - topLeftY + 1;
		graphicsContext.setFill(ColorsJavaFX.getColor(drawingColors.getForeground()));
		graphicsContext.fillRect(topLeftX, topLeftY, width, height);
	}

	@Override
	public synchronized void displayText(int x, int y, String text) throws IOException, EPaperException {
		Coordinates.validateCoordinates(x, y);
		Lines.validateText(text);
		Map<Character, Integer> characterWidthMap = PrintableCharacter.getCharacterWidthMap(fontSize);
		boolean firstCharacter = true;
		for (char character : text.toCharArray()) {
			if (firstCharacter) {
				firstCharacter = false;
			} else {
				graphicsContext.setFill(ColorsJavaFX.getColor(drawingColors.getBackground()));
				graphicsContext.fillRect(x, y, fontSize.getLetterSpacing(), fontSize.getHeight());
				x += fontSize.getLetterSpacing();
			}
			int width = characterWidthMap.get(character);
			graphicsContext.setFill(ColorsJavaFX.getColor(drawingColors.getBackground()));
			graphicsContext.fillRect(x, y, width, fontSize.getHeight());
			graphicsContext.setFill(ColorsJavaFX.getColor(drawingColors.getForeground()));
			graphicsContext.fillText(Character.toString(character), x, y, width);
			x += width;
		}
	}

	@Override
	public synchronized DrawingColors getDrawingColors() throws IOException, EPaperException {
		return drawingColors;
	}

	@Override
	public synchronized void setDrawingColors(DrawingColors drawingColors) throws IOException, EPaperException {
		Colors.validateDrawingColors(drawingColors);
		this.drawingColors = drawingColors;
	}

	@Override
	public synchronized FontSize getFontSize() throws IOException, EPaperException {
		return fontSize;
	}

	@Override
	public synchronized void setFontSize(FontSize fontSize) throws IOException, EPaperException {
		Lines.validateFontSize(fontSize);
		this.fontSize = fontSize;
		graphicsContext.setFont(Font.font("serif", fontSize.getHeight()));
	}

	@Override
	public synchronized void setDisplayDirection(DisplayDirection displayDirection)
			throws IOException, EPaperException {
		Coordinates.validateDisplayDirection(displayDirection);
		this.displayDirection = displayDirection;
	}

	@Override
	public synchronized DisplayDirection getDisplayDirection() throws IOException, EPaperException {
		return displayDirection;
	}
}
