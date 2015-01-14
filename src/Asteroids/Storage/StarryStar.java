package Asteroids.Storage;

import Uniplay.Storage.NGCustomGame;
import Uniplay.Storage.NGCustomGameObject;
import Uniwork.Graphics.NGPoint2D;
import Uniwork.Misc.NGRandomGenerator;

import java.util.ArrayList;

public class StarryStar extends NGCustomGameObject {

    public enum Direction{left, right}

    public class Star {

        protected NGPoint2D FPosition;
        protected Integer FSize;
        protected Integer FVelocity;
        protected Integer FMoveIndex;

        public Star(Integer aX, Integer aY, Integer aSize, Integer aVelocity) {
            FPosition = new NGPoint2D(aX, aY);
            FSize = aSize;
            setVelocity(aVelocity);
        }

        public Integer getPositionX() {
            return FPosition.getXAsInt();
        }

        public void setPositionX(Integer aX) {
            FPosition.setX(aX);
        }

        public Integer getPositionY() {
            return FPosition.getYAsInt();
        }

        public void setPositionY(Integer aY) {
            FPosition.setY(aY);
        }

        public Integer getSize() {
            return FSize;
        }

        public void setSize(Integer aSize) {
            FSize = aSize;
        }

        public Integer getVelocity() {
            return FVelocity;
        }

        public void setVelocity(Integer aVelocity) {
            FVelocity = aVelocity;
            FMoveIndex = MaxStarVelocity - FVelocity;
        }

        public void Move(Integer aDirection) {
            FMoveIndex--;
            if (FMoveIndex <= 0) {
                FMoveIndex = MaxStarVelocity - FVelocity;
                setPositionX(getPositionX() + aDirection);
            }
        }

    }

    protected ArrayList<Star> FStars;

    protected void newStar() {
        Integer x = NGRandomGenerator.GlobalRandomGenerator.getInteger(Width);
        Integer y = NGRandomGenerator.GlobalRandomGenerator.getInteger(Height);
        Integer starsize = NGRandomGenerator.GlobalRandomGenerator.getInteger(1, MaxStarSize);
        Integer velocity = DetermineVelocity(starsize);
        Star star = new Star(x, y, starsize, velocity);
        FStars.add(star);
    }

    protected Integer DetermineVelocity(Integer aStarSize) {
        Integer velocity = MaxStarVelocity / MaxStarSize;
        Integer offset = NGRandomGenerator.GlobalRandomGenerator.getInteger(0, velocity) + 1;
        velocity = (aStarSize - 1) * velocity + offset;
        return velocity;
    }

    @Override
    protected void DoReset() {
        super.DoReset();
        FStars.clear();
        Integer count = NGRandomGenerator.GlobalRandomGenerator.getInteger(MaxStars / 2, MaxStars);
        for (int i = 0; i < count; i++) {
            newStar();
        }
        writeLog(String.format("StarryStar reset with %d stars.", count));
    }

    @Override
    protected void DoExecute() {
        super.DoExecute();
        ArrayList<Star> martures = new ArrayList<Star>();
        for (Star star : FStars) {
            switch (Direction) {
                case left:
                    star.Move(-1);
                    if (star.getPositionX() < -star.getSize()) {
                        martures.add(star);
                    }
                    break;
                case right:
                    star.Move(+1);
                    if (star.getPositionX() > Width + star.getSize()) {
                        martures.add(star);
                    }
                    break;
            }
        }
        for (Star marture : martures) {
            switch (Direction) {
                case left:
                    marture.setPositionX(Width + MaxStarSize);
                    marture.setPositionY(NGRandomGenerator.GlobalRandomGenerator.getInteger(Height));
                    break;
                case right:
                    marture.setPositionX(- MaxStarSize);
                    marture.setPositionY(NGRandomGenerator.GlobalRandomGenerator.getInteger(Height));
                    break;
            }
            marture.setSize(NGRandomGenerator.GlobalRandomGenerator.getInteger(1, MaxStarSize));
            marture.setVelocity(DetermineVelocity(marture.getSize()));
        }
    }

    public StarryStar(NGCustomGame aGame, String aName) {
        super(aGame, aName);
        FStars = new ArrayList<Star>();
        MaxStars = 0;
        Width = 0;
        Height = 0;
        Direction = Direction.left;
        MaxStarSize = 3;
        MaxStarVelocity = 10;
    }

    public Integer MaxStars;

    public Integer Width;

    public Integer Height;

    public Integer MaxStarSize;

    public Integer MaxStarVelocity;

    public Direction Direction;

    public Integer getStarCount() {
        return FStars.size();
    }

    public Star getStar(Integer aIndex) {
        return FStars.get(aIndex);
    }

}
