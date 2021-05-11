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
        PlayerInput players) {
    HttpSession session = request.getSession(false);

    var mancala =session.getAttribute("mancala");   

    return Response.status(200).entity(mancala).build();
}
    
}
