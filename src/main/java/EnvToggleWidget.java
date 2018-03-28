import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.util.Consumer;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;


public class EnvToggleWidget implements StatusBarWidget.Multiframe, StatusBarWidget.IconPresentation {
    private static ResourceBundle myBundle = ResourceBundle.getBundle("messages.EnvToggle");
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
        return EnvToggleIcons.Cycle;
    }

    @Override
    public StatusBarWidget copy() {
        return new EnvToggleWidget(myProject);
    }

    @Override
    public String getTooltipText() {
        return myBundle.getString("envtoggle.tooltip");
    }

    @Override
    public Consumer<MouseEvent> getClickConsumer() {
        return mouseEvent -> {
            Messages.showMessageDialog(myProject, "Hello world!", "Greeting", EnvToggleIcons.Cycle);
        };
    }
}