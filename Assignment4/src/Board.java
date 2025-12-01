import java.util.HashMap;

public class Board {
    HashMap<String, BoardEntity> boardData = new HashMap<>();
    int size;

    Board(int boardSize){
        this.size = boardSize;
    }

    public void addEntity(BoardEntity entity){}

    public BoardEntity getEntity(EntityPosition position){
        return null;
    }

    public Direction getDirection(Insect insect){
        return null;
    }

    public int getDirectionSum(Insect insect){
        return 0;
    }

}
