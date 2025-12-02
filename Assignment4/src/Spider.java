import java.util.HashMap;

public class Spider extends Insect implements DiagonalMoving{

    Spider(EntityPosition entityPosition, InsectColor color){}


    @Override
    public int getDiagonalDirectionVisibleValue(Direction dir, EntityPosition entityPosition, HashMap<String, BoardEntity> boardData, int boardSize) {
        return 0;
    }

    @Override
    public int travelDiagonally(Direction dir, EntityPosition entityPosition, InsectColor color, HashMap<String, BoardEntity> boardData, int boardSize) {
        return 0;
    }
}
