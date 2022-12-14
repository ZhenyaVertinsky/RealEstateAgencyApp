package com.verta.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.verta.domain.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Set;


@Data
@Entity
@EqualsAndHashCode(exclude = {
        "roles", "orders", "info"
})
//@ToString(exclude = {
//        "roles", "orders", "info"
//})
@Table(name = "agents")
public class HibernateAgent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "agent_name")
    private String agentName;

    @Column(name = "agent_surname")
    private String agentSurname;

    @Column(name = "birthday")
    private Timestamp birthday;

    @Column(name = "agent_phone")
    private String agentPhone;

    @Column(name = "percent_reward")
    private Integer percentReward;

    @Column(name = "creation_date")
    @JsonIgnore
    private Timestamp creationDate;

    @Column(name = "modification_date")
    @JsonIgnore
    private Timestamp modificationDate;

    @Column(name = "is_deleted")
    @JsonIgnore
    private Boolean isDeleted;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "login", column = @Column(name = "agent_login")),
            @AttributeOverride(name = "password", column = @Column(name = "agent_password"))
    })
    private Credentials credentials;

//    @Column(name = "agent_login")
//    private String agentLogin;
//
//    @Column(name = "agent_password")
//    @JsonIgnore
//    private String agentPassword;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.NOT_SELECTED;

    @ManyToMany(mappedBy = "agents", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("agents")
    private Set<HibernateRole> roles;

    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private Set<HibernateAgentOrder> orders;

    @OneToOne(mappedBy = "agent", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private HibernateMedicalInfo info;

}
