import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Board gameBoard;


    public static void main(String[] args){
        try{
            File inputFile = new File("input.txt");
            Scanner scanner = new Scanner(inputFile);
            ArrayList<Insect> insects = new ArrayList<>();


            int D = scanner.nextInt();
            scanner.nextLine();

            int N = scanner.nextInt();
            scanner.nextLine();

            int M = scanner.nextInt();
            scanner.nextLine();

            gameBoard = new Board(D * D);

            //Insects parse/add
            for(int i = 0; i < N; i++){
                String color = scanner.next();
                String type = scanner.next();
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                EntityPosition pos = new EntityPosition(x, y);
                InsectColor insectColor = InsectColor.toColor(color);

                Insect insectForBoard = Insect.createInsect(type, pos, insectColor);
                insects.add(insectForBoard);

                try {
                    gameBoard.addEntity(insectForBoard);
                } catch(TwoEntitiesOnSamePosition e){
                    return;
                }

                scanner.nextLine();
            }

            //Food parse
            for(int i = 0; i < M; i++){
                int amount = scanner.nextInt();
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                EntityPosition pos = new EntityPosition(x, y);
                FoodPoint foodPoint = new FoodPoint(pos, amount);

                try{
                    gameBoard.addEntity(foodPoint);
                } catch(TwoEntitiesOnSamePositionExeption e){
                    return;
                }

                scanner.nextLine();
            }

            // Parse entities :3 with for
            for(Insect insect: insects){
                if(insect instanceof Ant ant){
                    ant.getBestDirection(gameBoard.boardData, D * D);
                    int maxFoodAmount = -1;
                    Direction bestDirection;
                    for(int i = 0; i < 4; i++){
                        Direction dir = Direction.toDirection(i);
                        int foodAmountDiagonal = ant.getDiagonalDirectionVisibleValue(dir, ant.entityPosition, gameBoard.boardData, D);
                        if(foodAmountDiagonal > maxFoodAmount){
                            maxFoodAmount = foodAmountDiagonal;
                            bestDirection = dir;
                        }
                    }
                    for(int i = 4; i < 8; i++){
                        Direction dir = Direction.toDirection(i);
                        int foodAmountOrthogonal = ant.getOrthogonalDirectionVisibleValue(dir, ant.entityPosition, gameBoard.boardData, D);
                        if(foodAmountOrthogonal > maxFoodAmount){
                            maxFoodAmount = foodAmountOrthogonal;
                            bestDirection = dir;
                        }
                    }



                }
            }



        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exeption e){

            if(e instanceof InvalidBoardSizeExeption)
        }
    }

}
