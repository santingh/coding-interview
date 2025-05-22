# Snake Game

A clean, modular Java implementation of the classic Snake game.

## High-Level Requirements

1. **Grid-Based Movement:** Snake moves in four directions on a fixed rows×cols grid.
2. **Growth Mechanism:** Eating food spawns a new food pellet and increases snake length by 1.
3. **Collision Detection:** Game ends if snake collides with wall boundaries or its own body (O(1) checks).
4. **Single Pellet Lifecycle:** Always one food pellet on board; respawn immediately after consumption.
5. **Score Tracking:** Score increments by 1 per pellet eaten.

## Major Trick

* Use a **`Deque<Point>`** to maintain the ordered segments of the snake body.
* Maintain a parallel **`HashSet<Point>`** for O(1) lookups to detect self-collisions and food collisions.
* On each move: add new head (`deque.addFirst`) and set entry, then remove tail (`deque.removeLast`) unless growing.

## Class Diagram

```
┌────────────────┐          ┌────────────────┐
│     Game       │1       1│     Board      │
│────────────────│◀────────▶│────────────────│
│- snake: Snake  │          │- food:Set<Point>│
│- board: Board  │          │- rows:int      │
│- score: int    │          │- cols:int      │
│+ start(): int  │          │+ generateFood()│
│+ pause(), stop()│         │+ isCollision(Point):bool│
└────────────────┘          │+ isFood(Point):bool     │
                            │+ eatFood(Point):void    │
                            └────────────────┘

┌────────────────┐          ┌─────────┐
│     Snake      │          │Point    │
│────────────────│          │─────────│
│- body:Deque<Point>      │  │- row:int      │
│- bodySet:Set<Point>     │  │- col:int      │
│+ move(dir): void        │  │+ getNext(dir):Point│
│+ grow(dir): void        │  │+ equals(), hashCode()│
│+ getHead(): Point       │  └─────────┘
│+ isBody(Point): bool    │
└────────────────┘           
         ▲                  
         │                  
    ┌─────────┐             
    │Direction│             
    │(UP,DOWN,│             
    │ LEFT,   │             
    │ RIGHT)  │             
    └─────────┘             
```

## Code Implementation

### Game.java

```java
import java.util.*;

public class Game {
    private final Snake snake;
    private final Board board;
    private int score = 0;

    public Game(int rows, int cols) {
        this.board = new Board(rows, cols);
        this.snake = new Snake(new Point(rows/2, cols/2));
    }

    public int start() {
        Direction dir = Direction.RIGHT;
        while (true) {
            // read user direction input (stub)
            Direction userDir = getUserInput();
            if (userDir != null) dir = userDir;

            Point nextHead = snake.getHead().getNextPoint(dir);
            if (board.isCollision(nextHead) || snake.isBody(nextHead)) break;

            if (board.isFood(nextHead)) {
                snake.grow(dir);
                board.eatFood(nextHead);
                score++;
            } else {
                snake.move(dir);
            }
            sleep(200); // tick rate
        }
        return score;
    }

    // stubs for I/O and timing
    private Direction getUserInput() { /* ... */ return null; }
    private void sleep(long ms) { try { Thread.sleep(ms); } catch (InterruptedException ignored) {} }
}
```

### Board.java

```java
import java.util.*;

class Board {
    private final Set<Point> food = new HashSet<>();
    private final int rows, cols;
    private final Random rand = new Random();

    public Board(int rows, int cols) {
        this.rows = rows; this.cols = cols;
        generateFood();
    }

    public void generateFood() {
        Point p;
        do {
            p = new Point(rand.nextInt(rows), rand.nextInt(cols));
        } while (food.contains(p));
        food.add(p);
    }

    public boolean isCollision(Point p) {
        return p.getRow() < 0 || p.getRow() >= rows
            || p.getCol() < 0 || p.getCol() >= cols;
    }

    public boolean isFood(Point p) {
        return food.contains(p);
    }

    public void eatFood(Point p) {
        if (food.remove(p)) generateFood();
    }
}
```

### Snake.java

```java
import java.util.*;

class Snake {
    private final Deque<Point> body = new ArrayDeque<>();
    private final Set<Point> bodySet = new HashSet<>();

    public Snake(Point init) {
        body.addFirst(init);
        bodySet.add(init);
    }

    public Point getHead() { return body.peekFirst(); }

    public void move(Direction dir) {
        Point next = getHead().getNextPoint(dir);
        body.addFirst(next);
        bodySet.add(next);
        Point tail = body.removeLast();
        bodySet.remove(tail);
    }

    public void grow(Direction dir) {
        Point next = getHead().getNextPoint(dir);
        body.addFirst(next);
        bodySet.add(next);
    }

    public boolean isBody(Point p) {
        return bodySet.contains(p);
    }
}
```

