package Asteroids.Storage;

import Uniplay.Storage.NG2DGame;
import Uniplay.Storage.NGGameManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Asteroids extends NG2DGame {

    protected Stage FGameControlStage;
    protected GameControlStageController FGameControlController;

    protected void CreateControlStage(){
        FGameControlStage = new Stage();
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("GameControlStage.fxml"));
        try {
            lXMLLoader.load();
            FGameControlController = lXMLLoader.getController();
            FGameControlController.Game = this;
            FGameControlController.Initialize();
            Parent lRoot = lXMLLoader.getRoot();
            FGameControlStage.setTitle("Asteroids.Control");
            FGameControlStage.setScene(new Scene(lRoot, 880, 50, Color.DARKGRAY));
            FGameControlStage.setResizable(false);
        }
        catch (Exception e) {
            writeError("CreateControlStage", e.getMessage());
        }
    }

    protected void perfectLayout() {
        FGameControlStage.setX(900);
        FGameControlStage.setY(110);
    }

    @Override
    protected void DoShowStages() {
        super.DoShowStages();
        perfectLayout();
        FGameControlStage.show();
    }

    public Asteroids(NGGameManager aManager, String aName) {
        super(aManager, aName);
        CreateControlStage();
    }

}
