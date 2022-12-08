#!/bin/sh

#!/bin/sh

echo 'variant 1'

javac -sourcepath ./src -d build/classes/ -cp ./lib/commons-lang3-3.12.0.jar src/ua/com/alevel/test/Congratulation.java src/ua/com/alevel/Greetings.java
cd lib
mkdir com
jar xf commons-lang3-3.12.0.jar
cp -rf com ../build/classes
cd ..
jar cvfm build/jar/congratulation.jar resources/MANIFEST.MF -C build/classes .
jar tf build/jar/congratulation.jar
java -jar build/jar/congratulation.jar

echo 'variant 2'

rm -rf lib/com
rm -rf lib/META-INF
javac -sourcepath ./src -d build/classes/ -cp ./lib/commons-lang3-3.12.0.jar src/ua/com/alevel/test/Congratulation.java src/ua/com/alevel/Greetings.java
cp -r lib/*.jar build/jar
jar cvfm build/jar/congratulation.jar resources/MANIFEST.MF -C build/classes .
java -jar build/jar/congratulation.jar

cd ../../

. ./remove-class.sh