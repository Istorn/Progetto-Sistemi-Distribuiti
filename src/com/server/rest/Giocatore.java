package com.server.rest;
import java.io.*;
import java.net.*;
import java.util.Enumeration;


public class Giocatore {
	private String nome;
	private String indirizzoIP;
	private long porta;
	
	public Giocatore(String nomeUtente,long portaDaSettare){
		this.nome=nomeUtente;
		this.porta=portaDaSettare;
	}
	public synchronized String getNome() {
		return nome;
	}
	public synchronized void setNome(String nome) {
		this.nome = nome;
	}
	public synchronized String getIndirizzoIP() {
		return this.indirizzoIP;
	}
	public synchronized void setIndirizzoIP() {
		try {
	        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
	        while (interfaces.hasMoreElements()) {
	            NetworkInterface iface = interfaces.nextElement();
	            // filters out 127.0.0.1 and inactive interfaces
	            if (iface.isLoopback() || !iface.isUp())
	                continue;

	            Enumeration<InetAddress> addresses = iface.getInetAddresses();
	            while(addresses.hasMoreElements()) {
	                InetAddress addr = addresses.nextElement();
	                this.indirizzoIP = addr.getHostAddress();
	                System.out.println(iface.getDisplayName() + " " + this.indirizzoIP);
	            }
	        }
	    } catch (SocketException e) {
	        throw new RuntimeException(e);
	    }
	}
	public synchronized long getPorta() {
		return porta;
	}
	public synchronized void setPorta(long porta) {
		this.porta = porta;
	}
}
