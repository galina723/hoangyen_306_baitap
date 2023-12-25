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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
public class TicTacToeServerThread_ClientStrategy extends Thread {

    private final static int PORT = 10;
    private final int[][] winners = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7},
    {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    public AbstractBoard board;
    private final Socket connection;

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                Socket connection = server.accept();
                Thread task = new TicTacToeServerThread_ClientStrategy(connection);
                task.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(TicTacToeServerThread_ClientStrategy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TicTacToeServerThread_ClientStrategy(Socket connection) {
        this.connection = connection;
    }

    public void run() {
        try {
            BufferedReader readBoard = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String strategy = readBoard.readLine();
            if (strategy.equals("left")) {
                board = new BoardLeft();
            } else {
                board = new BoardRight();
            }
            board.initialize();

            Writer out = new OutputStreamWriter(connection.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            board.initialize();
            while (true) {
                String move = in.readLine();
                if (move.equals("quit")) {
                    break;
                } else {
                    int cell = Character.getNumericValue(move.charAt(0));
                    if (cell >= 0 && cell < 9) {
                        boolean empty = board.checkMove(cell);
                        if (empty) {
                            board.updateBoard(cell);
                            if (board.checkStatus('o') == 0) {
                                if (board.checkBoard() == 0) {
                                    board.makeMove();
                                    if (board.checkStatus('x') == 0) {
                                        if (board.checkBoard() == 0) {
                                            out.write(board.encodeBoard() + "\r\n");
                                            out.flush();
                                        } else {
                                            out.write(board.encodeBoard() + " *** ");
                                            out.write("It's a draw!" + " *** ");
                                            out.write("Let's play again!" + " *** " + "\r\n");
                                            out.flush();
                                            board.initialize();
                                        }
                                    } else {
                                        out.write(board.encodeBoard() + " *** ");
                                        out.write("I won!" + " *** ");
                                        out.write("Let's play again!" + " *** " + "\r\n");
                                        out.flush();
                                        board.initialize();
                                    }
                                } else {
                                    out.write(board.encodeBoard() + " *** ");
                                    out.write("It's a draw!" + " *** ");
                                    out.write("Let's play again!" + " *** " + "\r\n");
                                    out.flush();
                                    board.initialize();
                                }
                            } else {
                                out.write(board.encodeBoard() + " *** ");
                                out.write("You won!" + " *** ");
                                out.write("Let's play again!" + " *** " + "\r\n");
                                out.flush();
                                board.initialize();
                            }
                        } else {
                            out.write("Occupied cell!" + "\r\n");
                            out.flush();
                        }
                    } else {
                        out.write("Wrong input!" + "\r\n");
                        out.flush();
                    }
                }
            }
            connection.close();
        } catch (IOException ex) {
            Logger.getLogger(TicTacToeServerThread_ClientStrategy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
