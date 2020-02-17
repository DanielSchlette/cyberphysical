/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.enums;

import java.util.Arrays;
import java.util.List;

/**
 * Industry attribute presettings
 * 
 * @author tarnschaf
 */
public enum IndustryPresettings {

    VEHICLE_INDUSTRY("Vehicle Industry"),
    TRANSPORTATION("Transportation"),
    HEALTCARE("Healthcare"),
    SMART_BUILDING("Smart Building"),
    SOCIAL_NETWORK("Social Network"),
    SCHEDULING("Scheduling"),
    POWER_MANAGEMENT("Power Management"),
    CLOUD_COMPUTING("Cloud Computing"),
    POWER_GRID("Power Grid"),
    INDUSTRIAL_CONTROL_PROCESS("Industrial Control Process"),
    AEROSPACE("Aerospace"),
    VIDEO_PROCESSING("Video Processing"),
    NATIONAL_DEFENSE("National Defense"),
    WATER_DISTRIBUTION("Water Distribution"),
    ROBOTICS("Robotics"),
    AGRICULTURE("Agriculture"),
    METEOROLOGY("Meteorology"),
    BIOMETRICS("Biometrics"),
    ENERGY("Energy"),
    INTERNET_OF_THINGS("Internet of Things"),
    SMART_CARS("Smart Cars");

    private final String industry;

    private IndustryPresettings(String industry) {
        this.industry = industry;
    }

    public String getIndustry() {
        return industry;
    }

    public List<IndustryPresettings> getAllIndustryTypes() {
        return Arrays.asList(IndustryPresettings.values());
    }
}
