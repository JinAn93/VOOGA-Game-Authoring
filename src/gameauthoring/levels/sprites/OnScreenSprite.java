package gameauthoring.levels.sprites;

import engine.ILevel;
import engine.rendering.AuthoringRenderer;
import engine.rendering.UnscaledFactory;
import engine.sprite.ISprite;
import gameauthoring.util.Glyph;
import gameauthoring.util.BasicUIFactory;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;


/**
 * Authoring environment sprite that allows for moving around the screen and includes
 * a context menu for deletion and path creation
 * 
 * @author Tommy
 *
 */
public class OnScreenSprite implements Draggable, Glyph {
    private static final String MOVE_STRING = "Moving";

    private AuthoringRenderer levelView;
    private ISprite mySprite;
    private SpriteController myController;

    public OnScreenSprite (AuthoringRenderer renderer, ILevel level, ISprite sprite) {
        levelView = renderer;
        mySprite = sprite;
        myController = new SpriteController(level);
    }

    @Override
    public Node draw () {
        Node node = mySprite.getDrawer().getVisualRepresentation(new UnscaledFactory());
        this.setActions(node);
        this.createRightClickMenu(node);
        return node;
    }

    @Override
    public void setOnDragDetected (MouseEvent e, Node source) {
        Dragboard db = source.startDragAndDrop(TransferMode.COPY);
        db.setContent(this.createClipboard(MOVE_STRING));
        db.setDragView(getSpriteImage(source));

        source.setVisible(false);
        levelView.getPane().setOnDragDropped(event -> setOnDragDropped(event));
    }

    private Image getSpriteImage (Node source) {
        return (new BasicUIFactory()).getImageFromNode(source);
    }

    @Override
    public void setOnDragOver (DragEvent e) {
    }

    @Override
    public void setOnDragDropped (DragEvent e) {
        if (checkPlaceableTile(e)) {
            myController.moveSprite(mySprite, e.getX(), e.getY());
        }
        levelView.render();
    }

    /**
     * Sets the sprite action menu on right click
     * 
     * @param node
     */
    private void createRightClickMenu (Node node) {
        node.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                spriteActionsMenu(levelView.getPane()).show(node,
                                                            event.getScreenX(),
                                                            event.getScreenY());
            }
        });
    }

    /**
     * Helper to get the context menu. Can be overridden for various
     * context menus
     * 
     * @param container
     * @return
     */
    protected ContextMenu spriteActionsMenu (Pane container) {
        SpriteContextMenu menu = new SpriteContextMenu(levelView, myController, mySprite);
        return menu.createActionMenu();
    }

    protected ISprite getSprite () {
        return mySprite;
    }

    private boolean checkPlaceableTile (DragEvent e){
        double halfHeight = (0.5) * (mySprite.getBounds().getBottom() - mySprite.getBounds().getTop());
        double halfWidth = (0.5) * (mySprite.getBounds().getRight() - mySprite.getBounds().getLeft());
        for(int r = (int)((e.getY()-halfHeight)/25); r < (int)((e.getY() + halfHeight)/25); r++){       
            for (int c = (int)((e.getX()-halfWidth)/25); c < (int)((e.getX()+halfWidth)/25); c++){
                if(levelView.getLevel().getPlaceableTileManager().getPlaceableMap().getBitMap()[r][c])
                    return false;
            }
        }
        return true;
    }
}
