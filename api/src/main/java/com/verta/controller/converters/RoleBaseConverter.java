package com.verta.controller.converters;

import com.verta.controller.request.RoleRequest;
import com.verta.domain.hibernate.HibernateRole;
import lombok.NoArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class RoleBaseConverter implements Converter<RoleRequest, HibernateRole> {
    @Override
    public HibernateRole convert(RoleRequest source) {
        System.out.println("In Hibernate role converter");
        return new HibernateRole();
    }
}