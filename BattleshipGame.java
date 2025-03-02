import java.util.Random;
import java.util.Scanner;
public class BattleshipGame {
static final int SIZE = 5;
static char[][] playerBoard = new char[SIZE][SIZE];
static char[][] computerBoard = new char[SIZE][SIZE];
static int playerShips = 3, computerShips = 3;
static Random rand = new Random();
static Scanner scanner = new Scanner(System.in);
public static void main(String[] args) {
initializeBoards();
placeShips(playerBoard, "Player");
placeShips(computerBoard, "Computer");
playGame();
}
static void initializeBoards() {
for (int i = 0; i < SIZE; i++)
for (int j = 0; j < SIZE; j++)
playerBoard[i][j] = computerBoard[i][j] = '-';
}static void placeShips(char[][] board, String owner) {
int shipsPlaced = 0;
while (shipsPlaced < 3) {
int row = rand.nextInt(SIZE), col = rand.nextInt(SIZE);
if (board[row][col] == '-') {
board[row][col] = 'S';
shipsPlaced++;
}
}
}
static void playGame() {
while (playerShips > 0 && computerShips > 0) {
printBoards();
playerTurn();
if (computerShips == 0) break;
computerTurn();
}
System.out.println(playerShips > 0 ? "You Win! " : "Computer Wins! ");
restartGame();
}
static void printBoards() {
System.out.println("\nYour Board:");
printBoard(playerBoard, true);
System.out.println("\nComputer's Board:");printBoard(computerBoard, false);
}
static void printBoard(char[][] board, boolean reveal) {
for (char[] row : board) {
for (char cell : row)
System.out.print((reveal || cell != 'S') ? cell + " " : "- ");
System.out.println();
}
}
static void playerTurn() {
int row, col;
while (true) {
System.out.print("\nEnter row (0-4) and column (0-4) to attack: ");
row = scanner.nextInt();
col = scanner.nextInt();
if (row >= 0 && row < SIZE && col >= 0 && col < SIZE && computerBoard[row]
[col] != 'X' && computerBoard[row][col] != 'O')
break;
System.out.println("Invalid move! Try again.");
}
if (computerBoard[row][col] == 'S') {
System.out.println("Hit!");
computerBoard[row][col] = 'X';
computerShips--;} else {
System.out.println("Miss!");
computerBoard[row][col] = 'O';
}
}
static void computerTurn() {
int row, col;
do {
row = rand.nextInt(SIZE);
col = rand.nextInt(SIZE);
} while (playerBoard[row][col] == 'X' || playerBoard[row][col] == 'O');
System.out.println("\nComputer attacks: (" + row + ", " + col + ")");
if (playerBoard[row][col] == 'S') {
System.out.println("Computer hit your ship!");
playerBoard[row][col] = 'X';
playerShips--;
} else {
System.out.println("Computer missed!");
playerBoard[row][col] = 'O';
}
}
static void restartGame() {
System.out.print("\nDo you want to play again? (yes/no): ");String choice = scanner.next();
if (choice.equalsIgnoreCase("yes")) {
playerShips = computerShips = 3;
initializeBoards();
placeShips(playerBoard, "Player");
placeShips(computerBoard, "Computer");
playGame();
} else {
System.out.println("Thanks for playing! ");
scanner.close();
}
}
}