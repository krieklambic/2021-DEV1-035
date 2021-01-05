package net.comexis.kata.tictactoe.exception;

public class InvalidCellNumberException extends IllegalArgumentException {

    public InvalidCellNumberException(String message){
        super(message);
    }

}
