package com.server.rest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@ApplicationPath("/*")
@Path("/servergame")
public class ServerAccessClass {
	public static GestorePartite gestore=new GestorePartite();
	
	//GET: elencho partite, elenco giocatori annessi alle partite
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String ottieniPartite(){
		return gestore.listaPartite();
	}
	//Post crearea partita
	//Put aggiungere utene partita
	//Delete eliminare partita o utente da partita
}
