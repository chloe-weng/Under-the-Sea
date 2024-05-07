import java.awt.*;
import java.awt.RenderingHints.Key;

import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import java.net.URL;

import javax.sound.sampled.*;

public class underTheSea
{
    static File file;
    static AudioInputStream stream;
    static Clip music;

    public static void main(String...args) throws Exception
    {
        /*
        file = new File("music.wav");//File must be .WAV, .AU, or .AIFF
        stream = AudioSystem.getAudioInputStream(file);
        music = AudioSystem.getClip();
        music.open(stream);
        music.start(); //Start the music
        music.loop(Clip.LOOP_CONTINUOUSLY); //   Loop the music
         */

        JFrame j = new JFrame();  //JFrame is the window; window is a depricated class
        MyPanelb m = new MyPanelb();
        j.setSize(m.getSize());
        j.add(m); //adds the panel to the frame so that the picture will be drawn
        //use setContentPane() sometimes works better then just add b/c of greater efficiency.

        j.setVisible(true); //allows the frame to be shown.
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //makes the dialog box exit when you click the "x" button.
    }


}


class MyPanelb extends JPanel implements ActionListener, KeyListener, MouseListener
{
    private Timer time;
    private int argoX, argoY;
    private int addlvl1;

    // waves bg variables
    private int wave1X, wave1Y, wave2X, wave2Y, wave3Y, wave4Y;

    //powerup variables
    private int shieldX, shieldY, heartpX, heartpY;
    private boolean shieldVisible, heartVisible, argowithShield;
    private long shieldTime;
    
    // intro variables
    private Image[] nextArray;
    private int nextIndex=0, timeInt=0;
    private int mouseX, mouseY;
    private boolean timeStart=false;

    // monster variables
    private int monster1X, monster1Y, monster1Type;
    private int monster2X, monster2Y;
    private boolean resetMonster1;

    MyPanelb()
    {
        time = new Timer(5, this); //sets delay to 15 millis and calls the actionPerformed of this class.
        setSize(1500, 700);
        setVisible(true); // calls the paintComponent method

        time.start();

        // initialize variables
        addlvl1 = 5;
        argoX = 650;
        argoY = 250;

        // waves bg variables
        wave1X = 0;
        wave1Y = 480;
        wave2X = 0;
        wave2Y = 320;
        wave3Y = 160;
        wave4Y = 0;

        //powerup variables
        shieldX = 0;
        shieldY = 0;
        heartpX = 0; 
        heartpY = 0;
        shieldVisible = false;
        heartVisible = false;
        argowithShield = false;

        // monster variables
        monster1X = monster2X = 1300;
        monster1Y = monster2Y = 0;
        monster1Type = 0;
        resetMonster1 = true;
        

        addMouseListener(this);
        setFocusable(true);
        addKeyListener(this);
    }


    public void paintComponent(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,1500,700);

        drawIntro1(g);
        drawButton(g);
        
        if(timeStart)
        {
            // draw background waves
        	drawWave4(g);
        	drawWave3(g);
        	drawWave2(g);
        	drawWave1(g);

            // draw kraken and siren
            drawMonster1(g);

        	// scarlett
        	if(argowithShield)
        	{
        		drawArgoWithShield(g);
        	}
         	else drawArgo(g);

             if(shieldVisible){drawShield(g);}
    
             // drawHeart (2)
            if(heartVisible){drawHeart(g);}

        }
        // PART 2 AFTER THURSDAY
        // chloe
        // drawLives -> lose screen

        // jessica
        // drawSiren (1)
        // drawKraken (2)

