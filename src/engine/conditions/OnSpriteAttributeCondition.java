package engine.conditions;

import java.util.function.DoublePredicate;
import engine.AttributeType;
import engine.IEventPackage;
import engine.IGame;
import engine.profile.IProfile;
import util.TimeDuration;


public class OnSpriteAttributeCondition extends Condition implements ICondition {

    private IGame myGame;
    private AttributeType myAttributeType;
    private DoublePredicate myValueCheck;
    private IEventPackage mySpritePackage;
    private IEventPackage myOtherPackage;
    private IEventPackage myGlobalPackage;

    public OnSpriteAttributeCondition (IGame game,
                                       AttributeType attributeType,
                                       DoublePredicate valueCheck,
                                       IEventPackage spritePackage,
                                       IEventPackage otherPackage,
                                       IEventPackage globalPackage) {
        myGame = game;
        myAttributeType = attributeType;
        myValueCheck = valueCheck;
        mySpritePackage = spritePackage;
        myOtherPackage = otherPackage;
        myGlobalPackage = globalPackage;

    }

    @Override
    public void update (TimeDuration duration) {
        getPackageFilteredSprites(myGame, mySpritePackage)
            .forEach(sprite -> sprite.getAttributes().stream()
                     .filter(atty -> atty.getType().equals(myAttributeType))
                     .forEach(attribute -> checkAttribute(attribute, myValueCheck, new FunctionalDoer() {
                         @Override
                         public void doIt () {
                             applyEventPackageToSprites(myGame, mySpritePackage);
                             applyOtherAndGlobalEventPackages(myGame,myOtherPackage, myGlobalPackage);
                            }
                        })));
                           
    }

    @Override
    public IProfile getProfile () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setProfile (IProfile profile) {
        // TODO Auto-generated method stub
        
    }


}
