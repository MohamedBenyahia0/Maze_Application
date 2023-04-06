package graphic_interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class StopEditMenuItem extends JMenuItem implements ActionListener {
	private final MyFrame myFrame;
	private boolean clicked ;
	public StopEditMenuItem(MyFrame myFrame) {
		super("Stop editing");
		this.myFrame = myFrame;
		addActionListener(this);
		this.setEnabled(false);
		setClicked(false);
		
	}
	public MyFrame getMyFrame() {
		return myFrame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==this) {
			setClicked(true);
			myFrame.getPanel().removeMouseListener(null);
			myFrame.getMazeModel().setCurrentEditionType(null);
	
			
		}
	}
	public void notifyForUpdate() {
		setClicked(myFrame.getMazeModel().getCurrentEditionType()==null);
		setEnabled(myFrame.getMazeModel().getCurrentEditionType()!=null);
	}
	public boolean isClicked() {
		return clicked;
	}
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

}
