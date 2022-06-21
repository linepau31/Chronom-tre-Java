import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;


public class Stopwatch implements ActionListener {
	
	JFrame frame = new JFrame();
	JButton startbutton = new JButton("START");
	JButton resetbutton = new JButton("RESET");
	JLabel timeLabel = new JLabel();
	int elapsedTime = 0; // le nmbre de milisecondes après le démarage de la minuterie
	int seconds = 0; // le nmbre de secondes qui se sont écoulées
	int minutes = 0;
	int hours = 0;
	boolean started = false; // pour déterminer si la minuterie a demarré ou pas
	String seconds_string = String.format("%02d", seconds); // %02d -> afficher 2 chiffres en secondes
	String minutes_string = String.format("%02d", minutes);
	String hours_string = String.format("%02d", hours);
	
	Timer timer = new Timer(1000, new ActionListener() { // 1000 milisecondes -> 1 seconde avec un écouteur d'évenement
		  
	public void actionPerformed(ActionEvent e) {
		   
	elapsedTime=elapsedTime+1000;
	hours = (elapsedTime/3600000); // 3600000 milisecondes en 1h
	minutes = (elapsedTime/60000) % 60; //60000 milisecondes en 1min + % 60 pour qu'a 60 min -> remise à 01 et non 61 min ...
	seconds = (elapsedTime/1000) % 60;
	seconds_string = String.format("%02d", seconds);
	minutes_string = String.format("%02d", minutes);
	hours_string = String.format("%02d", hours);
	timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
		   
	}
		  
	});
	
	
	Stopwatch() {
		
		timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
		timeLabel.setBounds(100,100,200,100); // délimite l'écriture: x,y,pixel de long, pixel pour la hauteur
		timeLabel.setFont(new Font("Verdana",Font.PLAIN,35));
		timeLabel.setBorder(BorderFactory.createBevelBorder(1)); // bordure autour du temps qui défile
		timeLabel.setOpaque(true);
		timeLabel.setHorizontalAlignment(JTextField.CENTER);// alignement horizontal du texte
		
		startbutton.setBounds(100,200,100,50);// bordure des btns
		startbutton.setFont(new Font("Ink Free",Font.PLAIN,20));
		startbutton.setFocusable(false); //remplace la focalisation par défaut du composant
		startbutton.addActionListener(this);
		
		resetbutton.setBounds(200,200,100,50);
		resetbutton.setFont(new Font("Ink Free",Font.PLAIN,20));
		resetbutton.setFocusable(false); 
		resetbutton.addActionListener(this);
		
		frame.add(startbutton);
		frame.add(resetbutton);
		frame.add(timeLabel);
		// création du cadre/fenetre
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // fermer la fenetre
		frame.setSize(420, 420); // dimension
		frame.setTitle("Chronomètre"); // titre
		frame.setLayout(null);
		frame.setVisible(true);
		
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 if(e.getSource()==startbutton) {
			   
			 if(started==false) {
			    started=true;
			    startbutton.setText("STOP"); // au clic le btn start devient stop
			    start();
			 }
			 else {
			    started=false;
			    startbutton.setText("START");
			    stop();
			 }
			   
			 }
			 if(e.getSource()==resetbutton) {
			   started=false;
			   startbutton.setText("START");
			   reset();
			}
		
	}
	
	void start() {
		timer.start();
	}
	
	void stop() {
		timer.stop();
	}
	
	void reset() {
		
		timer.stop();
		elapsedTime=0;
		seconds =0;
		minutes=0;
		hours=0;
		seconds_string = String.format("%02d", seconds);
		minutes_string = String.format("%02d", minutes);
		hours_string = String.format("%02d", hours);       
		timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
		
	}

	
}
