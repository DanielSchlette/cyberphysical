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
@Entity(name = "cpsbundle")
public class CPSBundle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "description")
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "bundle_combinations", joinColumns = {
        @JoinColumn(name = "bundle_id")}, inverseJoinColumns = {
        @JoinColumn(name = "cpe_id")}
    )
    private List<Cpe> cpes = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Cpe> getCpes() {
        return cpes;
    }

    public void setCpes(List<Cpe> cpes) {
        this.cpes = cpes;
    }

}
