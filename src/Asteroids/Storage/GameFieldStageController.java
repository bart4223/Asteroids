package Asteroids.Storage;

import Uniwork.Visuals.NGStageController;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;

public class GameFieldStageController extends NGStageController {

    public Asteroids Game;

    @FXML
    public Canvas LayerStarryStar;

    @FXML
    public Canvas LayerBack;

    @FXML
    public Canvas LayerFront;

    public GameFieldStageController() {
        super();
    }

}
