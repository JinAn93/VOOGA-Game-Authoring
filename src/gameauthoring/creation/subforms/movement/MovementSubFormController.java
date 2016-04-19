package gameauthoring.creation.subforms.movement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.IGame;
import gameauthoring.creation.subforms.DynamicSubFormController;


public class MovementSubFormController extends DynamicSubFormController {

    public MovementSubFormController (IGame game) {
        super(game, new MovementSFCFactory(game),
              new ArrayList<String>(Arrays.asList("Static", "Constant", "UserMover", "Tracking")));

        List<String> options =
                new ArrayList<String>(Arrays.asList("Static", "Constant", "User-Defined",
                                                    "Tracking"));
        setMyView(new MovementSubFormView(getMySubFormViews(), e -> changeSelection(e), options));
    }

    /*
     * private List<String> getOptions(){
     * List<String> options = new ArrayList<>();
     * for(ISubFormController sfc: getMySubFormControllers){
     * options.add(sfc.getTitle());
     * }
     * return options;
     * }
     */

}
