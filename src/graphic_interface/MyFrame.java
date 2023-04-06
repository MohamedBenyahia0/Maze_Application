package graphic_interface;

import model.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;


import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class MyFrame extends JFrame implements ChangeListener {
	
	private MazeModel mazeModel = new MazeModel();
	private MyPanel panel;
	
	private final MenuBar menuBar;

	public MyFrame() throws FileNotFoundException, IOException, MazeReadingException {
		super("Maze Application");
		mazeModel.addObserver(this);
		setJMenuBar(menuBar = new MenuBar(this));

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		topPanel.setBackground(Color.BLACK);
		topPanel.setPreferredSize(new Dimension(625, 625));

		panel = new MyPanel(this);



		topPanel.add(panel);

		this.add(topPanel);
		this.setSize(650, 650);

		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}






	public MyPanel getPanel() {
		return panel;
	}
	public void setPanel(MyPanel otherPanel) {
		this.panel = otherPanel;
	}
	public MenuBar getMyMenuBar(){
		return menuBar;
	}


	public MazeModel getMazeModel() {
		return mazeModel;
	}
	public void setMazeModel(MazeModel mazeModel) {
		this.mazeModel = mazeModel;
	}
	@Override
	public void stateChanged(ChangeEvent evt) {
		// TODO Auto-generated method stub
		panel.notifyForUpdate();
		menuBar.notifyForUpdate();
	
		
		
	}

}
