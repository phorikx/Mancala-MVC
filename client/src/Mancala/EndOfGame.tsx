import React, { useState } from "react";
import type { GameState } from "../gameState";
import "./EndOfGame.css";

type EndOfGameProps = {
    gameState: GameState;
    setGameState(newGameState: GameState): void;
}

export function EndOfGame({ gameState, setGameState }: EndOfGameProps) {
    const [errorMessage, setErrorMessage] = useState("");
    const [playedMove, setPlayedMove] = useState("");

    return (
    <div>
    {gameState.gameStatus.winner} has won.       
     </div>
    )
}