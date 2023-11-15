@echo off
setlocal enableDelayedExpansion
@REM  NOTE: Imagemagick does not provide good quality when converting SVG to PNG
@REM ManPage at https://inkscape.org/doc/inkscape-man.html
@REM The inkscapecom.com executable needs to be used instead of inkscape.exe
set INK_EXE="C:\Program Files\Inkscape\bin\inkscapecom.com"
set FOLDER=%1
@REM set FOLDER=C:\Users\marco\IdeaProjects\eom-commons\src\main\resources\icons\essentials
set SIZES=16 24 32 48
set EXT=png

echo Using Inkscape at %INK_EXE%
%INK_EXE% --version

cd %FOLDER%

for %%F in (*.svg) do (
  echo Converting %%F
  for %%S in (%SIZES%) do (
    set output=%%~nF_%%S.%EXT%
    echo to !output!
    call %INK_EXE% --export-type="%EXT%" "%%F" -w %%S -h %%S -o !output!
  )
)

echo DONE