/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eiu.cit.netprog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author ACER
 */
public abstract class AbstractBoard {

    char[] board = new char[9];
    final static int[][] winners = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7},
    {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void setBoard(char[] board) {
        this.board = board;

    }

    public void initialize() {
        for (int i = 0; i < board.length; i++) {
            board[i] = '-';
        }
    }

    public boolean checkMove(int cell) {
        return board[cell] == '-';
    }

    public char[] getBoard() {
        return board;
    }

    public int checkStatus(char player) {
        int status = 0;
        //
        for (int[] winner : winners) {
            if (checkWinner(winner, board, player)) {
                status = 1;
                break;
            }
        }
        return status;

    }

    public boolean checkWinner(int[] winner, char[] board, char player) {
        boolean check = true;
        for (int cell : winner) {
            if (board[cell] != player) {
                check = false;
                break;
            }
        }

        return check;

    }

    public int checkBoard() {
        int status = 1;
        //
        for (char c : board) {
            if (c == '-') {
                status = 0;
                break;
            }
        }
        return status;
    }

    public void updateBoard(int cell) {
        board[cell] = 'o';
    }

    public abstract void makeMove();

    public String encodeBoard() {
        StringBuilder builder = new StringBuilder();
        for (char c : board) {
            builder.append(c).append('.');
        }
        return builder.toString();

    }
}

// initialize
//    private static void initialize(char[] board) {
//        for (int i = 0; i < board.length; i++) {
//            board[i] = '-';
//        }
//    }
//
//    // check validity of move
//    private static boolean checkMove(char[] board, int cell) {
//        return board[cell] == '-';
//    }
//
//    // check status for a player
//    // 0. player has not won yet
//    // 1. player won
//    private static int checkStatus(char[] board, char player) {
//        int status = 0;
//        //
//        for (int[] winner : winners) {
//            if (checkWinner(winner, board, player)) {
//                status = 1;
//                break;
//            }
//        }
//        return status;
//
//    }
//
//    private static int checkBoard(char[] board) {
//        int status = 1;
//        //
//        for (char c : board) {
//            if (c == '-') {
//                status = 0;
//                break;
//            }
//        }
//        return status;
//
//    }
//
//    private static boolean checkWinner(int[] winner, char[] board, char player) {
//        boolean check = true;
//        for (int cell : winner) {
//            if (board[cell] != player) {
//                check = false;
//                break;
//            }
//        }
//
//        return check;
//
//    }
//
//    // update board
//    private static void updateBoard(char[] board, int cell) {
//        board[cell] = 'o';
//        // return board;
//
//    }
//
//    // make move
//    private static void makeMove(char[] board) {
//        for (int i = 0; i < board.length; i++) {
//            if (board[i] == '-') {
//                board[i] = 'x';
//                break;
//            }
//        }
//
//    }
//
//    // encoding the current board
//    private static String encodeBoard(char[] board) {
//        StringBuilder builder = new StringBuilder();
//        for (char c : board) {
//            builder.append(c).append('.');
//        }
//        return builder.toString();
//
//    }

