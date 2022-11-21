psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

if [ $# -ne  5 ]
  then
    exit 1
fi

vmstat_mb=$(vmstat --unit M)
hostname=$(hostname -f)
vmstat_d=$(vmstat -D)
df_hm=$(df -Hm)

memory_free=$(echo "$vmstat_mb" | awk '{print $4}'| tail -n1 | xargs)
cpu_idle=$(echo "$vmstat_mb" | awk '{print $15}' | tail -n1)
cpu_kernel=$(echo "$vmstat_mb" | awk '{print $14}' | tail -n1)
disk_io=$(echo "$vmstat_d" | grep 'progress IO' | awk '{print $1}')
disk_available=$(echo "$df_hm" | grep -w / | awk '{print $4}')
timestamp=$(vmstat -t | tail -n1 | rev | cut -c 1-19 | rev)

host_id="(SELECT id FROM host_info WHERE hostname='$hostname')";

insert_stmt="INSERT INTO host_usage(timestamp, host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available)
              VALUES('$timestamp', $host_id, $memory_free, $cpu_idle, $cpu_kernel, $disk_io, $disk_available)"

export PGPASSWORD=$psql_password

psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?