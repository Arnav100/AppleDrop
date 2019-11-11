import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**
 * Write a description of class MouseClickListener here.
 *
 * @author Arnav Parashar and Dana Nigrin
 * @version 11/10/19
 */
public class MouseClickListener implements MouseListener
{
    private AppleDrop game;
    public static final int MAX_CLICK_TIME = 5000;
    public static final int TIME_BETWEEN_CLICKS = 1000;
    public static final int NORMAL_APPLE_CLICK_LIMIT = 500;
    
    /**
     * Constructs a new MouseClickListener for the AppleDrop game to update it whenever 
     * the mouse is pressed
     * 
     * @param a the AppleDrop game to notify
     */
    public MouseClickListener(AppleDrop a)
    {
        game = a;
    }
    
    /**
     * Has no affect on gameplay
     * 
     * @param ke the MouseEvent that occurred
     */
    @Override
    public void mouseExited(MouseEvent me)
    {
    }
    
    /**
     * Has no affect on gameplay
     * 
     * @param ke the MouseEvent that occurred
     */
    @Override
    public void mouseEntered(MouseEvent me)
    {
    }
   
    /**
     * Creates a new apple based on the click that was released, previous click, and duration 
     * of the click
     * 
     * @param ke the MouseEvent that occurred
     */
    @Override
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
        game.setBarFill( false );
    }
    
    
    /**
     * Records the time the mouse was pressed
     * 
     * @param ke the MouseEvent that occurred
     */
    @Override
    public void mousePressed(MouseEvent me)
    {
        game.setPressedTime(System.currentTimeMillis());
        if(game.getPressedTime() - game.getLastPressedTime() >= TIME_BETWEEN_CLICKS )
        {
            game.setRecoilBar(false);
            game.setBarFill(true);
        }
    }
    
    /**
     * Has no affect on gameplay
     * 
     * @param ke the MouseEvent that occurred
     */
    @Override
    public void mouseClicked(MouseEvent me)
    {
        
    }
}
