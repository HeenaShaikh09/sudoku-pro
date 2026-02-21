package com.heena.sudoku.controller;

import com.heena.sudoku.model.SudokuRequest;
import com.heena.sudoku.model.SudokuResponse;
import com.heena.sudoku.service.SudokuService;
import com.heena.sudoku.service.SudokuGenerator;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/sudoku")
@CrossOrigin
public class SudokuController {

    private final SudokuService sudokuService;
    private final SudokuGenerator sudokuGenerator;

    // Constructor Injection (Industry Standard)
    public SudokuController(SudokuService sudokuService,
                            SudokuGenerator sudokuGenerator) {
        this.sudokuService = sudokuService;
        this.sudokuGenerator = sudokuGenerator;
    }

    @PostMapping("/solve")
    public ResponseEntity<SudokuResponse> solve(@RequestBody SudokuRequest request) {

        if (request.getBoard() == null || request.getBoard().length != 9) {
            return ResponseEntity.badRequest()
                    .body(new SudokuResponse(false, null, "Invalid board size"));
        }

        SudokuResponse response = sudokuService.solveSudoku(request);

        if (!response.isSuccess()) {
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/generate")
    public ResponseEntity<Map<String, Object>> generate(
            @RequestParam String difficulty) {

        int[][] puzzle = sudokuGenerator.generate(difficulty);

        Map<String, Object> response = new HashMap<>();
        response.put("board", puzzle);
        response.put("difficulty", difficulty);

        return ResponseEntity.ok(response);
    }
}