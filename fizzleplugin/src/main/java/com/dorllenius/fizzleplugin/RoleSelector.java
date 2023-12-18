package com.dorllenius.fizzleplugin;

import java.util.Dictionary;
import java.util.Random;

import org.bukkit.ChatColor;

public class RoleSelector {

    enum Teams {
        BOOGIE_MAN, GREEN, YELLOW, RED, OTHER, NONE
    };

    public Dictionary<Teams, ChatColor> teams;
    private static Random random = new Random();

    public <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public void createRoles() {
        teams.put(Teams.BOOGIE_MAN, ChatColor.BLACK);
        teams.put(Teams.GREEN, ChatColor.GREEN);
        teams.put(Teams.YELLOW, ChatColor.YELLOW);
        teams.put(Teams.RED, ChatColor.RED);
        teams.put(Teams.OTHER, ChatColor.GRAY);
    }

    public Teams selectRandomRole() {
        return randomEnum(Teams.class);
    }

    public void changeRole() {
    }

    public void removeRole() {
    }

}
