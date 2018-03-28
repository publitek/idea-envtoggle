import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.openapi.wm.impl.status.EditorBasedWidget;
import com.intellij.util.Consumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.event.MouseEvent;


public class EnvToggleWidget extends EditorBasedWidget implements StatusBarWidget.TextPresentation {

    protected EnvToggleWidget(@NotNull Project project) {
        super(project);
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

    @Nullable
    @Override
    public WidgetPresentation getPresentation(@NotNull PlatformType type) {
        return this;
    }

    @Override
    public void install(@NotNull StatusBar statusBar) {
        myProject = null;
    }

    @Override
    public void dispose() {

    }

    @NotNull
    @Override
    public String getText() {
        return "HELLLOOOOO!!!";
    }

    @NotNull
    @Override
    public String getMaxPossibleText() {
        return "75";
    }

    @Override
    public float getAlignment() {
        return 0;
    }

    @Nullable
    @Override
    public String getTooltipText() {
        return "TOOL TIP";
    }

    @Nullable
    @Override
    public Consumer<MouseEvent> getClickConsumer() {
        return null;
    }
}