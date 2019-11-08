import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
/**
 * Write a description of class KeyboardListener here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class KeyboardListener implements KeyListener
{
    private AppleDrop game;
    
    private boolean[] keys;
    

    public static int RIGHT = 0, LEFT = 1, SPACE = 2, ESCAPE = 3;

    public KeyboardListener(AppleDrop a)
    {
        game = a;
        keys = new boolean[4];
    }
    
    public void keyPressed( KeyEvent ke)
    {
        if( ke.getKeyCode() == KeyEvent.VK_RIGHT )
            keys[ RIGHT ] = true;
        if( ke.getKeyCode() == KeyEvent.VK_LEFT )
            keys[ LEFT ] = true;
        if( ke.getKeyCode() == KeyEvent.VK_SPACE )
            keys[ SPACE ] = true;
        if( ke.getKeyCode() == KeyEvent.VK_ESCAPE )
            keys[ ESCAPE ] = true;
        game.setKeys(keys);
    }

    
    public void keyReleased( KeyEvent ke)
    {
        if( ke.getKeyCode() == KeyEvent.VK_RIGHT )
            keys[ RIGHT  ] = false;
        if( ke.getKeyCode() == KeyEvent.VK_LEFT )
            keys[ LEFT ] = false;
        if( ke.getKeyCode() == KeyEvent.VK_SPACE )
            keys[ SPACE ] = false;
        game.setKeys(keys);
    }
    
    public void keyTyped( KeyEvent ke)
    {
    }
}
