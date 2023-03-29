#!/bin/bash

export JAVA_HOME=$HOME/.jdks/graalvm-ce-17
./gradlew nativeCompile -x test