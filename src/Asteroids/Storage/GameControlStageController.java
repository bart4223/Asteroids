package Asteroids.Storage;

import Uniwork.Visuals.NGStageController;
import javafx.fxml.FXML;

public class GameControlStageController extends NGStageController {

    public Asteroids Game;

    @FXML
    protected void handleExit(){
        Game.Shutdown();
    }

    public GameControlStageController() {
        super();
    }

}
