public enum Direction {
    N("North"),
    E("East"),
    S("South"),
    W("West"),
    NE("North-East"),
    SE("South-East"),
    SW("South-West"),
    NW("North-West");
    private final String textRepresentation;

    Direction(String textRepresentation){
        this.textRepresentation = textRepresentation;
    }

    public String getDisplayName() {
        return textRepresentation;
    }

    public static boolean isOrthogonal(Direction dir){
        return dir == N || dir == E || dir == S || dir == W;
    }

    public static boolean isDiagonal(Direction dir){
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
