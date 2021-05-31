package mancala.api;

import java.io.IOException;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import mancala.api.models.*;
import mancala.domain.*;
import mancala.domain.Player;

@Path("/play")
public class PlayMove {
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response processmove(
        @Context HttpServletRequest request, 
        PlayerMove playerMove) {
    HttpSession session = request.getSession(false);

    System.out.println(session.getAttribute("player1"));
    System.out.print("is this activated?");
    
    MancalaImpl gameMancala = (MancalaImpl) session.getAttribute("gameMancala");

    int index = playerMove.getPlayerMove();
    try{
        gameMancala.playPit(index);
        Mancala mancala = new Mancala(gameMancala, ((String) session.getAttribute("player1")), ((String) session.getAttribute("player2")));
        return Response.status(200).entity(mancala).build();
    } catch(MancalaException e) {
        Mancala mancala = new Mancala(gameMancala, ((String) session.getAttribute("player1")), ((String) session.getAttribute("player2")));
        return Response.status(406).entity(mancala).build();
    }   

}
    
}
