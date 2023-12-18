package com.dorllenius.fizzleplugin;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.dorllenius.fizzleplugin.RoleSelector.Teams;

/*
 * fizzleplugin java plugin
 */
public class Plugin extends JavaPlugin {
  private static final Logger LOGGER = Logger.getLogger("fizzleplugin");

  private RoleSelector roleSelector;

  public Plugin() {
    roleSelector = new RoleSelector();
  }

  @Override
  public void onEnable() {
    roleSelector.createRoles();
    var team = roleSelector.randomEnum(Teams.class);
    ChatColor color = roleSelector.teams.get(team);
    PlayerJoin playerJoin = new PlayerJoin(team, color);

    getServer().getPluginManager().registerEvents(playerJoin, this);
    LOGGER.info("fizzleplugin enabled");
  }

  @Override
  public void onDisable() {
    LOGGER.info("fizzleplugin disabled");
  }
}
