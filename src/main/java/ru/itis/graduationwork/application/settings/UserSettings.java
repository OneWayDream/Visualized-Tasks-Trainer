package ru.itis.graduationwork.application.settings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.graduationwork.application.ui.pages.main.MainPageFrame;

import java.util.TreeMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSettings {

    private Theme theme = Theme.DARK;
    private Locale locale = Locale.EN;
    private MainPageFrame.Mode mode = MainPageFrame.Mode.DEVELOP;
    private TreeMap<String, String> recentProject = new TreeMap<>();

}
