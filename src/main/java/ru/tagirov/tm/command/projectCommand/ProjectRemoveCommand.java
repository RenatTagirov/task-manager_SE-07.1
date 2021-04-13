package ru.tagirov.tm.command.projectCommand;

import ru.tagirov.tm.command.AbstractCommand;
import ru.tagirov.tm.entity.Project;
import ru.tagirov.tm.init.ServiceLocator;

import java.io.IOException;

public class ProjectRemoveCommand extends AbstractCommand {

    public ProjectRemoveCommand() {
    }

    @Override
    public String getRoleCommand() {
        return "user";
    }

    @Override
    public String getName() {
        return "project remove";
    }

    @Override
    public String getDescription() {
        return "delete the selected project";
    }

    @Override
    public void setServiceLocator(ServiceLocator serviceLocator) {
        super.setServiceLocator(serviceLocator);
    }

    @Override
    public void execute() throws IOException {
        if(!(serviceLocator.getUser() == null)) {
            System.out.println("[PROJECT REMOVE]");
            System.out.println("ENTER PROJECT NAME:");
            name = reader.readLine();
            for (Project tmp : serviceLocator.getIProjectService().findAll()) {
                if (tmp.getName().equals(name) && tmp.getUserId().equals(serviceLocator.getUser().getId())) {
                    serviceLocator.getIProjectService().remove(tmp.getId());
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
