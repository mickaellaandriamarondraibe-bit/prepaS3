#!/usr/bin/env bash
set -e

mkdir -p build/classes
javac -d build/classes $(find src/main/java -name "*.java")
cp -r src/main/resources/* build/classes/

echo "Compilation terminee dans build/classes"
