import java.util.*;

public class Maze {
    private int row;
    private int col;
    private Coordinate start;
    private Coordinate goal;
    private HashMap<String, Integer> cellToSet;
    private HashSet<String> coinCell;
    private HashSet<String> thiefCell;
    private int mazeRow;
    private int mazeCol;
    private String[][] maze;
    private ArrayList<Coordinate> walls = new ArrayList<Coordinate>();
    private ArrayList<Coordinate> savedWalls = new ArrayList<Coordinate>();
    private Coordinate location;
    private int cumulateCoin = 0;

    public Maze(int row, int col, Coordinate start, Coordinate goal){
        this.row = row;
        this.col = col;
        this.start = start;
        this.goal = goal;
        if (start.getX() == goal.getX() && start.getY() == goal.getY()){
            throw new IllegalArgumentException("Start and Goal cannot be the same");
        }
        location = new Coordinate(start.getX(), start.getY());
        mazeRow = 2 * row + 1;
        mazeCol = 2 * col + 1;
        cellToSet = new HashMap<>();
        //initialize maze
        maze = new String[mazeRow][mazeCol];
        int counter;
        counter = 0;
        //each row
        for (int r = 0; r < (mazeRow - 1); r += 2){
            //create first row +-+-+-+-+-+-+
            for (int c = 0; c < (mazeCol - 1); c += 2){
                maze[r][c] = "+";
                maze[r][c + 1] = "-";
            }
            maze[r][mazeCol - 1] = "+";
            //second row |0|1|2|3|4|5|
            for (int c = 0; c < (mazeCol - 1); c += 2){
                maze[r + 1][c] = "|";
                maze[r + 1][c + 1] = String.valueOf(counter);
                cellToSet.put(String.valueOf(counter), counter);
                counter++;
            }
            maze[r + 1][mazeCol - 1] = "|";
        }
        //complete the last row +-+-+-+-+-+-+
        for (int c = 0; c < (mazeCol - 1); c += 2){
            maze[mazeRow - 1][c] = "+";
            maze[mazeRow - 1][c + 1] = "-";
        }
        maze[mazeRow - 1][mazeCol - 1] = "+";
        buildWall();
        buildMaze();
        goldLocation();
        thiefLocation();
    }

    public int getCol() {
        return col;
    }
    public int getRow() {
        return row;
    }
    public Coordinate getStart(){return start; }
    public Coordinate getGoal(){return goal; }
    public int getMazeRow(){
        return mazeRow;
    }
    public int getMazeCol(){
        return mazeCol;
    }
    public String[][] getMaze(){
        return maze;
    }
    public Coordinate getLocation(){
        return location;
    }
    public HashMap<String, Integer> getCellToSet(){
        return cellToSet;
    }
    public HashSet<String> getCoinCell(){
        return coinCell;
    }
    public HashSet<String> getThiefCell(){
        return thiefCell;
    }
    public ArrayList<Coordinate> getWalls(){
        return walls;
    }
    public ArrayList<Coordinate> getSavedWalls(){
        return savedWalls;
    }
    public int getCumulateCoin(){return cumulateCoin; }

    public int addCoin(){
        cumulateCoin += 10;
        return cumulateCoin;
    }
    public int loseCoin(){
        cumulateCoin = (int) (cumulateCoin * 0.9);
        return cumulateCoin;
    }

    public void buildWall(){
        //add inside wall's coordinate to walls
        for (int r = 1; r < mazeRow - 1; r++){
            for(int c = 1; c < mazeCol - 1; c++){
                if (maze[r][c] == "-" || maze[r][c] == "|"){
                    walls.add(new Coordinate(r,c));
                }
            }
        }
    }

