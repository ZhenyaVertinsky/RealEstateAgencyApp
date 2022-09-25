package com.verta.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;


@Setter
@Getter
@EqualsAndHashCode
// @ToString (exclude = {"isDeleted"})
@AllArgsConstructor
@NoArgsConstructor

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
