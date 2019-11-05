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
    
    public enum Keys {
        RIGHT(0), LEFT(1), SPACE(2), ESCAPE(3);
        private final int index;
        Keys(int index)
        {
            this.index = index;
        }
        
        public int get()
        {
            return index;
        }
    }

    public KeyboardListener(AppleDrop a)
    {
        game = a;
        keys = new boolean[4];
    }
    
    public void keyPressed( KeyEvent ke)
    {
        if( ke.getKeyCode() == KeyEvent.VK_RIGHT )
            keys[ Keys.RIGHT.get() ] = true;
        if( ke.getKeyCode() == KeyEvent.VK_LEFT )
            keys[ Keys.LEFT.get() ] = true;
        if( ke.getKeyCode() == KeyEvent.VK_SPACE )
            keys[ Keys.SPACE.get() ] = true;
        game.setKeys(keys);
    }

    
    public void keyReleased( KeyEvent ke)
    {
        if( ke.getKeyCode() == KeyEvent.VK_RIGHT )
            keys[ Keys.RIGHT.get()  ] = false;
        if( ke.getKeyCode() == KeyEvent.VK_LEFT )
            keys[ Keys.LEFT.get() ] = false;
        if( ke.getKeyCode() == KeyEvent.VK_SPACE )
            keys[ Keys.SPACE.get() ] = false;
        game.setKeys(keys);
    }
    
    public void keyTyped( KeyEvent ke)
    {
    }
}
