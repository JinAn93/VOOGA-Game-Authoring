package gameplayer;

import engine.IGame;
import engine.IGamePlayable;
import engine.IOInterpeter;
import engine.rendering.IRenderer;
import engine.rendering.InGameRenderer;
import engine.rendering.LevelRenderer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import util.TimeDuration;


/**
 * This class represents a GameEngine and handles the rendering and updating of a game
 * 
 * @author David Maydew
 *
 */
public class GameEngine implements IGameEngine {

    private static final int FPS = 60;

    private IGame myGame;
    private LevelRenderer myRenderer;
    private IOInterpeter myIOIntercepter;
    private Timeline myTimeline = new Timeline();

    public GameEngine (IGame game, BorderPane gamePane, Pane levelPane, IOInterpeter ioInterpreter) {
        myGame = game;
        myRenderer = new InGameRenderer(game, levelPane);
        myIOIntercepter = ioInterpreter;
        createLevelView(gamePane);
        initializeTimeline();
    }

    private void createLevelView (BorderPane gamePane) {
        gamePane.setCenter(myRenderer.getPane());
        gamePane.setRight(new SideBarDisplay(myGame, myRenderer).draw());
        gamePane.setLeft(new HeadsUpDisplay(myGame).draw());
    }

    private void initializeTimeline () {
        Timeline timeline = getTimeline();
        //Duration frameDuration = Duration.seconds(1.0d / FPS);
        Duration frameDuration = Duration.seconds(1.0d / 5);
        KeyFrame repeatedFrame = new KeyFrame(frameDuration, e -> step(frameDuration));
        timeline.getKeyFrames().add(repeatedFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void step (Duration frameDuration) {
        getGame().internalizeKeyEvents(myIOIntercepter.deQueueKeyEvents());
        getGame().internalizeMouseEvents(myIOIntercepter.deQueueMouseEvents());
        getGame().update(new TimeDuration(frameDuration.toMillis()));
        getRenderer().render();
    }

    @Override
    public void play () {
        getTimeline().play();
    }

    @Override
    public void pause () {
        getTimeline().pause();
    }

    private IGamePlayable getGame () {
        return myGame;
    }

    private IRenderer getRenderer () {
        return myRenderer;
    }

    private Timeline getTimeline () {
        return myTimeline;
    }

}