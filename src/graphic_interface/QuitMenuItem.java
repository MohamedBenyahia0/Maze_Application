package graphic_interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;



public class QuitMenuItem extends JMenuItem implements ActionListener {
	private final MyFrame myFrame;
	public QuitMenuItem(MyFrame myFrame) {
		super("Quit");
		this.myFrame = myFrame;
		addActionListener(this);
	}
	public MyFrame getMyFrame() {
		return myFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (myFrame.getMazeModel().isEdited()) {
	         int response = JOptionPane.showInternalOptionDialog(this,
	                                                             "Drawing not saved. Save it ?",
	                                                             "Quit application",
	                                                             JOptionPane.YES_NO_CANCEL_OPTION,
	                                                             JOptionPane.WARNING_MESSAGE,
	                                                             null,null,null) ;
			   switch (response) {
			   case JOptionPane.CANCEL_OPTION:
				   return ;
			   case JOptionPane.OK_OPTION:
				   myFrame.getMazeModel().saveToFile() ;
				   break ;
			   case JOptionPane.NO_OPTION:
				   break ;
			   }
		   }
		   System.exit(0) ;
		
	}

}
