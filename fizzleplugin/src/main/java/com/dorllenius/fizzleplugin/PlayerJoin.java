package com.dorllenius.fizzleplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.dorllenius.fizzleplugin.RoleSelector.Teams;

public class PlayerJoin implements Listener {

    public Teams team = Teams.NONE;
    public ChatColor chatColor = ChatColor.GRAY;

    public PlayerJoin(RoleSelector.Teams team, ChatColor chatColor) {
        this.team = team;
        this.chatColor = chatColor;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        createScoreboard(event.getPlayer());
        updateScoreboard();

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        updateScoreboard();
    }

    public void createScoreboard(Player player) {
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("Teams", "dummy",
                chatColor + "Team");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        String playerName = player.getDisplayName();

        Score score = objective.getScore(playerName);
        score.setScore(Bukkit.getOnlinePlayers().size());
        player.setScoreboard(scoreboard);
    }

    public void updateScoreboard() {
        for (Player online : Bukkit.getOnlinePlayers()) {
            Score score = online.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(online.getDisplayName());
            score.setScore(Bukkit.getOnlinePlayers().size());
        }
    }
}
