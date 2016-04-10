package engine;

import java.util.List;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.modules.GraphicModule;
import engine.sprite.ISprite;
import graphics.ImageGraphic;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import util.Coordinate;
import util.TimeDuration;


/**
 * This class represents the notion of a level in an IGame and holds the interfaces required to
 * properly entail a level.
 * This includes a condition, attribute, and sprite manager to hold the rules, objects and
 * interactions for the level.
 *
 */
public class Level implements ILevel {

    private ObjectProperty<IConditionManager> myConditionManager;
    private ObjectProperty<ImageGraphic> myBackgroundImage;
    private ObjectProperty<ISpriteManager> mySpriteManager;
    private ObjectProperty<IAttributeManager> myAttributeManager;
    private ObjectProperty<INextLevelManager> myNextLevelManager;

    public Level () {
        // TODO need to actually instantiate internal manager objects
        // after creating the concrete classes
        myAttributeManager = new SimpleObjectProperty<>(new AttributeManager());
        myConditionManager = new SimpleObjectProperty<>(new ConditionManager());
        mySpriteManager = new SimpleObjectProperty<>(new SpriteManager());
        myNextLevelManager = new SimpleObjectProperty<>(new NextLevelManager());
        //TODO add default
       // myBackgroundImage = new SimpleObjectProperty<>(new ImageGraphic(400, 400, "/image/blank.jpg"));
    }

    @Override
    public void update (TimeDuration duration) {
        mySpriteManager.get().update(duration);
        myConditionManager.get().update(duration);
        myAttributeManager.get().update(duration);
        myNextLevelManager.get().update(duration);
    }

    @Override
    public ObservableList<ObjectProperty<ICondition>> getConditionsPropertyList () {
        return myConditionManager.get().getConditionListProperty();
    }

    @Override
    public void addGlobalResource (IResource resource) {
        myAttributeManager.get().addResource(resource);
    }

    @Override
    public ObservableList<ISprite> getSprites () {
        return mySpriteManager.get().getSprites();
    }

    @Override
    public void add (ISprite sprite, Coordinate coordinate) {
        mySpriteManager.get().add(sprite, coordinate);
    }

    @Override
    public ILevel getNextLevel () {
        return myNextLevelManager.get().getNextLevel();
    }

    @Override
    public boolean shouldSwitchLevel () {
        return myNextLevelManager.get().shouldGoToNextLevel();
    }

    @Override
    public ObservableList<? extends Drawable> getDrawables () {
        return mySpriteManager.get().getDrawables();
    }

    @Override
    public void internalizeKeyEvents (List<KeyIOEvent> list) {
        mySpriteManager.get().internalizeKeyEvents(list);
        myConditionManager.get().internalizeKeyEvents(list);

    }

    @Override
    public void internalizeMouseEvents (List<MouseIOEvent> list) {
        mySpriteManager.get().internalizeMouseEvents(list);
        myConditionManager.get().internalizeMouseEvents(list);

    }

    /**
     * Removes a sprite from the level whenever a sprite meets a particular death condition
     */
    @Override
    public void remove (ObjectProperty<ISprite> sprite) {
        mySpriteManager.get().remove(sprite);

    }

    @Override
    public ObjectProperty<ImageGraphic> getBackgroundImageProperty () {
       return myBackgroundImage;
    }
    
    

    @Override
    public ObjectProperty<IAttributeManager> getAttributeManager () {
        return myAttributeManager;
    }

    @Override
    public void add (ISprite sprite) {
        mySpriteManager.get().add(sprite);
        
    }

}
