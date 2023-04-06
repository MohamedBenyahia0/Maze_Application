package graph;

public interface Vertex {
	public String getLabel();
	public int getx();// recuperer l'abscisse 
	public int gety();// recuperer l'ordonnee
	public void setx(int x);//changer l'abscisse
	public void sety(int y);//changer l'ordonnee
	public boolean isWallBox();//verifier si le sommet en question est un mur ou non
	public boolean isArrivalBox();//verifier si le sommet en question est une case d'arrivee
	public boolean isDepartureBox();//verifier si le sommet en question est une case de depart
	public boolean isEmptyBox();//verifier si le sommet en question est une case vide
	

}