    public String getUpRoom(int r, int c){
        return maze[r - 1][c];
    }
    public String getDownRoom(int r, int c){
        return maze[r + 1][c];
    }
    public String geLeftRoom(int r, int c){
        return maze[r][c - 1];
    }
    public String getRightRoom(int r, int c){
        return maze[r][c + 1];
    }
    //build maze and collect the saved walls
    public void buildMaze(){
        Collections.shuffle(walls);
        for (int i = 0; i < walls.size(); i++){
            int r = walls.get(i).getX();
            int c = walls.get(i).getY();
            if (maze[r][c] == "-"){
                //check upRoomSet and downRoomSet
                int upRoomSet = cellToSet.get(getUpRoom(r, c)); ;
                int downRoomSet = cellToSet.get(getDownRoom(r, c));
                if (upRoomSet != downRoomSet) {
                    //remove this wall
                    maze[r][c] = " ";
                    for (String key : cellToSet.keySet()){
                        if (cellToSet.get(key) == upRoomSet) {
                            cellToSet.put(key, downRoomSet);
                        }
                    }
                }
                else{
                    //keep this wall and add it's coordinate to savedWalls
                    savedWalls.add(new Coordinate(r,c));
                }
            }
            if (maze[r][c] == "|"){
                int leftRoomSet = cellToSet.get(geLeftRoom(r, c));
                int rightRoomSet = cellToSet.get(getRightRoom(r, c));
                //check leftRoomSet and rightRoomSet
                if (leftRoomSet != rightRoomSet) {
                    maze[r][c] = " ";
                    for (String key : cellToSet.keySet()){
                        if (cellToSet.get(key) == leftRoomSet) {
                            cellToSet.put(key, rightRoomSet);
                        }
                    }
                }
                else{
                    //keep this wall and add it's coordinate to savedWalls
                    savedWalls.add(new Coordinate(r,c));
                }
            }
        }
    }

    public void sanityCheck(){
        if(savedWalls.size() ==  walls.size() - row * col + 1){
            System.out.println("true");
        }
        else{
            System.out.println("false");
        }
    }

    public HashSet<String> goldLocation(){
        ArrayList<String> keys = new ArrayList(cellToSet.keySet());
        Collections.shuffle(keys);
        coinCell = new HashSet<String>();
        for(int i = 0; i < 0.2 * cellToSet.size(); i++){
            coinCell.add(keys.get(i));
        }
        return coinCell;
    }

    public void removeGold(String goldCell){
        coinCell.remove(goldCell);
    }

    public void thiefLocation(){
        ArrayList<String> keys = new ArrayList(cellToSet.keySet());
        Collections.shuffle(keys);
        thiefCell = new HashSet<String>();
        for(int i = 0; i < 0.1 * cellToSet.size(); i++){
            thiefCell.add(keys.get(i));
        }
    }

    public ArrayList getPossibleMoves(){
        int row = location.getX();
        int col = location.getY();
        ArrayList<Direction> moves = new ArrayList<>();
        if (maze[row - 1][col].equals(" ")){
            moves.add(Direction.NORTH);
        }
        if (maze[row + 1][col].equals(" ")){
            moves.add(Direction.SOUTH);
        }
        if (maze[row][col + 1].equals(" ")){
            moves.add(Direction.EAST);
        }
        if (maze[row][col - 1].equals(" ")){
            moves.add(Direction.WEST);
        }
        System.out.println("Possible Moves: " + moves);
        return moves;
    }

