package model;

public class ArrivalBox extends MazeBox {
	public ArrivalBox(int x, int y) {
		super(x,y);
	}
	@Override
	public boolean isArrivalBox() {
		return true;
	}


}
