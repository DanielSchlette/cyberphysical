/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The cyber-physical capability (sensoring, actuatoring etc.)
 *
 * @author tarnschaf
 */
@Entity(name = "capability")
public class Capability implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "capability")
    private String capability;

    public String getCapability() {
        return capability;
    }

    public void setCapability(String capability) {
        this.capability = capability;
    }

}