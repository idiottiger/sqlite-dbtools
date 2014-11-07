#!/bin/sh
# DON'T USE THE ndk-r10 or above ndk version to build (can build ok, but when load library, will cann't find symbol: strpof), use ndk-r9
ndk-build