import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Game extends Application {
    
    // kích thước màn hình
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    
    // kích thước của người chơi và vị trí ban đầu
    private static final int PLAYER_WIDTH = 50;
    private static final int PLAYER_HEIGHT = 100;
    private static final int PLAYER_START_X = 100;
    private static final int PLAYER_START_Y = SCREEN_HEIGHT / 2 - PLAYER_HEIGHT / 2;
    
    // các phím điều khiển và hành động
    private static final String UP_CONTROL = "W";
    private static final String DOWN_CONTROL = "S";
    private static final String LEFT_CONTROL = "A";
    private static final String RIGHT_CONTROL = "D";
    private static final String PUNCH_ACTION = "J";
    private static final String KICK_ACTION = "K";
    private static final String DODGE_ACTION = "L";
    private static final String BLOCK_ACTION = "U";
    private static final String HEADBUTT_ACTION = "H";
    
    // vị trí của hai người chơi và AI
    private double player1X;
    private double player1Y;
    private double player2X;
    private double player2Y;
    private double aiX;
    private double aiY;
    
    // các biến khác
    private boolean isGameOver = false;
    private boolean isPlayer1Winner = false;
    
    @Override
    public void start(Stage primaryStage) {
        
        // tạo canvas và context để vẽ
        Canvas canvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        // tạo nhóm để chứa canvas
        Group root = new Group();
        root.getChildren().add(canvas);
        
        // tạo scene và đặt tiêu đề
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        primaryStage.setTitle("Đối kháng với máy");
        primaryStage.setScene(scene);
        
        // hiển thị cửa sổ
        primaryStage.show();
        
        // khởi tạo vị trí của các người chơi và AI
        player1X = PLAYER_START_X;
        player1Y = PLAYER_START_Y;
        player2X = SCREEN_WIDTH - PLAYER_START_X - PLAYER_WIDTH;
        player2Y = PLAYER_START_Y;
        aiX = SCREEN_WIDTH / 2;
        aiY = SCREEN_HEIGHT / 2 - PLAYER_HEIGHT / 2;
        
        // vòng lặp game
        while (!isGameOver) {
            
            // vẽ các thành phần của game
            draw(gc);
            
            // xử lý các sự kiện bàn phím
            handleInput();
            
            // di chuyển người chơi và AI
            movePlayers();
            moveAI();
            
            // kiểm tra va chạm và xử lý hành động tấn công/phòng thủ
            checkCollision();
            
            // Kiểm tra điều kiện thắng
            if (player1Score >= WINNING_SCORE) {
            System.out.println("Player 1 thắng cuộc!");
            stopGameLoop();
            } else if (player2Score >= WINNING_SCORE) {
            System.out.println("Player 2 thắng cuộc!");
            stopGameLoop();
            }
public class Player {
    private double x;
    private double y;
    private double width;
    private double height;
    private double speed;
    private Color color;
    public Player(double x, double y, double width, double height, double speed, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.color = color;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x, y, width, height);
    }

    public void moveUp() {
        y -= speed;
    }

    public void moveDown() {
        y += speed;
    }

    public void moveLeft() {
        x -= speed;
    }

    public void moveRight() {
        x += speed;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean isCollidingWith(Player other) {
        return x < other.getX() + other.getWidth() && x + width > other.getX()
                && y < other.getY() + other.getHeight() && y + height > other.getY();
    }
}

public class AI {
    private double x;
    private double y;
    private double width;
    private double height;
    private double speed;
    private Color color;

    public AI(double x, double y, double width, double height, double speed, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.color = color;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x, y, width, height);
    }

    public void moveTowards(double targetX, double targetY) {
        double dx = targetX - x;
        double dy = targetY - y;
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance > 0) {
            x += speed * dx / distance;
            y += speed * dy / distance;
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public boolean isCollidingWith(Player player) {
        return x < player.getX() + player.getWidth() && x + width > player.getX()
                && y < player.getY() + player.getHeight() && y + height > player.getY();
    }
}

public class FightingGame extends Application {

    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 400;

    private static final int PLAYER_WIDTH = 50;
    private static final int PLAYER_HEIGHT = 100;
    private static final int PLAYER_SPEED = 5;

    private static final int AI_WIDTH = 50;
    private static final int AI_HEIGHT = 100;
    private static final int AI_SPEED = 3;

    private static final int ATTACK_RANGE = 20;
    private static final int DEFENSE_RANGE = 40;

    private static final Color PLAYER_COLOR = Color.BLUE;
    private static final Color AI_COLOR = Color.RED;

    private static final KeyCode[] playerControls = { KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D,
            KeyCode.J, KeyCode.K, KeyCode.L, KeyCode.U, KeyCode.H };

    private static final KeyCode[] aiControls = { KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT,
            KeyCode.NUMPAD1, KeyCode.NUMPAD2, KeyCode.NUMPAD3, KeyCode.NUMPAD4, KeyCode.NUMPAD5 };

    private static int playerX;
    private static int playerY;
    private static int aiX;
    private static int aiY;
    private static int playerHealth = 100;
    private static int aiHealth = 100;
    private static boolean playerAttacking = false;
    private static boolean playerDefending = false;
    private static boolean aiAttacking = false;
    private static boolean aiDefending = false;

    private static ArrayList<KeyCode> playerPressedKeys = new ArrayList<>();
    private static ArrayList<KeyCode> aiPressedKeys = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        scene.setOnKeyPressed(e -> {
            KeyCode keyCode = e.getCode();
            if (!playerPressedKeys.contains(keyCode)) {
                playerPressedKeys.add(keyCode);
            }
        });

        scene.setOnKeyReleased(e -> {
            KeyCode keyCode = e.getCode();
            playerPressedKeys.remove(keyCode);
        });

        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {

                // Player movement
                if (playerPressedKeys.contains(KeyCode.W)) {
                    playerY -= PLAYER_SPEED;
                }
                if (playerPressedKeys.contains(KeyCode.S)) {
                    playerY += PLAYER_SPEED;
                }
                if (playerPressedKeys.contains(KeyCode.A)) {
                    playerX -= PLAYER_SPEED;
                }
                if (playerPressedKeys.contains(KeyCode.D)) {
                    playerX += PLAYER_SPEED;
                }

                // AI movement
                if (aiPressedKeys.contains(KeyCode.UP)) {
                    aiY -= AI_SPEED;
                }
                if (aiPressedKeys.contains(KeyCode.DOWN)) {
                    aiY += AI_SPEED;
                }
                if (aiPressedKeys.contains(KeyCode.LEFT)) {
                    aiX -= AI_SPEED;
                }
                if (aiPressedKeys.contains(KeyCode.RIGHT)) {
                    aiX += PLAYER_SPEED;
                } else if (aiPressedKeys.contains(KeyCode.LEFT)) {
                    aiX -= PLAYER_SPEED;
                }
                
                // Check for collision with AI
                if (player.intersects(ai.getBoundsInLocal())) {
                    // Player wins
                    winnerLabel.setText("Player wins!");
                    winnerLabel.setVisible(true);
                    gameState = GameState.GAME_OVER;
                } else if (player.getLayoutX() >= CANVAS_WIDTH) {
                    // Player goes off the screen on the right side
                    winnerLabel.setText("AI wins!");
                    winnerLabel.setVisible(true);
                    gameState = GameState.GAME_OVER;
                } else if (ai.getLayoutX() + PLAYER_WIDTH <= 0) {
                    // AI goes off the screen on the left side
                    winnerLabel.setText("Player wins!");
                    winnerLabel.setVisible(true);
                    gameState = GameState.GAME_OVER;
                }
            }
        }
    }
}

