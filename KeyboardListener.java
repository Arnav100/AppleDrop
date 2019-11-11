import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
/**
 * A class to listen to key presses and update an AppleDrop game
 *
 * @author Arnav Parashar and Dana Nigrin
 * @version 11/10/19
 */
public class KeyboardListener implements KeyListener
{
    private AppleDrop game;
    public static int RIGHT = 0, LEFT = 1, SPACE = 2, ESCAPE = 3;
    
    
    /**
     * Constructs a new KeyboardListener for the AppleDrop game to update it whenever a key is pressed
     * 
     * @param a the AppleDrop game to notify
     */
    public KeyboardListener(AppleDrop a)
    {
        game = a;
    }
    
    /**
     * Updates game movements based on key presses
     * 
     * @param ke the KeyEvent that occurred
     */
    @Override
    public void keyPressed( KeyEvent ke)
    {
        if( ke.getKeyCode() == KeyEvent.VK_RIGHT )
            game.getKeys()[ RIGHT ] = true;
        if( ke.getKeyCode() == KeyEvent.VK_LEFT )
            game.getKeys()[ LEFT ] = true;
        if( ke.getKeyCode() == KeyEvent.VK_SPACE )
            game.getKeys()[ SPACE ] = true;
        if( ke.getKeyCode() == KeyEvent.VK_ESCAPE )
            game.getKeys()[ ESCAPE ] = true;
    }

    /**
     * Updates game movements based on key releases
     * 
     * @param ke the KeyEvent that occurred
     */
    @Override
    public void keyReleased( KeyEvent ke)
    {
        if( ke.getKeyCode() == KeyEvent.VK_RIGHT )
            game.getKeys()[ RIGHT  ] = false;
        if( ke.getKeyCode() == KeyEvent.VK_LEFT )
            game.getKeys()[ LEFT ] = false;
        if( ke.getKeyCode() == KeyEvent.VK_SPACE )
            game.getKeys()[ SPACE ] = false;
    }
    
    /**
     * Has no affect on gameplay
     * 
     * @param ke the KeyEvent that occurred
     */
    @Override
    public void keyTyped( KeyEvent ke)
    {
    }
}
