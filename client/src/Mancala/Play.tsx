import React, { useState } from "react";
import type { GameState } from "../gameState";
import "./Play.css";

type PlayProps = {
    gameState: GameState;
    setGameState(newGameState: GameState): void;
}

export function Play({ gameState, setGameState }: PlayProps) {
    const [errorMessage, setErrorMessage] = useState("");
    const [playedMove, setPlayedMove] = useState("");

    async function tryPlayMove(e: React.FormEvent) {

    }

    return (
        <div>
            <table>
                <tr> 
                    <td> {gameState.players[0].name} vs </td>
                    <td> {gameState.players[1].name} </td>
                </tr>

                <tr> 
                    <td> {gameState.players[0].pits[0].nrOfStones} </td>
                    <td> {gameState.players[1].pits[0].nrOfStones} </td>
                </tr>
                <tr> 
                    <td> {gameState.players[0].pits[1].nrOfStones} </td>
                    <td> {gameState.players[1].pits[1].nrOfStones} </td>
                </tr>
                <tr> 
                    <td> {gameState.players[0].pits[2].nrOfStones} </td>
                    <td> {gameState.players[1].pits[2].nrOfStones} </td>
                </tr>
                <tr> 
                    <td> {gameState.players[0].pits[3].nrOfStones} </td>
                    <td> {gameState.players[1].pits[3].nrOfStones} </td>
                </tr>
                <tr> 
                    <td> {gameState.players[0].pits[4].nrOfStones} </td>
                    <td> {gameState.players[1].pits[4].nrOfStones} </td>
                </tr>
                <tr> 
                    <td> {gameState.players[0].pits[5].nrOfStones} </td>
                    <td> {gameState.players[1].pits[5].nrOfStones} </td>
                </tr>
                <tr> 
                    <td> {gameState.players[0].pits[6].nrOfStones} </td>
                    <td> {gameState.players[1].pits[6].nrOfStones} </td>
                </tr>

            </table>


    <form onSubmit={(e) => tryPlayMove(e)}>
        <input value={playedMove}
        placeholder="Which Pit do you want to select?"
        onChange={(e) => setPlayedMove(e.target.value)}
        />

        <p className="errorMessage">{errorMessage}</p>

        <button className="startGameButton" type="submit">
            Select Pit!
            </button>
     </form>
     </div>
    )
}