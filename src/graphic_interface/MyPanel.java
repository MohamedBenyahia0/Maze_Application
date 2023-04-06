package graphic_interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import graph.Dijkstra;
import graph.ShortestPathsImpl;
import graph.Vertex;
import model.ArrivalBox;
import model.DepartureBox;
import model.EmptyBox;
import model.MazeBox;
import model.MazeModel;
import model.MazeReadingException;
import model.WallBox;

import javax.swing.JOptionPane;

public class MyPanel extends JPanel implements MouseListener {
	
	private final MyFrame myFrame;
	
	private  int centerx0;
	private  int centery0;
	private  int sizeHexagon;
	private final int widthPanel = 600;
	private final int heightPanel = 600;
	private static final double sqrt3 = Math.sqrt(3);
	

	public MyPanel(MyFrame myFrame) {
		
		this.myFrame = myFrame;
		
		this.setPreferredSize(new Dimension(widthPanel, heightPanel));
		this.setBackground(Color.WHITE);
		if (myFrame.getMyMenuBar().getMazeMenu().getStopEditMenuItem().isClicked() == false) {
			addMouseListener(this);
		}


	}
	/**
	 * commentaire javadoc sur la fonction getCenterx0
	 * @return centerx0 :la coordonnee x0 du centre du premier hexagone en haut a gauche
	 */
	public int getCenterx0() {
		return centerx0;
	}
	
	public void setCenterx0(int centerx0) {
		this.centerx0 = centerx0;
	}
	/**
	 * commentaire javadoc sur la fonction getCentery0
	 * @return centery0 :la coordonnee y0 du centre du premier hexagone en haut a gauche 
	 */
	public int getCentery0() {
		return centery0;
	}
	public void setCentery0(int centery0) {
		this.centery0 = centery0;
	}

	/**
	 * commentaire javadoc sur la fonction getSizeHexagon
	 * @return sizeHexagon : le rayon d'un hexagone
	 */
	public int getSizeHexagon() {
		return sizeHexagon;
	}
	public void setSizeHexagon(int sizeHexagon) {
		this.sizeHexagon = sizeHexagon;
	}
	public int getWidthPanel() {
		return widthPanel;
	}
	public int getHeightPanel() {
		return heightPanel;
	}
	

	public MyFrame getmyFrame() {
		return myFrame;
	}
	

    /** commentaire javadoc sur la fonction initializeHexagoneSizes
     * initialiser la taille standard d'un hexagone et les coordonnees du centre du premier hexagone
     */
	private void initializeHexagonSizes() {
		sizeHexagon = Math.min(heightPanel / (myFrame.getMazeModel().getHeight() * 3), widthPanel / (myFrame.getMazeModel().getWidth() * 3));
		centerx0 = 5 * sizeHexagon;
		centery0 = 5 * sizeHexagon;
	}




    /** commentaire javadoc sur la fonction paint
     * @param g 
     * dessiner le labyrinthe sur le panel 
     */
	public void paint(Graphics g) {

		super.paint(g);
		if (myFrame.getMazeModel().getMazeMatrix() != null) {
			this.initializeHexagonSizes();

			Graphics2D g2D = (Graphics2D) g;
			MazeBox[][] mazeMatrix = myFrame.getMazeModel().getMazeMatrix();
			double centerx = 0;
			double centery = 0;
			for (int y = 0; y < myFrame.getMazeModel().getHeight(); y++) {
				for (int x = 0; x < myFrame.getMazeModel().getWidth(); x++) {

					centerx = getCenterx(y, x);
					centery = getCentery(y);

					if (mazeMatrix[x][y].isEmptyBox() == true) {
						g2D.setColor(Color.GRAY);
					}

					Polygon polygon = getPolygon(centerx, centery, sizeHexagon);
					g2D.drawPolygon(polygon);

					if (mazeMatrix[x][y].isArrivalBox() == true) {
						g2D.setColor(Color.RED);
						g2D.fillPolygon(polygon);

					}
					if (mazeMatrix[x][y].isDepartureBox() == true) {
						g2D.setColor(Color.GREEN);
						g2D.fillPolygon(polygon);
					}
					if (mazeMatrix[x][y].isWallBox() == true) {
						g2D.setColor(Color.BLUE);
						g2D.fillPolygon(polygon);

					}

				}

			}

		}

	}
	/**
	 * commentaire javadoc sur la fonction getCentery
	 * @param y : coordonnee en hauteur d'une certaine MazeBox
	 * @return centery : coordonee y de l'hexagone correspondant dans le panel
	 */

