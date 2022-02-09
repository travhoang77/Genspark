package reload_system.watch_for_reloads;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileWatcher {
    protected final File reloadFile;
    protected final Path reloadDirectory;

    public FileWatcher(File file, Path reloadDirectory) {
        this.reloadFile = file;
        this.reloadDirectory = reloadDirectory;
    }

    public void watch() {
        if (reloadFile.exists()) {
            // delete all other logs created by java logger
            for (var file : Objects.requireNonNull(reloadDirectory.toFile().listFiles())){
                if(!file.getName().equals("reload.log")){
                    file.delete();
                }
            }
            new Thread(() -> {
                Pattern redefinedNamePattern = Pattern.compile("redefined name=([0-9A-Za-z_\\.]+)");
                String newContent;
                Matcher m;
                Constructor<?> constructorForReloadedClass;
                Object reloadedObject;
                while(true){
                    try {
                        Thread.sleep(100);
                        newContent = Files.lines(reloadFile.toPath()).collect(Collectors.joining());
                        if(!newContent.isEmpty()){
                            m = redefinedNamePattern.matcher(newContent);
                            if (m.find()){
                                var check = Class.forName(m.group(1));
                                if (!check.isInterface()){
                                    constructorForReloadedClass = check.getConstructor();
                                    reloadedObject = constructorForReloadedClass.newInstance();
                                    reloadedObject.getClass().getMethod("test").invoke(reloadedObject);
                                }
                            }
                            new FileWriter(reloadFile, false).close();
                        }
                    } catch (IOException | ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
