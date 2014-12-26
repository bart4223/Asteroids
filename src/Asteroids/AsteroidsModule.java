package Asteroids;

import Asteroids.Storage.Asteroids;
import Uniplay.Kernel.NGGameEngineModule;
import Uniplay.Kernel.NGGameEngineModuleManager;
import Uniplay.NGGameEngineConstants;
import Uniplay.Storage.NGGameManager;

public class AsteroidsModule extends NGGameEngineModule {

    protected Asteroids FAsteroids;

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        FAsteroids = addGame("Asteroids");
    }

    @Override
    protected void registerEventHandlers() {
        super.registerEventHandlers();
        registerEventHandler(new AsteroidsModuleEventHandlerKernelStarted(this));
    }

    protected Asteroids addGame(String aName) {
        NGGameManager manager = getGameManager();
        Asteroids game = (Asteroids)manager.addGame(aName, Asteroids.class);
        return game;
    }

    protected NGGameManager getGameManager() {
        return (NGGameManager)ResolveObject(NGGameEngineConstants.CMP_GAME_MANAGER, NGGameManager.class);
    }

    public AsteroidsModule(NGGameEngineModuleManager aManager, String aName) {
        super(aManager, aName);
        FAsteroids = null;
    }

    public Asteroids getAsteroids() {
        return FAsteroids;
    }

}
