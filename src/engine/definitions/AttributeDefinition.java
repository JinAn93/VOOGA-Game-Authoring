package engine.definitions;

import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import engine.profile.IProfilable;
import engine.profile.IProfile;
import engine.profile.Profile;


/**
 * Definition for Attribute. Have getter/setter methods for each attribute.
 *
 * @author Jin An, Jeremy Schreck, Joe Lilien
 *
 */

public class AttributeDefinition implements IProfilable {

    private String myType;
    private double myStartingValue;
    private IProfile myProfile;
    private boolean isGlobal;

    /**
     * Constructor initialized with defaults
     * TODO put defaults in resource file
     */
    public AttributeDefinition () {
        
        myStartingValue = 0;
        myProfile = new Profile();
    }

    public IAttribute create () {
        
        return new Attribute(myStartingValue, new AttributeType(getProfile().getName().get()));
    }

    public void setType (String type) {
        myType = type;
    }

    public String getType () {
        return myType;
    }

    public double getStartingValue () {
        return myStartingValue;
    }

    public void setStartingValue (double startingValue) {
        myStartingValue = startingValue;
    }

    @Override
    public IProfile getProfile () {
        return myProfile;
    }

    @Override
    public void setProfile (IProfile profile) {
        myProfile = profile;
    }

    public boolean getIsGlobal () {
        return isGlobal;
    }

    public void setIsGlobal (boolean global) {
        isGlobal = global;
    }

}