	public double getCentery(int y) {
		double centery;

		centery = centery0 + (1.5) * sizeHexagon * y;
		return centery;
	}
    /**
     * commentaire javadoc sur la fonction getYfromCentery 
     * @param centery : coordonee y d'un certain hexagone dans le panel 
     * @return y : coordonnee en hauteur de la MazeBox correspondante
     */
	public int getYfromCentery(double centery) {
		int y;
		y = (int) Math.rint((centery - centery0) / ((1.5) * sizeHexagon));
		return y;
	}
    /**
     * commentaire javadoc sur la fonction getCenterx
     * @param y: coordonnee en hauteur d'une certaine MazeBox
     * @param x: coordonnee en largeur de la MazeBox
     * @return centerx : coordonnee x de l'hexagone correspondant dans le panel 
     */
	public double getCenterx(int y, int x) {
		double centerx;

		if (y % 2 == 0) {
			centerx = centerx0 + sqrt3 * sizeHexagon * x;
		} else {
			centerx = centerx0 + sqrt3 * sizeHexagon * (x + 0.5);
		}
		return centerx;
	}
    /**
     * commentaire javadoc sur la fonction getXfromCenterx
     * @param centery : coordonnee y d'un certain hexagone dans le panel
     * @param centerx :
     * @return
     */
	public int getXfromCenterx(double centery, double centerx) {
		int x;
		int y = this.getYfromCentery(centery);
		if (y % 2 == 0) {
			x = (int) Math.rint((centerx - centerx0) / (sqrt3 * sizeHexagon));
		} else {
			x = (int) Math.rint((centerx - centerx0) / (sqrt3 * sizeHexagon) - 0.5);
		}
		return x;
	}

	public Polygon getPolygon(double centerx, double centery, int size) {

		int xValue[] = { doubleToInt(centerx), doubleToInt(centerx + sqrt3 * size / 2),
				doubleToInt(centerx + sqrt3 * size / 2), doubleToInt(centerx), doubleToInt(centerx - sqrt3 * size / 2),
				doubleToInt(centerx - sqrt3 * size / 2) };
		int yValue[] = { doubleToInt(centery - size), doubleToInt(centery - size / 2), doubleToInt(centery + size / 2),
				doubleToInt(centery + size), doubleToInt(centery + size / 2), doubleToInt(centery - size / 2) };
		Polygon polygon = new Polygon(xValue, yValue, 6);
		return polygon;
	}

	public int doubleToInt(double d) {
		return Double.valueOf(d).intValue();
	}



	public void drawResolvedMaze(Graphics g) throws FileNotFoundException, IOException, MazeReadingException {

		ArrayList<Vertex> shortestPath = myFrame.getMazeModel().ResolveMaze();
		double centerx = 0;
		double centery = 0;
		Graphics2D g2D = (Graphics2D) g;
		if (shortestPath != null) {
			for (Vertex vertex : shortestPath) {
				int x = vertex.getx();
				int y = vertex.gety();
				centery = this.getCentery(y);
				centerx = this.getCenterx(y, x);
				Polygon polygon = getPolygon(centerx, centery, sizeHexagon);
				
				g2D.drawPolygon(polygon);
				g2D.setColor(Color.YELLOW);
				g2D.fillPolygon(polygon);

			}
		}
	}

	public void drawNewMaze(Graphics g) {
		//super.paint(g);
		this.initializeHexagonSizes();
		Graphics g2D = (Graphics2D) g;
		double centerx = 0;
		double centery = 0;

		for (int y = 0; y < myFrame.getMazeModel().getHeight(); y++) {
			for (int x = 0; x < myFrame.getMazeModel().getWidth(); x++) {
				myFrame.getMazeModel().getMazeMatrix()[x][y] = new EmptyBox(x, y);
				centerx = this.getCenterx(y, x);
				centery = this.getCentery(y);

				Polygon polygon = this.getPolygon(centerx, centery, sizeHexagon);
				g2D.setColor(Color.GRAY);
				g2D.drawPolygon(polygon);

			}

		}
	}

	public void drawHexagoneEdited(Graphics g, int x, int y, String editionType) {

		Graphics g2D = (Graphics2D) g;
		double centerx = 0;
		double centery = 0;
		centerx = this.getCenterx(y, x);
		centery = this.getCentery(y);
		Polygon polygon = this.getPolygon(centerx, centery, sizeHexagon);
		if (x >= 0 && x <= myFrame.getMazeModel().getWidth() - 1 && y >= 0 && y <= myFrame.getMazeModel().getHeight() - 1) {
			if (editionType == "E") {
				myFrame.getMazeModel().getMazeMatrix()[x][y] = new EmptyBox(x, y);

				g2D.drawPolygon(polygon);
				g2D.setColor(Color.WHITE);
				g2D.fillPolygon(polygon);

			}
			if (editionType == "W") {
				myFrame.getMazeModel().getMazeMatrix()[x][y] = new WallBox(x, y);
				g2D.setColor(Color.BLUE);
				g2D.fillPolygon(polygon);
			}
			if (editionType == "D") {
				myFrame.getMazeModel().getMazeMatrix()[x][y] = new DepartureBox(x, y);
				g2D.setColor(Color.GREEN);
				g2D.fillPolygon(polygon);
			}
			if (editionType == "A") {
				myFrame.getMazeModel().getMazeMatrix()[x][y] = new ArrivalBox(x, y);
				g2D.setColor(Color.RED);
				g2D.fillPolygon(polygon);
			}

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (myFrame.getMyMenuBar().getMazeMenu().getStopEditMenuItem().isClicked() == false) {
			this.initializeHexagonSizes();
			int y = this.getYfromCentery(e.getY());
			int x = this.getXfromCenterx(e.getY(), e.getX());

			this.drawHexagoneEdited(this.getGraphics(), x, y, myFrame.getMazeModel().getCurrentEditionType());

		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	public void notifyForUpdate() {
		repaint();
	}

}
