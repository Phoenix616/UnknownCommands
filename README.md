UnknownCommands
==============
Bukkit Plugin which hides commands from the user by displaying them the servers default unknown command message if they use an non-whitelisted command.

With that you will be able to simply make whole plugins not detectable via brute-forcing possible commands to get an permissions denied message. You could make a Bukkit server to a Vanilla server that way! Or you could prevent that griefers know that you have LogBlock or Prism!

Why not simply use Permissions?
--------
Permissions are neat but they have one shortcoming: 

The user get a permissions denied message. After that he knows that you have the plugin with that command installed. You could simply display the permissions denied message also for unknown commands but using the unknown command itself for commands you haven't whitelisted is more sexy!

But don't get me wrong, you should use Permissions! Even with this Plugin! It is only intended for small, public servers or to just set a general whitelist and because the next point

It has some important Shortcomings!
--------
* This plugin cancels the use of all commands which are not whitelisted. 
* You cannot allow commands by permissions. If it isn't on the whitelist it isn't accessible for normal players.
* Tab complete is currently (v0.1) not canceled. Use a plugin like [AntiCommandTab](http://dev.bukkit.org/bukkit-plugins/anticommandtab/ "AntiCommandTab Bukkit Plugin") if you care about that!
* If you whitelist /help, /? oder /plugin you will expose all your commands and plugins. Use the [help.yml](http://wiki.bukkit.org/Help.yml "Help.yml on the Bukkit Wiki") file and other plugins like [Vanilla](http://dev.bukkit.org/bukkit-plugins/vanilla/ "Vanilla Bukkit Plugin") to hide plugins from this messages!
* Commands are only whitelisted as is and not with their plugin-specific prefix! You have to allow them separately! *(This may change in a future version)*

Why a Whitelist?
--------
You may ask why I implemented it in the style of a command**white**list rather then a command**black**list and the answer is simple: Whitelists are always more reliable then blacklists! With the existence of aliases and the [addition](http://forums.bukkit.org/threads/craftbukkit-1-7-2-r0-3-is-now-available.232215/ "Scroll down to New Command Handling") to Bukkit to address all commands with a prefix of their plugins name there is a whole ranch of commands you would have to blacklist so a whitelist is way more suitable to deny access to the commands!

You may also ask why a whitelist instead of really checking if the player has the permissions to use the command. The reason is, again, simple: I just wanted it that way. It is the most simple one and if you look at the source you will see that I did not invest abnormal time to make it. Maybe I will implement a more permission-based approach in the future but currently you have to stick with the whitelist!

Permissions and Commands
--------
* */unhidecommand* - **unknowncommands.unhide** - lets you add a command to the commandwhitelist
* */hidecommand* - **unknowncommands.hide** - lets you remove a command from the commandwhitelist
* **unknowncommands.bypass** - bypass the features of this plugin
Operators have all three permissions on default!

[config.yml](https://github.com/Phoenix616/UnknownCommands/blob/master/UnkownCommands/config.yml "Default UnknownCommands config.yml")
--------

    # Don't want to use the server's default messages? Set your own. (This will override all other error message costumizations!)
    errormessage: []
    # This is the whitelist section (obviously)
    whitelist:
    # Add commands in the following style (or via console/ingame commands)
    - tell
    - me
    - version

devBukkit Page
--------
http://dev.bukkit.org/bukkit-plugins/unknowncommands/

Donations
--------
Programming can be exhausting!
Keep me going by buying me a beer with Ðogecoins:
D7G4Svo7GTNHpuhjwUBj2k7qANnZHwYD4p
