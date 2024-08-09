package xyz.herberto.bungeeUtils.utils;

import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CC {

    public static String translate(String s) {;

        Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            String hexCode = s.substring(matcher.start(), matcher.end());
            String replaceSharp = hexCode.replace('#', 'x');

            char[] ch = replaceSharp.toCharArray();
            StringBuilder builder = new StringBuilder("");
            for (char c : ch) {
                builder.append("&" + c);
            }

            s = s.replace(hexCode, builder.toString());
            matcher = pattern.matcher(s);
        }
        return ChatColor.translateAlternateColorCodes('&', s);

    }
}
