package com.scu.planeticket.utils;

/**
 * @Program: planeticket
 * @Description:
 * @Author: MOATSH
 * @Create: 2024-03-16 09:57
 **/

public class TimeAdder {
    // 解析ISO 8601格式的时间字符串，并返回分钟总数
    private static int parseIso8601ToMinutes(String time) {
        int hours = 0;
        int minutes = 0;

        int hIndex = time.indexOf("H");
        if (hIndex != -1) {
            hours = Integer.parseInt(time.substring(2, hIndex));
        }

        int mIndex = time.indexOf("M");
        if (mIndex != -1 && hIndex != -1) {
            minutes = Integer.parseInt(time.substring(hIndex + 1, mIndex));
        } else if (mIndex != -1) {
            minutes = Integer.parseInt(time.substring(2, mIndex));
        }

        return hours * 60 + minutes;
    }

    // 将分钟总数转换回ISO 8601格式的字符串
    private static String minutesToIso8601(int totalMinutes) {
        int hours = totalMinutes / 60;
        int minutes = totalMinutes % 60;
        return "PT" + hours + "H" + minutes + "M";
    }

    // 将两个ISO 8601格式的时间字符串相加，并返回结果
    public static String addIso8601Durations(String duration1, String duration2) {
        int minutes1 = parseIso8601ToMinutes(duration1);
        int minutes2 = parseIso8601ToMinutes(duration2);

        int totalMinutes = minutes1 + minutes2;

        return minutesToIso8601(totalMinutes);
    }
}

