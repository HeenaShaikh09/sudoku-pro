package com.heena.sudoku.service.impl;

import org.springframework.stereotype.Service;
import com.heena.sudoku.service.SudokuService;
import com.heena.sudoku.model.SudokuRequest;
import com.heena.sudoku.model.SudokuResponse;
import com.heena.sudoku.util.SudokuSolver;

@Service
public class SudokuServiceImpl implements SudokuService {

    private final SudokuSolver sudokuSolver;

    public SudokuServiceImpl(SudokuSolver sudokuSolver) {
        this.sudokuSolver = sudokuSolver;
    }

    @Override
    public SudokuResponse solveSudoku(SudokuRequest request) {

        int[][] board = request.getBoard();

        boolean solved = sudokuSolver.solve(board);

        if (!solved) {
            return new SudokuResponse(false, board, "Sudoku cannot be solved");
        }

        return new SudokuResponse(true, board, "Sudoku solved successfully");
    }

    @Override
    public SudokuResponse generateSudoku(String difficulty) {
        // temporary empty board
        return new SudokuResponse(true, new int[9][9], "Generated");
    }
}