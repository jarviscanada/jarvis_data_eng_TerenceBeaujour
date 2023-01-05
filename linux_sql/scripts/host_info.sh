psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

if [ $# -ne  5 ]
  then
    exit 1
fi

lscpu_out=`lscpu`
hostname=$(hostname -f)

cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$(lscpu)" | grep 'Architecture' | awk '{print $2}' | xargs)
cpu_model=$(echo "$(lscpu)" | grep 'Model name' | awk '{$1=$2=""; print $0}' | xargs)
cpu_mhz=$(echo "$(lscpu)" | grep 'MHz' | awk '{$1=$2=""; print $0}' | xargs)
l2_cache=$(echo "$(lscpu)" | grep -i 'l2' | awk '{$1=$2=""; print $0}' | xargs| tr -d K)
total_mem=$(echo "$(free)" | grep -i 'mem' | awk '{print $2}' | xargs)
timestamp=$(vmstat -t | tail -n1 | rev | cut -c 1-19 | rev)

insert_stmt="INSERT INTO host_info (hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache, total_mem, timestamp)
                          VALUES ('$hostname', $cpu_number, '$cpu_architecture', '$cpu_model', $cpu_mhz, $l2_cache, $total_mem, '$timestamp')"

psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?