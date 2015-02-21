package Asteroids.Control;

import Asteroids.Graphics.SpriteAir;
import Asteroids.Graphics.SpriteStone;
import Asteroids.Storage.MemoryCellValue;
import Uniplay.Control.NGControlMimicManager;
import Uniplay.Control.NGControlMimicPeriodicAction;
import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniplay.Kernel.NGGameEngineMemoryCellValueItem;
import Uniplay.Kernel.NGGameEngineMemoryManager;
import Uniplay.Storage.NGCustomGame;
import Uniwork.Misc.NGRandomGenerator;

import java.util.ArrayList;

public class StoneShifter extends NGControlMimicPeriodicAction {

    protected double FPassageFactor;

    @Override
    protected void DoHandleTick() {
        super.DoHandleTick();
        NGGameEngineMemoryCellValueItem cell;
        MemoryCellValue value;
        SpriteStone stone;
        NGGameEngineMemoryAddress address;
        NGCustomGame game = getGame();
        NGGameEngineMemoryManager mm = game.getMemoryManager();
        ArrayList<NGGameEngineMemoryCellValueItem> cells = new ArrayList<NGGameEngineMemoryCellValueItem>();
        for (int y = 0; y < mm.getBaseSize(game.getMemoryName()); y++) {
            for (int x = 1; x < mm.getOffsetSize(game.getMemoryName()); x++) {
                address = new NGGameEngineMemoryAddress(1, y, x);
                value = (MemoryCellValue)mm.getCellValue(game.getMemoryName(), address);
                switch (value.getInteger()) {
                    case 0: case 2: case 3: case 4:
                        NGGameEngineMemoryAddress newAddress = new NGGameEngineMemoryAddress(address.getPage(), address.getBase(), address.getOffset() - 1);
                        cell = new NGGameEngineMemoryCellValueItem(newAddress, value);
                        MemoryCellValue oldValue = (MemoryCellValue)mm.getCellValue(game.getMemoryName(), newAddress);
                        switch (oldValue.getInteger()) {
                            case 1:
                                break;
                            default:
                                cells.add(cell);
                                break;
                        }
                        break;
                }
            }
        }
        mm.setCellsValue(game.getMemoryName(), cells);
        cells.clear();
        for (int y = 0; y < mm.getBaseSize(game.getMemoryName()); y++) {
            address = new NGGameEngineMemoryAddress(1, y, mm.getOffsetSize(game.getMemoryName()) - 1);
            value = (MemoryCellValue)mm.getCellValue(game.getMemoryName(), address);
            value.setObject(new SpriteAir());
            cell = new NGGameEngineMemoryCellValueItem(address, value);
            cells.add(cell);
        }
        mm.setCellsValue(game.getMemoryName(), cells);
        cells.clear();
        int maxY = NGRandomGenerator.GlobalRandomGenerator.getInteger((int)(mm.getBaseSize(game.getMemoryName()) * FPassageFactor));
        for (int y = 0; y < maxY; y++) {
            address = new NGGameEngineMemoryAddress(1, y, mm.getOffsetSize(game.getMemoryName()) - 1);
            value = (MemoryCellValue)mm.getCellValue(game.getMemoryName(), address);
            stone = new SpriteStone();
            stone.setID(2);
            value.setObject(stone);
            cell = new NGGameEngineMemoryCellValueItem(address, value);
            cells.add(cell);
        }
        address = new NGGameEngineMemoryAddress(1, maxY, mm.getOffsetSize(game.getMemoryName()) - 1);
        value = (MemoryCellValue)mm.getCellValue(game.getMemoryName(), address);
        stone = new SpriteStone();
        stone.setID(3);
        value.setObject(stone);
        cell = new NGGameEngineMemoryCellValueItem(address, value);
        cells.add(cell);
        maxY = NGRandomGenerator.GlobalRandomGenerator.getInteger((int)(mm.getBaseSize(game.getMemoryName()) * FPassageFactor));
        for (int y = mm.getBaseSize(game.getMemoryName()) - maxY; y < mm.getBaseSize(game.getMemoryName()); y++) {
            address = new NGGameEngineMemoryAddress(1, y, mm.getOffsetSize(game.getMemoryName()) - 1);
            value = (MemoryCellValue)mm.getCellValue(game.getMemoryName(), address);
            stone = new SpriteStone();
            stone.setID(2);
            value.setObject(stone);
            cell = new NGGameEngineMemoryCellValueItem(address, value);
            cells.add(cell);
        }
        address = new NGGameEngineMemoryAddress(1, mm.getBaseSize(game.getMemoryName()) - maxY - 1, mm.getOffsetSize(game.getMemoryName()) - 1);
        value = (MemoryCellValue)mm.getCellValue(game.getMemoryName(), address);
        stone = new SpriteStone();
        stone.setID(4);
        value.setObject(stone);
        cell = new NGGameEngineMemoryCellValueItem(address, value);
        cells.add(cell);
        mm.setCellsValue(game.getMemoryName(), cells);
    }

    public StoneShifter(NGControlMimicManager aManager, NGCustomGame aGame, String aName) {
        super(aManager, aGame, aName);
        FPassageFactor = 0.25;
    }

}
