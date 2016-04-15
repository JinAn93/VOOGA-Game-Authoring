package engine.definitions;

import engine.IPositionable;
import engine.modules.IMovementModule;
import engine.modules.UserMover;

/**
 * This class represents the definition for a user controlled movement module
 *
 */
public class UserMoverDefinition extends MovementDefinition {

    private KeyControlDefinition myKeys;

    @Override
    public IMovementModule create (IPositionable parent) {
        return new UserMover(getSpeed(), myKeys.create(), parent);
    }

    public KeyControlDefinition getKeyControlDefintion () {
        return myKeys;
    }

    public void setKeyControlDefintion (KeyControlDefinition keyControl) {
        myKeys = keyControl;
    }

}
