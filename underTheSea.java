import java.awt.*;
import java.awt.RenderingHints.Key;

import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
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


    MyPanelb()
    {
        time = new Timer(5, this); //sets delay to 15 millis and calls the actionPerformed of this class.
        setSize(1500, 700);
        setVisible(true); // calls the paintComponent method

        time.start();

        // initialize variables
        addlvl1 = 8;
        argoX = 0;
        argoY = 0;

        // waves bg variables
        wave1X = 0;
        wave1Y = 480;
        wave2X = 0;
        wave2Y = 320;
        wave3Y = 160;
        wave4Y = 0;

        addMouseListener(this);
        setFocusable(true);
        addKeyListener(this);
    }


    public void paintComponent(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,1500,700);

        // chloe
        // cover - next
        // intro - next
        // instructions - next
        // cover lvl 1


        // drawLvl1 -> use boolean to keep track when beginning sequence is over

        // jessica
        drawWave4(g);
        drawWave3(g);
        drawWave2(g);
        drawWave1(g);

        // scarlett
        drawArgo(g);

        // PART 2 AFTER THURSDAY
        // chloe
        // drawLives -> lose screen

        // jessica
        // drawSiren (1)
        // drawKraken (2)

        // scarlett
        // drawShield (1)
        // drawHeart (2)
    }

    public void drawArgo(Graphics g)
    {
        Image argoimg;
        try{
            argoimg = ImageIO.read(new File("argoSprite.png"));
            g.drawImage(argoimg, argoX+750, argoY, 150,150,null);
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


    public void actionPerformed(ActionEvent e)
    {
        //argo movement
        argoX -= addlvl1;
        //if(argoX<-750||argoX>=1300)
            //loselife():


        //  waves bg movement
        wave1X -= addlvl1;
        if(wave1X < -1496)
            wave1X = 0;

        wave2X += addlvl1;
        if(wave2X > 2996)
            wave2X = 0;

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
            argoX +=90;


        repaint();
    }
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
    public void mousePressed(MouseEvent e){

        repaint();
    }
    public void mouseReleased(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}


}
