package com.heena.sudoku.util;

import org.springframework.stereotype.Component;

@Component
public class SudokuSolver {

    public boolean solve(int[][] board) {

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                if (board[row][col] == 0) {

                    for (int num = 1; num <= 9; num++) {

                        if (isValid(board, row, col, num)) {

                            board[row][col] = num;

                            if (solve(board)) {
                                return true;
                            }

                            board[row][col] = 0;
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

    private boolean isValid(int[][] board, int row, int col, int num) {

        for (int i = 0; i < 9; i++) {

            if (board[row][i] == num) return false;
            if (board[i][col] == num) return false;

            int subRow = 3 * (row / 3) + i / 3;
            int subCol = 3 * (col / 3) + i % 3;

            if (board[subRow][subCol] == num) return false;
        }

        return true;
    }
}