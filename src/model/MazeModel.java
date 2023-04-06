package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.*;
import java.util.stream.StreamSupport;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import graph.Dijkstra;
import graph.Graph;
import graph.ShortestPathsImpl;
import graph.Vertex;

public class MazeModel implements Graph {
	private ArrayList<Vertex> AllVertexes;
	private int height;
	private int width;
	private MazeBox[][] mazeMatrix;
	
	private final List<ChangeListener> listeners = new ArrayList<ChangeListener>();
	private String currentEditionType;// "A" or "D" or "W" or "E"
	private String currentFileName;
	private boolean modified = false;
	private boolean isNew = false;
	private boolean edited = false;
	


	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
		modified = true;
		stateChanged();
	}

	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
		modified = true;
		stateChanged();
	}

	public MazeBox[][] getMazeMatrix() {
		return this.mazeMatrix;
	}
	public void setMazeMatrix(MazeBox[][] mazeMatrix) {
		 this.mazeMatrix = mazeMatrix;
		 modified = true;
		 stateChanged();
	}

	public void add(Vertex vertex) {
		AllVertexes.add(vertex);
		modified = true;
		stateChanged();

	}

	public ArrayList<Vertex> getAllVertexes() {
		if (AllVertexes!=null) {
			AllVertexes.clear();	
		}
		for (MazeBox[] line : mazeMatrix) {
			for (MazeBox mazeBox : line) {
				AllVertexes.add(mazeBox);
			}
		}
		return AllVertexes;

	}
	public void setAllVertexes(ArrayList<Vertex> AllVertexes) {
		this.AllVertexes = AllVertexes;
		modified = true;
		stateChanged();
		
	}
	
	

    /**
     * Commentaire javadoc de la fonction isCoordinatesInBounds
     * @param x : coordonnee en largeur d'une certaine MazeBox
     * @param y : coordonnee en hauteur d'une certaine MazeBox
     * @return true si les valeurs des coordonnees de la MazeBox en question sont bien valides et false sinon 
     */
	public boolean isCoordinatesInBounds(int x, int y) {
		if (x < 0 || x > (width - 1) || y < 0 || y > (height - 1)) {
			return false;
		}
		return true;
	}

	public ArrayList<Vertex> getSuccessors(Vertex vertex) {
		ArrayList<Vertex> successors = new ArrayList<Vertex>();
		MazeBox box = (MazeBox) vertex;
		int x = box.getx();
		int y = box.gety();
		if (box.isWallBox() == true) {
			return null;
		}
		if (isCoordinatesInBounds(x + 1, y) && mazeMatrix[x + 1][y].isWallBox() == false) {
			successors.add(mazeMatrix[x + 1][y]);
		}
		if (isCoordinatesInBounds(x - 1, y) && mazeMatrix[x - 1][y].isWallBox() == false) {
			successors.add(mazeMatrix[x - 1][y]);
		}
		if (y % 2 == 0) {
			if (isCoordinatesInBounds(x - 1, y - 1) && mazeMatrix[x - 1][y - 1].isWallBox() == false) {
				successors.add(mazeMatrix[x - 1][y - 1]);
			}
			if (isCoordinatesInBounds(x, y - 1) && mazeMatrix[x][y - 1].isWallBox() == false) {
				successors.add(mazeMatrix[x][y - 1]);
			}

			if (isCoordinatesInBounds(x, y + 1) && mazeMatrix[x][y + 1].isWallBox() == false) {
				successors.add(mazeMatrix[x][y + 1]);
			}
			if (isCoordinatesInBounds(x - 1, y + 1) && mazeMatrix[x - 1][y + 1].isWallBox() == false) {
				successors.add(mazeMatrix[x - 1][y + 1]);
			}

		} else {
			if (isCoordinatesInBounds(x, y - 1) && mazeMatrix[x][y - 1].isWallBox() == false) {
				successors.add(mazeMatrix[x][y - 1]);
			}
			if (isCoordinatesInBounds(x + 1, y - 1) && mazeMatrix[x + 1][y - 1].isWallBox() == false) {
				successors.add(mazeMatrix[x + 1][y - 1]);
			}

			if (isCoordinatesInBounds(x + 1, y + 1) && mazeMatrix[x + 1][y + 1].isWallBox() == false) {
				successors.add(mazeMatrix[x + 1][y + 1]);
			}
			if (isCoordinatesInBounds(x, y + 1) && mazeMatrix[x][y + 1].isWallBox() == false) {
				successors.add(mazeMatrix[x][y + 1]);
			}

		}

		return successors;
	}
	
    /**
     * commentaire javadoc de la fonction getDistance
     * @param src : un certain Vertex
     * @param dst : un autre Vertex
     * @return la distance entre src et dst
     */
	public int getDistance(Vertex src, Vertex dst) {
		if (src.equals(dst)) {
			return 0;
		}
		else if (this.getSuccessors(src)!=null && this.getSuccessors(src).contains(dst)) {
			return 1;
			
		}
		else {
			return Integer.MAX_VALUE;
		}
		
		

	}
    /**
     * commentaire javadoc sur la fonction initFromTextFile
     * @param fileName : chemin du fichier dans lequel est stocke le labyrinthe
     * cette fonction initialise mazeMatrix a partir du fichier
     */ 
	public void initFromTextFile(String fileName) throws MazeReadingException, IOException, FileNotFoundException {

		try {

			Path path = Paths.get(fileName);
			String[] partsFileName = StreamSupport.stream(path.spliterator(), false).map(Path::toString)
					.toArray(String[]::new);
			int n= partsFileName.length;
			

			if (!partsFileName[n-2].equals("data") || partsFileName[n-1].matches("(.*)x(.*)labyrinthe(.*).maze")==false) {
				throw new MazeReadingException(fileName, "c est pas le bon fichier", 0);

			}

			BufferedReader myReader = new BufferedReader(new FileReader(fileName));
			String line;
			int numberOfLine = 0;
			while ((line = myReader.readLine()) != null) {
				numberOfLine += 1;
				if (line.length() != this.width) {
					throw new MazeReadingException(fileName, "nombre de cases pas bon", numberOfLine);
				}
				

				for (int i = 0; i < line.length(); i++) {
					char c = line.charAt(i);
					if (c == 'E') {
						mazeMatrix[i][numberOfLine - 1] = new EmptyBox(i, numberOfLine - 1);

					} else if (c == 'D') {
						mazeMatrix[i][numberOfLine - 1] = new DepartureBox(i, numberOfLine - 1);

					} else if (c == 'A') {
						mazeMatrix[i][numberOfLine - 1] = new ArrivalBox(i, numberOfLine - 1);

					} else if (c == 'W') {
						mazeMatrix[i][numberOfLine - 1] = new WallBox(i, numberOfLine - 1);

					} else {
						throw new MazeReadingException(fileName, "il y a un caractere incorrect", numberOfLine);
					}
				}

			}
			if (numberOfLine != this.height) {
				throw new MazeReadingException(fileName, "nombre de lignes pas bon ", numberOfLine);
			}

			myReader.close();

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
	
    /**
     * commentaire javadoc sur la fonction saveToTextFile
     * @param fileName: chemin du fichier dans lequel on veut stocker le labyrinthe
     * A partir de mazeMatrix cette fonction remplit le fichier 
     */
	public void saveToTextFile(String fileName) {
		try {

			PrintWriter pw = new PrintWriter(fileName);
			for (int y = 0; y < this.height; y++) {
				for (int x = 0; x < this.width; x++) {
					if (mazeMatrix[x][y].isArrivalBox() == true) {
						pw.write('A');
					}
					if (mazeMatrix[x][y].isDepartureBox() == true) {

						pw.write("D");
					}
					if (mazeMatrix[x][y].isEmptyBox() == true) {

						pw.write('E');
					}
					if (mazeMatrix[x][y].isWallBox() == true) {

						pw.write('W');
					}

				}
				pw.write('\n');

			}
			pw.flush();
			pw.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	public String getCurrentEditionType() {
		return currentEditionType;
	}

	public void setCurrentEditionType(String currentEditionType) {
		this.currentEditionType = currentEditionType;
		setModified(true);
		stateChanged();
		if (currentEditionType!=null) {
			edited = true;
			
		}
		
			
		
		
	}

	public List<ChangeListener> getListeners() {
		return listeners;
	}

	public String getCurrentFileName() {
		return currentFileName;
	}

	public void setCurrentFileName(String currentFileName) {
		this.currentFileName = currentFileName;
	}

	public boolean isModified() {
		return modified;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}
	public void addObserver(ChangeListener listener) {
		listeners.add(listener);
	}
	public void stateChanged() {
		ChangeEvent evt = new ChangeEvent(this);
		for (ChangeListener listener: listeners) {
			listener.stateChanged(evt);
		}
	}
	/**
	 * commentaire sur la fonction sizeMaze
	 * @param fileName chemin d'un fichier
	 * @return une liste qui contient la largeur et la hauteur du labyrinthe a partir du nom du fichier
	 */
	public List<Integer> sizeMaze(String fileName) {
		List<Integer> sizeList = new ArrayList<Integer>();
		Path path = Paths.get(fileName);
		String[] partsFileName = StreamSupport.stream(path.spliterator(), false).map(Path::toString)
				.toArray(String[]::new);
		int n = partsFileName.length;

		String sizeMaze = partsFileName[n - 1].split("labyrinthe(.*).maze")[0];
		String widthString = sizeMaze.split("x")[0];
		String heightString = sizeMaze.split("x")[1];
		sizeList.add(Integer.valueOf(widthString));

		sizeList.add(Integer.valueOf(heightString));
		return sizeList;

	}
	/**
	 * commentaire sur la fonction LoadMaze
	 * @param fileName chemin d'un fichier
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws MazeReadingException
	 * permet d'initialiser mazeMatrix et AllVertexes en faisant appel a la fonction initFromTextFile
	 */
	public void LoadMaze(String fileName) throws FileNotFoundException, IOException, MazeReadingException {
		List<Integer> sizeList = sizeMaze(fileName);
		width = sizeList.get(0);
		height = sizeList.get(1);
	
		AllVertexes = new ArrayList<Vertex>();
		mazeMatrix = new MazeBox[this.width][this.height];
		initFromTextFile(fileName);
		AllVertexes = this.getAllVertexes();
		
		modified = true;
		stateChanged();

	}
	/**commentaire de la fonction saveToFile
	 * permet d'enregistrer le labyrinthe dans le meme fichier s'il existe deja ou sinon dans un nouveau fichier
	 */
	public void saveToFile() {
		int p =1;
		File temporaryFile = new File("data"+"\\"+width+"x"+height+"labyrinthe"+p+".maze");
		if (!isNew) {
			saveToTextFile(temporaryFile.getPath());
			
		}
		else {
			boolean exists = temporaryFile.exists();
			while (exists==true) {
				p=p+1;
				temporaryFile = new File("data"+"\\"+width+"x"+height+"labyrinthe"+p+".maze");
				exists= temporaryFile.exists();
			}
			saveToTextFile(temporaryFile.getPath());
			
		}
		modified = false;
		
	}
	/**commentaire de la fonction ResolveMaze
	 * 
	 * @return shortestPath: liste de sommets representant le plus court chemin entre le depart et l'arrivee
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws MazeReadingException
	 * permet de resoudre le labyrinthe
	 */
	public ArrayList<Vertex> ResolveMaze() throws FileNotFoundException, IOException, MazeReadingException {
		Vertex startVertex = null;
		Vertex endVertex = null;
		for (Vertex vertex : getAllVertexes()) {
			if (vertex.isDepartureBox()) {

				if (startVertex != null) {
					JOptionPane.showMessageDialog(null, "plusieurs cases de depart sont presentes","Error",JOptionPane.ERROR_MESSAGE)  ;
					return null;
				}

				startVertex = vertex;
			}
			if (vertex.isArrivalBox()) {

				if (endVertex != null) {
					JOptionPane.showMessageDialog(null, "plusieurs cases d'arrivee sont presentes","Error",JOptionPane.ERROR_MESSAGE);
					return null;
				}

				endVertex = vertex;
			}
		}
		if (startVertex == null || endVertex == null) {
			JOptionPane.showMessageDialog(null,"la case de depart ou la case d'arrivee n'est pas presente","Error",JOptionPane.ERROR_MESSAGE);
			return null;

		}
		ShortestPathsImpl shortestPaths = Dijkstra.dijkstra(this, startVertex, endVertex);
		ArrayList<Vertex> shortestPath = shortestPaths.getShortestPath(endVertex);
		return shortestPath;

	}

	public boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(boolean isNew) {
		this.isNew = isNew;
		modified = true;
		stateChanged();
	}
	public boolean isEdited() {
		return edited;
	}
	public void setEdited(boolean edited) {
		this.edited = edited;
	}

}
