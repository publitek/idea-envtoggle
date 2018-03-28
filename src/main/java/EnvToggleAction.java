import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

public class EnvToggleAction extends AnAction {

    public EnvToggleAction() {
        super("Hello");
    }

    public void actionPerformed(AnActionEvent event) {
        Project project = event.getProject();
        Messages.showMessageDialog(project, "Hello world!", "Greeting", Messages.getInformationIcon());
    }
}