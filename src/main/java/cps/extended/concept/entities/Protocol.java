/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * The protocol in the cyber-physical network
 *
 * @author tarnschaf
 */
@Entity
public class Protocol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(mappedBy = "protocol")
    private List<Cpe> cpelist;

    @Column(name = "protocol")
    private String protocol;

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getProtocol() {
        return protocol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Cpe> getCpelist() {
        return cpelist;
    }

    public void setCpelist(List<Cpe> cpelist) {
        this.cpelist = cpelist;
    }

    @Override
    public String toString() {
        return protocol;
    }

}