    public void move(Direction direction){
        switch (direction) {
            case NORTH:
                //check whether direction is valid
                if (getLocation().getX() == 1){
                    throw new IllegalArgumentException("Invalid Direction");
                }
                String northCell = maze[location.getX() - 2][location.getY()];
                //check whether there's a wall
                if (maze[location.getX() - 1][location.getY()].equals("-")){
                    throw new IllegalArgumentException("Invalid Direction");
                }
                else{
                    //update the player's location
                    location.setX(location.getX() - 2);
                    //check whether it contains gold
                    if(coinCell.contains(northCell)){
                        addCoin();
                        System.out.println("gold cell, cumulate coin is " + cumulateCoin);
                        removeGold(maze[location.getX()][location.getY()]);
                    }
                    //check whether it contains thief
                    if(thiefCell.contains(northCell)){
                        loseCoin();
                        System.out.println("thief cell, cumulate coin is " + cumulateCoin);
                    }
                }
                break;
            case SOUTH:
                if (getLocation().getX() == getMazeRow() - 2){
                    throw new IllegalArgumentException("Invalid Direction");
                }
                String southCell = maze[location.getX() + 2][location.getY()];
                if (maze[location.getX() + 1][location.getY()].equals("-")){
                    throw new IllegalArgumentException("Invalid Direction");
                }
                else{
                    location.setX(location.getX() + 2);
                    //check whether it contains gold
                    if(coinCell.contains(southCell)){
                        addCoin();
                        System.out.println("gold cell, cumulate coin is " + cumulateCoin);
                        removeGold(maze[location.getX()][location.getY()]);
                    }
                    //check whether it contains thief
                    if(thiefCell.contains(southCell)){
                        loseCoin();
                        System.out.println("thief cell, cumulate coin is " + cumulateCoin);
                    }
                }
                break;
            case EAST:
                if (getLocation().getY() == getMazeCol() - 2){
                    throw new IllegalArgumentException("Invalid Direction");
                }
                String eastCell = maze[location.getX()][location.getY() + 2];
                if (maze[location.getX()][location.getY() + 1].equals("|")){
                    throw new IllegalArgumentException("Invalid Direction");
                }
                else{
                    location.setY(location.getY() + 2);
                    //check whether it contains gold
                    if(coinCell.contains(eastCell)){
                        addCoin();
                        System.out.println("gold cell, cumulate coin is " + cumulateCoin);
                        removeGold(maze[location.getX()][location.getY()]);
                    }
                    //check whether it contains thief
                    if(thiefCell.contains(eastCell)){
                        loseCoin();
                        System.out.println("thief cell, cumulate coin is " + cumulateCoin);
                    }
                }
                break;
            case WEST:
                if (getLocation().getY() == 1){
                    throw new IllegalArgumentException("Invalid Direction");
                }
                String westCell = maze[location.getX()][location.getY() - 2];
                if (maze[location.getX()][location.getY() - 1].equals("|")){
                    throw new IllegalArgumentException("Invalid Direction");
                }
                else{
                    location.setY(location.getY() - 2);
                    //check whether it contains gold
                    if(coinCell.contains(westCell)){
                        addCoin();
                        System.out.println("gold cell, cumulate coin is " + cumulateCoin);
                        removeGold(maze[location.getX()][location.getY()]);
                    }
                    //check whether it contains thief
                    if(thiefCell.contains(westCell)){
                        loseCoin();
                        System.out.println("thief cell, cumulate coin is " + cumulateCoin);
                    }
                }
                break;
        }
    }
    @Override
    public String toString(){
        String mazeGraph = "";
        for (int r = 0; r < mazeRow; r++){
            for (int c = 0; c < mazeCol; c++) {
                //print wall
                if (maze[r][c].equals("+") || maze[r][c].equals("|")){
                    mazeGraph += maze[r][c];
                }
                else if (r % 2 == 0 && maze[r][c].equals("-")){
                    mazeGraph += "----";
                }
                else if (r % 2 == 0 && maze[r][c].equals(" ")){
                    mazeGraph += "    ";
                }
                else if (r % 2 != 0 && maze[r][c].equals(" ")){
                    mazeGraph += " ";
                } else {
                    // cells
                    String cell = "";
                    //print start location
                    if (start.getX() == r && start.getY() == c){
                        cell += "^";
                    }
                    //print goal location
                    if (goal.getX() == r && goal.getY() == c){
                        cell += "#";
                    }
                    //print player location
                    if (location.getX() == r && location.getY() == c){
                        cell += "@";
                    }
                    if (coinCell.contains(maze[r][c])){
                        cell += "$";
                    }
                    if (thiefCell.contains(maze[r][c])){
                        cell += "&";
                    }
                    cell = cell.format("%" + 4 + "." + 4 + "s", cell);
                    mazeGraph += cell;
                }
            }
            mazeGraph += "\n";
        }
        return mazeGraph;
    }

    public static void main(String[] args) {
        Maze maze = new Maze(4, 6, new Coordinate(1, 1), new Coordinate(7, 11));
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
        while (maze.location.getX() != maze.goal.getX() || maze.location.getY() != maze.goal.getY()){
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
