#!/usr/bin/env bash
#
# This script is used to compile and prepare gBuild source
# files for Processing. It is necessary to close all Processing
# sketches before executing this script, as well as being at
# the path of the repository.
#
# Before executing this script, customize the configuration
# declared at the beggining of this file to prepare the
# library for your current machine installation
#
# WARNING!!
#   This script is designed to be executed considering that
#   current path is this repository, so check this condition
#   to avoid internal errors.

clear
echo "gBuild compiler (Linux version)"
echo "==================================="

GBUILD_PROCESSING_LIB="../libraries/"
GBUILD_MAVEN="/mnt/c/Users/Usuario/.m2/"

# Java compilation
echo -n ">> Compilation Process ... "
mkdir bin 2>/dev/null
javac -d bin -verbose -Xlint:-deprecation \
    -classpath "${GBUILD_MAVEN}repository/org/processing/core/3.3.7/core-3.3.7.jar" src/main/java/gbuild/*.java \
    -sourcepath src/main/java 2>/dev/null
echo "OK"

# Jar file
echo -n ">> Compression Process ... "
jar -cf bin/gBuild.jar bin/* 2>/dev/null
echo "OK"

# Javadoc installation
echo -n ">> Javadoc Process ... "
mkdir ./reference 2>/dev/null
rm -rf ./reference/* 2>/dev/null
javadoc -d ./reference -sourcepath ./src/main/java -subpackages gbuild.button gbuild.dialog gbuild.menu gbuild \
    -classpath ${GBUILD_MAVEN}repository/org/processing/core/3.3.7/core-3.3.7.jar \
    -docencoding 'UTF-8' -doctitle 'gBuild 4.0.2 API' -quiet 2>/dev/null
echo "OK"

# Processing library
echo -n ">> gBuild library ... "
mkdir ${GBUILD_PROCESSING_LIB}gBuild 2>/dev/null
rm -rf ${GBUILD_PROCESSING_LIB}gBuild/* 2>/dev/null

mkdir ${GBUILD_PROCESSING_LIB}gBuild/library 2>/dev/null
mkdir ${GBUILD_PROCESSING_LIB}gBuild/examples 2>/dev/null
mkdir ${GBUILD_PROCESSING_LIB}gBuild/reference 2>/dev/null
mkdir ${GBUILD_PROCESSING_LIB}gBuild/src/ 2>/dev/null

cp -r reference/* ${GBUILD_PROCESSING_LIB}gBuild/reference 2>/dev/null
cp -r examples/* ${GBUILD_PROCESSING_LIB}gBuild/examples 2>/dev/null
cp -r bin/gBuild.jar ${GBUILD_PROCESSING_LIB}gBuild/library 2>/dev/null
cp -r src/main/java/* ${GBUILD_PROCESSING_LIB}gBuild/src 2>/dev/null
echo "OK"

# Clean phase
echo -n ">> Cleaning ... "
rm -rf ./bin 2>/dev/null
echo "OK"