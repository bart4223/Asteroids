package Asteroids.Storage;

import Uniplay.Storage.NGCustomGame;
import Uniplay.Storage.NGCustomGameObject;
import Uniwork.Graphics.NGPoint2D;
import Uniwork.Misc.NGRandomGenerator;

import java.util.ArrayList;

public class StarryStar extends NGCustomGameObject {

    protected enum Direction{left, right}

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
            FMoveIndex = FMaxStarVelocity - FVelocity;
        }

        public void Move(Integer aDirection) {
            FMoveIndex--;
            if (FMoveIndex <= 0) {
                FMoveIndex = FMaxStarVelocity - FVelocity;
                setPositionX(getPositionX() + aDirection);
            }
        }

    }

    protected Integer FWidth;
    protected Integer FHeight;
    protected ArrayList<Star> FStars;
    protected Direction FDirection;
    protected Integer FMaxStars;
    protected Integer FMaxStarSize;
    protected Integer FMaxStarVelocity;

    protected void newStar() {
        Integer x = NGRandomGenerator.GlobalRandomGenerator.getInteger(FWidth);
        Integer y = NGRandomGenerator.GlobalRandomGenerator.getInteger(FHeight);
        Integer starsize = NGRandomGenerator.GlobalRandomGenerator.getInteger(1, FMaxStarSize);
        Integer velocity = DetermineVelocity(starsize);
        Star star = new Star(x, y, starsize, velocity);
        FStars.add(star);
    }

    protected Integer DetermineVelocity(Integer aStarSize) {
        Integer velocity = FMaxStarVelocity / FMaxStarSize;
        Integer offset = NGRandomGenerator.GlobalRandomGenerator.getInteger(0, velocity) + 1;
        velocity = (aStarSize - 1) * velocity + offset;
        return velocity;
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        Integer count = NGRandomGenerator.GlobalRandomGenerator.getInteger(FMaxStars / 2, FMaxStars);
        for (int i = 0; i < count; i++) {
            newStar();
        }
    }

    protected void DoExecute() {
        ArrayList<Star> martures = new ArrayList<Star>();
        for (Star star : FStars) {
            switch (FDirection) {
                case left:
                    star.Move(-1);
                    if (star.getPositionX() < -star.getSize()) {
                        martures.add(star);
                    }
                    break;
                case right:
                    star.Move(+1);
                    if (star.getPositionX() > FWidth + star.getSize()) {
                        martures.add(star);
                    }
                    break;
            }
        }
        for (Star marture : martures) {
            switch (FDirection) {
                case left:
                    marture.setPositionX(FWidth + FMaxStarSize);
                    marture.setPositionY(NGRandomGenerator.GlobalRandomGenerator.getInteger(FHeight));
                    break;
                case right:
                    marture.setPositionX(- FMaxStarSize);
                    marture.setPositionY(NGRandomGenerator.GlobalRandomGenerator.getInteger(FHeight));
                    break;
            }
            marture.setSize(NGRandomGenerator.GlobalRandomGenerator.getInteger(1, FMaxStarSize));
            marture.setVelocity(DetermineVelocity(marture.getSize()));
        }
    }

    public StarryStar(NGCustomGame aGame, Integer aWidth, Integer aHeight, Integer aMaxStars) {
        super(aGame);
        FStars = new ArrayList<Star>();
        FWidth = aWidth;
        FHeight = aHeight;
        FDirection = Direction.left;
        FMaxStars = aMaxStars;
        FMaxStarSize = 3;
        FMaxStarVelocity = 10;
    }

    public void Initialize() {
        DoInitialize();
    }

    public Integer getWidth() {
        return FWidth;
    }

    public Integer getHeight() {
        return FHeight;
    }

    public Integer getMaxStars() {
        return FMaxStars;
    }

    public Integer getMaxStarSize() {
        return FMaxStarSize;
    }

    public void setMaxStarSize(Integer aMaxStarSize) {
        FMaxStarSize = aMaxStarSize;
    }

    public Integer getMaxStarVelocity() {
        return FMaxStarVelocity;
    }

    public void setMaxStarVelocity(Integer aMaxStarVelocity) {
        FMaxStarVelocity = aMaxStarVelocity;
    }

    public Integer getStarCount() {
        return FStars.size();
    }

    public Star getStar(Integer aIndex) {
        return FStars.get(aIndex);
    }

    public Direction getDirection() {
        return FDirection;
    }

    public void setDirection(Direction aDirection) {
        FDirection = aDirection;
    }

    public void Execute() {
        DoExecute();
    }

}
