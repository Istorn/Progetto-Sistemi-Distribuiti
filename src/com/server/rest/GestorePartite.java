package com.server.rest;
import java.util.*;
import java.io.*;
public class GestorePartite {
	private List<Partita> partite;
	
	public GestorePartite(){
		this.partite=new ArrayList<Partita>();
	}
	//Restituisce l'indice della partita cercata in base al nome nella lista delle partite attive
	//Restituisce -1 se la partita non è stata trovata
	public synchronized int indicePartitaPerNome(String nomePartita){
		for (int i=0;i<this.partite.size();i++){
			if (this.partite.get(i).getNome()==nomePartita){
				return i;
			}
		}
		return -1;
	}
	//Aggiunge una nuova partita richiedendo i dati del giocatore e della dimensione del campo stesso
	public synchronized String aggiungiPartita(String nomePartita, Giocatore creatore, int lato){
		if ((lato>1)&&(lato<10)){

			if (this.indicePartitaPerNome(nomePartita)>0){
				return "È già presente una parita con il nome '"+nomePartita+"'!";
			}else{
				this.partite.add(new Partita(nomePartita, creatore, lato));
				return "Partita creata!";
			}
		}
		else{
			return "Il lato dev'essere di almeno due e massimo 10 unità!";
		}
	}
	
	//Verifica l'esistenza di un giocatore in una data partita
	public synchronized boolean cercaGiocatorePartita(String nomeGiocatore, String nomePartita){
		if (this.indicePartitaPerNome(nomePartita)>0){
			for (int i=0;i<this.partite.get(this.indicePartitaPerNome(nomePartita)).getGiocatori().size();i++){
				if (this.partite.get(this.indicePartitaPerNome(nomePartita)).getGiocatori().get(i).getNome()==nomeGiocatore){
					return true;
				}
			}
		}
		return false;
	}
	//Aggiunge un giocatore nuovo a una partita preesistente e verifica che non ce ne sia uno con lo stesso nome
	public synchronized String aggiungiGiocatorePartita(Giocatore giocatoreDaAggiungere, String nomePartita){
		
		if (this.cercaGiocatorePartita(giocatoreDaAggiungere.getNome(), nomePartita)==false){
			this.partite.get(this.indicePartitaPerNome(nomePartita)).aggiungiGiocatore(giocatoreDaAggiungere);
			return giocatoreDaAggiungere.getNome()+" è stato aggiunto alla partita '"+nomePartita+"'!";
		}
		return "È già presente un giocatore con lo stesso nome nella partita selezionata!";
		
	}
	//Rimuove un giocatore da una specifica partita e ne verifica un'esistenza precedente
	public synchronized String rimuoviGiocatore(Giocatore giocatoreDaRimuovere,String nomePartita){
		if(this.partite.get(this.indicePartitaPerNome(nomePartita)).rimuoviGiocatore(giocatoreDaRimuovere)==true){
			return giocatoreDaRimuovere.getNome()+" è stato rimosso dalla partita!";
		}
		return "Il giocatore "+giocatoreDaRimuovere.getNome()+" non è presente nella partita selezionata!";
	}
	//Restituisce l'elenco delle partite
	public synchronized String listaPartite(){
		String dettagli="";
		for (int i=0;i<this.partite.size();i++){
			dettagli+=this.partite.get(i).getNome()+'\n';
		}
		if (dettagli.length()==0){
			return "Non ci sono partite correntemente attive!";
		}
		return dettagli;
	}
	//Resistuisce i dettagli di una partita in base al nome specificato
	public synchronized String getDettagliPartita(String nomePartita){
		String dettagli="Partita inesistente!";
		if (this.indicePartitaPerNome(nomePartita)>0){
			dettagli="Partita '"+this.partite.get(this.indicePartitaPerNome(nomePartita)).getNome()+"':"+'\n';
			for (int i=0;i<this.partite.get(this.indicePartitaPerNome(nomePartita)).getGiocatori().size();i++){
				dettagli+=Integer.toString(i)+"-"+this.partite.get(this.indicePartitaPerNome(nomePartita)).getGiocatori().get(i).getNome()+" Indirizzo: "+this.partite.get(this.indicePartitaPerNome(nomePartita)).getGiocatori().get(i).getIndirizzoIP()+" Porta: "+this.partite.get(this.indicePartitaPerNome(nomePartita)).getGiocatori().get(i).getPorta()+'\n'; 
			}
			dettagli+='\n';
		}
		return dettagli;
	}
	//Restituisce i dettagli delle partite in corso
	public synchronized String dettagliPartite(){
		String dettagli="";
		for (int i=0;i<this.partite.size();i++){
			dettagli+=this.getDettagliPartita(this.partite.get(i).getNome());
		}
		return dettagli;
	}
	
	
}
