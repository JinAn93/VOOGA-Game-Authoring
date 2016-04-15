package engine;

import java.util.List;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import graphics.ImageGraphic;
import util.Coordinate;


/**
 * This interface represents a playable game, essentially exposing the necessary functionality to
 * execute a game, but not to edit it.
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface IGamePlayable extends Updateable, IAdder {

    /**
     * @return the object containing information about this game
     */
    IGameInformation getGameInformation ();

    /**
     * @return list of Drawable objects that a game contains
     */
    List<? extends Drawable> getDrawables ();

    /**
     * @return the global attributes for this game
     */
    List<IAttribute> getGlobalAttributes ();

    /**
     * @return the background image of the running level of the game
     */
    ImageGraphic getBackroundImage ();

    void internalizeKeyEvents (List<KeyIOEvent> list);

    void internalizeMouseEvents (List<MouseIOEvent> list);

    void add (ISprite sprite, Coordinate coordinate);

    void add (ISprite sprite);

}
