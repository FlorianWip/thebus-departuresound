echo "EN: Welcome to the TheBus-DepartureSound installer"
echo "DE: Willkommen im TheBus-DepartureSound Installer"
echo " "
echo "EN: This mod needs java to operate"
echo "DE: Diese Mod benötigt Java um zu funktionieren"
echo "EN: By typing 'y' you accept the license of java"
echo "DE: Wenn sie die Taste 'y' drücken, stimmen sie den Lizenzbedingungen von Java zu"
echo "URL: https://www.oracle.com/downloads/licenses/javase-license1.html"
choice /C YN /D N /T 30
set input=%ERRORLEVEL%
if %input%==2 echo "EN: You declined the license, Abort"&echo "DE: Sie haben die Lizenzbestimmungen abgelehnt, Abbruch."&pause&exit
powershell -Command "(New-Object Net.WebClient).DownloadFile('https://download.oracle.com/java/20/latest/jdk-20_windows-x64_bin.zip', 'temp.zip')"
powershell Expand-Archive temp.zip -DestinationPath java-binary
powershell -Command "Get-ChildItem 'java-binary' |foreach {Rename-Item $_.FullName -NewName 'jdk20'}"
del temp.zip
echo "EN: You successfully installed java"
echo "DE: Sie haben erfolgreich java installiert"
echo "EN: Installing application"
echo "DE: Anwendung wird installiert"
powershell -Command ^
$response = Invoke-RestMethod -Uri 'https://api.github.com/repos/FlorianWip/thebus-departuresound/releases/latest'; ^
$url = $response.assets[0].browser_download_url; ^
(New-Object Net.WebClient).DownloadFile($url, 'application.jar');
echo "EN: You are now able to double click the file 'run.bat' to start the mod"
echo "DE: Sie können nun die 'run.bat' ausführen, wenn sie die Mod starten wollen"
pause
exit