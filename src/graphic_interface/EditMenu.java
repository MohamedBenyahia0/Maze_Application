package graphic_interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class EditMenu extends JMenu implements ActionListener {
	private final MyFrame myFrame;
	private final EmptyEditMenuItem emptyEditMenuItem;
	private final WallEditMenuItem wallEditMenuItem;
	private final DepartureEditMenuItem departureEditMenuItem;
	private final ArrivalEditMenuItem arrivalEditMenuItem;
	
	public EditMenu(MyFrame myFrame) {
		super("Edit a maze");
		this.myFrame= myFrame;
		add(emptyEditMenuItem = new EmptyEditMenuItem(myFrame));
		add(wallEditMenuItem = new WallEditMenuItem(myFrame));
		add(departureEditMenuItem = new DepartureEditMenuItem(myFrame));
		add(arrivalEditMenuItem = new ArrivalEditMenuItem(myFrame));
		
		addActionListener(this);
		this.setEnabled(false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public MyFrame getMyFrame() {
		return myFrame;
	}
	public EmptyEditMenuItem getEmptyEditMenuItem() {
		return emptyEditMenuItem;
	}
	public WallEditMenuItem getWallEditMenuItem() {
		return wallEditMenuItem;
	}
	public DepartureEditMenuItem getDepartureEditMenuItem() {
		return departureEditMenuItem;
	}
	public ArrivalEditMenuItem getArrivalEditMenuItem() {
		return arrivalEditMenuItem;
	}
	public void notifyForUpdate() {
		setEnabled(myFrame.getMazeModel().getMazeMatrix()!=null);
		emptyEditMenuItem.notifyForUpdate();
		wallEditMenuItem.notifyForUpdate();
		departureEditMenuItem.notifyForUpdate();
		arrivalEditMenuItem.notifyForUpdate();
		
		
	}


}
