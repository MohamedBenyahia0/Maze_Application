package graphic_interface;

import javax.swing.JMenu;

public class FileMenu extends JMenu {
	private final NewMenuItem newMenuItem;
	private final LoadMenuItem loadMenuItem;
	private final SaveMenuItem saveMenuItem;
	private final CloseMenuItem closeMenuItem;
	private final QuitMenuItem quitMenuItem;
	
	public FileMenu(MyFrame myFrame) {
		super("File"); 
		
		add(loadMenuItem = new LoadMenuItem(myFrame));
		add(newMenuItem = new NewMenuItem(myFrame));
		add(saveMenuItem = new SaveMenuItem(myFrame));
		add(closeMenuItem = new CloseMenuItem(myFrame));
		add(quitMenuItem = new QuitMenuItem(myFrame));
		
		
		
	}
	public NewMenuItem getNewMenuItem() {
		return newMenuItem;
	}
	public LoadMenuItem getLoadMenuItem() {
		return loadMenuItem;
	}
	public SaveMenuItem getSaveMenuItem() {
		return saveMenuItem;
	}
	public CloseMenuItem getCloseMenuItem() {
		return closeMenuItem;
	}
	public QuitMenuItem getQuitMenuItem() {
		return quitMenuItem;
	}
	public void notifyForUpdate() {
		loadMenuItem.notifyForUpdate();
		newMenuItem.notifyForUpdate();
		saveMenuItem.notifyForUpdate();
		closeMenuItem.notifyForUpdate();
	}

}
