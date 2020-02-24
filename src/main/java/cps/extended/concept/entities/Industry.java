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

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "industrylist")
    @JoinTable(name = "cpe_industry",
            joinColumns = @JoinColumn(name = "cpe_id"),
            inverseJoinColumns = @JoinColumn(name = "industry_id")
    )
    private List<Cpe> industrycpes = new ArrayList<>();

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return industry;
    }

    public List<Cpe> getIndustrycpes() {
        return industrycpes;
    }

    public void setIndustrycpes(List<Cpe> industrycpes) {
        this.industrycpes = industrycpes;
    }

}
