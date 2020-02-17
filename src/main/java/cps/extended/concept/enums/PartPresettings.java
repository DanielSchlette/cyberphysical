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
 * Part attribute presettings
 *
 * @author tarnschaf
 */
public enum PartPresettings {

    // Application, operating systems, hardware devices, sensors, actuators, programmable logic controller, remote terminal units
    A("a"), O("o"), H("h"), SE("se"), AC("ac"), P("p"), R("r");

    private final String part;

    private PartPresettings(String partType) {
        this.part = partType;
    }

    public String getPart() {
        return part;
    }

    public List<PartPresettings> getAllParts() {
        return new ArrayList<>(Arrays.asList(A, O, H, SE, AC, P, R));
    }
}
