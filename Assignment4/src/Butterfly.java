import java.util.HashMap;

public class Butterfly extends Insect implements OrthogonalMoving{

    Butterfly(EntityPosition position, InsectColor color) {
        super(position, color);
    }

    @Override
    Direction getBestDirection(HashMap<String, BoardEntity> boardData, int boardSize) {
        return null;
    }

    @Override
    int travelDirection(Direction dir, HashMap<String, BoardEntity> boardData, int boardSize) {
        return travelOrthogonally(dir, entityPosition, color, boardData, boardSize);
    }

    @Override
    public int getOrthogonalDirectionVisibleValue(Direction dir, EntityPosition entityPosition, HashMap<String, BoardEntity> boardData, int boardSize) {
        int totalFood = 0;
        int dirX = 0, dirY = 0;
        switch (dir){
            case N: dirX = 0; dirY = -1; break;
            case S: dirX = 0; dirY = 1; break;
            case E: dirX = 1; dirY = 0; break;
            case W: dirX = -1; dirY = 0; break;
            default: return 0;
        }
        int x = entityPosition.x;
        int y = entityPosition.y;

        while (!(x < 1 || y < 1 || x > boardSize || y > boardSize)) {
            x += dirX;
            y += dirY;

            String currPosition = x + ":" + y;
            BoardEntity boardEntity = boardData.get(currPosition);
            if (boardEntity instanceof FoodPoint) {
                totalFood += ((FoodPoint) boardEntity).value;
            }
        }
        return totalFood;
    }

    @Override
    public int travelOrthogonally(Direction dir, EntityPosition entityPosition, InsectColor color, HashMap<String, BoardEntity> boardData, int boardSize) {
        int foodEaten = 0;
        int dirX = 0, dirY = 0;
        switch (dir) {
            case N: dirX = 0; dirY = -1; break;
            case S: dirX = 0; dirY = 1; break;
            case E: dirX = 1; dirY = 0; break;
            case W: dirX = -1; dirY = 0; break;
            default: return 0;
        }

        int x = entityPosition.x;
        int y = entityPosition.y;

        while (!(x < 1 || x > boardSize || y < 1 || y > boardSize)) {
            x += dirX;
            y += dirY;

            String currPosition = x + ":" + y;
            BoardEntity boardEntity = boardData.get(currPosition);

            if (boardEntity instanceof FoodPoint) {
                foodEaten += ((FoodPoint) boardEntity).value;
                boardData.remove(currPosition);
            } else if (boardEntity instanceof Insect) {
                Insect insect = (Insect) boardEntity;
                if (!insect.color.equals(this.color)) {
                    break;
                }
            }
        }
        boardData.remove(entityPosition.x + ":" + entityPosition.y);
        return foodEaten;
    }
}
