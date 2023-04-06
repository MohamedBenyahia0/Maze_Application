package graphic_interface;

import javax.swing.JMenu;

public class MazeMenu extends JMenu {
	private final EditMenu editMenu;
	private final ResolveMenuItem resolveMenuItem;
	private final StopEditMenuItem stopEditMenuItem;
	
	public MazeMenu(MyFrame myFrame) {
		super("Maze");
		
		add(editMenu = new EditMenu(myFrame));
		add(resolveMenuItem = new ResolveMenuItem(myFrame));
		add(stopEditMenuItem = new StopEditMenuItem(myFrame));
	}
	public EditMenu getEditMenu() {
		return editMenu;
	}
	public ResolveMenuItem getResolveMenuItem() {
		return resolveMenuItem;
	}
	public StopEditMenuItem getStopEditMenuItem() {
		return stopEditMenuItem;
	}
	
	public void notifyForUpdate() {
		editMenu.notifyForUpdate();
		resolveMenuItem.notifyForUpdate();
		stopEditMenuItem.notifyForUpdate();
	}

}
