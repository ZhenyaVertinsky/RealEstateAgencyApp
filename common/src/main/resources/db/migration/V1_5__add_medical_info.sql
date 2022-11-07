create table if not exists medical_info
(
    id                bigserial
        constraint medical_info_pk
            primary key,
    blood_type        int,
    rh                varchar(2)   not null,
    creation_date     timestamp(6) not null,
    modification_date timestamp(6) not null,
    agent_id          bigint       not null
        constraint medical_info_agents_id_fk
            references entity.agents
            on update cascade on delete cascade
);

create unique index if not exists medical_info_agent_id_uindex
    on medical_info (agent_id);
