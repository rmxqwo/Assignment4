import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static Board gameBoard;


    public static void main(String[] args){
        try{
            File inputFile = new File("C:\\Users\\PopCorn\\Documents\\GitHub\\Assignment4\\Assignment4\\src\\input.txt");
            Scanner scanner = new Scanner(inputFile);
            ArrayList<Insect> insects = new ArrayList<>();

            int D = scanner.nextInt();
            if (D < 4 || D > 1000) throw new InvalidBoardSizeException();
            scanner.nextLine();

            int N = scanner.nextInt();
            if (N < 1 || N > 16) throw new InvalidNumberOfInsectsException();
            scanner.nextLine();

            int M = scanner.nextInt();
            if (M < 1 || M > 200) throw new InvalidNumberOfFoodPointsException();
            scanner.nextLine();

            gameBoard = new Board(D * D);
            HashSet<String> insectKeys = new HashSet<>();

            for(int i = 0; i < N; i++){
                String color = scanner.next();
                String type = scanner.next();
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                scanner.nextLine();

                if (!color.equals("Red") && !color.equals("Green") &&
                        !color.equals("Blue") && !color.equals("Yellow"))
                    throw new InvalidInsectColorException();


                if (x < 1 || x > D || y < 1 || y > D)
                    throw new InvalidEntityPositionException();

                String insectKey = color + type;
                if (insectKeys.contains(insectKey))
                    throw new DuplicateInsectException();

                EntityPosition pos = new EntityPosition(x, y);
                InsectColor insectColor = InsectColor.toColor(color);
                Insect insectForBoard = Insect.createInsect(type, pos, insectColor);
                insects.add(insectForBoard);
                insectKeys.add(insectKey);
                gameBoard.addEntity(insectForBoard);
            }

            for(int i = 0; i < M; i++){
                int amount = scanner.nextInt();
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                scanner.nextLine();

                if (x < 1 || x > D || y < 1 || y > D)
                    throw new InvalidEntityPositionException();

                EntityPosition pos = new EntityPosition(x, y);
                FoodPoint foodPoint = new FoodPoint(pos, amount);
                gameBoard.addEntity(foodPoint);
            }


            // Parse entities :3 with forEach
            for(Insect insect: insects){
                int maxFoodAmount = -1;
                Direction bestDirection = null;

                if(insect instanceof Ant ant){
                    ant.getBestDirection(gameBoard.boardData, D);

                    for(int i = 0; i < 4; i++){
                        Direction dir = Direction.toDirection(i);
                        int foodAmountDiagonal = ant.getOrthogonalDirectionVisibleValue(dir, ant.entityPosition, gameBoard.boardData, D);
                        if(foodAmountDiagonal > maxFoodAmount){
                            maxFoodAmount = foodAmountDiagonal;
                            bestDirection = dir;
                        }
                    }

                    for(int i = 4; i < 8; i++){
                        Direction dir = Direction.toDirection(i);
                        int foodAmountOrthogonal = ant.getDiagonalDirectionVisibleValue(dir, ant.entityPosition, gameBoard.boardData, D);
                        if(foodAmountOrthogonal > maxFoodAmount){
                            maxFoodAmount = foodAmountOrthogonal;
                            bestDirection = dir;
                        }
                    }

                    if(Direction.isDiagonal(bestDirection)){
                        maxFoodAmount = ant.travelDiagonally(bestDirection, ant.entityPosition, ant.color, gameBoard.boardData, D);
                    } else{
                        maxFoodAmount = ant.travelOrthogonally(bestDirection, ant.entityPosition, ant.color, gameBoard.boardData, D);
                    }



                }
                //Spider
                else if(insect instanceof Spider spider){
                    spider.getBestDirection(gameBoard.boardData, D);

                    for(int i = 4; i < 8; i++){
                        Direction dir = Direction.toDirection(i);
                        int foodAmountDiagonal = spider.getDiagonalDirectionVisibleValue(dir, spider.entityPosition, gameBoard.boardData, D);
                        if(foodAmountDiagonal > maxFoodAmount){
                            maxFoodAmount = foodAmountDiagonal;
                            bestDirection = dir;
                        }
                    }

                    if(Direction.isDiagonal(bestDirection)){
                        maxFoodAmount = spider.travelDiagonally(bestDirection, spider.entityPosition, spider.color, gameBoard.boardData, D);
                    }

                } else if(insect instanceof Grasshopper grasshopper){
                    grasshopper.getBestDirection(gameBoard.boardData, D);

                    for(int i = 0; i < 4; i++){
                        Direction dir = Direction.toDirection(i);
                        int foodAmountDiagonal = grasshopper.getOrthogonalDirectionVisibleValue(dir, grasshopper.entityPosition, gameBoard.boardData, D);
                        if(foodAmountDiagonal > maxFoodAmount){
                            maxFoodAmount = foodAmountDiagonal;
                            bestDirection = dir;
                        }
                    }

                    if(Direction.isOrthogonal(bestDirection)){
                        maxFoodAmount = grasshopper.travelOrthogonally(bestDirection, grasshopper.entityPosition, grasshopper.color, gameBoard.boardData, D);
                    }

                } else if(insect instanceof Butterfly butterfly) {
                    butterfly.getBestDirection(gameBoard.boardData, D);

                    for (int i = 0; i < 4; i++) {
                        Direction dir = Direction.toDirection(i);
                        int foodAmountDiagonal = butterfly.getOrthogonalDirectionVisibleValue(dir, butterfly.entityPosition, gameBoard.boardData, D);
                        if (foodAmountDiagonal > maxFoodAmount) {
                            maxFoodAmount = foodAmountDiagonal;
                            bestDirection = dir;
                        }
                    }

                    if (Direction.isOrthogonal(bestDirection)) {
                        maxFoodAmount = butterfly.travelOrthogonally(bestDirection, butterfly.entityPosition, butterfly.color, gameBoard.boardData, D);
                    }
                }
                String color = insect.color.getDisplayName();
                String insectType = "";

                if(insect instanceof Ant){
                    insectType = "Ant";
                } else if(insect instanceof Butterfly){
                    insectType = "Butterfly";
                } else if(insect instanceof Grasshopper){
                    insectType = "Grasshopper";
                } else if(insect instanceof Spider){
                    insectType = "Spider";
                }
                assert bestDirection != null;
                String direction = bestDirection.getDisplayName();



                System.out.println(color + " " + insectType + " " + direction + " " + maxFoodAmount);
            }



        } catch (InvalidBoardSizeException e) {
            System.out.println(e.getMessage());
        } catch (InvalidNumberOfInsectsException e) {
            System.out.println(e.getMessage());
        } catch (InvalidNumberOfFoodPointsException e) {
            System.out.println(e.getMessage());
        } catch (InvalidInsectColorException e) {
            System.out.println(e.getMessage());
        } catch (DuplicateInsectException e) {
            System.out.println(e.getMessage());
        } catch (InvalidInsectTypeException e) {
            System.out.println(e.getMessage());
        } catch (InvalidEntityPositionException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
