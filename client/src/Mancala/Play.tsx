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

    if (gameState.players[0].hasTurn) {
        var nameHasTurn = gameState.players[0].name;
    } else {
        var nameHasTurn = gameState.players[1].name;
    }

    async function tryPlayMove(e: React.FormEvent) {
        if (!playedMove) {
            setErrorMessage("You need to enter a move");
            console.log("You need to enter a move");
            return;
        }
        if (isNaN(Number(playedMove))) {
            setErrorMessage("You need to enter a valid number");
            console.log("You need to enter a valid number");
            return;
        }
        if(Number(playedMove)<1 || Number(playedMove)>6) {
            setErrorMessage("You need to enter a number between 1 and 6");
            console.log("You need to enter a number between 1 and 6");
            return;
        }
        if(gameState.players[0].hasTurn) {
            var nameString = gameState.players[0].name;
        } else {
            var nameString = gameState.players[1].name;
        }
        
        try {
            console.log(playedMove);
            const response = await fetch('mancala/api/start', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({playerName: nameString, playerMove: Number(playedMove)})
            });

            if (response.ok) {
                console.log("Do we get here?");
                const updatedGameState = await response.json();                
                setGameState(updatedGameState);
            } else {
                console.error(response.statusText);
            }
        } catch (error) {
            console.error(error.toString());
        }

    }

    return (
        <div>
            <table>
                <tbody>
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
                </tbody>
            </table>

        {nameHasTurn} has the turn. Enter a move!

        <form onSubmit={(e) => tryPlayMove(e)}>
            <input value={playedMove}
            placeholder="Which Pit do you want to select?"
            onChange={(e) => setPlayedMove(e.target.value)}
            />

            <p className="errorMessage">{errorMessage}</p>

            <button className="submitMoveButton" type="submit">
                Select Pit!
            </button>
        </form>

     </div>
    )
}