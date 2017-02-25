package com.acl;

public class PacketModel {

	private String sourceIP;
	private String destinationIP;
	private String anyAny;
	private int sourcePort;
	private int destPort;
	
	
	
	public int getSourcePort() {
		return sourcePort;
	}
	public void setSourcePort(int sourcePort) {
		this.sourcePort = sourcePort;
	}
	public int getDestPort() {
		return destPort;
	}
	public void setDestPort(int destPort) {
		this.destPort = destPort;
	}
	public String getAnyAny() {
		return anyAny;
	}
	public void setAnyAny(String anyAny) {
		this.anyAny = anyAny;
	}
	public String getSourceIP() {
		return sourceIP;
	}
	public void setSourceIP(String sourceIP) {
		this.sourceIP = sourceIP;
	}
	public String getDestinationIP() {
		return destinationIP;
	}
	public void setDestinationIP(String destinationIP) {
		this.destinationIP = destinationIP;
	}
}
