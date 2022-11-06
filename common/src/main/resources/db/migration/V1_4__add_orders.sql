create table if not exists agent_order
(
    id            bigserial
        constraint order_pk
            primary key,
    creation_date timestamp(6),
    sum           float4,
    agent_id      bigint not null
        constraint order_agents_id_fk
            references entity.agents
            on update cascade on delete cascade
);

create index if not exists order_agent_id_sum_index
    on agent_order (agent_id, sum);

