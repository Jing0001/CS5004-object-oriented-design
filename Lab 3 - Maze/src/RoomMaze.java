import java.util.ArrayList;
import java.util.Collections;

public class RoomMaze extends Maze{
    private final int numRemainingWall;
    public RoomMaze(int row, int col, Coordinate start, Coordinate goal, int numRemainingWall) {
        super(row, col, start, goal);
        this.numRemainingWall = numRemainingWall;
        if (numRemainingWall < 0 || numRemainingWall > (col * (row - 1) + row * (col - 1) - col * row + 1)){
            throw new IllegalArgumentException("Invalid Remaining Wall");
        }
        removeWall();
    }

    public int getNumRemainingWall() {
        return numRemainingWall;
    }

    @Override
    public void buildWall() {
        //add all wall's coordinate to getWalls()
        for (int r = 1; r < getMazeRow(); r++){
            for(int c = 1; c < getMazeCol(); c++){
                if (getMaze()[r][c] == "-" || getMaze()[r][c] == "|"){
                    getWalls().add(new Coordinate(r,c));
                }
            }
        }
    }

    public void removeWall(){
        Collections.shuffle(getSavedWalls());
        int totalRemainingWall = getSavedWalls().size();
        for(int i = 0; i < totalRemainingWall - getNumRemainingWall(); i++){
            int r = getSavedWalls().get(getSavedWalls().size() - 1).getX();
            int c = getSavedWalls().get(getSavedWalls().size() - 1).getY();
            getMaze()[r][c] = " ";
            getSavedWalls().remove(getSavedWalls().size() - 1);
        }
    }

    public static void main(String[] args) {
        RoomMaze maze = new RoomMaze(4, 6, new Coordinate(6, 6), new Coordinate(12, 12), 8);
        System.out.println(maze.toString());
    }
}
