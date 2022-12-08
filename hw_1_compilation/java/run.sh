#!/bin/sh

echo 'run level 1'

echo 'run simple'
cd ./level_1/simple
javac Greetings.java
java Greetings

cd ../

echo 'run package'
cd ./package
javac ua/com/alevel/Greetings.java
java ua.com.alevel.Greetings

cd ../

echo 'run separate packages'
cd ./separate_packages
javac ua/com/alevel/Greetings.java
java ua.com.alevel.Greetings

cd ../

echo 'run minimal proj'
cd ./minimal_proj
javac -sourcepath ./src -d build/classes ./src/ua/com/alevel/Greetings.java
java -cp build/classes ua.com.alevel.Greetings

cd ../

echo 'run med proj and create simple jar'
cd ./med_proj
javac -sourcepath ./src -d build/classes ./src/ua/com/alevel/Greetings.java
jar -cvfm build/jar/greetings.jar resources/MANIFEST.MF -C build/classes .
java -jar build/jar/greetings.jar

cd ../../

echo 'run level 2 (include libs)'

cd ./level_2

echo 'run simple proj who contains external library (jar)'
cd ./include_libs
javac -sourcepath ./src -d build/classes/ -cp ./lib/commons-lang3-3.12.0.jar src/ua/com/alevel/test/Congratulation.java src/ua/com/alevel/Greetings.java
java -cp build/classes/:./lib/commons-lang3-3.12.0.jar ua.com.alevel.Greetings

cd ../

echo 'run create jar who contains external library (jar)'
cd ./jar
. ./run_jar.sh