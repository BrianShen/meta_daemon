#!/usr/bin/env bash
source /etc/profile
cd `dirname "$0"`/..
update_home=`pwd`
echo ${update_home}

run_path="${update_home}"
opt=" -Xmx4024M -Xss2048K"
cmd="${JAVA_HOME}/bin/java -classpath \"${run_path}/lib/*:${run_path}/conf/*\" ${opt} com.fanli.metadata.core.work.StartContext"
echo ${cmd}
eval ${cmd}

ps -ef | grep -i "java" |grep -i "com.fanli.metadata.core.work.StartContext"