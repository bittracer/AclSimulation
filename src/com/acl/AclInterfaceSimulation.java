package com.acl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class AclInterfaceSimulation {

	public static List<AclModel> aclQueries = new ArrayList<AclModel>();

	public static List<PacketModel> packets = new ArrayList<PacketModel>();

	public static AllOtherPackets otherPackets = new AllOtherPackets();

	public static void main(String[] ar) throws Exception {

		AclInterfaceSimulation simulation = new AclInterfaceSimulation();
		// Get details for ACL
		simulation.readAclDataFromFile();
		// Get details for Packet
		simulation.readPacketDataFromFile();
		// Process the request through Router
		simulation.processRequest();

	}

	private void readAclDataFromFile() throws Exception {

		File file = new File("/Users/bharatjain/Desktop/Acl.txt");

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String line;
			while ((line = br.readLine()) != null) {
				// process the line.

				if (line.length() > 0) {
					if (!line.split(" ")[0].equals("interface")) {
						if (!line.split(" ")[0].equals("ip")) {

							AclModel _model = new AclModel();
							String[] _split = line.split(" ");

							// verify whether Standard or Extended ACL
							if (Integer.parseInt(line.split(" ")[1]) > 100) {

								generateAclModelForExtended(_model, _split);

								// Add it to List
								aclQueries.add(_model);

							} else {

								generateAclModelForStandards(_model, _split);

								// Add it to List
								aclQueries.add(_model);
							}

						}

					}
				}

			}

		}
	}

	private void readPacketDataFromFile() throws Exception {

		File file = new File("/Users/bharatjain/Desktop/packet.txt");

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String line;
			while ((line = br.readLine()) != null) {

				if (line.length() > 0) {
					PacketModel model = new PacketModel();
					if (!line.contains("etc.")) {

						String[] _split = line.split(" ");
						if (_split.length > 2) {
							model.setSourceIP(_split[0]);
							model.setDestinationIP(_split[1]);
							model.setSourcePort(Integer.parseInt(_split[2]));
							model.setDestPort(Integer.parseInt(_split[3]));
						} else {
							model.setSourceIP(_split[0]);
							model.setDestinationIP(_split[1]);
						}

						packets.add(model);

					}
				}

			}

		}

	}

	private void processRequest() {

		for (PacketModel _packetModel : packets) {
			for (AclModel _aclModel : aclQueries) {

				String[] _packetSplitSource = _packetModel.getSourceIP().split("\\.");
				String[] _aclSplitSource = _aclModel.getSource().split("\\.");

				if (_packetModel.getSourcePort() != 0 && _packetModel.getDestPort() != 0) {

					// Test for Exact IP
					if (_packetModel.getSourceIP().contains(_aclModel.getSource())
							&& _packetModel.getDestinationIP().contains(_aclModel.getDestination())
							&& _packetModel.getDestPort() == _aclModel.getProtocolPort()) {
						if (_aclModel.isPermission()) {
							permit(_packetModel.getSourceIP(), _packetModel.getDestinationIP());
							break;
						} else {
							deny(_packetModel.getSourceIP(), _packetModel.getDestinationIP());

							break;
						}
					} else if (_packetSplitSource[0].contains(_aclSplitSource[0])
							&& _packetSplitSource[1].contains(_aclSplitSource[1])
							&& _packetSplitSource[2].contains(_aclSplitSource[2])
							&& _packetModel.getDestPort() == _aclModel.getProtocolPort()) {
						// Test for Subnet Mask 0.0.0.255
						if (_aclModel.isPermission()) {
							permit(_packetModel.getSourceIP(), _packetModel.getDestinationIP());

							break;
						} else {
							deny(_packetModel.getSourceIP(), _packetModel.getDestinationIP());

							break;
						}
					}
				} else {
					if (_packetModel.getSourceIP().contains(_aclModel.getSource())) {
						if (_aclModel.isPermission()) {
							permit(_packetModel.getSourceIP(), _packetModel.getDestinationIP());

							break;

						} else {
							deny(_packetModel.getSourceIP(), _packetModel.getDestinationIP());

							break;

						}
					} else if (_packetSplitSource[0].contains(_aclSplitSource[0])
							&& _packetSplitSource[1].contains(_aclSplitSource[1])
							&& _packetSplitSource[2].contains(_aclSplitSource[2])) {
						if (_aclModel.isPermission()) {
							permit(_packetModel.getSourceIP(), _packetModel.getDestinationIP());

							break;

						} else {
							deny(_packetModel.getSourceIP(), _packetModel.getDestinationIP());

							break;
						}

					}
				}
			}
		}
	}

	public AclModel generateAclModelForExtended(AclModel _model, String[] _split) {

		// Check whether or not to permit or deny all other packets
		if (_split[3].contains("any") && _split[4].contains("any")) {

			if (_split[2].contains("permit")) {
				otherPackets.setPermitAll(true);
			} else {
				otherPackets.setPermitAll(false);
			}
		} else {
			// Set ACL Number
			_model.setAclnumber(_split[1]);

			// Set Permission
			if (_split[2].equals("permit")) {
				_model.setPermission(true);
			} else {
				_model.setPermission(false);
			}

			// set protocol
			_model.setProtocol(_split[3]);

			// Set source IP
			_model.setSource(_split[4]);

			// Set Source Mask
			_model.setSourceMask(_split[5]);

			// Set Destination IP
			_model.setDestination(_split[6]);

			// Set Destination Mask
			_model.setDestinationMask(_split[7]);

			// set Port number
			_model.setProtocolPort(Integer.parseInt(_split[8]));
		}

		return _model;
	}

	public AclModel generateAclModelForStandards(AclModel _model, String[] _split) {

		if (_split[3].contains("any")) {
			if (_split[2].contains("permit")) {
				otherPackets.setPermitAll(true);
			} else {
				otherPackets.setPermitAll(false);
			}
		} else {
			// Set ACL number
			_model.setAclnumber(_split[1]);

			// Set whether the permission is given or
			// not
			if (_split[2].equals("permit")) {
				_model.setPermission(true);
			} else {
				_model.setPermission(false);
			}

			// Set Source IP Address
			_model.setSource(_split[3]);

			// Set Destination IP Address
			_model.setSourceMask(_split[4]);
		}

		return _model;
	}

	public void permit(String sIP, String dIP) {
		System.out.println(sIP + "   " + dIP + "   permit");
	}

	public void deny(String sIP, String dIP) {
		System.out.println(sIP + "   " + dIP + "   deny");
	}
}
