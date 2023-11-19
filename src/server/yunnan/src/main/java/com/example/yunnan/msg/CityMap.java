package com.example.yunnan.msg;

import java.util.HashMap;
import java.util.Map;

public class CityMap {
    private static final Map<String, String> cityMap = new HashMap<>();

    static {
        cityMap.put("5300", "云南省");
        cityMap.put("5301", "昆明市");
        cityMap.put("5303", "曲靖市");
        cityMap.put("5304", "玉溪市");
        cityMap.put("5305", "保山市");
        cityMap.put("5306", "昭通市");
        cityMap.put("5307", "丽江市");
        cityMap.put("5308", "普洱市");
        cityMap.put("5309", "临沧市");
        cityMap.put("5323", "楚雄彝族自治州");
        cityMap.put("5325", "红河哈尼族彝族自治州");
        cityMap.put("5326", "文山壮族苗族自治州");
        cityMap.put("5328", "西双版纳傣族自治州");
        cityMap.put("5329", "大理白族自治州");
        cityMap.put("5331", "德宏傣族景颇族自治州");
        cityMap.put("5333", "怒江傈僳族自治州");
        cityMap.put("5334", "迪庆藏族自治州");
    }

    public static String mapTocity(String code) {
        return cityMap.getOrDefault(code, "云南省");
    }
}
