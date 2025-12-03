import java.util.HashMap;

public class Grasshopper extends Insect implements OrthogonalMoving{

    Grasshopper(EntityPosition position, InsectColor color) {
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
            case N: dirX = 0; dirY = -2; break;
            case S: dirX = 0; dirY = 2; break;
            case E: dirX = 2; dirY = 0; break;
            case W: dirX = -2; dirY = 0; break;
            default: return 0;
        }
        int x = entityPosition.x;
        int y = entityPosition.y;

        while (!(x < 1 || x > boardSize || y < 1 || y > boardSize)) {
            int targetX = x + dirX;
            int targetY = y + dirY;
            if (targetX < 1 || targetX > boardSize || targetY < 1 || targetY > boardSize) break;

            String currPosition = targetX + ":" + targetY;
            BoardEntity boardEntity = boardData.get(currPosition);
            if (boardEntity instanceof FoodPoint) {
                totalFood += ((FoodPoint) boardEntity).value;
            }
            x = targetX;
            y = targetY;
        }
        return totalFood;
    }

    @Override
    public int travelOrthogonally(Direction dir, EntityPosition entityPosition, InsectColor color, HashMap<String, BoardEntity> boardData, int boardSize) {
        int foodEaten = 0;
        int dirX = 0, dirY = 0;
        switch (dir) {
            case N: dirX = 0; dirY = -2; break;
            case S: dirX = 0; dirY = 2; break;
            case E: dirX = 2; dirY = 0; break;
            case W: dirX = -2; dirY = 0; break;
            default: return 0;
        }

        int stepX = dirX / 2;
        int stepY = dirY / 2;

        int x = entityPosition.x;
        int y = entityPosition.y;

        while (!(x < 1 || x > boardSize || y < 1 || y > boardSize)) {
            int targetX = x + dirX;
            int targetY = y + dirY;
            if (targetX < 1 || targetX > boardSize || targetY < 1 || targetY > boardSize) break;

            String targetPosition = targetX + ":" + targetY;
            BoardEntity targetEntity = boardData.get(targetPosition);

            if (targetEntity instanceof FoodPoint) {
                foodEaten += ((FoodPoint) targetEntity).value;
                boardData.remove(targetPosition);
            } else if (targetEntity instanceof Insect) {
                Insect insect = (Insect) targetEntity;
                if (!insect.color.equals(this.color)) {
                    break;
                }
            }

            x = targetX;
            y = targetY;
        }
        boardData.remove(entityPosition.x + ":" + entityPosition.y);
        return foodEaten;
    }
}
