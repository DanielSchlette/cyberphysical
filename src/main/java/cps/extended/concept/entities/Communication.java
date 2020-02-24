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
 * The communication of the cyber-physical elements
 *
 * @author tarnschaf
 */
@Entity(name = "communication")
public class Communication implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "communication")
    private String communication;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "communicationlist")
    @JoinTable(name = "cpe_communication",
            joinColumns = @JoinColumn(name = "cpe_id"),
            inverseJoinColumns = @JoinColumn(name = "communication_id")
    )
    private List<Cpe> communicationcpes = new ArrayList<>();

    public Communication() {
    }

    public String getCommunication() {
        return communication;
    }

    public void setCommunication(String communication) {
        this.communication = communication;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Cpe> getCommunicationcpes() {
        return communicationcpes;
    }

    public void setCommunicationcpes(List<Cpe> communicationcpes) {
        this.communicationcpes = communicationcpes;
    }

    @Override
    public String toString() {
        return communication;
    }

}
