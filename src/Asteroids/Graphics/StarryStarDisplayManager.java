package Asteroids.Graphics;

import Asteroids.Storage.StarryStar;
import Uniwork.Visuals.NGDisplayManager;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class StarryStarDisplayManager extends NGDisplayManager {

    protected Color FCurrentStarColor;
    protected Integer FCurrentStarSize;

    protected void DoRender() {
        fillCircle((int) getPositionX(), (int) getPositionY(), FCurrentStarSize, FCurrentStarColor);
    }

    protected void InternalRender() {
        clearRect(0, 0, FWidth, FHeight);
        if (StarryStar != null) {
            for (int i = 0; i < StarryStar.getStarCount(); i++) {
                StarryStar.Star star = StarryStar.getStar(i);
                FCurrentStarSize = star.getSize();
                FCurrentStarColor = DefaultStarColor;
                Integer index = StarryStar.getMaxStarSize() / 2;
                if (FCurrentStarSize > index + 1) {
                    FCurrentStarColor = DefaultStarColor.brighter();
                }
                else if (FCurrentStarSize <= index) {
                    FCurrentStarColor = DefaultStarColor.darker();
                }
                setPosition(star.getPositionX(), star.getPositionY());
                super.InternalRender();
            }
        }
    }

    public StarryStarDisplayManager(Canvas aCanvas) {
        this(aCanvas, "");
    }

    public StarryStarDisplayManager(Canvas aCanvas, String aName) {
        super(aCanvas, aName);
    }

    public StarryStar StarryStar;

    public Color DefaultStarColor;

}

