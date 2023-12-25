/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eiu.cit.netprog;

/**
 *
 * @author ACER
 */
public class BoardRight extends AbstractBoard {

    @Override
    public void makeMove() {
        for (int i = board.length - 1; i >= 0; i--) {
            if (board[i] == '-') {
                board[i] = 'x';
                break;
            }
        }

    }
}
