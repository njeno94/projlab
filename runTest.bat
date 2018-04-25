@ECHO OFF

SET /P src/Test="valassz tesztesetet 1-19-ig: "

2>Nul CALL :CASE_%src/Test%
IF ERRORLEVEL 1 CALL :DEFAULTCASE

EXIT /B

:CASE_1
java -classpath out -Dfile.encoding=UTF8 Model.Main  src/Test/Input/test1.txt < src/Test/Input/right.in > src/Test/Output/test1Output.txt
GOTO END_CASE
:CASE_2
java -classpath out -Dfile.encoding=UTF8 Model.Main  src/Test/Input/test2.txt < src/Test/Input/right.in > src/Test/Output/test2Output.txt
GOTO END_CASE
:CASE_3
java -classpath out -Dfile.encoding=UTF8 Model.Main  src/Test/Input/test3.txt < src/Test/Input/right.in > src/Test/Output/test3Output.txt
GOTO END_CASE
:CASE_4
java -classpath out -Dfile.encoding=UTF8 Model.Main  src/Test/Input/test4.txt < src/Test/Input/right.in > src/Test/Output/test4Output.txt
GOTO END_CASE
:CASE_5
java -classpath out -Dfile.encoding=UTF8 Model.Main  src/Test/Input/test5.txt < src/Test/Input/left.in > src/Test/Output/test5Output.txt
GOTO END_CASE
:CASE_6
java -classpath out -Dfile.encoding=UTF8 Model.Main  src/Test/Input/test6.txt < src/Test/Input/left.in > src/Test/Output/test6Output.txt
GOTO END_CASE
:CASE_7
java -classpath out -Dfile.encoding=UTF8 Model.Main  src/Test/Input/test7.txt < src/Test/Input/right.in > src/Test/Output/test7Output.txt
GOTO END_CASE
:CASE_8
java -classpath out -Dfile.encoding=UTF8 Model.Main  src/Test/Input/test8.txt < src/Test/Input/right.in > src/Test/Output/test8Output.txt
GOTO END_CASE
:CASE_9
java -classpath out -Dfile.encoding=UTF8 Model.Main  src/Test/Input/test9.txt < src/Test/Input/honey.in > src/Test/Output/test9Output.txt
GOTO END_CASE
:CASE_10
java -classpath out -Dfile.encoding=UTF8 Model.Main  src/Test/Input/test10.txt < src/Test/Input/oil.in > src/Test/Output/test10Output.txt
GOTO END_CASE
:CASE_11
java -classpath out -Dfile.encoding=UTF8 Model.Main  src/Test/Input/test11.txt < src/Test/Input/right.in > src/Test/Output/test11Output.txt
GOTO END_CASE
:CASE_12
java -classpath out -Dfile.encoding=UTF8 Model.Main  src/Test/Input/test12.txt < src/Test/Input/right.in > src/Test/Output/test12Output.txt
GOTO END_CASE
:CASE_13
java -classpath out -Dfile.encoding=UTF8 Model.Main  src/Test/Input/test13.txt < src/Test/Input/left.in > src/Test/Output/test13Output.txt
GOTO END_CASE
:CASE_14
java -classpath out -Dfile.encoding=UTF8 Model.Main  src/Test/Input/test14.txt < src/Test/Input/left.in > src/Test/Output/test14Output.txt
GOTO END_CASE
:CASE_15
java -classpath out -Dfile.encoding=UTF8 Model.Main  src/Test/Input/test15.txt < src/Test/Input/left.in > src/Test/Output/test15Output.txt
GOTO END_CASE
:CASE_16
java -classpath out -Dfile.encoding=UTF8 Model.Main  src/Test/Input/test16.txt < src/Test/Input/left.in > src/Test/Output/test16Output.txt
GOTO END_CASE
:CASE_17
java -classpath out -Dfile.encoding=UTF8 Model.Main  src/Test/Input/test17.txt < src/Test/Input/right.in > src/Test/Output/test17Output.txt
GOTO END_CASE
:CASE_18
java -classpath out -Dfile.encoding=UTF8 Model.Main  src/Test/Input/test18.txt < src/Test/Input/right.in > src/Test/Output/test18Output.txt
GOTO END_CASE
:CASE_19
java -classpath out -Dfile.encoding=UTF8 Model.Main  src/Test/Input/test19.txt < src/Test/Input/right.in > src/Test/Output/test19Output.txt
GOTO END_CASE
:DEFAULTCASE
echo Rossz szam.
GOTO END_CASE
:END_CASE
VER > Nul
GOTO :EOF