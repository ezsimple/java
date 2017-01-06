#!/bin/sh
MAX=100
for i in $(seq 1 $MAX)
do
  ID="SAMPLE-"$(printf "%05d" "$i");
  NO=$(printf "%d" "$i");
  QUERY="INSERT INTO SAMPLE VALUES('"${ID}"','*','제목"#${NO}"','내용-"${NO}"','Y','eGov');"
  echo $QUERY
done
IDS="INSERT INTO IDS VALUES('SAMPLE',"$(($MAX+1))");";
echo $IDS
