import java.util.HashMap;

public class Board {
    HashMap<String, BoardEntity> boardData = new HashMap<>();
    int size;

    Board(int boardSize){
        this.size = boardSize;
    }

    public void addEntity(BoardEntity entity){
        this.boardData.put(entity.getX() + ":" + entity.getY(), entity);
    }

    public BoardEntity getEntity(EntityPosition position){
        BoardEntity currEntity = boardData.get(position.x + ":" + position.y);

        return currEntity;
    }

    public Direction getDirection(Insect insect){
        return insect.getBestDirection(boardData, size);
    }

    public int getDirectionSum(Insect insect){
        return 0;
    }

}
