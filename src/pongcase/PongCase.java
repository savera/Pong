package pongcase;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class PongCase extends JComponent implements KeyListener
{
    JFrame frame;
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    Ellipse2D.Double ball;
    Rectangle2D.Double paddle;
    int ballXspeed = 5; 
    int ballYspeed = 5; 
    int ballXpos = 600;
    int ballYpos = 500;
    int paddleYpos = 500;
    int score = 0;
    URL soundAddress = getClass().getResource("boing_1.1.aiff");
    AudioClip soundFile = Applet.newAudioClip(soundAddress);
    URL buzzAddress = getClass().getResource("buzzer_x.aiff");
    AudioClip buzzSoundFile = Applet.newAudioClip(buzzAddress);
    

    public static void main(String[] args)
    {
        new PongCase().getGoing();
    }

    private void getGoing()
    {
        frame = new JFrame("Pong Case");
        frame.setVisible(true);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.addKeyListener(this);
    }
    
    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        ball = new Ellipse2D.Double(ballXpos, ballYpos, 50, 50);
        paddle = new Rectangle2D.Double(0, paddleYpos, 20, 200);
        ballXpos = ballXpos + ballXspeed;
        ballYpos = ballYpos + ballYspeed;
        g2.setColor(Color.yellow);
        g2.fill(ball);
        g2.setColor(Color.BLUE);
        g2.fill(paddle);
        if (ballYpos > height - 200)
        {
           ballYspeed = -ballYspeed; 
        }
        if (ballXpos > width - 75)
        {
           ballXspeed = -ballXspeed; 
        }
        if (ballYpos <  0)
        {
           ballYspeed = -ballYspeed;
        }
        if (ballXpos < 0 )
        {
            buzzSoundFile.play();
        }
        if (ball.intersects(paddle))
        {
            if (ballXspeed < 0)
            {
               ballXspeed = -ballXspeed;
               score = score + 1;
               soundFile.play();
            }
        }
        g2.setFont(new Font("Bank Gothic", Font.BOLD, 99));
        g2.drawString(score + "", width - 200, 150);
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent ke)
    {
    }

    @Override
    public void keyPressed(KeyEvent ke)
    {
        if (ke.getKeyCode() == 38)// up arrow
        {
           paddleYpos = paddleYpos - 20; 
        }
        if (ke.getKeyCode() == 40)// down arrow
        {
           paddleYpos = paddleYpos + 20; 
        }
    }

    @Override
    public void keyReleased(KeyEvent ke)
    {
    }
}
