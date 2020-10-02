
import java.awt.Point;
import java.util.*;
public class ConfigurationModel {
	public int  nbre_ligne;
	public int nbre_colonne;
	public Vector <Point> Emplac_Sac ;
	public Vector <Point> Emplac_Obstacle ;
	public Vector <Point> Emplac_Sortie ;
	public Vector <Point> Emplac_Robots ;
	public Vector <Point> Emplac_Intrus ;
	public Vector<Integer> freq_Robots;
	public Vector<Integer> freq_Intrus;
	String Nom_joueur1;
	String Nom_joueur2;
	
	ConfigurationModel ()
	{
		nbre_ligne =15;
		nbre_colonne=30;
		Emplac_Sac= new Vector <Point>();
		Emplac_Obstacle= new Vector <Point>();
		Emplac_Sortie= new Vector <Point>();
		Emplac_Robots= new Vector <Point>();
		Emplac_Intrus= new Vector <Point>();
		freq_Robots=new Vector<Integer>();
		freq_Intrus =new Vector<Integer>();
		Nom_joueur1="Joueur 1";
		Nom_joueur2="Joueur 2";
		
	}
	
	
	
	
	

}

