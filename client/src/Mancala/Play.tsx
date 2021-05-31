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
        var player1hasturn=true;
        var player2hasturn=false;
    } else {
        var nameHasTurn = gameState.players[1].name;        
        var player1hasturn=false;
        var player2hasturn=true;
    }

    async function tryPlayMove(playedMove: number, e: React.MouseEvent<HTMLButtonElement, MouseEvent>) {
        e.preventDefault();
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
        if(Number(playedMove)<1 || Number(playedMove)==7 || Number(playedMove)>13 ) {
            setErrorMessage("You need to enter a number between 1 and 6");
            console.log("You need to enter a number between 1 and 6");
            return;
        }        
        try {
            console.log(playedMove);
            const response = await fetch('mancala/api/play', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({playerName: nameHasTurn, playerMove: Number(playedMove)-1})
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
            /**        <div>
          
             {nameHasTurn} has the turn. Enter a move!     
             <form onSubmit={(e) => tryPlayMove(e)}>
             <input value={playedMove}
             placeholder="Which Pit do you want to select?"
             onChange={(e) => setPlayedMove(e.target.value)}
             />
            
             <p className="errorMessage">{errorMessage}</p>
            
            <button className="submitMoveButton" type="submit">
                 Select Pit!
                         </form>
             </button> */            
            <div>
            <header> {gameState.players[0].name} vs {gameState.players[1].name} </header>
            <table>
                <tbody>

                <tr> 
                    <td> </td>
                    <td> <button onClick = {(e) => tryPlayMove(13, e)} disabled={!player2hasturn}>{gameState.players[1].pits[5].nrOfStones} </button>  </td>
                    <td> <button onClick = {(e) => tryPlayMove(12, e)} disabled={!player2hasturn}>{gameState.players[1].pits[4].nrOfStones} </button>  </td>
                    <td> <button onClick = {(e) => tryPlayMove(11, e)} disabled={!player2hasturn}>{gameState.players[1].pits[3].nrOfStones} </button>  </td>
                    <td> <button onClick = {(e) => tryPlayMove(10, e)} disabled={!player2hasturn}>{gameState.players[1].pits[2].nrOfStones} </button>  </td>  
                    <td> <button onClick = {(e) => tryPlayMove(9, e)} disabled={!player2hasturn}>{gameState.players[1].pits[1].nrOfStones} </button>  </td>
                    <td> <button onClick = {(e) => tryPlayMove(8, e)} disabled={!player2hasturn}>{gameState.players[1].pits[0].nrOfStones} </button>  </td>
                    <td> </td>
                </tr>

                <tr> 
                    <td> {gameState.players[1].pits[6].nrOfStones} </td>  
                    <td> </td> 
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td> {gameState.players[0].pits[6].nrOfStones} </td>
                </tr>    

                <tr>
                    <td></td>
                    <td> <button onClick = {(e) => tryPlayMove(1, e)} disabled={!player1hasturn}>{gameState.players[0].pits[0].nrOfStones} </button> </td>
                    <td> <button onClick = {(e) => tryPlayMove(2, e)} disabled={!player1hasturn}>{gameState.players[0].pits[1].nrOfStones} </button>  </td>
                    <td> <button onClick = {(e) => tryPlayMove(3, e)} disabled={!player1hasturn}>{gameState.players[0].pits[2].nrOfStones} </button>  </td>
                    <td> <button onClick = {(e) => tryPlayMove(4, e)} disabled={!player1hasturn}>{gameState.players[0].pits[3].nrOfStones} </button>  </td>
                    <td> <button onClick = {(e) => tryPlayMove(5, e)} disabled={!player1hasturn}>{gameState.players[0].pits[4].nrOfStones} </button>  </td>
                    <td> <button onClick = {(e) => tryPlayMove(6, e)} disabled={!player1hasturn}>{gameState.players[0].pits[5].nrOfStones} </button>  </td>
                </tr>
                </tbody>
            </table>
         

     </div>
    )
}