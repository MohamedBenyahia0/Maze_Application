package graphic_interface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.MazeModel;


public class CloseMenuItem extends JMenuItem implements ActionListener {
	private final MyFrame myFrame;

	public CloseMenuItem(MyFrame myFrame) {
		super("Close Maze");
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
			
			if (myFrame.getMazeModel().isEdited()) {
				int response = JOptionPane.showInternalOptionDialog(this, "Drawing not saved. Save it ?",
						"Quit application", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, null,
						null);
				switch (response) {
				case JOptionPane.CANCEL_OPTION:
					return;
				case JOptionPane.OK_OPTION:
					myFrame.getMazeModel().saveToFile();
					break;
				case JOptionPane.NO_OPTION:
					break;
				}
			}
			myFrame.getMazeModel().setMazeMatrix(null);
			myFrame.getMazeModel().setAllVertexes(null);
			myFrame.getMazeModel().setCurrentEditionType(null);
			myFrame.getMazeModel().setModified(false);
			myFrame.getMazeModel().setEdited(false);

		}
	}

	public void notifyForUpdate() {
		setEnabled(myFrame.getMazeModel().getMazeMatrix() != null);
	}

}
