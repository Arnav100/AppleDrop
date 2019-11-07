import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**
 * Write a description of class MouseClickListener here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MouseClickListener implements MouseListener
{
    private AppleDrop game;
    
    public MouseClickListener(AppleDrop a)
    {
        game = a;
    }
    
    public void mouseExited(MouseEvent me)
    {
    }
    
    public void mouseEntered(MouseEvent me)
    {
    }
    
    
    private  long lastPressedTime;
    
    public void mouseReleased(MouseEvent me)
    {
        if( game.getPressedTime() - lastPressedTime >= 1000 )
        { 
            
            lastPressedTime = game.getPressedTime();
            long totalTimePressed = System.currentTimeMillis() -  game.getPressedTime();
            if(me.getButton() == MouseEvent.BUTTON3 && totalTimePressed < 500)
            {
                game.getApples().add( new Apple( game.getWidth(), game.getHeight(), me.getX() ,true));
            }
               
            else if(me.getButton() == MouseEvent.BUTTON1 && totalTimePressed < 500 )
            {
                game.getApples().add( new Apple( game.getWidth(), game.getHeight(), me.getX() ) );
            }
            
            else
            {
                totalTimePressed = game.MAX_CLICK_TIME;
                double speed = (totalTimePressed/(double)game.MAX_CLICK_TIME)*
                (Apple.MAX_SPEED - 5);
                game.getApples().add( new Apple( game.getWidth(), game.getHeight(),
                me.getX(), (int)speed + 5 ) );
            }
        }
        game.setStartBarFill(false);
    }
    
    
    public void mousePressed(MouseEvent me)
    {
        game.setPressedTime(System.currentTimeMillis());
        game.setStartBarFill(true);
    }
    
    public void mouseClicked(MouseEvent me)
    {
        
    }
}
