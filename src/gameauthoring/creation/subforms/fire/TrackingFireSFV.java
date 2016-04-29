package gameauthoring.creation.subforms.fire;

import java.util.ResourceBundle;
import splash.LocaleManager;
import engine.AuthorshipData;
import engine.SpriteGroup;
import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.creation.entryviews.CheckEntryView;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.tabs.AuthoringView;
import gameauthoring.util.BasicUIFactory;
import javafx.beans.property.BooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;


/**
 * View representing a subform that creates the information required to build a tracking mover
 * module
 * 
 * @author Dhrumil
 * @author Joe Lilien
 *
 */
public class TrackingFireSFV extends SubFormView implements ITrackingFireSFV {

    private HBox myPane;
    private ResourceBundle myLabel;
    private String myWaitTimeKey;
    private String myTargetsKey;
    private String myRangedKey;
    private String myRangeValueKey;
    private IEntryView myWaitTime;
    private SingleChoiceEntryView<SpriteGroup> myTargets;
    private SingleChoiceEntryView<SpriteDefinition> myMissileSelectionView;
    private BasicUIFactory myUIFactory = new BasicUIFactory();
    private RemoveOption myRemove;
    private CheckEntryView isRangedSelectionView;
    private NumberEntryView myRangeValue;

    public TrackingFireSFV (AuthorshipData data, RemoveOption remove) {
        setResourceBundleAndKey();
        myRemove = remove;
        myWaitTime =
                new NumberEntryView(myWaitTimeKey, this.getData(), 150, 30,
                                    AuthoringView.DEFAULT_ENTRYVIEW);
        myTargets =
                new SingleChoiceEntryView<SpriteGroup>(myTargetsKey, data.getMyCreatedGroups()
                        .getItems(),
                                                       AuthoringView.DEFAULT_ENTRYVIEW);
        myMissileSelectionView =
                new SingleChoiceEntryView<>("Missile", data.getMyCreatedMissiles().getItems(),
                                            AuthoringView.DEFAULT_ENTRYVIEW);
        isRangedSelectionView =
                new CheckEntryView(myRangedKey, AuthoringView.DEFAULT_ENTRYVIEW);
        myRangeValue = new NumberEntryView(myRangeValueKey, this.getData(), 150, 30, AuthoringView.DEFAULT_ENTRYVIEW);
        
        initView();
        initBinding();

    }

    private void setResourceBundleAndKey () {
        myLabel = ResourceBundle.getBundle("languages/labels", LocaleManager
                .getInstance().getCurrentLocaleProperty().get());
        myWaitTimeKey = myLabel.getString("WaitTimeKey");
        myTargetsKey = myLabel.getString("TargetsKey");
    }

    @Override
    protected void initView () {
        myPane =
                myUIFactory.makeHBox(20, Pos.TOP_LEFT, myMissileSelectionView.draw(),
                                     myWaitTime.draw(), myTargets.draw(), myRemove.draw());
        myPane.getStyleClass().add("firer");
    }

    @Override
    public SpriteGroup getTargetsCoice () {
        return myTargets.getSelected();
    }

    @Override
    public Node draw () {
        return myPane;
    }

    @Override
    public String getWaitTimeKey () {
        return myWaitTimeKey;
    }

    @Override
    public SpriteDefinition getSelectedMissile () {
        return myMissileSelectionView.getSelected();
    }

    @Override
    public void setTargetsChoice (SpriteGroup targets) {
        this.myTargets.setSelected(targets);
    }

    @Override
    public void setSelectedMissile (SpriteDefinition missile) {
        this.myMissileSelectionView.setSelected(missile);
    }

    @Override
    public String getMyRangedKey () {
        // TODO Auto-generated method stub
        return myRangedKey;
    }

    @Override
    public String getMyRangeValueKey () {
        // TODO Auto-generated method stub
        return myRangeValueKey;
    }

    @Override
    public BooleanProperty isRangedProperty () {
        // TODO Auto-generated method stub
        return isRangedSelectionView.isCheckedProperty();
    }
    
    private void initBinding () {
        myRangeValue.draw().visibleProperty().bind(isRangedProperty());
       
    }

}
