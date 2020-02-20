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
 * This class represents the industry type of an cyber-physical component.
 *
 * @author tarnschaf
 */
@Entity(name = "industry")
public class Industry implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "industry")
    private String industry;

    public String getIndustry() {
        return industry;
    }
    @OneToMany(mappedBy = "industry")
    private List<Cpe> cpelist;

    public void setIndustry(String industry) {
        this.industry = industry;
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
        return industry;
    }

}
