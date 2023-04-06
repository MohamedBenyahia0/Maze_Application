package graphic_interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.StreamSupport;

import javax.swing.JMenuItem;



public class SaveMenuItem extends JMenuItem implements ActionListener {
	private final MyFrame myFrame;
	public SaveMenuItem(MyFrame myFrame) {
		super("Save a maze");
		this.myFrame = myFrame;
		addActionListener(this);
		this.setEnabled(false);
		
		
	}
	public MyFrame getMyFrame() {
		return myFrame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==this) {
		
			myFrame.getMazeModel().saveToFile();
			myFrame.getMazeModel().setEdited(false);
		
		}
		
	}
	public void notifyForUpdate() {
		setEnabled(myFrame.getMazeModel().getMazeMatrix()!= null);
	}
	

}
