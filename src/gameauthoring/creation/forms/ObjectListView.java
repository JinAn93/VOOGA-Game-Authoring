package gameauthoring.creation.forms;

import java.util.function.Consumer;
import engine.profile.IProfilable;
import gameauthoring.ProfileCellView;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;


/**
 * View class for individual object lists in Object Creation Tabs
 * 
 * @author Jin An, Jeremy Schreck
 *
 */
public class ObjectListView<E extends IProfilable> implements IObjectListView<E> {

    private ObservableList<E> myItems;
    private ListView<E> myListView;

    public ObjectListView (ObservableList<E> items) {
        myItems = items;
        myListView = new ListView<E>();
        myListView.setItems(getMyItems());
        myListView.setCellFactory( c -> new ProfileCellView<E> () );
    }

    @Override
    public Node draw () {
        return myListView;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub

    }

    @Override
    public void setEditAction (Consumer<E> action) {
        getMyListView().setOnMouseClicked(e -> handleEditCell(action));

    }

    /**
     * Event handler for cell selection. Passes the item in the listview to the
     * given Consumer action
     * 
     * Note: can delete this if we want because selection is now hooked up automatically
     * 
     * @param action The consumer to which to pass the selected item
     */
    private void handleEditCell (Consumer<E> action) {
        E item = getMyListView().getSelectionModel().getSelectedItem();
        action.accept(item);
    }

    @Override
    public ObservableList<E> getMyItems () {
        return myItems;
    }

    private ListView<E> getMyListView () {
        return myListView;
    }

    @Override
    public E getSelectedItem () {
        return this.myListView.getSelectionModel().getSelectedItem();
    }

    @Override
    public void setSelectedItem (E item) {
        this.myListView.getSelectionModel().select(item);

    }

    @Override
    public void setMyItems (ObservableList<E> items) {
        this.myItems = items;
    }

}
