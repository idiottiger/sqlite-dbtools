./gradlew compileDebugJava
cd build/intermediates/classes/debug
javah -jni pkg.id2.dbtools.library.DBTools
cp *.h ../../../../jni/
cd ../../../..