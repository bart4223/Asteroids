package Asteroids.Graphics;

import Uniplay.Graphics.NG2DCustomSprite;
import Uniplay.Graphics.NGCustomRenderEngineItem;
import Uniplay.Storage.NGCustomGameObject;
import Uniwork.Base.NGPropertyItem;
import Uniwork.Visuals.NGDisplayController;

import java.util.concurrent.CopyOnWriteArrayList;

public class AsteroidsSprite extends NG2DCustomSprite {

    public AsteroidsSprite() {
        super();
    }

    @Override
    public Boolean IsRenderEngineResponsible(NGCustomRenderEngineItem aRenderEngine) {
        return aRenderEngine.getName().equals("Back");
    }

    @Override
    public String getResponsibleDisplayControllerName(NGCustomRenderEngineItem aRenderEngine) {
        return "Default";
    }

    @Override
    public CopyOnWriteArrayList<NGPropertyItem> getDisplayControllerProps(String aRenderEngine, NGDisplayController aDisplayController) {
        return new CopyOnWriteArrayList<NGPropertyItem>();
    }

    @Override
    public CopyOnWriteArrayList<NGPropertyItem> getDisplayControllerLayerProps(String aRenderEngine, NGDisplayController aDisplayController) {
        return new CopyOnWriteArrayList<NGPropertyItem>();
    }

    @Override
    public Integer getValueForDisplayController(String aRenderEngine, NGDisplayController aDisplayController) {
        return getID();
    }

    public NGCustomGameObject getGameObject() {
        return null;
    }

}
