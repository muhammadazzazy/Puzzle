package com.muhammadazzazy.puzzle;
import java.util.ArrayList;
import java.util.Collections;

public class Puzzle {
    public static final int SIZE = 4;
    public int[] arr = new int[2];
    private final int[][] board = new int[SIZE][SIZE];

    public Puzzle(boolean flag) {
        if(!flag) {
            ArrayList<Integer> numbers = new ArrayList<>();
            for(int i = 0; i < SIZE*SIZE; i++) {
                numbers.add(i);
            }

            Collections.shuffle(numbers);

            int idx = 0;

            for(int i = 0; i < SIZE; i++) {
                for(int j = 0; j < SIZE; j++) {
                    if(numbers.get(idx) == 0) {
                        arr[0] = i;
                        arr[1] = j;
                    }
                    board[i][j] = numbers.get(idx++);
                }
            }
        } else {
            board[0][0] = 1;
            board[0][1] = 2;
            board[0][2] = 3;
            board[0][3] = 4;
            board[1][0] = 5;
            board[1][1] = 6;
            board[1][2] = 7;
            board[1][3] = 8;
            board[2][0] = 13;
            board[2][1] = 10;
            board[2][2] = 12;
            board[2][3] = 11;
            board[3][0] = 9;
            board[3][1] = 14;
            board[3][2] = 15;
            board[3][3] = 0;
            arr[0] = 3;
            arr[1] = 3;
        }

    }

    public void updateBoardData(String move) {
        int row = move.charAt(0) - 'a';
        int col = move.charAt(1) - '0' - 1;
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                if(board[i][j] == 0) {
                    int temp = board[row][col];
                    board[row][col] = 0;
                    arr[0] = row;
                    arr[1] = col;
                    board[i][j] = temp;
                }
            }
        }
    }

    public int[][] queryBoardData() {
        return board;
    }

    public boolean validateMove(String move) {
        int row = move.charAt(0) - 'a';
        int col = move.charAt(1) - '0' - 1;
        if(row >= 0 && col >= 0 && row < SIZE && col < SIZE) {
            if(board[row][col] != 0) {
                if(row == 0 && col == 0) {
                    return board[row][col+1] == 0 || board[row+1][col] == 0;
                }
                else if(row == 0 && col == SIZE-1) {
                    return board[row][col-1] == 0 || board[row+1][col] == 0;
                }
                else if(row == SIZE-1 && col == 0) {
                    return board[row][col+1] == 0 || board[row-1][col] == 0;
                }
                else if(row == SIZE-1 && col == SIZE-1) {
                    return board[row][col-1] == 0 || board[row-1][col] == 0;
                }
                else if(row == 0) {
                    return board[row][col-1] == 0 || board[row+1][col] == 0 || board[row][col+1] == 0;
                }
                else if(row < SIZE-1 && col == 0) {
                    return board[row][col+1] == 0 || board[row-1][col] == 0 || board[row+1][col] == 0;
                }
                else if(row == SIZE-1) {
                    return board[row][col-1] == 0 || board[row-1][col] == 0 || board[row][col+1] == 0;
                }
                else if(col == SIZE-1) {
                    return board[row][col-1] == 0 || board[row-1][col] == 0 || board[row+1][col] == 0;
                }
                else {
                    return board[row][col-1] == 0 || board[row][col+1] == 0 || board[row-1][col] == 0 || board[row+1][col] == 0;
                }
            }
        }
        return false;
    }

    public boolean checkSorted() {
        int count = 1;
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                if(!(i == SIZE-1 && j == SIZE-1) && board[i][j] != count) {
                    return false;
                }
                count++;
            }
        }
        return true;
    }
}


