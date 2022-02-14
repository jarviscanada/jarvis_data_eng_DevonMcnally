-- 1. Group hosts by hardware info
SELECT cpu_number, id, total_mem
FROM host_info
GROUP BY cpu_number, total_mem, id
order by total_mem DESC;

-- 2. Average memory usage
--to try
DATEADD
SELECT date_trunc('hour', timestamp) + date_part('minute', timestamp):: int / 5 * interval '5 min'
FROM host_usage;