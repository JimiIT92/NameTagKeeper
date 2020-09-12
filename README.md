# NAME TAG KEEPER
<p align="center"><img align="center" width=40% height=40% src="logo.png"/></p>

Have you ever been frustrated by the fact that when your beloved pet dies there's nothing to remember him? Or the fact that you spent 800+ hours to find a Name Tag that you now won't got back? Well, my friend, I got some good news for you! 

Introducing: **NameTagKeeper**! 

# 🔧 How it works
Every time a living entity dies on your server, a name tag with its name will be dropped. This
won't take your precious pet back, but at least you'll have the ability to name another one with the same name
without using another name tag.

You can also decide which entities will drop the name tag, or even if they will drop if they have 
a certain name using the configuration file.

# ⚙ Configuring

The configuration file allows you to configure different aspects of the plugin

- **update_check** This will let you configure if the plugin should periodically check for updates.
It will also let you define which permission a Player must have to be notified about a new update.

- **excluded_names** This will prevent any entity with a name in this list to drop a Name Tag.
As the example configuration that comes with the plugin, any entity named `Dinnerbone` will not drop a Name Tag.
**PLEASE NOTE THAT THIS IS CASE SENSITIVE**, so for example an entity named `dinnerbone` will drop the Name Tag

- **excluded_entities** This will prevent any entity whit the ID in this list to drop a Name Tag.
As the example configuration that comes with the plugin, a named pig will not drop a Name Tag.
**PLEASE NOTE THAT THIS IS NOT CASE SENSITIVE** so if you type for example `Minecraft:Pig` it will still
be valid and pigs won't drop a Name Tag (poor souls...)

# 📜 Requirements
NameTagKeeper does not require any additional plugin or mod to run, other than [Sponge itself](https://www.spongepowered.org/downloads/). 
The plugin runs both on Sponge Vanilla and Sponge Forge.

# ✔ Compatibility
NameTagKeeper has been built on top of **Sponge API 7.2.0** and has been tested on **Minecraft 1.12.2**.

Unless some major changes happens on either Sponge API or Minecraft itself, the plugin should work on newest versions as well.

If you find any bug related to a new version of one of these, please report them to the [Issue Tracker](https://github.com/JimiIT92/NameTagKeeper/issues).

# 👨🏼‍💻 How to contribute
You can add your contribute to the project via [Pull Requests](https://github.com/JimiIT92/NameTagKeeper/pulls).

# 🖥 Downloads

You can download NameTagKeeper from the [Sponge Ore](https://ore.spongepowered.org/Francesco_Jimi/Name-Tag-Keeper/versions).

This is the only place where I directly upload new releases. Every other website shouldn't be trusted.

# 🍺 Support the project
Developing and maintaining this plugin requires some time and effort, but after all I really enjoy doing this ❤

This plugin is **FREE TO USE** and will ALWAYS be. If someone asked you some money, you got scammed! 😥 

But if you want to show some support to the project (or just buy me some beer to produce more code), these are the only ways:


<p align="center"><a href="https://www.buymeacoffee.com/JimiIT92" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/default-orange.png" width=330 height=75 alt="Buy Me A Beer"></a>&emsp;&emsp;&emsp;&emsp;&emsp;<a href="https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=26VTWV4CY282S" target="_blank"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/PayPal_logo.svg/1280px-PayPal_logo.svg.png" width=330 height=75 alt="Donate with PayPal"></a></p>

Any other websites asking you money for this project is a scam, and you should immediately report it! 😡

# 😁 In conclusion

I hope you like NameTagKeeper and make it grow! Peace! 😁
