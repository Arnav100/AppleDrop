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
    

    public static int RIGHT = 0, LEFT = 1, SPACE = 2, ESCAPE = 3;

    public KeyboardListener(AppleDrop a)
    {
        game = a;
    }
    
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

    
    public void keyReleased( KeyEvent ke)
    {
        if( ke.getKeyCode() == KeyEvent.VK_RIGHT )
            game.getKeys()[ RIGHT  ] = false;
        if( ke.getKeyCode() == KeyEvent.VK_LEFT )
            game.getKeys()[ LEFT ] = false;
        if( ke.getKeyCode() == KeyEvent.VK_SPACE )
            game.getKeys()[ SPACE ] = false;
    }
    
    public void keyTyped( KeyEvent ke)
    {
    }
}
//***/****//***/****//***/****//***/****//***/****//***/****//***/****//***/****//***/****