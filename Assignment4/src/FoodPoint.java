public class FoodPoint extends BoardEntity{
    int value;

    FoodPoint(EntityPosition position, int value){
        super(position);
        this.value = value;
    }
}
