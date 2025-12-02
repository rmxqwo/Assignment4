public enum Direction {
    N("North"),
    E("East"),
    S("South"),
    W("West"),
    NE("NorthEast"),
    SE("SouthEast"),
    SW("SouthWest"),
    NW("NorthWest");
    String textRepresentation;

    Direction(String text){

    }
    public boolean isOrthogonal(Direction dir){
        return dir == N || dir == E || dir == S || dir == W;
    }

    public boolean isDiagonal(Direction dir){
        return dir == NE || dir == SE || dir == SW || dir == NW;
    }

    public static Direction toDirection(int num){
        return switch (num) {
            case (0) -> N;
            case (1) -> E;
            case (2) -> S;
            case (3) -> W;
            case (4) -> NE;
            case (5) -> SE;
            case (6) -> SW;
            case (7) -> NW;
            default -> throw new IllegalStateException("Unexpected value: " + num);
        };
    }
}
