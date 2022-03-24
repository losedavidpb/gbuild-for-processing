#!/usr/bin/env bash
#
# This script is used to compile and prepare gBuild source
# files for Processing. It is necessary to close all Processing
# sketches before executing this script, as well as being at
# the path of the repository.
#
# This script is designed considering that you are
# executing it at the repository path and Processing library
# folder is at ../library relative path.
# Check this conditions before executing
#

clear
echo "gBuild compiler (Linux version)"
echo "==================================="

# Java compilation
echo -n ">> Compilation Process ... "

javac -d bin -verbose -Xlint:-deprecation \
    -classpath /mnt/c/Users/david/.m2/repository/org/processing/core/3.3.7/core-3.3.7.jar src/main/java/gbuild/*.java \
    -sourcepath src/main/java 2>/dev/null

echo "OK"

# Jar file
echo -n ">> Compression Process ... "
jar -cf bin/gBuild.jar /bin/*
echo "OK"

# Javadoc installation
echo -n ">> Javadoc Process ... "

mkdir ./reference 2>/dev/null
rm -rf ./reference/* 2>/dev/null
javadoc -d ./reference -sourcepath ./src/main/java -subpackages gbuild.button gbuild.dialog gbuild.menu gbuild \
    -classpath /mnt/c/Users/david/.m2/repository/org/processing/core/3.3.7/core-3.3.7.jar \
    -docencoding 'UTF-8' -doctitle 'gBuild 4.0.1 API' -quiet 2>/dev/null

echo "OK"

# Processing library
echo -n ">> gBuild library ... "

mkdir ../libraries/gBuild 2>/dev/null
rm -rf ../libraries/gBuild/* 2>/dev/null

mkdir ../libraries/gBuild/library 2>/dev/null
mkdir ../libraries/gBuild/examples 2>/dev/null
mkdir ../libraries/gBuild/reference 2>/dev/null
mkdir ../libraries/gBuild/src/ 2>/dev/null

cp -r javadoc/* ../libraries/gBuild/reference 2>/dev/null
cp -r examples/* ../libraries/gBuild/examples 2>/dev/null
cp -r bin/gBuild.jar ../libraries/gBuild/library 2>/dev/null
cp -r src/main/java/* ../libraries/gBuild/src 2>/dev/null

echo "OK"

# Clean phase
echo -n ">> Cleaning ... "
rm -rf ./bin 2>/dev/null
echo "OK"