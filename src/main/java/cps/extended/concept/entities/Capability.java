/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

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
    private int id;

    @Column(name = "capability")
    private String capability;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "capabilitylist")
    @JoinTable(name = "cpe_capability",
            joinColumns = @JoinColumn(name = "cpe_id"),
            inverseJoinColumns = @JoinColumn(name = "capability_id")
    )
    private List<Cpe> capabilitycpes = new ArrayList<>();

    public String getCapability() {
        return capability;
    }

    public void setCapability(String capability) {
        this.capability = capability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Cpe> getCapabilitycpes() {
        return capabilitycpes;
    }

    public void setCapabilitycpes(List<Cpe> capabilitycpes) {
        this.capabilitycpes = capabilitycpes;
    }

    @Override
    public String toString() {
        return capability;
    }

}
