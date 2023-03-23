package ru.itis.graduationwork.application.entities.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.TreeMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecentList {

    private TreeMap<String, RecentListEntry> content = new TreeMap<>();

}
