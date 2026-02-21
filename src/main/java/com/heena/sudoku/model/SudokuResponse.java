package com.heena.sudoku.model;

public class SudokuResponse {

    private boolean success;
    private int[][] board;
    private String message;

    public SudokuResponse(boolean success, int[][] board, String message) {
        this.success = success;
        this.board = board;
        this.message = message;
    }

    public boolean isSuccess() {   // ✅ THIS WAS MISSING
        return success;
    }

    public int[][] getBoard() {
        return board;
    }

    public String getMessage() {
        return message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}