package com.progetto;
import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@ApplicationPath("/*")
@Path("/servergame")
public class ServerAccessClass extends Application{
	
	//GET: elencho partite, elenco giocatori annessi alle partite
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String ottieniPartite(){
		
		return "ciao";
	}
	//Post crearea partita
	//Put aggiungere utene partita
	//Delete eliminare partita o utente da partita
}
