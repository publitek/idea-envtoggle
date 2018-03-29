import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.util.Consumer;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.Objects;
import java.util.ResourceBundle;


public class EnvToggleWidget implements StatusBarWidget.Multiframe, StatusBarWidget.IconPresentation {
    private static ResourceBundle myBundle = ResourceBundle.getBundle("messages.EnvToggle");
    private static String myKey = "envtoggle.MODE";
    private Project myProject;
    private StatusBar myStatusBar;

    EnvToggleWidget(@NotNull Project project) {
        myProject = project;
    }

    @NotNull
    @Override
    public String ID() {
        return getWidgetID();
    }

    @NotNull
    private static String getWidgetID() {
        return EnvToggleWidget.class.getName();
    }

    @Override
    public WidgetPresentation getPresentation(@NotNull PlatformType type) {
        return this;
    }

    @Override
    public void install(@NotNull StatusBar statusBar) {
        myStatusBar = statusBar;
    }

    @Override
    public void dispose() {
        myStatusBar = null;
        myProject = null;
    }

    @Override
    @NotNull
    public Icon getIcon() {
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance(myProject);
        String mode = propertiesComponent.getValue("envtoggle.MODE");
        return (Objects.equals(mode, "PROD")) ? EnvToggleIcons.Red : EnvToggleIcons.Green;
    }

    @Override
    public StatusBarWidget copy() {
        return new EnvToggleWidget(myProject);
    }

    @Override
    public String getTooltipText() {
        final String format = myBundle.getString("envtoggle.tooltip");
        return String.format(format, getMode());
    }

    @Override
    public Consumer<MouseEvent> getClickConsumer() {
        return mouseEvent -> {
            toggleMode();
            myStatusBar.updateWidget(ID());
        };
    }

    private String getMode() {
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance(myProject);
        return propertiesComponent.getValue(myKey);
    }

    private void toggleMode() {
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance(myProject);
        propertiesComponent.setValue(myKey, (Objects.equals(getMode(), "PROD")) ? "DEV" : "PROD");
    }
}