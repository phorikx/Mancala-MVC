package mancala.api;

import java.io.IOException;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import mancala.api.models.*;
import mancala.domain.*;

@Path("/start")
public class StartMancala {
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response initialize(
			@Context HttpServletRequest request, 
			PlayerInput players) {
        
        String namePlayer1 = players.getNameplayer1();
		String namePlayer2 = players.getNameplayer2();

		var gameMancala = new MancalaImpl();

		var mancala = new Mancala(gameMancala, namePlayer1, namePlayer2);
		
        HttpSession session = request.getSession(true);
        session.setAttribute("mancala", mancala);
        session.setAttribute("player1", namePlayer1);
        session.setAttribute("player2", namePlayer2);
		session.setAttribute("gameMancala", gameMancala);
		//session.setAttribute("playerObject", firstPlayer);

		return Response.status(200).entity(mancala).build();
	}
}
