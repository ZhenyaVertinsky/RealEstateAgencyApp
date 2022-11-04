package com.verta.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.verta.domain.Gender;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
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
    private Timestamp creationDate;

    @Column(name = "modification_date")
    private Timestamp modificationDate;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "agent_login")
    private String agentLogin;

    @Column(name = "agent_password")
    private String agentPassword;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany(mappedBy = "agents", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JsonIgnoreProperties("users")
    private List<HibernateRole> roles;
}
