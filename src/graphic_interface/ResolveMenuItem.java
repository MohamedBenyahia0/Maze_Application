package graphic_interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JMenuItem;

import model.MazeReadingException;

public class ResolveMenuItem extends JMenuItem implements ActionListener {
	private final MyFrame myFrame;
	

	public ResolveMenuItem(MyFrame myFrame) {
		super("Resolve maze");
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
		if (e.getSource() == this) {
			MyPanel panel = myFrame.getPanel();
			

			try {
				panel.drawResolvedMaze(panel.getGraphics());
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
	public void notifyForUpdate() {
		setEnabled(myFrame.getMazeModel() != null);
	}
 
}
