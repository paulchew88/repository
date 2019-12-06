package com.pc1crt.application.model;

import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    @Column(nullable = false, unique = true)
    private String username;
    @NotEmpty
    private String password;
    private Date dateCreated;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "authority_id") })
    private Set<Authority> authorities = new HashSet<>();
    public User() {
    }
    // getters and setters
}
