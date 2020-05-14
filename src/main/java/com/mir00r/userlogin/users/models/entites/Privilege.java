package com.mir00r.userlogin.users.models.entites;

import com.mir00r.userlogin.base.entities.BaseEntity;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * @author mir00r on 14/5/20
 * @project IntelliJ IDEA
 */
@Entity
@Table(name = "privileges")
public class Privilege extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "label", nullable = false, unique = true)
    private String label;

    @Column(name = "description")
    private String description;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(name = "privileges_access_urls")
    private List<String> accessUrls;

    public Privilege() {
    }

    public Privilege(String name, String label) {
        this.name = name;
        this.label = label;
    }

    public List<String> getAccessUrls() {
        return accessUrls;
    }

    public void setAccessUrls(List<String> accessUrls) {
        this.accessUrls = accessUrls;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
