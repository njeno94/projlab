rmdir /S /Q out
mkdir out
javac -encoding utf8 -sourcepath src -d out src/Controller/Controller.java
xcopy /s /i "src/Images" "out/Images"

