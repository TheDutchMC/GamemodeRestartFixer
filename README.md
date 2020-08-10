# GamemodeRestartFixer
A Spigot plugin for people with hosts that keep breaking stuff.

This plugin can help you if:
- Your host changes the default gamemode for some reason
- Your host enables hardcore for some reason

## IMPORTANT
This plugin relies on NMS (net.minecraft.server) code which changes EVERY minecraft version!  
Make sure you download the correct version for your installation!

## Downloading
The downloads can be found under [releases](https://github.com/TheDutchMC/GamemodeRestartFixer/releases)

## Configuration
The config file can be found at plugins/GamemodeRestartFixer/config.yml

Default config.yml:
```
#The gamemode which should be set on restart. Possible values:
# - SURVIVAL
# - CREATIVE
# - ADVENTURE
# - SPECTATOR
forcedGamemode: "SURVIVAL"

#Enable hardcore. Default: false
hardcore: "false"
```

## Compilig yourself
You need to have run BuildTools for 1.16 at least once!

#### Test jar:
```
./gradlew testJar
```
This will put the jar in ProjectRoot/server/plugins, allowing for rapid testing

#### Release jar
```
./gradlew releaseJar
```
This will put the jar in ProjectRoot/releeases
