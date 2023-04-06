package graphic_interface;

import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {
	private final FileMenu fileMenu;
	private final MazeMenu mazeMenu;
	public MenuBar(MyFrame myFrame) {
		super();
		
		add(fileMenu = new FileMenu(myFrame));
		add(mazeMenu = new MazeMenu(myFrame));
		
	}
	public MazeMenu getMazeMenu() {
		return mazeMenu;
	}
	public FileMenu getFileMenu() {
		return fileMenu;
	}
	public void notifyForUpdate() {
		fileMenu.notifyForUpdate();
		mazeMenu.notifyForUpdate();
	}

}
