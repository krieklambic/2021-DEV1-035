package net.comexis.kata.tictactoe.exception;

public class InvalidGameException extends RuntimeException {

    public InvalidGameException(String message){
        super(message);
    }

}
