package com.angelcantero.tema01.boletin01.ejercicio4;

import java.io.*;
import java.util.Arrays;

/**
 * Representa el estado de una partida de 3 en raya.
 * Guarda la posición de las fichas, el turno actual y la puntuación acumulada.
 */
public class TicTacToeState implements Serializable {

    private char[][] board; // Tablero: 'X', 'O' o ' ' (vacío)
    private char currentPlayer; // Jugador actual: 'X' o 'O'
    private int xWins; // Número de partidas ganadas por X
    private int oWins; // Número de partidas ganadas por O

    /**
     * Constructor que inicializa un tablero vacío y turno de X.
     * La puntuación se inicializa a 0 para ambos jugadores.
     */
    public TicTacToeState() {
        board = new char[3][3];
        for (char[] row : board) {
            Arrays.fill(row, ' ');
        }
        currentPlayer = 'X';
        xWins = 0;
        oWins = 0;
    }

    /** @return el tablero actual */
    public char[][] getBoard() {
        return board;
    }

    /** @param board nuevo tablero a establecer */
    public void setBoard(char[][] board) {
        this.board = board;
    }

    /** @return el jugador que tiene el turno actual */
    public char getCurrentPlayer() {
        return currentPlayer;
    }

    /** @param currentPlayer jugador al que se le asigna el turno ('X' o 'O') */
    public void setCurrentPlayer(char currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /** @return número de partidas ganadas por X */
    public int getXWins() {
        return xWins;
    }

    /** @param xWins número de victorias de X */
    public void setXWins(int xWins) {
        this.xWins = xWins;
    }

    /** @return número de partidas ganadas por O */
    public int getOWins() {
        return oWins;
    }

    /** @param oWins número de victorias de O */
    public void setOWins(int oWins) {
        this.oWins = oWins;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char[] row : board) {
            sb.append(Arrays.toString(row)).append("\n");
        }
        sb.append("Turno de: ").append(currentPlayer).append("\n");
        sb.append("Puntuación X: ").append(xWins).append(", O: ").append(oWins);
        return sb.toString();
    }
}

/**
 * Gestiona el guardado y la carga de partidas de 3 en raya en disco.
 */
class GameStorage {

    /**
     * Guarda el estado de una partida en un archivo mediante serialización.
     *
     * @param state    estado de la partida a guardar
     * @param filename ruta del archivo donde se guardará el estado
     */
    public static void saveGame(TicTacToeState state, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(state);
            System.out.println("Partida guardada en " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga un estado de partida desde un archivo previamente guardado.
     *
     * @param filename ruta del archivo desde donde se cargará el estado
     * @return el estado de la partida, o null si ocurre un error
     */
    public static TicTacToeState loadGame(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            TicTacToeState state = (TicTacToeState) in.readObject();
            System.out.println("Partida cargada desde " + filename);
            return state;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

/**
 * Ejemplo de uso de las clases TicTacToeState y GameStorage.
 */
class Main {
    public static void main(String[] args) {
        // Crear un estado de partida
        TicTacToeState state = new TicTacToeState();
        state.setCurrentPlayer('O');
        state.setXWins(1);
        state.setOWins(2);
        state.getBoard()[0][0] = 'X';
        state.getBoard()[1][1] = 'O';

        System.out.println("Estado inicial:");
        System.out.println(state);

        // Guardar partida
        GameStorage.saveGame(state, "tictactoe");

        // Cargar partida
        TicTacToeState loadedState = GameStorage.loadGame("tictactoe");
        System.out.println("Estado cargado:");
        System.out.println(loadedState);
    }
}
