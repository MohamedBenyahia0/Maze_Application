package model;

public class DepartureBox extends MazeBox {
	public DepartureBox(int x, int y) {
		super(x,y);
	}
	@Override
	public boolean isDepartureBox() {
		return true;
	}


}
