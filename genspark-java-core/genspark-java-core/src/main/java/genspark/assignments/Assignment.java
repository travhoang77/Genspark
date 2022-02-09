package genspark.assignments;
import genspark.assignments.section6.*;
import reload_system.session.Session;

import java.io.IOException;

public interface Assignment {
    String ANSI_RESET = "\u001B[0m";
    String ANSI_RED = "\u001B[31m";
    String ANSI_PURPLE = "\u001B[35m";

    default void test() throws IOException {
        if (
            this.getClass() == Human.class    ||
            this.getClass() == Cat.class      ||
            this.getClass() == Goblin.class   ||
            this.getClass() == Humanoid.class ||
            this.getClass() == Dog.class
        ) {
            System.out.println(ANSI_PURPLE + "COMPILE SUCCESSFUL... now compile the class that the gui is currently reflecting" + ANSI_RESET);
        }
        else if (Session.session == null){
            System.out.println(ANSI_RED + "GUI IS NOT CONNECTED!!!" + ANSI_RESET);
        }
        else {
            Session.sendMessage(backend.harness.dispatcher(this));
        }
    }
}