        // scarlett
        // drawShield (1)
       
        
    }

    public void drawIntro1(Graphics g)
    {
    	try
        {
        	Image cover = ImageIO.read(new File("Cover.png"));
        	Image intro = ImageIO.read(new File("Introduction.png"));
        	Image instr1 = ImageIO.read(new File("Instructions Lvl 1.png"));
        	Image lvl1 = ImageIO.read(new File("Level 1.png"));
        	Image button = ImageIO.read(new File("nextSprite.png"));

        	nextArray = new Image[4];
        	nextArray[0] = cover.getScaledInstance(1500,700,Image.SCALE_DEFAULT);
        	nextArray[1] = intro.getScaledInstance(1500,700,Image.SCALE_DEFAULT);
        	nextArray[2] = instr1.getScaledInstance(1500,700,Image.SCALE_DEFAULT);
        	nextArray[3] = lvl1.getScaledInstance(1500,700,Image.SCALE_DEFAULT);
        	
        	g.drawImage(button.getScaledInstance(100,100,Image.SCALE_DEFAULT),1350,500,null);
        	
        	
        	if(nextIndex==0)
        	{
        		g.drawImage(nextArray[0],0,0,null);
        	}
        	else if(nextIndex==1)
        	{
        		g.drawImage(nextArray[1],0,0,null);
        	}
        	else if(nextIndex==2)
        	{
        		g.drawImage(nextArray[2],0,0,null);
        	}
        	else if(nextIndex==3)
        	{
        		g.drawImage(nextArray[3],0,0,null);
        	}
        	else
        	{
        		timeInt = 60;
        		timeStart = true;
        	}
        }
        catch(Exception e) {}
    	
    }
    public void drawButton(Graphics g)
    {
    	try
        {
        	Image button = ImageIO.read(new File("nextSprite.png"));
        	g.drawImage(button.getScaledInstance(100,100,Image.SCALE_DEFAULT),1120,450,null);
        	
        }
        catch(Exception e) {}
    	
    }
    
    public void drawArgo(Graphics g)
    {
        Image argoimg;
        try{
            argoimg = ImageIO.read(new File("argoSprite.png"));
            g.drawImage(argoimg, argoX, argoY, 150,150,null);
        }
        catch(Exception e) {}
    
    }

    public void drawWave1(Graphics g) {
        Image wave1;
        Image wave2;
        try {
            wave1 = ImageIO.read(new File("wavesSprite.png"));
            wave2 = ImageIO.read(new File("wavesSprite.png"));

            g.drawImage(wave1, wave1X, wave1Y,null);
            g.drawImage(wave2, wave1X + 1497, wave1Y, null);
        }
        catch(Exception e) {}
    }

    public void drawWave2(Graphics g) {
        Image wave1;
        Image wave2;
        try {
            wave1 = ImageIO.read(new File("wavesSprite.png"));
            wave2 = ImageIO.read(new File("wavesSprite.png"));

            g.drawImage(wave1, wave2X, wave2Y,null);
            g.drawImage(wave2, wave2X - 1497, wave2Y, null);
        }
        catch(Exception e) {}
    }

    public void drawWave3(Graphics g) {
        Image wave1;
        Image wave2;
        try {
            wave1 = ImageIO.read(new File("wavesSprite.png"));
            wave2 = ImageIO.read(new File("wavesSprite.png"));

            g.drawImage(wave1, wave1X, wave3Y,null);
            g.drawImage(wave2, wave1X + 1497, wave3Y, null);
        }
        catch(Exception e) {}
    }

    public void drawWave4(Graphics g) {
        Image wave1;
        Image wave2;
        try {
            wave1 = ImageIO.read(new File("wavesSprite.png"));
            wave2 = ImageIO.read(new File("wavesSprite.png"));

            g.drawImage(wave1, wave2X, wave4Y,null);
            g.drawImage(wave2, wave2X - 1497, wave4Y, null);
        }
        catch(Exception e) {}
    }

    public void drawShield(Graphics g)
    {
        Image powerup;
        try{
            powerup = ImageIO.read(new File("powerupSprite.png"));
            g.drawImage(powerup, shieldX, shieldY, 50,50,null);
        }
        catch(Exception e) {}
    
    }

    public void drawHeart(Graphics g)
    {
        Image powerup;
        try{
            powerup = ImageIO.read(new File("powerupSprite.png"));
            g.drawImage(powerup, heartpX, heartpY, 50,50,null);
        }
        catch(Exception e) {}
    
    }

    public void drawArgoWithShield(Graphics g)
    {
        Image argowithshield;
        try{
            argowithshield = ImageIO.read(new File("argoWithShieldSprite.png"));
            g.drawImage(argowithshield, argoX, argoY, 200,200,null);
        }
        catch(Exception e) {}
    }

    private boolean touchingPowerup(int pwpX, int pwpY, int argoX, int argoY) {
        return pwpX < argoX + 150 &&
               pwpX + 50 > argoX &&
               pwpY < argoY + 150 &&
               pwpY + 50 > argoY;
    }

    private void drawMonster1(Graphics g) {
        Image monster;
        int[] randY = {wave1Y, wave2Y, wave3Y, wave4Y};

        if(resetMonster1) { // at or reset to the front
            resetMonster1 = false;
            int chooseY = (int) (Math.random() * 4) + 1;
            // below MIGHT change depending on height of the sprite, might need to crop siren and reupload to git
            monster1Y = randY[chooseY - 1];
            monster1X = 1300;

            monster1Type = (int) (Math.random() * 2) + 1;
            if(monster1Type == 1) {
                try {
                    monster = ImageIO.read(new File("krakenSprite.png"));
                    g.drawImage(monster, monster1X, monster1Y + 10, 150, 150, null);
                }
                catch(Exception e) {}
            }
            else {
                try {
                    monster = ImageIO.read(new File("sirenSprite.png"));
                    g.drawImage(monster, monster1X, monster1Y + 20, 150, 150, null);
                }
                catch(Exception e) {}
            }
        }
        else {
            if(monster1Type == 1) {
                try {
                    monster = ImageIO.read(new File("krakenSprite.png"));
                    g.drawImage(monster, monster1X, monster1Y + 10, 150, 150, null);
                }
                catch(Exception e) {}
            }
            else {
                try {
                    monster = ImageIO.read(new File("sirenSprite.png"));
                    g.drawImage(monster, monster1X, monster1Y + 20, 150, 150, null);
                }
                catch(Exception e) {}
            }
        }
    }

    /*
    private void drawMonster2(Graphics g) {
        Image monster;
        int rand = (int) (Math.random() * 2) + 1;

        if(rand == 1) {
            try {
                monster = ImageIO.read(new File("krakenSprite.png"));


                //g.drawImage(monster, )
            }
            catch(Exception e) {}
        }
    }

     */

    public void actionPerformed(ActionEvent e)
    {
        //argo movement

         argoX -= addlvl1;
         argoX -= addlvl1;
         if(argoX< -100||argoX>= 1450)
            {
                //loselife():
                argoX = 650;
                argoY = 250;
            }    
                
        if(argoY< -100|argoY>= 650)
            {
                //loselife():
                argoX = 650;
                argoY = 250;
            }


        //  waves bg movement
        wave1X -= addlvl1;
        if(wave1X < -1496)
            wave1X = 0;

        wave2X += addlvl1;
        if(wave2X > 1500)
            wave2X = 0;

        //powerup movement
        shieldX -= addlvl1;
        heartpX -= addlvl1;
        
        //shield powerup mechanics
        if(!shieldVisible && Math.random() < 0.001) 
        { 
            shieldX = 1450;
            shieldY = (int) (Math.random() * 650);
            shieldVisible = true;
        }

        if(shieldVisible && shieldX < -50)
            {
                shieldVisible = false;
            }

        if(shieldVisible && (touchingPowerup(shieldX, shieldY, argoX, argoY)))
        {
            shieldVisible = false;
            argowithShield = true;
            shieldTime = System.currentTimeMillis();
            //play sound effect?
        }
        if (argowithShield && System.currentTimeMillis() - shieldTime >= 5000) {
            argowithShield = false;
        }

       //heart powerup mechanics
       if(!heartVisible && Math.random() < 0.001) 
       { 
           heartpX = 1450;
           heartpY = (int) (Math.random() * 650);
           heartVisible = true;
       }

       if(heartVisible && heartpX < -50)
           {
               heartVisible = false;
           }

       if(heartVisible && (touchingPowerup(heartpX, heartpY, argoX, argoY)))
       {
           heartVisible = false;
           //if(less than four lives)
                //addlife();
           //play sound effect?
       }

       // monster movement
        monster1X -= addlvl1 + 20;

       // resets monster even if not touching??
       if(!argowithShield && monster1X < argoX + 100 && monster1X + 100 > argoX && monster1Y < argoY + 100 && monster1Y + 100 > argoY) {
           resetMonster1 = true;
           
           // decrease lives
       }

       if(monster1X + 150 < 0)
           resetMonster1 = true;

        repaint();
    }


    public void keyPressed(KeyEvent e) {
        // Moves the argoSprite
        if(e.getKeyCode() == KeyEvent.VK_UP)
            argoY-=35;
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
            argoY+=35;
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
            argoX -=35;
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            argoX +=100;


        repaint();
    }
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
    public void mousePressed(MouseEvent e){
    	
    	 Point pos = e.getPoint();
    	 mouseX = pos.x;
         mouseY = pos.y;
     	
         // nextbutton
         if(mouseX>=1120 && mouseX<=1220 && mouseY>=450 && mouseY<=550)
         {
         	nextIndex++;
         }
         

        repaint();
    }
    public void mouseReleased(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}


}
