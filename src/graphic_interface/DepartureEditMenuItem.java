package graphic_interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class DepartureEditMenuItem extends JMenuItem implements ActionListener {
	private final MyFrame myFrame;
	public DepartureEditMenuItem(MyFrame myFrame) {
		super("Edit a departure");
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
		
			myFrame.getMazeModel().setCurrentEditionType("D");
		}
		
	}
	public void notifyForUpdate() {
		setEnabled(myFrame.getMazeModel().getMazeMatrix()!=null);
		
	}

}
