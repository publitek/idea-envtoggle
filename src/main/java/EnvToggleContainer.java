import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Map;
import java.util.Objects;


class EnvToggleContainer {
    static private String propertiesKey = "envtoggle.MODE";
    static private String replaceKey = "$MODE";
    static private String initialValue = "DEV";
    static private String secondaryValue = "PROD";

    @NotNull
    static Icon getIcon(@NotNull Project myProject) {
        String value = currentValue(myProject);
        return (Objects.equals(value, secondaryValue)) ? EnvToggleIcons.Red : EnvToggleIcons.Green;
    }

    @NotNull
    static String currentValue(@NotNull Project myProject) {
        final PropertiesComponent propertiesComponent = PropertiesComponent.getInstance(myProject);
        return propertiesComponent.getValue(propertiesKey, initialValue);
    }

    @NotNull
    static String toggleValue(@NotNull Project myProject) {
        final PropertiesComponent propertiesComponent = PropertiesComponent.getInstance(myProject);
        final Boolean toggle = Objects.equals(currentValue(myProject), initialValue);
        final String newValue = toggle ? secondaryValue : initialValue;
        propertiesComponent.setValue(propertiesKey, newValue);
        return newValue;
    }

    static void updateEnv(@NotNull Map<String, String> environmentVariables, @NotNull Project myProject) {
        final String currentMode = currentValue(myProject);
        for (Map.Entry<String, String> entry : environmentVariables.entrySet()) {
            String value = entry.getValue();
            if (value.contains(replaceKey)) {
                entry.setValue(value.replace(replaceKey, currentMode));
            }
        }
    }
}
