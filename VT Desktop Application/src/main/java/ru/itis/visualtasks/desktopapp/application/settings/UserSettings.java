package ru.itis.visualtasks.desktopapp.application.settings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSettings {

    @Builder.Default
    private Theme theme = Theme.DARK;
    @Builder.Default
    private Locale locale = Locale.EN;
    @Builder.Default
    private Mode mode = Mode.DEVELOP;
    private String backgroundImageName;

}
