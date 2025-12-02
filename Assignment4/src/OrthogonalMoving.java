import java.util.HashMap;

public interface OrthogonalMoving {
    int getOrthogonalDirectionVisibleValue(Direction dir, EntityPosition entityPosition, HashMap<String, BoardEntity> boardData, int boardSize);
    int travelOrthogonally(Direction dir, EntityPosition entityPosition, InsectColor color, HashMap<String, BoardEntity> boardData, int boardSize);
}
