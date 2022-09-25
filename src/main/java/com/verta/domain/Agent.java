package com.verta.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;
import java.util.Objects;

public class Agent {

    private Long id;
    private String agentName;
    private String agentSurname;
    private Timestamp birthday;
    private String agentPhone;
    private Integer percentReward;
    private Timestamp creationDate;
    private Timestamp modificationDate;
    private boolean isDeleted;

    public Agent() {

    }

    public Agent(Long id, String agentName, String agentSurname, Timestamp birthday, String agentPhone,
                 Integer percentReward, Timestamp creationDate, Timestamp modificationDate, boolean isDeleted) {
        this.id = id;
        this.agentName = agentName;
        this.agentSurname = agentSurname;
        this.birthday = birthday;
        this.agentPhone = agentPhone;
        this.percentReward = percentReward;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentSurname() {
        return agentSurname;
    }

    public void setAgentSurname(String agentSurname) {
        this.agentSurname = agentSurname;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public String getAgentPhone() {
        return agentPhone;
    }

    public void setAgentPhone(String agentPhone) {
        this.agentPhone = agentPhone;
    }

    public Integer getPercentReward() {
        return percentReward;
    }

    public void setPercentReward(Integer percentReward) {
        this.percentReward = percentReward;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Timestamp modificationDate) {
        this.modificationDate = modificationDate;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agent agent = (Agent) o;
        return isDeleted == agent.isDeleted && Objects.equals(id, agent.id) && Objects.equals(agentName,
                agent.agentName) && Objects.equals(agentSurname, agent.agentSurname) && Objects.equals(birthday,
                agent.birthday) && Objects.equals(agentPhone, agent.agentPhone) && Objects.equals(percentReward,
                agent.percentReward) && Objects.equals(creationDate, agent.creationDate) && Objects.equals
                (modificationDate, agent.modificationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, agentName, agentSurname, birthday, agentPhone, percentReward, creationDate,
                modificationDate, isDeleted);
    }

    @Override
    public String toString() {
        return "Agent{" +
                "id=" + id +
                ", agentName='" + agentName + '\'' +
                ", agentSurname='" + agentSurname + '\'' +
                ", birthday=" + birthday +
                ", agentPhone='" + agentPhone + '\'' +
                ", percentReward=" + percentReward +
                ", creationDate=" + creationDate +
                ", modificationDate=" + modificationDate +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
