import java.util.ArrayList;
import java.util.Collections;

public class WrappingRoomMaze extends RoomMaze{
    public WrappingRoomMaze(int row, int col, Coordinate start, Coordinate goal, int numRemainingWall) {
        super(row, col, start, goal, numRemainingWall);
    }

    @Override
    public void buildMaze(){
        Collections.shuffle(getWalls());
        for (int i = 0; i < getWalls().size(); i++){
            int r = getWalls().get(i).getX();
            int c = getWalls().get(i).getY();
            if (getMaze()[r][c] == "-"){
                //check upRoomSet and downRoomSet
                int upRoomSet = getCellToSet().get(getUpRoom(r, c)); ;
                int downRoomSet = getCellToSet().get(getDownRoom(r, c));
                if (upRoomSet != downRoomSet) {
                    //remove this wall
                    if (r == 0){
                        getMaze()[getMazeRow() - 1][c] = " ";
                    }
                    if (r == getMazeRow() - 1){
                        getMaze()[0][c] = " ";
                    }
                    getMaze()[r][c] = " ";
                    for (String key : getCellToSet().keySet()){
                        if (getCellToSet().get(key) == upRoomSet) {
                            getCellToSet().put(key, downRoomSet);
                        }
                    }
                }
                else{
                    //keep this wall and add it's coordinate to savedWalls
                    getSavedWalls().add(new Coordinate(r,c));
                }
            }
            if (getMaze()[r][c] == "|"){
                int leftRoomSet = getCellToSet().get(geLeftRoom(r, c));
                int rightRoomSet = getCellToSet().get(getRightRoom(r, c));
                //check leftRoomSet and rightRoomSet
                if (leftRoomSet != rightRoomSet) {
                    if (c == 0){
                        getMaze()[r][getMazeCol() - 1] = " ";
                    }
                    if (c == getMazeCol() - 1){
                        getMaze()[r][0] = " ";
                    }
                    getMaze()[r][c] = " ";
                    for (String key : getCellToSet().keySet()){
                        if (getCellToSet().get(key) == leftRoomSet) {
                            getCellToSet().put(key, rightRoomSet);
                        }
                    }
                }
                else{
                    //keep this wall and add it's coordinate to savedWalls
                    getSavedWalls().add(new Coordinate(r,c));
                }
            }
        }
    }
    @Override
    public void removeWall(){
        Collections.shuffle(getSavedWalls());
        int totalRemainingWall = getSavedWalls().size();
        for(int i = 0; i < totalRemainingWall - getNumRemainingWall(); i++){
            int r = getSavedWalls().get(getSavedWalls().size() - 1).getX();
            int c = getSavedWalls().get(getSavedWalls().size() - 1).getY();
            if (r == 0){
                getMaze()[getMazeRow() - 1][c] = " ";
            }
            if (r == getMazeRow() - 1){
                getMaze()[0][c] = " ";
            }
            if (c == 0){
                getMaze()[r][getMazeCol() - 1] = " ";
            }
            if (c == getMazeCol() - 1){
                getMaze()[r][0] = " ";
            }
            getMaze()[r][c] = " ";
            getSavedWalls().remove(getSavedWalls().size() - 1);
        }
    }

    @Override
    public String getUpRoom(int r, int c){
        if (r == 0){
            return getMaze()[getMazeRow() - 2][c];
        }
        else if (r == getMazeRow() - 1){
            return getMaze()[r - 1][c];
        }
        else{
            return getMaze()[r - 1][c];
        }
    }
    @Override
    public String getDownRoom(int r, int c){
        if (r == 0){
            return getMaze()[r + 1][c];
        }
        else if (r == getMazeRow() - 1){
            return getMaze()[1][c];
        }
        else{
            return getMaze()[r + 1][c];
        }
    }

    @Override
    public String geLeftRoom(int r, int c){
        if (c == 0){
            return getMaze()[r][getMazeCol() - 2];
        }
        else if (c == getMazeCol() - 1){
            return getMaze()[r][c - 1];
        }
        else{
            return getMaze()[r][c - 1];
        }
    }
    @Override
    public String getRightRoom(int r, int c){
        if (c == 0){
            return getMaze()[r][c + 1];
        }
        else if (c == getMazeCol() - 1){
            return getMaze()[r][1];
        }
        else{
            return getMaze()[r][c + 1];
        }
    }

