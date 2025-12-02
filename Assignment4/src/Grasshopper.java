import java.util.HashMap;

public class Grasshopper extends Insect implements OrthogonalMoving{

    Grasshopper(EntityPosition position, InsectColor color) {
        super(position, color);
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
