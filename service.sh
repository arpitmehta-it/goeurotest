#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# Keep the pwd in mind!
# Example: RUN="java -jar $DIR/target/magic.jar"
RUN="java -jar $DIR/build/libs/go-euro-route-finder-service-0.0.1.jar --fileloc=$2"
NAME=go-euro-route-finder

DATA_FILE=$2

PIDFILE=/tmp/$NAME.pid
LOGFILE=/tmp/$NAME.log

start() {
  if [ -f $PIDFILE ] && kill -0 $(cat $PIDFILE); then
    echo 'Service already running' >&2
    return 1
  fi
  local CMD="$RUN $DATA_FILE &> \"$LOGFILE\" & echo \$!"
  sh -c "$CMD" > "$LOGFILE"
  sh -c "pgrep -f $NAME" > "$PIDFILE"  
}

stop() {
  if [ ! -f "$PIDFILE" ] || ! kill -0 $(cat "$PIDFILE"); then
    echo 'Service not running' >&2
    return 1
  fi
  kill -15 $(cat "$PIDFILE") && rm -f "$PIDFILE"
}


case "$1" in
  start)
    start
    ;;
  stop)
    stop
    ;;
  block)
    start
    sleep infinity
    ;;
  *)
    echo "Usage: $0 {start|stop|block} DATA_FILE"
esac
