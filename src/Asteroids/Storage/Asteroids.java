package Asteroids.Storage;

import Asteroids.Graphics.AsteroidsSprite;
import Asteroids.Graphics.SpriteAir;
import Asteroids.Graphics.SpriteSpaceship;
import Asteroids.Graphics.SpriteStone;
import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniplay.Kernel.NGGameEngineMemoryIntegerCellValue;
import Uniplay.Kernel.NGGameEngineMemoryObjectCellValue;
import Uniplay.Storage.NG2DGame;
import Uniplay.Storage.NGGameManager;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Asteroids extends NG2DGame {

    protected Stage FGameControlStage;
    protected GameControlStageController FGameControlController;
    protected Stage FGameFieldStage;
    protected GameFieldStageController FGameFieldController;

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
            FGameControlStage.setScene(new Scene(lRoot, 800, 50, Color.DARKGRAY));
            FGameControlStage.setResizable(false);
        }
        catch (Exception e) {
            writeError("CreateControlStage", e.getMessage());
        }
    }

    protected void CreateGameFieldStage(){
        FGameFieldStage = new Stage();
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("GameFieldStage.fxml"));
        try {
            lXMLLoader.load();
            FGameFieldController = lXMLLoader.getController();
            FGameFieldController.Game = this;
            FGameFieldController.Initialize();
            Parent lRoot = lXMLLoader.getRoot();
            FGameFieldStage.setTitle("Asteroids.GameField");
            Scene scene = new Scene(lRoot, 800, 640, Color.WHITE);
            FGameFieldStage.setScene(scene);
            FGameFieldStage.setResizable(false);
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    handleKeyPressed(keyEvent);
                }
            });
        }
        catch (Exception e) {
            writeError("CreateGameFieldStage", e.getMessage());
        }
    }

    protected void handleKeyPressed(KeyEvent e) {

    }

    protected void perfectLayout() {
        FGameControlStage.setX(900);
        FGameControlStage.setY(310);
        FGameFieldStage.setX(900);
        FGameFieldStage.setY(400);
    }

    @Override
    protected void registerObjects() {
        registerObject(String.format("%s.GameField.LayerStarryStar", getName()), FGameFieldController.LayerStarryStar);
        registerObject(String.format("%s.GameField.LayerBack", getName()), FGameFieldController.LayerBack);
        registerObject(String.format("%s.GameField.LayerFront", getName()), FGameFieldController.LayerFront);
    }

    @Override
    protected void DoShowStages() {
        super.DoShowStages();
        perfectLayout();
        FGameControlStage.show();
        FGameFieldStage.show();
    }

    @Override
    protected Class getMemoryCellValueClass() {
        return MemoryCellValue.class;
    }

    @Override
    protected void assignMemoryCellValueFrom(NGGameEngineMemoryAddress aAddress, NGGameEngineMemoryObjectCellValue aCellValue, Object aObject) {
        if (aObject instanceof NGGameEngineMemoryIntegerCellValue) {
            NGGameEngineMemoryIntegerCellValue cellValue = (NGGameEngineMemoryIntegerCellValue)aObject;
            AsteroidsSprite sprite = getSpriteFrom(aAddress, cellValue.getInteger());
            aCellValue.setObject(sprite);
        }
    }

    protected AsteroidsSprite getSpriteFrom(NGGameEngineMemoryAddress aAddress, Integer aID)  {
        switch (aID) {
            case 0:
                return new SpriteAir();
            case 1:
                return new SpriteSpaceship();
            case 2: case 3: case 4:
                SpriteStone stone = new SpriteStone();
                stone.setID(aID);
                return stone;
        }
        return null;
    }

    public Asteroids(NGGameManager aManager, String aName) {
        super(aManager, aName);
        CreateControlStage();
        CreateGameFieldStage();
    }

}
