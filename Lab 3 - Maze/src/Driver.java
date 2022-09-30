import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        System.out.println("^ means the start location\n" +
                "# means the goal location \n" +
                "@ means the player location\n" +
                "$ means the gold location\n" +
                "& means thief location\n");
//        Maze maze = new Maze(4, 6, new Coordinate(1, 1), new Coordinate(11, 11));

        WrappingRoomMaze maze = new WrappingRoomMaze(4, 6, new Coordinate(1, 1), new Coordinate(7, 11), 8);
        System.out.println(maze.toString());
//        maze.sanityCheck();
        //check whether it contains gold
        if(maze.getCoinCell().contains(maze.getMaze()[maze.getLocation().getX()][maze.getLocation().getY()])){
            maze.addCoin();
            System.out.println("gold cell, cumulate coin is " + maze.getCumulateCoin());
            maze.removeGold(maze.getMaze()[maze.getLocation().getX()][maze.getLocation().getY()]);
        }
        //check whether it contains thief
        if(maze.getThiefCell().contains(maze.getMaze()[maze.getLocation().getX()][maze.getLocation().getY()])){
            maze.loseCoin();
            System.out.println("thief cell, cumulate coin is " + maze.getCumulateCoin());
        }
        while (maze.getLocation().getX() != maze.getGoal().getX() || maze.getLocation().getY() != maze.getGoal().getY()){
            // getPossibleMoves
            maze.getPossibleMoves();
            Scanner myObj = new Scanner(System.in);
            Direction direction;
            System.out.println("Enter move direction");
            direction = Direction.valueOf(myObj.nextLine());
            System.out.println("move direction is: " + direction);
            //move to that direction
            maze.move(direction);
            //print the updated maze
            System.out.println(maze.toString());
        }
        System.out.println("Achieved the goal location");
    }
}
