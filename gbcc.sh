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

# Welcome Process.
#   This function would show a message that
#   informs about the bash script.
function _gbcc_welcome() {
    clear
    echo "gBuild compiler (Linux version)"
    echo "==================================="
}

_gbcc_welcome

# >>>>>>>>>>>>>> CONFIGURATION <<<<<<<<<<<<<<

GBUILD_PROCESSING_LIB="../libraries/"
GBUILD_MAVEN="/mnt/c/Users/david/.m2/"

# >>>>>>>>>>>>>> END CONFIGURATION <<<<<<<<<<<<<<

# ==== Flags ====
# Please do not modify any of these variables
# since they are used to check parameters
gbcc_flag_javadoc=0
gbcc_flag_src=0
gbcc_flag_examples=0
gbcc_flag_mode=0

# Show an error message and exit this script 
function _gbcc_err() {
    echo "error gbcc: $1" 1>&2
    exit 1
}

# Param Process.
#   This function would check parameters
#   in order to know what the script must
#   do during the execution
function _gbcc_check_param() {
    if [[ $1 =~ "-s" ]]; then
        gbcc_flag_src=1
    elif [[ $1 =~ "-j" ]]; then
        gbcc_flag_javadoc=1
    elif [[ $1 =~ "-e" ]]; then
        gbcc_flag_examples=1
    fi

    if [[ $1 =~ "-f" ]]; then
        gbcc_flag_src=0
        gbcc_flag_javadoc=0
        gbcc_flag_examples=0
        gbcc_flag_mode=1
    elif [[ $1 =~ "-c" ]]; then
        gbcc_flag_src=1
        gbcc_flag_javadoc=1
        gbcc_flag_examples=1
        gbcc_flag_mode=1
    fi
}

# Compilation Process.
#   This function would compile all java files
#   located at gBuild source folder.
function _gbcc_compile() {
    rm -rf bin 2>/dev/null
    mkdir bin 2>/dev/null
    mkdir -p bin/gbuild/button 2>/dev/null
    mkdir bin/gbuild/dialog 2>/dev/null
    mkdir bin/gbuild/slider 2>/dev/null
    mkdir bin/gbuild/menu 2>/dev/null
    mkdir bin/gbuild/event 2>/dev/null

    javac -verbose -Xlint:deprecation \
        -classpath "${GBUILD_MAVEN}repository/org/processing/core/3.3.7/core-3.3.7.jar" src/main/java/gbuild/*/* \
        -sourcepath src/main/java 2>/dev/null

    mv src/main/java/gbuild/*.class bin/gbuild 2>/dev/null
    mv src/main/java/gbuild/button/*.class bin/gbuild/button 2>/dev/null
    mv src/main/java/gbuild/menu/*.class bin/gbuild/menu 2>/dev/null
    mv src/main/java/gbuild/dialog/*.class bin/gbuild/dialog 2>/dev/null
    mv src/main/java/gbuild/slider/*.class bin/gbuild/slider 2>/dev/null
    mv src/main/java/gbuild/event/*.class bin/gbuild/event 2>/dev/null
}

# Compression Process.
#   This function would compress all binary files
#   compiled previously and located at bin folder
function _gbcc_jar() {
    cd bin
    jar -cf gBuild.jar * 2>/dev/null
    cd ..
}

# Javadoc Process.
#   This function would generate all documentation
#   for each source file of gBuild library
function _gbcc_javadoc() {
    mkdir ./reference 2>/dev/null
    rm -rf ./reference/* 2>/dev/null

    javadoc -d ./reference -sourcepath ./src/main/java \
        -subpackages gbuild.button gbuild.dialog gbuild.menu gbuild.slider gbuild \
        -classpath ${GBUILD_MAVEN}repository/org/processing/core/3.3.7/core-3.3.7.jar \
        -docencoding 'UTF-8' -doctitle 'gBuild 4.1.0 API' -quiet 2>/dev/null
}

# Processing Process.
#   This function would prepare all files to use
#   this library at Processing sketches
function _gbcc_processing_lib() {
    rm -rf ${GBUILD_PROCESSING_LIB}gBuild 2>/dev/null
    mkdir ${GBUILD_PROCESSING_LIB}gBuild 2>/dev/null
    rm -rf ${GBUILD_PROCESSING_LIB}gBuild/* 2>/dev/null

    mkdir ${GBUILD_PROCESSING_LIB}gBuild/library 2>/dev/null

    if (( $gbcc_flag_examples == 1 )); then
        mkdir ${GBUILD_PROCESSING_LIB}gBuild/examples 2>/dev/null
        cp -r examples/* ${GBUILD_PROCESSING_LIB}gBuild/examples 2>/dev/null
    fi

    if (( $gbcc_flag_javadoc == 1 )); then
        mkdir ${GBUILD_PROCESSING_LIB}gBuild/reference 2>/dev/null
        cp -r reference/* ${GBUILD_PROCESSING_LIB}gBuild/reference 2>/dev/null
    fi

    if (( $gbcc_flag_src == 1 )); then  
        mkdir ${GBUILD_PROCESSING_LIB}gBuild/src/ 2>/dev/null
        cp -r src/main/java/* ${GBUILD_PROCESSING_LIB}gBuild/src 2>/dev/null
    fi

    cp -r library.properties ${GBUILD_PROCESSING_LIB}gBuild/ 2>/dev/null
    cp -r bin/gBuild.jar ${GBUILD_PROCESSING_LIB}gBuild/library 2>/dev/null
}

# Clean Process.
#   This function would clean binary files
#   generated at the beggining of the execution
function _gbcc_clean() {
    rm -rf ./bin 2>/dev/null

    unset gbcc_flag_examples
    unset gbcc_flag_javadoc
    unset gbcc_flag_mode
    unset gbcc_flag_src
    
    unset GBUILD_PROCESSING_LIB
    unset GBUILD_MAVEN
}

# Check parameters before execution
if (( $# == 0 )); then
    gbcc_flag_javadoc=1
    gbcc_flag_src=1
    gbcc_flag_examples=1
else
    (( $# < 1 || $# > 3 )) && _gbcc_err "invalid number of params"

    while (( $# > 0 )); do
        _gbcc_check_param $1

        if (( $gbcc_flag_mode == 1 )); then
            [[ $1 =~ "-s" || $1 =~ "-j" || $1 =~ "-e" ]] &&
                _gbcc_err "mode must be declared alone"
        fi

        shift
    done
fi

echo -n ">> Compilation Process ... "; _gbcc_compile; echo "OK"
echo -n ">> Compression Process ... "; _gbcc_jar; echo "OK"

if (( $gbcc_flag_javadoc == 1 )); then
    echo -n ">> Javadoc Process ... "
    _gbcc_javadoc
    echo "OK"
fi

echo -n ">> gBuild library ... "; _gbcc_processing_lib; echo "OK"
#echo -n ">> Cleaning ... "; _gbcc_clean; echo "OK"