import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.wm.IdeFrame;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.WindowManager;
import org.jetbrains.annotations.NotNull;


public class EnvToggleComponent implements ProjectComponent {
    private EnvToggleWidget myWidget;
    private Project myProject;
    private IdeFrame myFrame;

    public EnvToggleComponent(Project project) {
        myProject = project;
    }

    public void initComponent() {
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @NotNull
    public String getComponentName() {
        return "GitflowComponent";
    }

    public void projectOpened() {
        myFrame = WindowManager.getInstance().getIdeFrame(myProject);
        final StatusBar statusBar = myFrame.getStatusBar();
        myWidget = new EnvToggleWidget(myProject);
        statusBar.addWidget(myWidget);
    }

    public void projectClosed() {
        if (myWidget != null) {
            final StatusBar statusBar = myFrame.getStatusBar();
            if (statusBar != null) {
                statusBar.removeWidget(myWidget.ID());
            }
            Disposer.dispose(myWidget);
        }
    }
}
