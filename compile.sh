#!/bin/bash
if [ -d out ]; then
	rm -r out
fi
mkdir out
javac -encoding utf8 -sourcepath src -d out src/Controller/Controller.java
cp -r src/Images out/Images
