# NovaLib

An advanced API and library for my Bukkit plugins that can be used for many things, including other plugins, minigames, and so much more!

Join our discord server: https://discord.gg/2TFqyuy

Javadoc: https://docs.novamaday.com/plugins/novalib/

Wiki with WAY more info: https://wiki.novamaday.com/plugins/novalib/start

## About

NovaLib is a robust API for bukkit plugins. It handles NMS code for you so that you don't have to build 10 versions of the same plugin or handle complex class loading. NovaLib also integrates easy to use file utilities, database utilities and so much more!

NovaLib can also be used for Bungee Servers to handle cross-server talk and more.

NovaLib is mainly static typed allowing you to use the various hooks without needing a constant reference to the plugin.

## Current Features

- NMS packet handling so you don't have to touch NMS code.
- CustomConfig object to easily handle custom configs/files for you
- Database API for easy connections to MySQL databases.
- File/folder utilities for convenience.
- Custom entity handling (Work inProgress).
- EXP Timers.
- Easy and simple communicate between Servers in a Bungeecord network.
- Command API for simplifying the way you make commands (and simplifying complex commands)
- And more!


## Planned and In-Progress Features

- SQLite support
- Full and robust minigames API built in (replace NovaGamesLib)
- Further handling of even more NMS code for you
- Region/Cuboid API
- 1.13.x Support
- Custom Entity handling/API (Work in Progress)
- And more!
- Don't see a feature you want? DM me or open a ticket and I will add it in!

## Supported Minecraft Versions

- 1.7.10
- 1.8.x
- 1.9.x
- 1.10.x
- 1.11.x
- 1.12.x

## Issues
Should you find an issue with NovaLib, please create a new issue in the Issues pages on this repository or via Dev Bukkit with a proper ticket.

## Plugins Using NovaLib
Add your plugin below or send us a DM letting us know about your awesome new plugin that utilizes NovaLib!

## Use in Your Plugins
Implementing NovaLib is super simple!
Directions on how to use it are provided below (currently only Maven is supported, gradle coming soon).
### Maven
1. Import into pom.xml by inserting the following (where version is your target version):
    ```xml
       <repository>
           <id>maven-public</id>
           <url>http://repo.novafox161.com/repository/maven-public/</url>
       </repository>
    ```
    
    ```xml
       <dependency>
           <groupId>com.novamaday.novalib</groupId>
           <artifactId>API</artifactId>
           <version>VERSION</version>
       </dependency>
    ```
2. Use the JavaDoc provided here: https://docs.novamaday.com/plugins/novalib/

3. And now you can start using NG in your plugin. Just check the Javadoc and wiki for further help.
   
### Examples and Tutorials
Example code, plugins, and more will soon be provided on our wiki (its a work in progress)

## Contributing
1. Fork this repo and make changes in your own copy
2. Add a test if applicable and run the existing tests with `mvn clean test` to make sure they pass
3. Commit your changes and push to your fork `git push origin master`
4. Create a new pull request and submit it back to us!

### Deprecation Policy

NovaLib will use the following policy for deprecation:

- All Minor.patch versions under the same Major will be compatible
- SNAPSHOT builds may be unstable and/or change many times before official release. Use these builds at your own risk.
- Deprecated classes and methods will be available until the next Major release.