CREATE TABLE host_info(
    id SERIAL NOT NULL PRIMARY KEY,
    hostname VARCHAR(50) UNIQUE NOT NULL,
    cpu_number INTEGER NOT NULL,
    cpu_architecture VARCHAR(50) NOT NULL,
    cpu_model VARCHAR(50) NOT NULL,
    cpu_mhz DECIMAL(10,5) NOT NULL,
    L2_cache INTEGER NOT NULL,
    total_mem INTEGER NOT NULL,
    timestamp TIMESTAMP NOT NULL
);

CREATE TABLE host_usage(
    timestamp TIMESTAMP NOT NULL,
    host_id SERIAL NOT NULL,
    memory_free INTEGER NOT NULL,
    cpu_idle INTEGER NOT NULL,
    cpu_kernel INTEGER NOT NULL,
    disk_io INTEGER NOT NULL,
    disk_available INTEGER NOT NULL,
    CONSTRAINT fk_host_id
        FOREIGN KEY(host_id)
            REFERENCES host_info(id)
);