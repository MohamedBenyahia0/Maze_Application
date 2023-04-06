package graph;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeListener;

import model.MazeBox;
import model.MazeReadingException;
public interface Graph {
	public int getHeight();
	public void setHeight(int height);
	public int getWidth();
	public void setWidth(int width);
	public MazeBox[][] getMazeMatrix();
	public void setMazeMatrix(MazeBox[][] mazeMatrix);
	public void add(Vertex vertex);
	public ArrayList<Vertex> getAllVertexes();
	public void setAllVertexes(ArrayList<Vertex> AllVertexes);
	public boolean isCoordinatesInBounds(int x, int y);
	public ArrayList<Vertex> getSuccessors(Vertex vertex);
	public int getDistance(Vertex src,Vertex dst);
	public void initFromTextFile(String fileName) throws MazeReadingException, IOException, FileNotFoundException ;
	public void saveToTextFile(String fileName);
	public String getCurrentEditionType();
	public void setCurrentEditionType(String currentEditionType);
	public List<ChangeListener> getListeners() ;
	public String getCurrentFileName();
	public void setCurrentFileName(String currentFileName);
	public boolean isModified();
	public void setModified(boolean modified);
	public void addObserver(ChangeListener listener);
	public void stateChanged();
	public List<Integer> sizeMaze(String fileName);
	public void LoadMaze(String fileName) throws FileNotFoundException, IOException, MazeReadingException;
	public void saveToFile();
	public ArrayList<Vertex> ResolveMaze() throws FileNotFoundException, IOException, MazeReadingException;
	public boolean getIsNew();
	public void setIsNew(boolean isNew);
	public boolean isEdited();
	public void setEdited(boolean edited);

}
