package com.heena.sudoku.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SudokuGenerator {

    private static final int SIZE = 9;
    private Random random = new Random();

    public int[][] generate(String difficulty) {

        int[][] board = new int[SIZE][SIZE];

        fillBoard(board);

        int cellsToRemove = getCellsToRemove(difficulty);

        removeCells(board, cellsToRemove);

        return board;
    }

    private boolean fillBoard(int[][] board) {

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {

                if (board[row][col] == 0) {

                    List<Integer> numbers = getShuffledNumbers();

                    for (int num : numbers) {
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num;

                            if (fillBoard(board))
                                return true;

                            board[row][col] = 0;
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

    private List<Integer> getShuffledNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 9; i++) numbers.add(i);
        Collections.shuffle(numbers);
        return numbers;
    }

    private boolean isSafe(int[][] board, int row, int col, int num) {

        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num)
                return false;
        }

        int boxRow = row - row % 3;
        int boxCol = col - col % 3;

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i + boxRow][j + boxCol] == num)
                    return false;

        return true;
    }

    private void removeCells(int[][] board, int count) {

        while (count > 0) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);

            if (board[row][col] != 0) {
                board[row][col] = 0;
                count--;
            }
        }
    }

    private int getCellsToRemove(String difficulty) {
        switch (difficulty) {
            case "easy": return 35;
            case "medium": return 45;
            case "hard": return 55;
            default: return 40;
        }
    }
}