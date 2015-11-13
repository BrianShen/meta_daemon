#!/usr/bin/env bash
source /etc/profile

ps -ef | grep -i "java" |grep -i "com.fanli.metadata.core.work.StartContext" | awk '{print "kill "$2}' | sh