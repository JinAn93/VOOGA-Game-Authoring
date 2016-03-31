package gameauthoring;

import engine.ISprite;
import javafx.collections.ObservableList;


/**
 * This interface defines a wrapper for an object that holds lists of Sprites
 * 
 * @author Jeremy Schreck
 *
 */
public interface SpriteListHolder {

    /**
     * Get the sprites stored in this holder
     * 
     * @return An observable list of editable sprites
     */
    ObservableList<ISprite> getSprites ();
    
    /**
     * Add a sprite to the list
     * 
     * @param sprite The sprite to add
     */
    void addSprite(ISprite sprite);
}
