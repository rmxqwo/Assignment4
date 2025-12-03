import javax.swing.text.html.parser.Entity;
import java.util.HashMap;

abstract class Insect extends BoardEntity{
    protected InsectColor color;

    Insect(EntityPosition position, InsectColor color){
        super(position);
        this.color = color;
    }

    public static Insect createInsect(String type, EntityPosition pos, InsectColor color) throws InvalidInsectTypeException {
        return switch (type.toLowerCase()) {
            case "ant" -> new Ant(pos, color);
            case "butterfly" -> new Butterfly(pos, color);
            case "spider" -> new Spider(pos, color);
            case "grasshopper" -> new Grasshopper(pos, color);
            default -> throw new InvalidInsectTypeException();
        };
    }


    abstract Direction getBestDirection(HashMap<String, BoardEntity> boardData, int boardSize);
    abstract int travelDirection(Direction dir, HashMap<String, BoardEntity> boardData, int boardSize);
}
