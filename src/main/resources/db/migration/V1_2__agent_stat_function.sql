drop function get_agents_stats_average_percent_reward(boolean);

create function get_agents_stats_average_percent_reward(is_deleted_param boolean) returns double precision
    language sql
    as
$$
select avg(percent_reward)
from entity.agents
where is_deleted = is_deleted_param;


$$;

alter function get_agents_stats_average_percent_reward(boolean) owner to postgres;