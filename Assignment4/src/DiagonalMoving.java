import java.util.HashMap;

public interface DiagonalMoving {
    int getDiagonalDirectionVisibleValue(Direction dir, EntityPosition entityPosition, HashMap<String, BoardEntity> boardData, int boardSize);
    int travelDiagonally(Direction dir, EntityPosition entityPosition, InsectColor color, HashMap<String, BoardEntity> boardData, int boardSize);
}
