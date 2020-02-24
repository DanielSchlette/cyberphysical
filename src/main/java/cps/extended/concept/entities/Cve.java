package cps.extended.concept.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * This entity representates a simple Cyber Vulnerability from the Common
 * Vulnerability Enumeration from The MITRE Corporation.
 *
 * @author tarnschaf
 */
@Entity(name = "cve")
@Table(name = "cve")
public class Cve implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(name = "cve_id")
    protected String cveId;

    // has to be at least big enough for large strings
    @Lob
    @Column(name = "description")
    protected String description;

    @Lob
    @Column(name = "date")
    protected String date;

    @Lob
    @Column(name = "url")
    private String url;

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(name = "cve_cpe",
            joinColumns = @JoinColumn(name = "cpe_id"),
            inverseJoinColumns = @JoinColumn(name = "cve_id")
    )
    private List<Cpe> cyberCpes = new ArrayList<>();

    @Column(name = "source")
    private String source;

    public Cve() {
    }

    public void setCyberCpes(List<Cpe> cyberCpes) {
        this.cyberCpes = cyberCpes;
    }

    public List<Cpe> getCyberCpes() {
        return cyberCpes;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCveId() {
        return cveId;
    }

    public void setCveId(String cveId) {
        this.cveId = cveId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
