package ru.itis.graduationwork.application.settings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSettings {

    private Theme theme = Theme.DARK;
    private Locale locale = Locale.EN;
    private Mode mode = Mode.DEVELOP;
    private String backgroundImageName;

}
