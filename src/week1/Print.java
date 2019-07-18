package week1;

public class Print {
    private int n = 0;
    private int m = 0;
    private int value = -1;
    private int[][] snake = null;
    public Direction nextDirection = null;

    static enum Direction {
        Right, Down, Left, Up
    }

    public Print(int n, int m, Direction direction, int model) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("n m 必须是一个正整数");
        }
        this.n = n;
        this.m = m;
        this.snake = new int[n][m];
        this.value = 1;
        nextDirection = direction;
        initArray(model);
    }


    public void initArray(int model) {
        int row = 0, col = 0;
        for (int i = 0; i < n * m; i++) {
            snake[row][col] = value;
            switch (model) {
                case 1:
                    nextDirection = judgeDirection1(row, col);
                    break;
                case 2:
                    nextDirection = judgeDirection2(row, col);
                    break;
                case 3:
                    nextDirection = judgeDirection3(row, col);
                    break;
            }


            switch (nextDirection) {
                case Right:
                    col++;
                    break;
                case Down:
                    row++;
                    break;
                case Left:
                    col--;
                    break;
                case Up:
                    row--;
                    break;
                default:
                    System.out.println("error");
            }
            value++;
        }
    }

    private Direction judgeDirection1(int row, int col) {
        Direction direction = this.nextDirection;
        switch (direction) {

            case Right:
                if ((col == m - 1) || (snake[row][col + 1] != 0)) {
                    direction = Direction.Down;
                }
                break;

            case Down:
                if ((row == n- 1) || (snake[row + 1][col] != 0)) {
                    direction = Direction.Left;
                }
                break;

            case Left:
                if ((col == 0) || snake[row][col - 1] != 0) {
                    direction = Direction.Up;
                }
                break;

            case Up:
                if (snake[row - 1][col] != 0) {
                    direction = Direction.Right;
                }
                break;
        }

        return direction;
    }

    private Direction judgeDirection2(int row, int col) {
        Direction direction = this.nextDirection;
        switch (direction) {

            case Right:
                if ((col == m - 1) || (snake[row][col + 1] != 0)) {
                    direction = Direction.Down;
                }
                break;

            case Down:
                if (col ==m- 1) {
                    direction = Direction.Left;
                }
                if (col == 0) {
                    direction = Direction.Right;
                }
                break;

            case Left:
                if (col == 0) {
                    direction = Direction.Down;
                }
                break;


        }

        return direction;
    }
    boolean flag1 =true;
    boolean flag2 =true;
    boolean flag3 =true;
    boolean flag4 =true;
    private Direction judgeDirection3(int row, int col) {
        Direction direction = this.nextDirection;
        switch (direction) {

            case Right:
                if(!flag4){
                    if(row==0){
                        direction=Direction.Down;
                        flag4=!flag4;
                    }
                }else {
                    if (col == m - 1 || snake[row][col + 1] != 0) {
                        direction = Direction.Up;
                        flag4=!flag4;
                    }
                }
                break;

            case Down:
                if ((row == n- 1) || (snake[row + 1][col] != 0)) {
                    if(flag1) {
                        direction = Direction.Right;
                        flag1 = !flag1;
                    }else {
                        direction=Direction.Left;
                        flag1 = !flag1;
                    }
                }
                break;

            case Left:
               if(flag3){
                   if(row==0) {direction=Direction.Down;
                   flag3=!flag3;}
               }else {
                   if (snake[row][col - 1] != 0)
                   {direction = Direction.Up;
                   flag3=!flag3;}
               }
                break;

            case Up:
                if (row==0 || snake[row-1][col]!=0){
                    if (flag2){
                        direction=Direction.Left;
                        flag2=!flag2;
                    }else {
                        direction=Direction.Right;
                        flag2=!flag2;
                    }
                }
                break;
        }

        return direction;
    }
    public void printSnake() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.printf("%5d", snake[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String... args) {
      /*  Print s1 = new Print(5,6,Direction.Right,1);
        s1.printSnake();

        Print s2 = new Print(5,6,Direction.Right,2);
        s2.printSnake();
        System.out.println();*/
        Print s3 = new Print(10,10,Direction.Down,3);
        s3.printSnake();


    }
}