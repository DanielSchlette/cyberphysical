/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Communication attribute presettings
 * 
 * @author tarnschaf
 */
public enum CommunicationPresettings {

    WIRED("w"), FUNK("f");

    private final String communication;

    private CommunicationPresettings(String communication) {
        this.communication = communication;
    }

    public String getCommunication() {
        return communication;
    }

    public List<CommunicationPresettings> getAllParts() {
        return new ArrayList<>(Arrays.asList(WIRED, FUNK));
    }
}
