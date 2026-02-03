@ECHO OFF

if not exist ..\bin mkdir ..\bin
del ACTUAL.TXT 2>nul

javac -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\*.java
IF ERRORLEVEL 1 exit /b 1

java -classpath ..\bin Tasky < %1 > ACTUAL.TXT
FC ACTUAL.TXT %2