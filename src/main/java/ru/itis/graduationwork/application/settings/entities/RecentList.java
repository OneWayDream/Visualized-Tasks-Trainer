package ru.itis.graduationwork.application.settings.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.TreeMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecentList {

    private TreeMap<String, String> content = new TreeMap<>();

}
