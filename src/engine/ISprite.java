package engine;

import gameauthoring.IProfile;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import util.Coordinate;


public interface ISprite extends Drawable, Updateable, Affectable {

    SimpleObjectProperty<IProfile> getProfileProperty ();
    
    SimpleObjectProperty<Coordinate> getLocation ();

    SimpleObjectProperty<IMovementStrategy> getMovementStrategyProperty ();

    ObservableList<IModule> getModulesProperty ();

    ObservableList<IAttribute> getAttributesProperty ();

    ObservableList<IResource> getResourcesProperty ();

}