    @Override
    public ArrayList getPossibleMoves(){
        int row = getLocation().getX();
        int col = getLocation().getY();
        ArrayList<Direction> moves = new ArrayList<>();
        if (getMaze()[row - 1][col].equals(" ")){
            moves.add(Direction.NORTH);
        }
        if (row == 0) {
            moves.add(Direction.NORTH);
        }
        if (getMaze()[row + 1][col].equals(" ")){
            moves.add(Direction.SOUTH);
        }
        if (row == getRow() - 1) {
            moves.add(Direction.SOUTH);
        }
        if (getMaze()[row][col + 1].equals(" ")){
            moves.add(Direction.EAST);
        }
        if (col == getCol() - 1) {
            moves.add(Direction.EAST);
        }
        if (getMaze()[row][col - 1].equals(" ")){
            moves.add(Direction.WEST);
        }
        if (col == 0) {
            moves.add(Direction.WEST);
        }
        System.out.println(moves);
        return moves;
    }
    @Override
    public void move(Direction direction){
        String northCell;
        switch (direction) {
            case NORTH:
                //check whether direction is valid
                if (getLocation().getX() == 1){
                    northCell = getMaze()[getMazeRow() - 2][getLocation().getY()];
                    //update the player's getLocation()
                    getLocation().setX(getMazeRow() - 2);
                }
                else{
                    northCell = getMaze()[getLocation().getX() - 2][getLocation().getY()];
                    //check whether there's a wall
                    if (getMaze()[getLocation().getX() - 1][getLocation().getY()].equals("-")){
                        throw new IllegalArgumentException("Invalid Direction");
                    }
                    //update the player's getLocation()
                    getLocation().setX(getLocation().getX() - 2);
                }
                //check whether it contains gold
                if(getCoinCell().contains(northCell)){
                    addCoin();
                }
                //check whether it contains thief
                if(getThiefCell().contains(northCell)){
                    loseCoin();
                }
                break;
            case SOUTH:
                String southCell;
                //check whether direction is valid
                if (getLocation().getX() == getMazeRow() - 2){
                    southCell = getMaze()[1][getLocation().getY()];
                    //update the player's getLocation()
                    getLocation().setX(1);
                }
                else{
                    southCell = getMaze()[getLocation().getX() + 2][getLocation().getY()];
                    //check whether there's a wall
                    if (getMaze()[getLocation().getX() + 1][getLocation().getY()].equals("-")){
                        throw new IllegalArgumentException("Invalid Direction");
                    }
                    //update the player's getLocation()
                    getLocation().setX(getLocation().getX() + 2);
                }
                //check whether it contains gold
                if(getCoinCell().contains(southCell)){
                    addCoin();
                }
                //check whether it contains thief
                if(getThiefCell().contains(southCell)){
                    loseCoin();
                }
                break;
            case EAST:
                String eastCell;
                //check whether direction is valid
                if (getLocation().getX() == getMazeCol() - 2){
                    eastCell = getMaze()[getLocation().getX()][1];
                    //update the player's getLocation()
                    getLocation().setY(1);
                }
                else{
                    eastCell = getMaze()[getLocation().getX()][getLocation().getY() + 2];
                    //check whether there's a wall
                    if (getMaze()[getLocation().getX()][getLocation().getY() + 1].equals("|")){
                        throw new IllegalArgumentException("Invalid Direction");
                    }
                    //update the player's getLocation()
                    getLocation().setY(getLocation().getY() + 2);
                }
                //check whether it contains gold
                if(getCoinCell().contains(eastCell)){
                    addCoin();
                }
                //check whether it contains thief
                if(getThiefCell().contains(eastCell)){
                    loseCoin();
                }
                break;
            case WEST:
                String westCell;
                //check whether direction is valid
                if (getLocation().getX() == 1){
                    westCell = getMaze()[getLocation().getX()][getMazeCol() - 2];
                    //update the player's getLocation()
                    getLocation().setY(getMazeCol() - 2);
                }
                else{
                    westCell = getMaze()[getLocation().getX()][getLocation().getY() - 2];
                    //check whether there's a wall
                    if (getMaze()[getLocation().getX()][getLocation().getY() - 1].equals("|")){
                        throw new IllegalArgumentException("Invalid Direction");
                    }
                    //update the player's getLocation()
                    getLocation().setY(getLocation().getY() - 2);
                }
                //check whether it contains gold
                if(getCoinCell().contains(westCell)){
                    addCoin();
                }
                //check whether it contains thief
                if(getThiefCell().contains(westCell)){
                    loseCoin();
                }
                break;
        }
    }
}
