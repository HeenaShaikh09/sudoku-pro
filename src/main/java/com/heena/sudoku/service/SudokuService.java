package com.heena.sudoku.service;

import com.heena.sudoku.model.SudokuRequest;
import com.heena.sudoku.model.SudokuResponse;


public interface SudokuService {

    SudokuResponse solveSudoku(SudokuRequest request);

    SudokuResponse generateSudoku(String difficulty);
}