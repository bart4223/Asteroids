package Asteroids.Storage;

import Asteroids.Graphics.AsteroidsSprite;
import Uniplay.Kernel.NGGameEngineMemoryObjectCellValue;

public class MemoryCellValue extends NGGameEngineMemoryObjectCellValue {

    protected AsteroidsSprite getSprite() {
        return (AsteroidsSprite)getObject();
    }

    public MemoryCellValue() {
        super();
    }

    @Override
    public Integer getInteger() {
        return getSprite().getID();
    }

}
