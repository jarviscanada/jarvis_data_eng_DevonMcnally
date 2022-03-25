-- 1. Group hosts by hardware info
SELECT cpu_number, id, total_mem
FROM host_info
GROUP BY cpu_number, total_mem, id
order by total_mem DESC;

-- 2. Average memory usage
CREATE FUNCTION round5(ts timestamp) RETURNS timestamp AS
    $$
BEGIN
RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
END;
$$
LANGUAGE PLPGSQL;

SELECT u.host_id, i.hostname, round5(u.timestamp) as rounded_timestamp, avg((i.total_mem - u.memory_free)/i.total_mem *100) as avg_used_memory_percent
from host_info i, host_usage u
where u.host_id = i.id
group by host_id, rounded_timestamp, hostname;

-- 3. Detect host failure

CREATE FUNCTION round5(ts timestamp) RETURNS timestamp AS
    $$
BEGIN
RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
END;
$$
LANGUAGE PLPGSQL;

SELECT rounded_timestamp, count(*)
from (select round5(timestamp) as rounded_timestamp from host_usage) a group by rounded_timestamp having count(*) < 3;

