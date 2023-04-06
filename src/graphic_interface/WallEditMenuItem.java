package graphic_interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class WallEditMenuItem extends JMenuItem implements ActionListener{
	private final MyFrame myFrame;
	public WallEditMenuItem(MyFrame myFrame) {
		super("Edit a wall");
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
			
			myFrame.getMazeModel().setCurrentEditionType("W");
			
			
		}
		
	}
	public void notifyForUpdate() {
		setEnabled(myFrame.getMazeModel().getMazeMatrix()!=null);
		
	}

}
