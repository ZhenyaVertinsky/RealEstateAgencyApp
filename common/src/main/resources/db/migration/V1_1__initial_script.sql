
create table if not exists agents
(
    id                serial
        constraint agents_pk
            primary key,
    agent_name        varchar(20),
    agent_surname     varchar(50),
    birthday          timestamp(6),
    agent_phone       varchar(20),
    percent_reward    integer,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP(6)                  not null,
    modification_date timestamp(6) default CURRENT_TIMESTAMP(6)                  not null,
    is_deleted        boolean      default false                                 not null,
    agent_login       varchar(100),
    agent_password    varchar(200) default 'default_password'::character varying not null
);

alter table agents
    owner to postgres;

create table if not exists sellers
(
    id                integer                                   not null
        constraint sellers_pk
            primary key,
    seller_name       varchar(20)                               not null,
    seller_surname    varchar(50)                               not null,
    seller_phone      varchar(20),
    creation_date     timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    modification_date timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    is_deleted        boolean      default false                not null,
    seller_city       varchar(20)                               not null
);

alter table sellers
    owner to postgres;

create table if not exists buyers
(
    id                serial
        constraint buyers_pk
            primary key,
    buyer_name        varchar(20)                               not null,
    buyer_surname     varchar(50)                               not null,
    buyer_phone       varchar(20),
    creation_date     timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    modification_date timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    is_deleted        boolean      default false                not null,
    buyer_city        varchar(20)                               not null
);

alter table buyers
    owner to postgres;

create table if not exists estate
(
    id            serial
        constraint estate_pk
            primary key,
    estate_type   varchar(40) not null,
    estate_object varchar(20) not null,
    sellers_id    integer     not null
        constraint estate_sellers_id_fk
            references sellers
            on update cascade on delete cascade,
    estate_city   varchar(20) not null
);

alter table estate
    owner to postgres;

create table if not exists contracts
(
    id                serial
        constraint l_contracts_pk
            primary key,
    contract_number   varchar(20),
    contract_date     date,
    contract_type     varchar(20)                               not null,
    deal_amount       double precision                          not null,
    agents_amount     integer                                   not null,
    agent_id          integer
        constraint contracts_agents_id_fk
            references agents
            on update cascade on delete cascade,
    seller_id         integer
        constraint contracts_sellers_id_fk
            references sellers
            on update cascade on delete cascade,
    buyer_id          integer
        constraint contracts_buyers_id_fk
            references buyers
            on update cascade on delete cascade,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    modification_date timestamp(6) default CURRENT_TIMESTAMP(6) not null
);

alter table contracts
    owner to postgres;

create table if not exists l_agent_buyer
(
    id                bigserial
        constraint l_agent_buyer_pk
            primary key,
    agent_id          bigint not null
        constraint l_agent_buyer_agents_id_fk
            references agents
            on update cascade on delete cascade,
    buyer_id          bigint not null
        constraint l_agent_buyer_buyers_id_fk
            references buyers
            on update cascade on delete cascade,
    creation_date     timestamp(6),
    modification_date timestamp(6)
);

alter table l_agent_buyer
    owner to postgres;

create table if not exists l_agent_seller
(
    id                bigserial
        constraint l_agent_seller_pk
            primary key,
    agent_id          bigint not null
        constraint l_agent_seller_agents_id_fk
            references agents
            on update cascade on delete cascade,
    seller_id         bigint not null
        constraint l_agent_seller_sellers_id_fk
            references sellers
            on update cascade on delete cascade,
    creation_date     timestamp(6),
    modification_date timestamp(6)
);

alter table l_agent_seller
    owner to postgres;

create table if not exists roles
(
    id                bigserial
        constraint roles_pk
            primary key,
    role_name         varchar(15) default 'AGENT'::character varying not null,
    creation_date     timestamp(6),
    modification_date timestamp(6)
);

alter table roles
    owner to postgres;

create unique index if not exists roles_id_uindex
    on roles (id);

create table if not exists l_role_agent
(
    id       bigserial
        constraint l_role_agent_pk
            primary key,
    agent_id bigint not null
        constraint l_role_agent_agents_id_fk
            references agents
            on update cascade on delete cascade,
    role_id  bigint not null
        constraint l_role_agent_roles_id_fk
            references roles
            on update cascade on delete cascade
);

alter table l_role_agent
    owner to postgres;

create index if not exists l_role_agent_agent_id_role_id_index
    on l_role_agent (agent_id, role_id);

create unique index if not exists l_role_agent_id_uindex
    on l_role_agent (id);


