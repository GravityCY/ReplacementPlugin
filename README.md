# Replacement Plugin
A simple plugin to replace template strings like this depending on the platforms you define

This was mainly a learning experience for gradle plugins...

## Example:

`build.gradle`
```groovy
replacement {
    fileName = "README.md"
    types = ["modrinth", "curseforge"]
    values = [
            "fabric": ["https://modrinth.com/mod/fabric-api", "https://curseforge.com/minecraft/mc-mods/fabric-api"],
            "yacl": ["https://modrinth.com/mod/yacl", "https://curseforge.com/minecraft/mc-mods/yacl"],
            "modmenu": ["https://modrinth.com/mod/modmenu", "https://curseforge.com/minecraft/mc-mods/modmenu"],
            "inventory_tabs": ["https://modrinth.com/mod/inventory-tabs", "https://www.curseforge.com/minecraft/mc-mods/inventory-tabs-updated"]
    ]
}
```
## The above will create tasks `replaceCurseforge` and `replaceModrinth`
So imagine a readme like this you can get two different readmes copied to your clipboard

`README.md`
```markdown
Some Minecraft Mod, Bla Bla Bla

Dependencies: [Fabric](${fabric}) | [YACL](${yacl}) | [ModMenu](${modmenu}) | [Inventory Tabs](${inventory_tabs})
```

