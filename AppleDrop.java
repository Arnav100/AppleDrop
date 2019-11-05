import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Write a description of class AppleDrop here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AppleDrop extends JPanel implements ActionListener, KeyListener, MouseListener
{
    private static final int MAX_WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().
    getWidth();
    private static final int MAX_HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().
    getHeight();
    private static final Rectangle SCREEN_DIMENSIONS = new Rectangle( 0, 0, MAX_WIDTH,
    MAX_HEIGHT );
    
    private static final int QUARTER = 4;
    private static final int THREE = 3;
    private static final int TRAP_POINTS = 4;
    
    private int panelWidth;
    private int panelHeight;
    
    private int time; 
    private boolean[] keys;
    private static final int NUM_KEYS = 4;
    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int SPACE = 2;
    private static final int ESCAPE = 3;
    
    private long pressedTime;
    private long lastPressedTime;
    
    private ArrayList< Apple > apples;
    private Basket[] baskets;
    
    private boolean first;
    
    public AppleDrop()
    {
        apples = new ArrayList< Apple >();
        
        first = true;
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        for( Apple apple : apples )
             apple.move();
        repaint();
    }
    
    public void keyPressed( KeyEvent ke)
    {
        if( ke.getKeyCode() == KeyEvent.VK_RIGHT )
            keys[ RIGHT ] = true;
        if( ke.getKeyCode() == KeyEvent.VK_LEFT )
            keys[ LEFT ] = true;
        if( ke.getKeyCode() == KeyEvent.VK_SPACE )
            keys[ SPACE ] = true;
        update();
    }
    
    public void keyReleased( KeyEvent ke)
    {
        if( ke.getKeyCode() == KeyEvent.VK_RIGHT )
            keys[ RIGHT ] = false;
        if( ke.getKeyCode() == KeyEvent.VK_LEFT )
            keys[ LEFT ] = false;
        if( ke.getKeyCode() == KeyEvent.VK_SPACE )
            keys[ SPACE ] = false;
    }
    
    public void keyTyped( KeyEvent ke)
    {
    }
    
    int x;
    private void update()
    {
        if( keys[ RIGHT ] )
            for( Basket basket : baskets )
                basket.moveRight();
        if( keys[ LEFT ] )
            for( Basket basket : baskets )
                basket.moveLeft();
        if( keys[ SPACE ] )
        {
            for( Apple apple : apples )
            {
                x = apple.getX();    
                if( apple.getY() > panelHeight - baskets[0].getHeight() - apple.getSize() 
                    /*make so that apple has to be in x-range too*/ )
                    apple.swat();
                }
        }
        repaint();
    }
    
    public void mouseExited(MouseEvent me)
    {
    }
    
    public void mouseEntered(MouseEvent me)
    {
    }
    
    public void mouseReleased(MouseEvent me)
    {
        if( pressedTime - lastPressedTime >= 4000 )
        {
            lastPressedTime = pressedTime;
            if(System.currentTimeMillis() - pressedTime < 500 )
            {
                apples.add( new Apple( this.panelWidth, this.panelHeight, me.getX() ) );
                return;
            }
            long totalTimePressed = System.currentTimeMillis() - pressedTime;
            if(totalTimePressed > 5000)
                totalTimePressed = 5000;
            double speed = (totalTimePressed/(double)5000)*25;
            apples.add( new Apple( this.panelWidth, this.panelHeight, me.getX(),
            (int)speed ) );
        }
    }
    
    public void mousePressed(MouseEvent me)
    {
        pressedTime = System.currentTimeMillis();
    }
    
    public void mouseClicked(MouseEvent me)
    {
        
    }
    
    int size;
    public void paintComponent( Graphics g )
    {
        if( first )
            initialize();
        background( g );
        for( Apple apple : apples )
        {
            g.setColor( apple.getColor() );
            size = apple.getSize();
            g.fillOval( apple.getX(), apple.getY(), size, size );
        }
        for( Basket basket : baskets )
        {
            g.setColor( basket.getColor() );
            g.fillPolygon( basket.getXCoords(), basket.getYCoords(), TRAP_POINTS ); 
        }
    }
    private void background( Graphics g )
    {
        super.paintComponent( g );
    }
    private void initialize()
    {
        panelWidth = this.getWidth();
        panelHeight = this.getHeight();
        
        time = 0;
        Timer clock = new Timer( 20 /*what should this be*/, this ); 
        clock.start();
            
        keys = new boolean[ NUM_KEYS ];
        baskets = new Basket[]{ new Basket( panelWidth, panelHeight, panelWidth / 2 ) };
        
        first = false;
    }
    
    public static void main( String[] args )
    {
        JFrame frame = new JFrame( "Apple Drop" );
        frame.setBounds( SCREEN_DIMENSIONS );
        
        AppleDrop ad = new AppleDrop();
        frame.addKeyListener( ad );
        frame.addMouseListener( ad );
        frame.add( ad );
        
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setResizable( false );
        frame.setAlwaysOnTop( true );
        
        frame.setVisible( true );
    }
    
    /*Player1 = mouse 
     * red apples - slow, left-click + points for p1
     * green apples - faster (variable speed, length held) left-click + more pts for p1
     * brown rotten apples - medium speed, right-click - remove pts for p2
     * 
     * Player2 = keyboard
     * 
     * left/right + <space> swatting away bad apples
     * 
     * 
     * Ideas: 
     * 
     * All speeds should be written as relative to the screen size
     * 
     * 1 point for red and then a point based on a ratio of points to speed
     * 
     * Basket should wrap around to the other side of the screen if it hits one end
     * 
     * 1 minute rounds, number can be adjusted later
     * 
     * Mac:
     * 
     * setExtendedState java
    */
}
