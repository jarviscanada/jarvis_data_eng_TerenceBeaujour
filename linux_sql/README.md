# Introduction

A Linux cluster of 10 nodes is running on CentOS 7 using google cloud platform with a virtual machine. These server can communicate thanks to their IP addresses.
The Jarvis Linux Cluster Administration (LCA) team is managing these servers.
The goal of this project is to record the hardware information and the usage of the memory and CPU in RDBMS and to allow the team to better plan the resources in the future utilizing the generated and stored data.
Bash scripts are generated to get the data PostgreSQL is used as a database and Docker is used to run the the database.

## Quick Start

- Start, stop and create a psql container:

```
./scripts/psql_docker.sh start
./scripts/psql_docker.sh stop
./scripts/psql_docker.sh create db_username db_password
```

- Create tables:

```
psql -h localhost -U postgres -d host_agent -f sql/ddl.sql
```

- Insert hardware specs data into the DB:

```
./scripts/host_info.sh psql_host psql_port db_name psql_user psql_password
```

- Insert hardware usage data into the DB using host_usage.sh

```
./scripts/host_usage.sh psql_host psql_port db_name psql_user psql_password
```

- Crontab

```
crontab -e
* * * * * bash PATH_TO_host_usage.sh localhost 5432 host_agent postgres password > /tmp/host_usage.log
```

## Implementation

### Architecture

![Architecture](/linux_sql/assets/architecture.png?raw=true "Architecture")
### Scripts

- `psql_docker.sh`: Starts, stops, creates a psql instance.
- `host_info.sh`: Inserts host hardware information.
- `host_usage.sh`: Collects and stores resources usage.
- `crontab`: The script should be executed every minute.

### Database modeling

- `host_info`
  - `host_id`: Unique id for each row
  - `hostname`: Name of the host machine
  - `cpu_number`: Number of cores in the CPU
  - `cpu_architecture`: Architecture of the CPU
  - `cpu_model`: Model name
  - `cpu_mhz`: Frequency in Mhz
  - `L2_cache`: Cache in KB
  - `total_mem`: Memory in KB
  - `timestamp`: Timestamp

- `host_usage`
  - `host_id`: Unique id for each row, from `host_info`
  - `memory_free`: Memory free in MB
  - `cpu_idle`: CPU idle in %
  - `cpu_kernel`: Usage of the kernel in %
  - `disk_io`: Number of the disk's Input/Output
  - `disk_available`: Disk available
  - `timestamp`: Timestamp

## Test

The test have been made on a JRD CentOS 7 based with google cloud

## Deployment

The code have been deployed on a Github repository

## Improvements

- Add more feature (columns) to the tables
- Refactor the code
- Implement a script to run all the tests at once.
