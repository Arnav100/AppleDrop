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
    
    
    
    public void actionPerformed(ActionEvent ae)
    {
    }
    
    public void keyReleased( KeyEvent ke)
    {

    }
    
    public void keyPressed( KeyEvent ke)
    {
 
    }
    
    public void keyTyped( KeyEvent ke)
    {
    }
    
    public void mouseExited(MouseEvent me)
    {
    }
    
    public void mouseEntered(MouseEvent me)
    {
    }
    
    public void mouseReleased(MouseEvent me)
    {
    }
    
    public void mousePressed(MouseEvent me)
    {
    }
    public void mouseClicked(MouseEvent me)
    {
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