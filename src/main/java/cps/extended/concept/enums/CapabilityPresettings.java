/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.enums;

import java.util.Arrays;
import java.util.List;

/**
 * Capability attribute presettings
 * 
 * @author tarnschaf
 */
public enum CapabilityPresettings {

    PRESSURE_SENSORING("Pressure Senoring"),
    TEMPERATURE_SENSORING("Temperature Sensoring"),
    ACCELERATION_SENSORING("Acceleration Sensoring"),
    HUMIDITY_SENSORING("Humidity Sensoring"),
    CURRENT_SENSORING("Current Sensoring"),
    SPEED_SENSORING("Speeed Sensoring"),
    ELECTROCHEMICAL_ACTUATING("Electrochemical Actuating"),
    HYDRAULICAL_ACTUATING("Hydraulical Actuating"),
    ACTUATING_THROUGH_CYLINDER("Actuating through Cylinder"),
    ELECTRIC_ACTUATING("Electric Actuating"),
    OVERWATCH_PRIVACY("Overwatch Privacy");

    private final String capability;

    private CapabilityPresettings(String capability) {
        this.capability = capability;
    }

    public String getCapability() {
        return capability;
    }

    /**
     *
     * @return a list of all cps capabilities
     */
    public List<CapabilityPresettings> getAllCpsCapabilites() {
        return Arrays.asList(CapabilityPresettings.values());
    }
}
