# TheBus DepartureSound
This mod for TheBus can notify you when the departure time of your start station is reached

## Table of Contents
User Information:
- [Installation](#installation)
- [Configuration](#configuration)
  - [Change Notification Sound](#change-the-notification-sound)
  - [Change Notification Time](#change-the-notification-time)
- [Credits](#credits)

Developer Information
- [Dependency](#dependency)
    - [Maven](#maven)
    - [Gradle](#gradle)


## Installation
This mod install-script needs Powershell 5 (this is included in Windows 10 or higher)
1. Download the current install.bat from the
[Latest Release Page](https://github.com/FlorianWip/thebus-departuresound/releases/latest/)
2. Execute the ``install.bat`` 
3. Execute the ``run.bat`` to start the mod

If you want to update the mod, simply run the ``update.bat``

## Configuration
After the first run, the app will create a data/ directory.
There you can find two files.

### Change the notification sound
Simply replace the audio.wav with your wanted sound. It has to be a wav-sound.
If you want to change it back to the default one, simply delete the audio.wav

## Change the notification time
Per default the notification sound is played after departure time is reached.<br>
In the config.json you can change the notifytime. A value below 0 will inform you earlier.
Above 0 will inform you later. The value represents seconds. The real time, where you get
notified can be up to 1 second off

## Dependency
### Maven
```
<repository>
  <id>flammenfuchs-repo-public</id>
  <name>Flammenfuchs_YT's Repository</name>
  <url>https://repo.flammenfuchs.de/public</url>
</repository>
```
```
<dependency>
    <groupId>de.flammenfuchs</groupId>
    <artifactId>thebus-departuresound</artifactId>
    <version>1.0.0</version>
</dependency>
```
### Gradle
```
maven {
	url "https://repo.flammenfuchs.de/public"
}
```
```
implementation("de.flammenfuchs:thebus-departuresound:1.0.0")
```
## Credits
A special thanks goes to **ma.this44** for giving me the real departure sound of the
main public transportation company from Dresden in germany (DVB) 