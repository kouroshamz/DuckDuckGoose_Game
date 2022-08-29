#!/bin/bash
echo 1
javac -d build -cp "$1:src" $(find src -name "*.java")
echo 2
jar cf $2 -C build . -C . assets
echo 3
java -cp "$1:$2" com.BowesGaming.Engine.Game