#!/bin/sh
MAIN_CLASS="com.{projectName}.{modualName}.bootstrap.Bootstrap"

SCRIPTS_DIR=`dirname "$0"`
PROJECT_DIR=`cd $SCRIPTS_DIR && pwd`
DT=`date +"%Y%m%d_%H%M%S"`

MEM_OPTS="-Xms512m -Xmx512m -Xmn384m"
GC_OPTS="$GC_OPTS -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:CMSInitiatingOccupancyFraction=60 -XX:CMSTriggerRatio=70"
GC_OPTS="$GC_OPTS -Xloggc:${PROJECT_DIR}/logs/gc_${DT}.log"
GC_OPTS="$GC_OPTS -XX:+PrintGCDateStamps -XX:+PrintGCDetails"
GC_OPTS="$GC_OPTS -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${PROJECT_DIR}/tmp/heapdump_${DT}.hprof"
CLASS_PATH="$PROJECT_DIR/conf:$PROJECT_DIR/lib/*:$CLASS_PATH"

#run java
mkdir -p "$PROJECT_DIR/tmp/"
mkdir -p "$PROJECT_DIR/logs/"

exec java -server $MEM_OPTS $GC_OPTS $JMX_OPTS $START_OPTS -classpath $CLASS_PATH $MAIN_CLASS

# END OF FILE
