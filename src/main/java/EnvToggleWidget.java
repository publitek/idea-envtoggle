import com.intellij.execution.ExecutionManager;
import com.intellij.execution.runners.ExecutionUtil;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.execution.ui.RunContentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.util.Consumer;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.text.MessageFormat;
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
        return EnvToggleContainer.getIcon(myProject);
    }

    @Override
    public StatusBarWidget copy() {
        return new EnvToggleWidget(myProject);
    }

    @Override
    public String getTooltipText() {
        final String format = myBundle.getString("envtoggle.tooltip");
        final String value = EnvToggleContainer.currentValue(myProject);
        return MessageFormat.format(format, value);
    }

    @Override
    public Consumer<MouseEvent> getClickConsumer() {
        return mouseEvent -> {
            EnvToggleContainer.toggleValue(myProject);
            final RunContentManager contentManager = ExecutionManager.getInstance(myProject).getContentManager();
            for (RunContentDescriptor descriptor : contentManager.getAllDescriptors()) {
                ExecutionUtil.restartIfActive(descriptor);
            }
            myStatusBar.updateWidget(ID());
        };
    }
}