package fr.romainmoreau.epaper.client.api.table;

import java.io.IOException;

import fr.romainmoreau.epaper.client.api.EPaperException;

public interface CellContent {
	void draw(int x0, int y0, int x1, int y1, CellContentDrawer cellContentDrawer) throws IOException, EPaperException;
}
