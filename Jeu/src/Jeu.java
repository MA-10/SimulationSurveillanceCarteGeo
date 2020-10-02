import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StreamCorruptedException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.Box;

public class Jeu extends JFrame{
	String temps ="";
	JLabel statut;
	JPanel panel = new JPanel();
	JLabel time = new JLabel("Time :");
	JLabel sacs_restants = new JLabel("Sacs restants :");
	JLabel intrus_restants = new JLabel("Intrus restants :");
	JLabel nbre_robots = new JLabel("Robots :");
	private Component verticalStrut;
	private final Component horizontalStrut = Box.createHorizontalStrut(20);
	private final Component horizontalStrut_1 = Box.createHorizontalStrut(20);
	public Jeu(ConfigurationModel configuration) {
		super("Jeu");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		 statut = new JLabel("Hello");
		 statut.setForeground(Color.red);
		statut.setVisible(true);
		getContentPane().add(statut,BorderLayout.SOUTH);
		getContentPane().add(new Map(configuration),BorderLayout.CENTER);
		getContentPane().add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{52, 0, 0, 0, 0, 56, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 16, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 0;
		gbc_verticalStrut.gridy = 0;
		panel.add(verticalStrut, gbc_verticalStrut);
		
		//JLabel lblNewLabel = new JLabel("Time :");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		panel.add(time, gbc_lblNewLabel);
		
		
		
		
		//lblNewLabel_1 = new JLabel("Sacs restants :");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		panel.add(sacs_restants, gbc_lblNewLabel_1);
		
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 1;
		gbc_horizontalStrut.gridy = 2;
		panel.add(horizontalStrut, gbc_horizontalStrut);
		
		//lblNewLabel_2 = new JLabel("nbre d'Intrus restants :");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 2;
		panel.add(intrus_restants, gbc_lblNewLabel_2);
		
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_1.gridx = 3;
		gbc_horizontalStrut_1.gridy = 2;
		panel.add(horizontalStrut_1, gbc_horizontalStrut_1);
		
		//lblNewLabel_3 = new JLabel("nbre de robots :");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 4;
		gbc_lblNewLabel_3.gridy = 2;
		panel.add(nbre_robots, gbc_lblNewLabel_3);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	}
	private class Map extends JPanel implements KeyListener,MouseListener,WindowListener {
		int width,height,cellWidth,cellHeight;
		private ConfigurationModel configuration ;
		private Vector<Robot> robots = new Vector<Robot>();
		private Vector<Intru> intrus = new Vector<Intru>();
		private Vector<Sac> sacs = new Vector<Sac>();
		private Vector<Obstacle> obstacles = new Vector<Obstacle>();
		private Vector<Sortie> sorties = new Vector<Sortie>();
		private int joueurActif ;
		private boolean jeuPause;
		private int intrusAttrapé;
		
		public Map(ConfigurationModel configuration) {
			this.configuration = configuration ; 
			joueurActif = 1;
			intrusAttrapé=0;
			jeuPause = true;
			width=getWidth();
			height=getHeight();
			cellWidth =40;
			cellHeight =40;
			initialiser();
			//changerTour();
			calculertemps();
			sacs_restants.setText("Sacs restants: "+ sacs.size());
			intrus_restants.setText("Intrus restants: "+intrus.size());
			nbre_robots.setText("Robots: "+ robots.size());
			this.addKeyListener(this);
			this.addMouseListener(this);
			addWindowListener(this);
	        this.setFocusable(true);
	        this.requestFocusInWindow();
		}
		
		
		
		//Calculer le temps passé
		private void calculertemps() {
			Timer myTimer1 = new Timer();
			TimerTask task1= new TimerTask() {
				int SecondesPassed1 = 0;
				int min=0,heu=0;
				@Override
				public void run() {
					if (intrus.size()!=0) {
						SecondesPassed1 ++;  
					}
					
					String ch ="";
					
					if (SecondesPassed1 ==60) {
						min+=1;
						SecondesPassed1=0;
					}
					if (min==60) {
						heu+=1;
						min=0;
					}
					
					if (SecondesPassed1 / 10 == 0 && min/ 10==0 ) {
						ch="0"+heu+" : "+"0"+min+" : "+"0"+SecondesPassed1;
					}
					if (SecondesPassed1 / 10 != 0 && min/ 10==0) {
						ch="0"+heu+" : "+"0"+min+" : "+SecondesPassed1;
					}
					if (SecondesPassed1 / 10 == 0 && min/ 10!=0) {
						ch="0"+heu+" : "+min+" : "+"0"+SecondesPassed1;
					}
					
					if (SecondesPassed1 / 10 != 0 && min/ 10!=0) {
						ch="0"+heu+" : "+min+" : "+SecondesPassed1; 
					}
					temps=ch;
					time.setText("Time: " + ch );
				}
			};
				myTimer1.scheduleAtFixedRate(task1, 1000, 1000);
		}
		
		
		// Construire les objects obstacle / sac / sortie / robot / intru à partir de la configuration
		private void initialiser()
		
		{
			for (int i = 0; i < configuration.Emplac_Intrus.size(); i++) {
				int x= configuration.Emplac_Intrus.get(i).x;
				int y= configuration.Emplac_Intrus.get(i).y;
				Intru intru = new Intru(y, x, configuration.freq_Intrus.get(i));
				intrus.add(intru);
				MoveIntrus moveIntrus =new MoveIntrus(intru);
				moveIntrus.start();
			}
			for (int i = 0; i < configuration.Emplac_Robots.size(); i++) {
				int x= configuration.Emplac_Robots.get(i).x;
				int y= configuration.Emplac_Robots.get(i).y;
				Robot robot = new Robot(y,x, configuration.freq_Robots.get(i));
				robots.add(robot);
				MoveRobots moveRobots = new MoveRobots(robot);
				moveRobots.start();
			}
			for (int i = 0; i < configuration.Emplac_Obstacle.size(); i++) {
				int x= configuration.Emplac_Obstacle.get(i).x;
				int y= configuration.Emplac_Obstacle.get(i).y;
				Obstacle obstacle = new Obstacle(y, x);
				obstacles.add(obstacle);
			}
			for (int i = 0; i < configuration.Emplac_Sac.size(); i++) {
				int x= configuration.Emplac_Sac.get(i).x;
				int y= configuration.Emplac_Sac.get(i).y;
				Sac sac = new Sac(y, x);
				sacs.add(sac);
			}
			for (int i = 0; i < configuration.Emplac_Sortie.size(); i++) {
				int x= configuration.Emplac_Sortie.get(i).x;
				int y= configuration.Emplac_Sortie.get(i).y;
				Sortie sortie = new Sortie(y, x);
				sorties.add(sortie);
			}
		}
		
		
		// verifier si p1 est adjacent à p2
		private boolean EstAdjacent(Point p1,Point p2)
		{
			if(((p1.y+1 == p2.y || p1.y-1 == p2.y) && (p1.x+1 == p2.x || p1.x-1 == p2.x ||p1.x == p2.x))||
			((p1.x+1 == p2.x || p1.x-1 == p2.x) && (p1.y+1 == p2.y || p1.y-1 == p2.y ||p1.y == p2.y)))
			return true;
			else return false;
		}
		
		
		// si un robot ou un intrus ne trouve pas un objet(un intrus / un robot / un sac / une sortie / un obstacle )  
		//qui empeche son mouvement le mouvement est valide sinon le mouvement est invalide
		private boolean MovementValide(Point p , char direction) {
			Point pa = null;
			switch (direction) {
			case 'D': pa = new Point(p.x+1,p.y);break;
			case 'G': pa = new Point(p.x-1,p.y);break;
			case 'B': pa = new Point(p.x,p.y+1);break;
			case 'H': pa = new Point(p.x,p.y-1);break;
			default:
				break;
			}
			if (pa != null) {
				for (int i = 0; i < obstacles.size(); i++) {
					if (obstacles.get(i).x == pa.x && obstacles.get(i).y== pa.y ) {
						return false;
					}
				}
				for (int i = 0; i < sacs.size(); i++) {
					if (sacs.get(i).x == pa.x && sacs.get(i).y== pa.y ) {
						return false;
					}
				}
				for (int i = 0; i < intrus.size(); i++) {
					if (intrus.get(i).x == pa.x && intrus.get(i).y== pa.y ) {
						return false;
					}
				}
				for (int i = 0; i < robots.size(); i++) {
					if (robots.get(i).x == pa.x && robots.get(i).y== pa.y ) {
						return false;
					}
				}
				for (int i = 0; i < sorties.size(); i++) {
					if (sorties.get(i).x == pa.x && sorties.get(i).y== pa.y ) {
						return false;
					}
				}
			}
			
			return true;
		}

		
		// La classe MoveIntrus qui hérite de Thread controle le mouvement d'un seul intrus
		public class MoveIntrus extends Thread 
		{
			Intru intru ;
			MoveIntrus(Intru intru)
			{
				this.intru=intru;
			}
			
			@Override
			public void run() {
				
				while(true)
					
				{	
					
					if (jeuPause) {
						try {
							sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else 
					{
						// un intru prend un sac
						for (int i = 0; i < sacs.size(); i++) {
							if (EstAdjacent(intru, sacs.get(i))) {
								if (intru.PrendSac(sacs.get(i))) {
									sacs.remove(sacs.get(i));
									repaint();
									sacs_restants.setText("Sacs restants: "+ sacs.size());
									
								}
																}
						} 
						//un robot attrape un intru 
						for (int i = 0; i < robots.size(); i++) {
							
							if (EstAdjacent(intru, robots.get(i)) )
								{
								intrusAttrapé++;
									for (int j = 0; j < intru.sacs.size(); j++) {
										
										sacs.add(intru.sacs.get(j));
										sacs_restants.setText("Sacs restants: "+ sacs.size());
										
									}
									intru.retournerSac();
									intrus.remove(intru);
									repaint();
									intrus_restants.setText("Intrus restants: "+intrus.size());

									try {
										join();
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									
									
								}
						}
						// un intru sort à partir d'une sortie 
						for (int i = 0; i < sorties.size(); i++) {
							
							if (EstAdjacent(intru, sorties.get(i)) )
								{
									intrus.remove(intru);
									repaint();
									intrus_restants.setText("Intrus restants: "+intrus.size());

									try {
										join();
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									


								}
						}
						// le mouvement d'un intru d'une case à une autre avec la frequence qu'on a définit dans la configuration					
							if (MovementValide(intru, intru.direction))
							{
								intru.move();
								repaint();
								try {
									sleep(1000/intru.freq);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								
								
							}
						
					}
				
						
				}
							
				}
							
			}
		
		// La classe MoveRobots qui hérite de Thread controle le mouvement d'un  robot
		class MoveRobots extends Thread 
		{
			Robot robot;
			public MoveRobots(Robot robot) {
				this.robot = robot ;
			}

			@Override
			public void run() {
				
				while(true)
				{
					// si aucun intrus n'est present dans la grille le robot s'arrete
					if (intrus.size() == 0) {
						try {
							join();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					
					if (jeuPause) {
						try {
							sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else {
						
						// le mouvement d'un robot d'une case à une autre avec la frequence qu'on a définit dans la configuration
						if (MovementValide(robot, robot.direction))
						{
							try {
								sleep(1000/robot.freq);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							robot.move();
							repaint();
							
						}
						
					}
					
				}
							
				}
					
			}
		
		// dimensionner la JPanel
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(configuration.nbre_colonne*cellWidth, configuration.nbre_ligne*cellHeight);
			}
		// dessiner tous les objects 
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			// Si aucun intrus est présent dans la grille le jeu est terminé
			if (intrus.size() == 0 )
			{
				JeuTerminé(g);
				
				
			}
			// S'il exite des intrus dans la grille, le jeu se déroule et on dessine la nouvelle etat de la grille apres chaque mouvement 
			else {
				for (int i = 0; i < configuration.nbre_colonne; i++) {
					for (int j = 0; j < configuration.nbre_ligne; j++) {
						g.setColor(Color.WHITE);
						g.fillRect(i*cellWidth, j*cellHeight, cellWidth, cellHeight);
					}
					
				}
				for (int i = 0; i < intrus.size(); i++) {
					if(intrus.get(i).active==true)
					{
						if (i == n && joueurActif == 2 ) {
							
							g.setColor(Color.red);
							g.fillRect(intrus.get(i).x*cellWidth,intrus.get(i).y*cellHeight, cellWidth, cellHeight);
						}
						intrus.get(i).draw(g);
					}
				}
				for (int i = 0; i < robots.size(); i++) {
					if (i == n && joueurActif ==1) {
						
						g.setColor(Color.green);
						g.fillRect(robots.get(i).x*cellWidth,robots.get(i).y*cellHeight, cellWidth, cellHeight);
					}
					robots.get(i).draw(g);
				}
				for (int i = 0; i < obstacles.size(); i++) {
					
					obstacles.get(i).draw(g);
				}
				for (int i = 0; i < sacs.size(); i++) {
					
					sacs.get(i).draw(g);
				}
				for (int i = 0; i < sorties.size(); i++) {
					
					sorties.get(i).draw(g);
				}
			}
		}
		
			// On affiche si le jeu est terminé par la fuite des intrus ou ils sont attrapés par les robots
			private void JeuTerminé(Graphics g) {
				jeuPause = true;
				if (intrusAttrapé == 0) {
					statut.setText("le jeu est terminé! Intrus gagnants");
					ImageIcon imageIcon = new ImageIcon(getClass().getResource("IntrusWin.png"));
					Image image = imageIcon.getImage();
					g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
				}
				else {
					statut.setText("le jeu est terminé! Robots gagnants");
					ImageIcon imageIcon = new ImageIcon(getClass().getResource("RobotWin.png"));
					Image image = imageIcon.getImage();
					g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
				}		
			}
	
	

		int n = -1 ; // l'index de robot (ou intrus) choisi par le joueur 1 (ou 2) dans le vecteur des robots (ou inrus) pour controler sa direction
		
		@Override
		public void mouseClicked(MouseEvent e) { // en cliquant sur la souris ( button gauche ) on fait pauser le jeu puis on choisit un robot (joueur1) ou un intru (joueur2) pour donner sa direction
			jeuPause = true;
			n=-1;
			if  (intrus.size() != 1 || robots.size() != 1){
				int colonne = e.getX()/cellWidth ;
				int ligne = e.getY() / cellWidth ;
				Point point = new Point(ligne,colonne);
				if (joueurActif == 1  ) {
					for (int i = 0; i < robots.size(); i++) {
						if (robots.get(i).x == point.y && robots.get(i).y == point.x) {
							n=i;
						}
					}
					if (n == -1 ) {
						JOptionPane.showMessageDialog(null, "Choisir  un robot ! ");
					}
					
				}
				if (joueurActif == 2  ) {
					for (int i = 0; i < intrus.size(); i++) {
						if (intrus.get(i).x == point.y && intrus.get(i).y == point.x) {
							n=i;
						}
					}
					if (n == -1 ) {
						JOptionPane.showMessageDialog(null, "Choisir un intru ! ");
					}
				}
				
			}else if (robots.size() == 1 || intrus.size() == 1)  {
				n = 0;
			}
			repaint();

			
			
		}
		
		
		@Override
		public void keyTyped(KeyEvent e) {//controler le direction du robot (ou intrus) par les buttons ( Z : haut , S : bas , D : droit, Q : gauche )
			if (jeuPause) {
				if (joueurActif == 1) { // le joueur 1 controle le mouvement du robot choisi 

						Robot robot =  robots.get(n);
						switch (e.getKeyChar()) {
						case 'd': robot.TournerD();break;
						case 'q':robot.TournerG();break;
						case 'z':robot.TournerH();break;
						case 's':robot.TournerB();break;
						default:
							throw new IllegalArgumentException("Unexpected value: " + e.getKeyChar());
						}
						joueurActif = 2 ; // donner le tour suivant pour le joueur 2 
						statut.setText("C'est le tour du joueur 2 ");
						jeuPause = false; // les robots et les intrus continue ses mouvements dans le couloir ou ils se trouvent
						n=-1;
					}
				
					else if (joueurActif == 2) { // le joueur 2 controle le mouvement d'intrus choisi
							Intru intru =  intrus.get(n);
							switch (e.getKeyChar()) {
							case 'd': intru.TournerD();break;
							case 'q':intru.TournerG();break;
							case 'z':intru.TournerH();break;
							case 's':intru.TournerB();break;
							}
							joueurActif = 1 ; // donner le tour suivant pour le joueur 1
							statut.setText("C'est le tour du joueur 1");
							jeuPause=false; // les robots et les intrus continue ses mouvements dans le couloir ou ils se trouvent
							n=-1;						
				}
			}

				
			
		}
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	
		
		//verifier avant de quitter
		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			exit();
			
			}
	
		private void exit() {
			
			int a= JOptionPane.showConfirmDialog(null, "Clicker sur \"oui \" pour quitter vers le menu principal", "Quit options", JOptionPane.YES_NO_OPTION);
			if (a==JOptionPane.YES_OPTION) {
				append("Historique.txt", temps+","+configuration.Nom_joueur1+","+intrusAttrapé
						+","+configuration.Nom_joueur2+','+(configuration.Emplac_Sac.size()-sacs.size()));
				dispose();
				Menu window = new Menu();
				window.main(null);
				
			}			
		}
		
		private void append(String filename, String text) {
			URL url = null;
            
		    try {
		    //localisation du fichier
		    url = this.getClass().getResource(filename);
		    OutputStream file = new FileOutputStream(url.getPath(),true);
		         
		    //préparation d'écriture dans le fichier
		    OutputStreamWriter output = new OutputStreamWriter(file, "UTF-8");
		                         
		    //écrire dans le fichier
		    output.write("\n");
		    output.write(text);
		    output.close();
	    }
		    catch (IOException e) {
				// TODO: handle exception
			}
	}
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
				
	}
}