### Point.java

```java
import java.util.Objects;

class Point {
    private final int row, col;
    public Point(int row, int col) {
        this.row = row; this.col = col;
    }
    public int getRow() { return row; }
    public int getCol() { return col; }

    public Point getNextPoint(Direction dir) {
        switch (dir) {
            case UP:    return new Point(row - 1, col);
            case DOWN:  return new Point(row + 1, col);
            case LEFT:  return new Point(row, col - 1);
            case RIGHT: return new Point(row, col + 1);
            default:    throw new IllegalArgumentException("Unknown dir");
        }
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point p = (Point) o;
        return row == p.row && col == p.col;
    }

    @Override public int hashCode() {
        return Objects.hash(row, col);
    }
}
```

### Direction.java

```java
enum Direction {
    UP, DOWN, LEFT, RIGHT
}
```

### Test
```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SnakeGameTest {

    // --------- Point Tests ---------

    @Test
    void testPointEqualsAndHashCode() {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(2, 3);
        Point p3 = new Point(3, 2);

        assertEquals(p1, p2, "Points with same coords should be equal");
        assertEquals(p1.hashCode(), p2.hashCode(),
                     "Equal points must have same hashCode");
        assertNotEquals(p1, p3, "Different coords => not equal");
    }

    @Test
    void testGetNextPoint() {
        Point origin = new Point(5, 5);
        assertEquals(new Point(4, 5), origin.getNextPoint(Direction.UP));
        assertEquals(new Point(6, 5), origin.getNextPoint(Direction.DOWN));
        assertEquals(new Point(5, 4), origin.getNextPoint(Direction.LEFT));
        assertEquals(new Point(5, 6), origin.getNextPoint(Direction.RIGHT));
    }

    // --------- Snake Tests ---------

    @Test
    void testSnakeInitialState() {
        Point start = new Point(0, 0);
        Snake snake = new Snake(start);
        assertEquals(start, snake.getHead(), "Head should be at init point");
        assertTrue(snake.isBody(start), "Body set should contain the start");
    }

    @Test
    void testSnakeMove() {
        Snake snake = new Snake(new Point(2, 2));
        snake.move(Direction.RIGHT);
        assertEquals(new Point(2, 3), snake.getHead(),
                     "After moving RIGHT, head col should +1");
        // Tail should have moved too, so body size remains 1
        assertFalse(snake.isBody(new Point(2, 2)),
                    "Old head shouldn't remain in body after move");
    }

    @Test
    void testSnakeGrow() {
        Snake snake = new Snake(new Point(2, 2));
        snake.move(Direction.RIGHT);      // to (2,3)
        snake.grow(Direction.DOWN);       // to (3,3)
        assertEquals(new Point(3, 3), snake.getHead(),
                     "After grow, head at new point");
        // Body should now contain both (2,3) and (2,2)
        assertTrue(snake.isBody(new Point(2, 3)));
        assertTrue(snake.isBody(new Point(2, 2)));
    }

    @Test
    void testSnakeSelfCollisionDetection() {
        // Build a U-shape: start (2,2) → RIGHT → DOWN → LEFT
        Snake snake = new Snake(new Point(2, 2));
        snake.grow(Direction.RIGHT);  // (2,3)
        snake.grow(Direction.DOWN);   // (3,3)
        snake.grow(Direction.LEFT);   // (3,2)
        // Next UP would hit the original head at (2,2)
        Point collisionPoint = snake.getHead().getNextPoint(Direction.UP);
        assertTrue(snake.isBody(collisionPoint),
                   "Next move should collide with body");
    }

    // --------- Board Tests ---------

    @Test
    void testBoardCollision() {
        Board board = new Board(3, 4); // rows=3 (0–2), cols=4 (0–3)
        assertTrue(board.isCollision(new Point(-1, 0)));
        assertTrue(board.isCollision(new Point(0, -1)));
        assertTrue(board.isCollision(new Point(3, 1)));
        assertTrue(board.isCollision(new Point(1, 4)));
        assertFalse(board.isCollision(new Point(0, 0)));
        assertFalse(board.isCollision(new Point(2, 3)));
    }

    @Test
    void testFoodGenerationBounds() {
        Board board = new Board(10, 10);
        // We rely on the fact that generateFood() places within bounds,
        // but since food is private, just verify no exception and collision logic holds
        for (int i = 0; i < 100; i++) {
            board.generateFood();
            // we can't peek at food set directly, but ensure no invalid state via collision()
            // generateFood should never throw and leave board in valid state
            // so this loop serves as a smoke test
        }
    }
}
```
