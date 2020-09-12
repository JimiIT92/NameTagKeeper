# NAME TAG KEEPER ![CodeQL](https://github.com/JimiIT92/NameTagKeeper/workflows/CodeQL/badge.svg) ![https://www.spongepowered.org/](https://img.shields.io/badge/Sponge-7.2.0-yellow) ![https://ore.spongepowered.org/Francesco_Jimi/Name-Tag-Keeper/versions/1.0](https://img.shields.io/badge/Release-1.0-yellow)
<p align="center"><img align="center" width=40% height=40% src="logo.png"/></p>

Have you ever been frustrated by the fact that when your beloved pet dies there's nothing to remember him? Or the fact that you spent 800+ hours to find a Name Tag that you now won't get back? Well, my friend, I got some good news for you! 

Introducing: **NameTagKeeper**! 

# 🔧 How it works
Every time a living entity dies on your server, a Name Tag with its name will be dropped. This
won't take your precious pet back, but at least you'll have the ability to name another one with the same name
without using another one.

# ⚙ Configuring

The configuration file allows you to configure different aspects of the plugin

#### update_check #####

This will let you configure if the plugin should periodically check for updates.
It will also let you define which permission a Player must have to be notified about a new update.

#### entries #### 

This is where you will configure some special cases for entities. Let's say you want all mobs to drop the Name Tag
except Sheeps (HOW DARE YOU?). Or you want that only Pigs named `Dinnerbone` will drop the Name Tag. Or maybe any mob
named `Grumm` will not drop the Name Tag. This configuration allows you to do just that. 

To specify an entry for a specific
mob you must create a new configuration object using the mob id. For example, if you want to create a rule for Sheeps, you
need to type `"minecraft:sheep": {}` (quotation marks included). By doing this you will prevent all Sheeps from dropping
the Name Tag. 

Now, let's say that you want Sheeps to be able to drop Name Tags but only if they are named `Dinnerbone`. 
To do this you need to specify the `drop` property like this:
```
"minecraft:sheep": {
    drop=["Dinnerbone"]
}
```
Similar you can set a rule so all Sheeps will drop the Name Tag unless they are called `Dinnerbone`.
```
"minecraft:sheep": {
    nodrop=["Dinnerbone"]
}
```

But what if you want to set a rule for all mobs? Well, you can use the `*` entry. This works like any other entry, except
it will be applied to all mobs. And here comes a question: what happens if there are both the `*` entry and a mob specific one.
The answer is: they are merged! 

So, let's see an example: you want that all mobs named `Dinnerbone` will drop the Name Tag,
unless they are Sheeps, in which case you want them to drop also if they are named `Grumm`. To do so, simply configure the
two entries like normal!
```
"*": {
    drop=["Dinnerbone"]
},
"minecraft:sheep": {
    drop=["Grumm"]
}
```

**PLEASE NOTE THAT THIS IS CASE SENSITIVE**, so for example an entity named `dinnerbone` will not drop the Name Tag.

Even further, what if you want all mobs not to drop a Name Tag if they are named `Dinnerbone` unless is a Pig?
Just configure the two entries like normal!
```
"*": {
    nodrop=["Dinnerbone"]
},
"minecraft:pig": {
    drop=["Dinnerbone"]
}
```

The only limit is your imagination (and your needs, of course)!

An example entries configuration could be this

```
entries {
    "*" {
        nodrop=[
            Grumm
        ]
    }
    "minecraft:pig" {
        drop=[
            Grumm
        ]
    }
    "minecraft:sheep" {}
}
```

This will make so any entity will drop a Name Tag unless they are named Grumm. But this rule doesn't apply
to Pigs, which will drop the Name Tag ONLY if they are named Grumm. Same for Sheeps, which won't drop
a Name Tag in any case.

PS: No Minecraft Dev has been tortured during the creation of this plugin.

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
