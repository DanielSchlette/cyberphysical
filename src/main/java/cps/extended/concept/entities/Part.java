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
 * Extension of the part attribute in CPE for more specific differentiation
 * between components
 *
 * @author tarnschaf
 */
@Entity(name = "part")
public class Part implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "part")
    private String part;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "partlist")
    @JoinTable(name = "cpe_part",
            joinColumns = @JoinColumn(name = "cpe_id"),
            inverseJoinColumns = @JoinColumn(name = "part_id")
    )
    private List<Cpe> partcpes = new ArrayList<>();

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Cpe> getPartcpes() {
        return partcpes;
    }

    public void setPartcpes(List<Cpe> partcpes) {
        this.partcpes = partcpes;
    }

    @Override
    public String toString() {
        return part;
    }

}
