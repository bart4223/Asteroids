package Asteroids;

import Uniplay.Kernel.NGGameEngineEventHandlerKernelStarted;
import Uniplay.NGGameEngineConstants;
import Uniplay.Storage.NGGameManager;

public class AsteroidsModuleEventHandlerKernelStarted extends NGGameEngineEventHandlerKernelStarted {

    protected AsteroidsModule FModule;

    @Override
    protected void DoHandleEvent() {
        super.DoHandleEvent();
        getGameManager().showGames();
    }

    protected NGGameManager getGameManager() {
        return (NGGameManager)FModule.ResolveObject(NGGameEngineConstants.CMP_GAME_MANAGER, NGGameManager.class);
    }

    public AsteroidsModuleEventHandlerKernelStarted(AsteroidsModule aModule) {
        super();
        FModule = aModule;
    }

}
