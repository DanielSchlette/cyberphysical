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
public class ProgrammingLang implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "language")
    private String language;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "programmingLanglist")
    @JoinTable(name = "cpe_programmingLang",
            joinColumns = @JoinColumn(name = "cpe_id"),
            inverseJoinColumns = @JoinColumn(name = "programmingLang_id")
    )
    private List<Cpe> programmingLangcpes = new ArrayList<>();

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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
        return language;
    }

}
