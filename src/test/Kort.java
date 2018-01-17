package test;

import java.awt.*;
import java.applet.Applet;



class Kort {

	

  static final int padX = 6, padY = 4;

   // the coordinates for the upper left corner of the card
   public int X = 0;
   public int Y = 0;

 
   // the width and height of the tile
   int width = 60;
   int height = 17;
   int heightSist = 90;
   
   Font f = new Font("Helvetica", Font.BOLD, 12);
   
     // the word for this card
   int nummer;
   int typ;
   boolean synlig;
   boolean markerad;
   private Image tecken;
 
	
	

/////////////////////////////////////////////////////////////////////////////////////////////

   // kort konstrukt?r
 Kort (int n, int t )  {

      // nummer typ och synlig
      nummer = n;
      typ = t;
      markerad=false;
      
     
	
      

   } // slut p? kort konstrukt?r
   
/////////////////////////////////////////////////////////////////////////////////////////////


   // is the current mouse position within the card's bounds?
   public boolean musInom (int x_coord, int y_coord, boolean sist)  {

     boolean within = false;
	
	if (sist) {
		if (x_coord >= X  && (x_coord <=X+width) && y_coord >= Y && (y_coord <= Y+heightSist))
	    within = true;
	    }
	else {
		if (x_coord >= X  && (x_coord <=X+width) && y_coord >= Y && (y_coord <= Y+height))
	    within = true;
	   
	    } 
	  
     // true if current x and y position is within the bounds of the card
     return (within);

   } // end of musInom



//////////////////////////////////////////////////////////////////////////////////////////////

   public void ritaKort (Graphics g,boolean vald,Image kortTecken[]) {
   		if (nummer == 14) {
   			g.setColor (Color.blue);
			g.drawRoundRect ((X-5), (Y+12), (width+10), (heightSist+10),10,10);	
   		}
   		else {
	   		if (synlig){
				if (vald){
					g.setColor (new Color(135,135,137));
					g.fillRoundRect (X, Y, width, heightSist,8,8);
					g.setColor (Color.red);
					g.drawRoundRect (X, Y, width, heightSist,8,8);
					g.drawImage(kortTecken[typ+8],(X+21),(Y+5),null);
					g.drawImage(kortTecken[typ+12],(X+21),(Y+65),null);
				}
				else {
					g.setColor (Color.white);
					g.fillRoundRect (X, Y, width, heightSist,8,8);
					g.setColor (Color.black);
					g.drawRoundRect (X, Y, width, heightSist,8,8);
					g.drawImage(kortTecken[typ],(X+21),(Y+5),null);
					g.drawImage(kortTecken[typ+4],(X+21),(Y+65),null);
				}
				
				
				g. setFont(f);
				if (typ>1)
					g.setColor(Color.black);
				else
					g.setColor(Color.red);
				if (nummer ==11 || nummer ==10){
					g.drawString (bytTill(nummer), (X+4), (Y +17));
					g.drawString (bytTill(nummer), (X+43), (Y + 83));
				}
				else{
					g.drawString (bytTill(nummer), (X+7), (Y +17));
					g.drawString (bytTill(nummer), (X+46), (Y + 83));
				}
			}
			else {
				g.setColor (Color.blue);
				g.fillRoundRect (X, Y, width, heightSist,8,8);
				g.setColor(Color.black);
				g.drawRoundRect (X, Y, width, heightSist,8,8);
	
				}
			
			} 
		}
		
	public void ritaStege (Graphics g,Image kortTecken[]) {
   		
		g.setColor (Color.blue);
		g.drawRoundRect ((X-5), (Y-5), (width+10), (heightSist+10),10,10);	
		if (nummer==0)
			g.drawImage(kortTecken[typ+16],(X+21),(Y+35),null);
			
			
		if (nummer>0){
			g.setColor (Color.white);
			g.fillRoundRect (X, Y, width, heightSist,8,8);
			g.setColor (Color.black);
			g.drawRoundRect (X, Y, width, heightSist,8,8);
			g.drawImage(kortTecken[typ],(X+21),(Y+5),null);
			g.drawImage(kortTecken[typ+4],(X+21),(Y+65),null);g. setFont(f);
			if (typ>1)
				g.setColor(Color.black);
			else
				g.setColor(Color.red);
			if (nummer ==11 && nummer ==10){
				g.drawString (bytTill(nummer), (X+4), (Y +17));
				g.drawString (bytTill(nummer), (X+43), (Y + 83));
			}
			else{
				g.drawString (bytTill(nummer), (X+7), (Y +17));
				g.drawString (bytTill(nummer), (X+46), (Y + 83));
			}
		}
	}
		
		
		
			
		String bytTill(int val){
			
			switch (val) {
				case 1: return "E";
				case 2: return "2";
				case 3: return "3";
				case 4: return "4";
				case 5: return "5";
				case 6: return "6";
				case 7: return "7";
				case 8: return "8";
				case 9: return "9";
				case 10: return "10";
				case 11: return "Kn";
				case 12: return "Q";
				case 13: return "K";
				default: return "";
				}
			}
		}
		

