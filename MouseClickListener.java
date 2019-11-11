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
    public static final int MAX_CLICK_TIME = 5000;
    public static final int TIME_BETWEEN_CLICKS = 1000;
    public static final int NORMAL_APPLE_CLICK_LIMIT = 500;
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
   
    public void mouseReleased(MouseEvent me)
    {
        if( game.getPressedTime() - game.getLastPressedTime() >= TIME_BETWEEN_CLICKS )
        { 
            game.setLastPressedTime(game.getPressedTime());
            long totalTimePressed = System.currentTimeMillis() -  game.getPressedTime();
            if(me.getButton() == MouseEvent.BUTTON3 && totalTimePressed < NORMAL_APPLE_CLICK_LIMIT)
                game.getApples().add( new Apple( game.getWidth(), game.getHeight(), me.getX() ,true));
            else if(me.getButton() == MouseEvent.BUTTON1 && totalTimePressed < NORMAL_APPLE_CLICK_LIMIT )
                game.getApples().add( new Apple( game.getWidth(), game.getHeight(), me.getX() ) );
            else {
                if(totalTimePressed > MAX_CLICK_TIME)
                    totalTimePressed = MAX_CLICK_TIME;
                double speed = ( totalTimePressed / (double)MAX_CLICK_TIME ) *
                ( Apple.MAX_SPEED - Apple.RED_SPEED );
                game.getApples().add( new Apple( game.getWidth(), game.getHeight(),
                me.getX(), (int)speed + Apple.RED_SPEED ) );
            }
            game.setRecoilBar(true);
        }
        game.setStartBarFill( false );
    }
    
    
    public void mousePressed(MouseEvent me)
    {
        game.setPressedTime(System.currentTimeMillis());
        if(game.getPressedTime() - game.getLastPressedTime() >= TIME_BETWEEN_CLICKS )
        {
            game.setRecoilBar(false);
            game.setStartBarFill(true);
        }
    }
    
    public void mouseClicked(MouseEvent me)
    {
    }
}
//***/****//***/****//***/****//***/****//***/****//***/****//***/****//***/****//***/****