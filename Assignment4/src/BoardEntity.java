public abstract class BoardEntity{
    EntityPosition entityPosition;

    BoardEntity(EntityPosition entityPosition) {
        this.entityPosition = entityPosition;
    }

    public int getX(){
        return entityPosition.x;
    }

    public int getY(){
        return entityPosition.y;
    }

//    BoardEntity(int boardSize, EntityPosition entityPosition) {
//        super(boardSize);
//        this.entityPosition = entityPosition;
//    }
}
