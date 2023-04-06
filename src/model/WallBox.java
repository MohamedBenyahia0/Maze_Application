package model;

public class WallBox extends MazeBox {
	public WallBox(int x, int y) {
		super(x,y);
	}
	@Override
	public boolean isWallBox() {
		return true;
	}

}
