/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eiu.cit.netprog;

/**
 *
 * @author ACER
 */
public class BoardLeft extends AbstractBoard {

    @Override
    public void makeMove() {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == '-') {
                board[i] = 'x';
                break;
            }
        }

    }
}
