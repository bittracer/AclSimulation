package com.acl;

public class AclModel {

	private boolean permission;
	private String source;
	private String sourceMask;
	private String aclnumber;
	private String destination;
	private String destinationMask;
	private int protocolPort;
	private String protocol;

	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public int getProtocolPort() {
		return protocolPort;
	}
	public void setProtocolPort(int protocolPort) {
		this.protocolPort = protocolPort;
	}
	public boolean isPermission() {
		return permission;
	}
	public void setPermission(boolean permission) {
		this.permission = permission;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSourceMask() {
		return sourceMask;
	}
	public void setSourceMask(String sourceMask) {
		this.sourceMask = sourceMask;
	}
	public String getAclnumber() {
		return aclnumber;
	}
	public void setAclnumber(String aclnumber) {
		this.aclnumber = aclnumber;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDestinationMask() {
		return destinationMask;
	}
	public void setDestinationMask(String destinationMask) {
		this.destinationMask = destinationMask;
	}
}
