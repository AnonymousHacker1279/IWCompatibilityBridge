![Immersive Weapons Logo](logo.png)

# Immersive Weapons Compatibility Bridge

[![](http://cf.way2muchnoise.eu/full_633345_Downloads.svg)](https://www.curseforge.com/minecraft/mc-mods/immersive-weapons-compatibility-bridge)
[![](http://cf.way2muchnoise.eu/versions/633345.svg)](https://www.curseforge.com/minecraft/mc-mods/immersive-weapons-compatibility-bridge)
[![CodeFactor](https://www.codefactor.io/repository/github/anonymoushacker1279/iwcompatibilitybridge/badge/master)](https://www.codefactor.io/repository/github/anonymoushacker1279/iwcompatibilitybridge/overview/master)

## Add inter-mod compatibility to Immersive Weapons

The IWCB mod adds support for various mods. Those are:

- Just Enough Items
- What The Hell Is That?<sup>1</sup>
- Patchouli
- Project MMO

<sup>1 - WTHIT is a Waila fork; the plugin *may* work with other forks.</sup>

[Interested in the development of this project, or just want to hang out? Join the Official Discord server](https://discord.gg/WNMCTg7TsT)

### Just Enough Items

JEI support consists of a recipe handler for the Tesla Synthesizer.

#### Disabling: Set the `enable_jei` key to `false` in the configuration.

### What The Hell is That?

WTHIT support consists of an override handler for the Pitfall block to make it appear like regular grass blocks.

#### Disabling: Set the `enable_wthit` key to `false` in the configuration.

### Patchouli

Patchouli support consists of two books for guiding players. The Encyclopedia is the main one, and it is granted on
join. There is also a lorebook item which can be found in certain structures around the world.

#### Disabling: Use a datapack to remove the Patchouli books under `data/iwcompatbridge/patchouli_books`

### Project MMO

PMMO support consists of EXP-granting events for crafting objects with the Small Parts Table or Tesla Synthesizer. More
events to come!

#### Disabling: Set the `enable_pmmo` key to `false` in the configuration.

## Version Support

Active development will always be focused on the latest Immersive Weapons version. Bugfixes may be ported to older
versions, but you should always upgrade when possible.

## Contributing

See [CONTRIBUTING.md](https://github.com/AnonymousHacker1279/ImmersiveWeapons/blob/master/CONTRIBUTING.md). The same
information applies here as it does for IW.

## Bug Reports and Feature Suggestions

See [README.md](https://github.com/AnonymousHacker1279/ImmersiveWeapons#bug-reports-and-feature-suggestions). The same
information applies here as it does for IW.