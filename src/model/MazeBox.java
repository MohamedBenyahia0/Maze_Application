package model;
import graph.Vertex;

public abstract class MazeBox implements Vertex {
	private int x;
	private int y;
	public MazeBox(int x, int y) {
		this.x = x;
		this.y=y;
	}
	
	public int getx() {
		return x;
	}
	public void setx(int x) {
		this.x =x;
	}
	public int gety() {
		return y;
	}
	public void sety(int y) {
		this.y=y;
	}
	public String getLabel() {
		return "getx():gety()";
	}
	public boolean isWallBox() {
		return false;
	}
	public boolean isArrivalBox() {
		return false;
	}
	public boolean isEmptyBox() {
		return false;
	}
	public boolean isDepartureBox() {
		return false;
	}

}
