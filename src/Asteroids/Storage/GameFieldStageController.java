package Asteroids.Storage;

import Asteroids.Graphics.StarryStarDisplayManager;
import Uniwork.Visuals.NGStageController;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class GameFieldStageController extends NGStageController {

    public static String CStarryStar = "STARRYSTAR";

    public Asteroids Game;

    @FXML
    public Canvas LayerStarryStar;

    protected void CreateDisplayController() {
        super.CreateDisplayController();
        StarryStarDisplayManager dm = new StarryStarDisplayManager(LayerStarryStar, CStarryStar);
        dm.DefaultStarColor = Color.GRAY;
        registerDisplayController(dm);
    }

    public GameFieldStageController() {
        super();
        FOwnRenderThread = true;
    }

}
