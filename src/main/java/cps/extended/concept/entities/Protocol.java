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

    @Column(name = "protocol")
    private String protocol;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "protocollist")
    @JoinTable(name = "cpe_protocol",
            joinColumns = @JoinColumn(name = "cpe_id"),
            inverseJoinColumns = @JoinColumn(name = "protocol_id")
    )
    private List<Cpe> programmingLangcpes = new ArrayList<>();

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

    public List<Cpe> getProgrammingLangcpes() {
        return programmingLangcpes;
    }

    public void setProgrammingLangcpes(List<Cpe> programmingLangcpes) {
        this.programmingLangcpes = programmingLangcpes;
    }

    @Override
    public String toString() {
        return protocol;
    }

}
