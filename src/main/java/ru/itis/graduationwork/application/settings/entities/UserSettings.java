package ru.itis.graduationwork.application.settings.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.graduationwork.application.settings.units.Locale;
import ru.itis.graduationwork.application.settings.units.Theme;
import ru.itis.graduationwork.application.ui.pages.main.MainPageFrame;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSettings {

    private Theme theme = Theme.DARK;
    private Locale locale = Locale.EN;
    private MainPageFrame.Mode mode = MainPageFrame.Mode.DEVELOP;

}
