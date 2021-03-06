package ru.tagirov.tm.command.taskCommand;

import ru.tagirov.tm.init.Bootstrap;
import ru.tagirov.tm.command.AbstractCommand;
import ru.tagirov.tm.entity.Project;
import ru.tagirov.tm.init.ServiceLocator;
import ru.tagirov.tm.util.TerminalService;

import java.io.IOException;

public class TaskRemoveToProjectCommand extends AbstractCommand {

    public TaskRemoveToProjectCommand() {
    }

    @Override
    public String getRoleCommand() {
        return "user";
    }

    @Override
    public String getName() {
        return "task remove to project";
    }

    @Override
    public String getDescription() {
        return "delete the selected task in the project";
    }

    @Override
    public void setServiceLocator(ServiceLocator serviceLocator) {
        super.setServiceLocator(serviceLocator);
    }

    @Override
    public void execute() throws IOException {
        if (!(Bootstrap.user == null)) {
            System.out.println("[TASK REMOVE TO PROJECT]");
            System.out.println("ENTER PROJECT NAME:");
            nameProject = TerminalService.service();
            System.out.println("ENTER TASK NAME:");
            nameTask = TerminalService.service();
            for (Project tmp : serviceLocator.getIProjectService().findAll()) {
                if (tmp.getName().equals(nameProject) && tmp.getUserId().equals(Bootstrap.user.getId())) {
                    for (int i = 0; i < tmp.taskListToProject.size(); i++) {
                        if (tmp.taskListToProject.get(i).getName().equals(nameTask)) {
                            tmp.taskListToProject.remove(i);
                        }
                    }
                }

            }
            System.out.println("[OK]");
            System.out.println();
        }else{
            System.out.println("[YOU ARE NOT LOGGED IN TO YOUR ACCOUNT!]");
            System.out.println();
        }
    }
}
