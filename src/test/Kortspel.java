package test;

import javax.swing.*;


import java.awt.*;
import java.net.*;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.event.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Kortspel extends JPanel implements MouseListener, ActionListener {

   // board width and height
   final int boardWidth = 700;
   final int boardHeight =750;
   final int bredd=90;
   final int hojd=130;
	private MediaTracker mTracker; 
   	


   // for double buffering to prevent flicker
   Image offScreenImage;
   Graphics offScreen;
   Image tecken[]=new Image[20];
   JButton nyOmgong;

// nummer av kort i varje fﬁrg
   int antalNummer;
   int antaletUtdeladeKort=0;
   int antaletKortIHog;
   int kortnummer=0;
   int farg;
   int kortKvar;
   int hognummer;
   int kortPlats;
   int puNummer;
   String poeng;
   boolean finnsmarkeratKort=false;
   boolean avbryt = false;
   boolean knappuppe= true;
   Cursor hand = new Cursor(Cursor.HAND_CURSOR);
   Cursor pil = new Cursor(Cursor.DEFAULT_CURSOR);
   Font p = new Font("Helvetica", Font.BOLD, 12);
 
   // define the array of kort
   Kort[] flerakort;
   Kort[][] hog=new Kort[7][51];
   
   Kort[] stege= new Kort[4];
   Kort markeratKort =null;
    
	public Kortspel() {
 		super();
 		setLayout(null);
		nyOmgong = new JButton("Ny OmgÂng");
		nyOmgong.setBounds(20, 20, 100, 40);
      	nyOmgong.addActionListener(this);
      	nyOmgong.setFocusPainted(false);
      	add(nyOmgong);
      
		addMouseListener(this);
		tecken[0]=getImage( "images/hjarter.GIF");
		tecken[1]=getImage( "images/ruter.GIF");
		tecken[2]=getImage( "images/spader.GIF");
		tecken[3]=getImage( "images/klover.GIF");
		tecken[4]=getImage( "images/hjarter2.GIF");
		tecken[5]=getImage( "images/ruter2.GIF");
		tecken[6]=getImage( "images/spader2.GIF");
		tecken[7]=getImage( "images/klover2.GIF");
		tecken[8]=getImage( "images/hjarter_m.GIF");
		tecken[9]=getImage( "images/ruter_m.GIF");
		tecken[10]=getImage( "images/spader_m.GIF");
		tecken[11]=getImage( "images/klover_m.GIF");
		tecken[12]=getImage( "images/hjarter_m2.GIF");
		tecken[13]=getImage( "images/ruter_m2.GIF");
		tecken[14]=getImage( "images/spader_m2.GIF");
		tecken[15]=getImage( "images/klover_m2.GIF");
		tecken[16]=getImage( "images/hjarter_g.GIF");
		tecken[17]=getImage( "images/ruter_g.GIF");
		tecken[18]=getImage( "images/spader_g.GIF");
		tecken[19]=getImage( "images/klover_g.GIF");
		
		
		
		setBackground(new Color (0,167,167));
		taframKort(); 
//		blandaDelaUt();
		repaint();

	
	}

		
//////////////////////////////////////////////////////////////////////////

  	public void taframKort() {
  	
	   // lista av nummer
		int nummerLista[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,1,2,3,4,5,6,7,8,9,10,11,12,13,1
		,2,3,4,5,6,7,8,9,10,11,12,13,1,2,3,4,5,6,7,8,9,10,11,12,13 };
		antalNummer=nummerLista.length;
		flerakort = new Kort[antalNummer];
		
		
		 // gﬂr iordning en spelkortshﬂg
		 kortnummer=0;
		 farg=0;
		 for (int i=0; i < antalNummer; i++)  {
			flerakort[kortnummer] = new Kort (nummerLista[i],farg);
			flerakort[kortnummer].synlig=false;
			if (farg>=3)
				farg=0;
			else
				farg++;
			kortnummer++;
		}
	}

	public void blandaDelaUt() {
  		// Blanda
	  	hog=new Kort[7][51];
	  	stege= new Kort[4];
	  	markeratKort =null;
		for (int longtime=0;longtime<400;longtime++) {
			int anum=(int)Math.floor(Math.random()*52);
			int bnum=(int)Math.floor(Math.random()*52);
			Kort temp= flerakort[anum];
			flerakort[anum]=flerakort[bnum];
			flerakort[bnum]=temp;
		}
		
		
		 
		// dela ut korten i hogarna
		for (int t=0; t<=3;t++) {
			stege[t] = new Kort(0,t);
			
		}
		
		for (int x=0; x<=6;x++) {
			hog[x][0] = new Kort(14,5);
		}
		antaletUtdeladeKort=0;
		for (int y=1;y<=8;y++) {
			for (int x=0; x<=6;x++){
				if  (antaletUtdeladeKort<52) {
					hog[x][y] = flerakort[antaletUtdeladeKort];
					antaletUtdeladeKort++;
					if (x<y)
						hog[x][y].synlig=true;
					else
						hog[x][y].synlig=false;
					
				}
			
			}
		}
		
		puNummer=0;
	}
//////////////////////////////
	
///////////////////////////////////////////////
	
	public void actionPerformed(ActionEvent event) 
	{
		if (event.getSource() == nyOmgong) {
			this.blandaDelaUt();
			puNummer=0;
			repaint();
		}
		
	}
	
	public void mouseClicked(MouseEvent e){
		
		
		
		for (int x =0; x<=6; x++)  {
		 	int siffra =0;
		 	while (hog[x][siffra] != null){	
	  			if  (hog[x][siffra+1] == null){
	  				
	  				if (hog[x][siffra].musInom(e.getX(),e.getY(),true)){
	      				if (hog[x][siffra].synlig){
	      					if (hog[x][siffra].nummer==(stege[hog[x][siffra].typ].nummer+1)){
	      						stege[hog[x][siffra].typ]=hog[x][siffra];
	      						hog[x][siffra]=null;
	      						puNummer=puNummer+5;
	      						repaint();
	      					}
	      				}
	      				else {
	      					hog[x][siffra].synlig=true;
	      					repaint();
	      				}
	      			}
	      		}
	      		siffra++;
	  		
	  		}
		  	
		}  	
		
		avbryt = false;
	}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	
	public void mouseReleased (MouseEvent e){
		 if  (finnsmarkeratKort){
			 for (int x =0; x<=6; x++)  {
			 	int siffra =0;
			 	while (hog[x][siffra] != null){	
			 		
			 		if  (markeratKort != null && (hog[x][siffra+1] == null) &&  ((hog[x][siffra] .typ==markeratKort.typ) ||
			 				 (hog[x][siffra] .typ==5))&& (hog[x][siffra] .nummer==(markeratKort.nummer+1)) && 
			 				 (hognummer!=x) && (hog[x][siffra].musInom(e.getX(),e.getY(),(hog[x][siffra+1] == null)))) {
		      				
		      				int temp=1;
		      				puNummer++;
		      				while (hog[hognummer][kortPlats] != null){
		      					hog[x][siffra+temp]=hog[hognummer][kortPlats];
		      					hog[hognummer][kortPlats]=null;
		      					temp++;
		      					kortPlats++;
		      				}
	      					finnsmarkeratKort=false;
	      					markeratKort.markerad=false;
	      					markeratKort=null;
	      					setCursor(pil);
	      					repaint();	
	      					
		      				
					}
					siffra++;
				}
					
			}
		}
		
	      
	finnsmarkeratKort=false;
	if (markeratKort != null)
		markeratKort.markerad=false;
	markeratKort=null;
	setCursor(pil);
	repaint();	
	avbryt=false;		
	}
	
	public void mousePressed (MouseEvent e){
		 if (e.getX() >= 20  && (e.getX()  <=100) && e.getY() >= 20 && ( e.getY() <= 60)){
		 	knappuppe = false;
		 	repaint();
		 }
		else {
		
			 for (int x =0; x<=6; x++)  {
			 	boolean sistaKortet = false;
		      		int siffra =0;
		      		while (hog[x][siffra] != null){	
		      			if (hog[x][siffra].musInom(e.getX(),e.getY(),(hog[x][siffra+1] == null))&&hog[x][siffra].synlig){
			      			hog[x][siffra].markerad=true;
			      			finnsmarkeratKort=true;
			      			markeratKort=hog[x][siffra];
			      			hognummer=x;
			      			kortPlats=siffra;
			      			setCursor(hand);
			      			repaint();
			      			
			      		}
			      		siffra++;
		      		
	      			}
	      		}
      		}
     		
      	}
		

	public Image getImage(String filename)
	{
        try {
	       	URL url = ClassLoader.getSystemClassLoader().getResource(filename);
            Image image =  Toolkit.getDefaultToolkit().getImage(url);
            mTracker = new MediaTracker(this);
	 		mTracker.addImage(image, 1);
		 	mTracker.waitForID(1);
		 	return image;	
	                   
        }
        catch(Exception e) {
            System.out.println("det gick inte att ladda bilden.");
            return null;
        }
    }	

/////////////////////////////////////////////////////////////////////////////

   public void paint (Graphics offScreen) {
    
    	super.paint(offScreen);	
      // set up the playing rectangle
      offScreen.setFont(p);
      
      offScreen.setColor (Color.white);
      offScreen.fillRect (170, 30,40, 20);
      offScreen.setColor (Color.black);	
      offScreen.drawString ("Po‰ng",130,45);
      offScreen.drawString ( Integer.toString(puNummer),175,45);
      offScreen.drawString ("Programmering: Ulrik Nilsson",25,90);   
      offScreen.drawString ("ulrik.n@transware.se",25,115);   
     
 
      
      // draw the tiles (from the back of the list to the front)
     
    
      for (int x =0; x<=6; x++)  {
      		int siffra =0;
      		boolean markerad=false;
      		while (hog[x][siffra] != null){	
      			if (hog[x][siffra].markerad)
      				markerad=true; 
	      		hog[x][siffra].X=(20+x*bredd);
	      		hog[x][siffra].Y=(hojd+siffra*17);
	      		hog[x][siffra].ritaKort(offScreen,markerad,tecken);
	      		
	      		siffra++;
	      		
      		}
      		
      	}
	 for (int t =0; t<=3; t++)  {
	 	stege[t].X=(20+(t+3)*bredd);	
	 	stege[t].Y=25;	
	 	stege[t].ritaStege(offScreen,tecken);
	 }

       
  	
    
   } // end of paint
  
///////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////

   public void update(Graphics g) {

      // Paint the applet
      paint(g);

   }

////////////////////////////////////////////////////////////////////////////
}

