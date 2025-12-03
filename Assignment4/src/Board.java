import java.util.HashMap;

public class Board {
    public static HashMap<String, BoardEntity> boardData = new HashMap<>();
    int size;

    Board(int boardSize){
        this.size = boardSize;
    }

    public void addEntity(BoardEntity entity){
        try {
            if (this.boardData.get(entity.getX() + ":" + entity.getY()) != null)
                throw new TwoEntitiesOnSamePositionException();
            this.boardData.put(entity.getX() + ":" + entity.getY(), entity);
        } catch(TwoEntitiesOnSamePositionException e){
            System.out.println(e.getMessage());
        }
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
