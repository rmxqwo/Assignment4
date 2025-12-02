import java.util.HashMap;

public class Ant extends Insect implements OrthogonalMoving, DiagonalMoving{


    Ant(EntityPosition position, InsectColor color) {
        super(position, color);
    }

    @Override
    Direction getBestDirection(HashMap<String, BoardEntity> boardData, int boardSize) {
        for(int i = 0; i < 8; i++){


        }
    }

    @Override
    int travelDirection(Direction dir, HashMap<String, BoardEntity> boardData, int boardSize) {
        return 0;
    }

    @Override
    public int getDiagonalDirectionVisibleValue(Direction dir, EntityPosition entityPosition, HashMap<String, BoardEntity> boardData, int boardSize) {
        return 0;
    }

    @Override
    public int travelDiagonally(Direction dir, EntityPosition entityPosition, InsectColor color, HashMap<String, BoardEntity> boardData, int boardSize) {
        return 0;
    }

    @Override
    public int getOrthogonalDirectionVisibleValue(Direction dir, EntityPosition entityPosition, HashMap<String, BoardEntity> boardData, int boardSize) {
        return 0;
    }

    @Override
    public int travelOrthogonally(Direction dir, EntityPosition entityPosition, InsectColor color, HashMap<String, BoardEntity> boardData, int boardSize) {
        return 0;
    }
}
