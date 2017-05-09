package com.server.rest;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
@ApplicationPath("/*")
@Path("/Gioco")
public class ServerAccessClass {
	public static GestoreParite partite=new GestorePartite();
	
	//GET: elencho partite, elenco giocatori annessi alle partite
	@GET
	@Produces("text/plain")
	public String ottieniPartite(){
		return partite.listaPartite();
	}
	//Post crearea partita
	//Pu aggiungere utene partita
	//Delete eliminare partita o utente da partita
}
