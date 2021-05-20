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
        PlayerMove playermove) {
    HttpSession session = request.getSession(false);

    var firstPlayer = session.getAttribute("playerObject");
    if(playermove.getPlayerName() == session.getAttribute("player1")) {
        ((Player) firstPlayer).takeTurn(playermove.getPlayerMove());
    }   else {
        ((Player) firstPlayer).getOpponent().takeTurn(playermove.getPlayerMove());
    }

    var mancala = new Mancala( ((Player) firstPlayer), ((String) session.getAttribute("player1")), ((String) session.getAttribute("player2")));

    return Response.status(200).entity(mancala).build();
}
    
}
