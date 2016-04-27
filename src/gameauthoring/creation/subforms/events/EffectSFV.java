package gameauthoring.creation.subforms.events;

import java.util.List;
import engine.definitions.concrete.AttributeDefinition;
import engine.profile.ProfileDisplay;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.entryviews.TextEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.shareddata.IDefinitionCollection;
import gameauthoring.tabs.AuthoringView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Implementation of IEffectSFV using VBox arrangement, allows users to define an effect package
 * 
 * @author Tommy
 * @author Joe Lilien
 *
 */
public class EffectSFV extends SubFormView implements IEffectSFV{

    private SingleChoiceEntryView<AttributeDefinition> myAttribute;
    private SingleChoiceEntryView<ProfileDisplay> myEffectType;
    private NumberEntryView myLength;
    private NumberEntryView myValue;
    private TextField myName;
    private String myLengthKey = "For length in time: ";
    private String myValueKey = "By amount: ";
    private String myAttributeKey = "Affect Attribute ";
    private String myTypeKey = "Effect type: ";
    private VBox myContainer;

    public EffectSFV (IDefinitionCollection<AttributeDefinition> myCreatedAttributes,
                              List<String> effectTypes) {
        
        //TODO: extract new IProfileSFV implementation that just has a textfield for name so we can reuse this code
        myName = new TextField();
        myName.setPromptText("Enter name: ");
        
        myEffectType = new SingleChoiceEntryView<ProfileDisplay> (myTypeKey,
                                                                  createEffectTypeList(effectTypes),
                                                                  AuthoringView.DEFAULT_ENTRYVIEW);
        myAttribute =
                new SingleChoiceEntryView<AttributeDefinition>(myAttributeKey,
                                                               myCreatedAttributes.getItems(),
                                                               AuthoringView.DEFAULT_ENTRYVIEW);
        myValue =
                new TextEntryView(myValueKey, this.getData(), 100, 30,
                                  AuthoringView.DEFAULT_ENTRYVIEW);
        myLength =
                new TextEntryView(myLengthKey, this.getData(), 100, 30,
                                  AuthoringView.DEFAULT_ENTRYVIEW);
        initView();
    }

    private ObservableList<ProfileDisplay> createEffectTypeList (List<String> effectTypes) {
        ObservableList<ProfileDisplay> types = FXCollections.observableArrayList();
        effectTypes.stream()
                   .forEach(e -> types.add(new ProfileDisplay(e)));
        return types;
    }

    @Override
    protected void initView () {
        myContainer = new VBox(4);
        HBox hbox = new HBox(10);
        myName.setMaxWidth(150);
        myContainer.getChildren().add(myName);
        hbox.getChildren().add(myEffectType.draw());
        hbox.getChildren().add(myAttribute.draw());
        hbox.getChildren().add(myValue.draw());
        hbox.getChildren().add(myLength.draw());
        myContainer.getChildren().add(hbox);
    }

    public AttributeDefinition getAttribute () {
        return myAttribute.getSelected();
    }
    
    public String getEffectType () {
        return myEffectType.getSelected().getProfile().getName().get();
    }
    
    public String getName () {
        return myName.getText();
    }
    
    public String getValueKey () {
        return myValueKey;
    }
    
    public String getLengthKey () {
        return myLengthKey;
    }

    @Override
    public Node draw () {
        return myContainer;
    }

    public void setName (String name) {
        myName.setText(name);
    }

    public void setEventSelection (String effectType) {
        //TODO this is a hacky fix
        for (ProfileDisplay pd : myEffectType.getItems()){
            if(pd.getProfile().getName().get().equals(effectType)){
                myEffectType.setSelected(pd);
            }
        }
    }

}
