package graphic_interface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import graph.Vertex;
import model.EmptyBox;
import model.MazeBox;
import model.MazeModel;

public class NewMenuItem extends JMenuItem implements ActionListener {
	private final MyFrame myFrame;
	
	public NewMenuItem(MyFrame myFrame) {
		super("New maze");
		this.myFrame=myFrame;
		addActionListener(this);
		
	}
	public MyFrame getMyFrame() {
		return myFrame;
	}
	public boolean isInputInteger(String string) throws NumberFormatException {
		try {
			int p = Integer.parseInt(string);
			return true;
		}catch(NumberFormatException ex){
			return false;
			
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==this) {
			MyPanel panel = this.myFrame.getPanel();
			String stringWidthMaze = JOptionPane.showInputDialog("Enter width of the maze");
			while (!this.isInputInteger(stringWidthMaze)) {
				stringWidthMaze = JOptionPane.showInputDialog("You haven't entered a number.Retry.Enter width of the maze");
			}
			String stringHeightMaze = JOptionPane.showInputDialog("Enter height of the maze");
			while (!this.isInputInteger(stringHeightMaze)) {
				stringHeightMaze = JOptionPane.showInputDialog("You haven't entered a number.Retry.Enter height of the maze");	
			}
			myFrame.getMazeModel().setIsNew(true);
			myFrame.getMazeModel().setCurrentEditionType(null);
			int width =Integer.parseInt(stringWidthMaze);
			int height = Integer.parseInt(stringHeightMaze);
			myFrame.getMazeModel().setWidth(width);
			myFrame.getMazeModel().setHeight(height);
			
			myFrame.getMazeModel().setMazeMatrix(new MazeBox[width][height]);
			myFrame.getMazeModel().setAllVertexes(new ArrayList<Vertex>());
			panel.drawNewMaze(panel.getGraphics());
		}
		
	}
	public void notifyForUpdate() {
		
	}
	

}
