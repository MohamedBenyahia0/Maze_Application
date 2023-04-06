package graphic_interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import model.MazeReadingException;

public class LoadMenuItem extends JMenuItem implements ActionListener {
	private final MyFrame myFrame;
	
	public LoadMenuItem(MyFrame myFrame) {
		super("Load a maze");
		this.myFrame = myFrame;
		addActionListener(this);
	}
	public MyFrame getMyFrame() {
		return myFrame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this) {
			
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("data"));
			int result = fileChooser.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				try {
					myFrame.getMazeModel().setCurrentEditionType(null);
					myFrame.getMazeModel().LoadMaze(fileChooser.getSelectedFile().getAbsolutePath());
					myFrame.getMazeModel().setCurrentFileName(fileChooser.getSelectedFile().getAbsolutePath());
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (MazeReadingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
	}
	public void notifyForUpdate() {
		
	}


}
