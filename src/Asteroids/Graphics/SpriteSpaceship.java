package Asteroids.Graphics;

import Asteroids.AsteroidsConsts;
import Uniplay.Graphics.NGCustomRenderEngineItem;

public class SpriteSpaceship extends AsteroidsSprite {

    public SpriteSpaceship() {
        super();
        FID = AsteroidsConsts.SPRITE_SPACESHIP;
    }

    @Override
    public Boolean IsRenderEngineResponsible(NGCustomRenderEngineItem aRenderEngine) {
        return aRenderEngine.getName().equals("Back");
    }

}