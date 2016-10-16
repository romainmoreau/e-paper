package fr.romainmoreau.epaper.client.common.table;

public class DrawableBorder {
	private final int x0;

	private final int y0;

	private final int x1;

	private final int y1;

	public DrawableBorder(int x0, int y0, int x1, int y1) {
		this.x0 = x0;
		this.y0 = y0;
		this.x1 = x1;
		this.y1 = y1;
	}

	public int getX0() {
		return x0;
	}

	public int getY0() {
		return y0;
	}

	public int getX1() {
		return x1;
	}

	public int getY1() {
		return y1;
	}
}
