@ECHO OFF
SETLOCAL
SET MVNW_WRAPPER_DIR=%~dp0\.mvn\wrapper
SET MVN=mvn
IF EXIST "%MVNW_WRAPPER_DIR%\maven-wrapper.jar" (
  java -jar "%MVNW_WRAPPER_DIR%\maven-wrapper.jar" %*
) ELSE (
  %MVN% %*
)
