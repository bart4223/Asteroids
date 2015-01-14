package Asteroids;

import Uniplay.Kernel.NGGameEngineModule;
import Uniplay.Kernel.NGGameEngineModuleManager;

public class AsteroidsModule extends NGGameEngineModule {

    @Override
    protected void registerEventHandlers() {
        super.registerEventHandlers();
        registerEventHandler(new AsteroidsModuleEventHandlerKernelStarted(this));
    }

    public AsteroidsModule(NGGameEngineModuleManager aManager, String aName) {
        super(aManager, aName);
    }

}
