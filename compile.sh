#!/bin/bash
rm -r out/*
javac -encoding utf8 -sourcepath src -d out src/Controller/Controller.java
cp -r src/Images out/Images
