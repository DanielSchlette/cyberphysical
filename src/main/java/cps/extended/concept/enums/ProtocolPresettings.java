/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.enums;

import java.util.Arrays;
import java.util.List;

/**
 * Protocol attribute presettings
 * 
 * @author tarnschaf
 */
public enum ProtocolPresettings {

    TCP_IP("TCP/IP"),
    UDP("UDP"),
    ICMP("ICMP"),
    BLUETOOTH("Bluetooth"),
    NFC("NFC"),
    MODBUS("Modbus"),
    DNP3("DNP3"),
    ICCP("ICCP"),
    LF("LF"),
    BAN("BAN"),
    V2V("V2V"),
    ZigBee("ZigBee"),
    ALLEN_BRADLEY("Allen Bradley"),
    SIEMENS_SINAUT("Siemens Sinaut"),
    DF1("DF1"),
    DH("DH"),
    DH_PLUS("DH+"),
    WESTINGHOUSE("Westinghouse"),
    CAN("CAN"),
    WirelessHART("WirelessHART"),
    ISA_100_11_A("ISA 100.11a"),
    COAP("CoAP"),
    KNX("KNX"),
    LIN("LIN");

    private final String protocol;

    private ProtocolPresettings(String protocol) {
        this.protocol = protocol;
    }

    public String getProtocol() {
        return protocol;
    }

    public List<ProtocolPresettings> getDefaultProtocolSettings() {
        return Arrays.asList(ProtocolPresettings.values());
    }
}
