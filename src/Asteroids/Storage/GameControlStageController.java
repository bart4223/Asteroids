package Asteroids.Storage;

import Uniwork.Visuals.NGStageController;
import javafx.fxml.FXML;

public class GameControlStageController extends NGStageController {

    public Asteroids Game;

    @FXML
    protected void handleQuit(){
        Game.Shutdown();
    }

    @FXML
    protected void handleNew(){
        Game.Restart();
    }

    public GameControlStageController() {
        super();
    }

}
