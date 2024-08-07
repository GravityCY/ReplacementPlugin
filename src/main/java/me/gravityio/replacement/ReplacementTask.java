package me.gravityio.replacement;

import org.gradle.api.DefaultTask;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.provider.MapProperty;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.InputFile;
import org.gradle.api.tasks.TaskAction;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicReference;

public abstract class ReplacementTask extends DefaultTask {

    @Input
    public abstract MapProperty<String, String> getReplacements();
    @InputFile
    public abstract RegularFileProperty getInputFile();

    @TaskAction
    void replacementTask() {
        try {
            File file = this.getInputFile().getAsFile().get();
            AtomicReference<String> newText = new AtomicReference<>(Files.readString(file.toPath()));
            this.getReplacements().get().forEach((key, value) -> newText.set(newText.get().replace("${" + key + "}", value)));
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(newText.get()), null);
        } catch (IOException ignored) {}
    }
}