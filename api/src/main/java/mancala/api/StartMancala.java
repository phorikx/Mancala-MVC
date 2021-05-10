package mancala.api;

import java.io.IOException;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import mancala.api.models.*;
import mancala.domain.*;
import mancala.domain.Player;

@Path("/start")
public class StartMancala {
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response initialize(
			@Context HttpServletRequest request, 
			PlayerInput players) {
        var mancala = new PlayerImpl();
        String namePlayer1 = players.getNameplayer1();
		String namePlayer2 = players.getNameplayer2();
		
        HttpSession session = request.getSession(true);
        session.setAttribute("mancala", mancala);
        session.setAttribute("player1", namePlayer1);
        session.setAttribute("player2", namePlayer2);

		var output = new Player(null,null);
		return Response.status(200).entity(output).build();
	}
}
