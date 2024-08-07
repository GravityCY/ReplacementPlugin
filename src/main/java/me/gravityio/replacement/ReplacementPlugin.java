/*
 * This source file was generated by the Gradle 'init' task
 */
package me.gravityio.replacement;

import org.gradle.api.Project;
import org.gradle.api.Plugin;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReplacementPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        var ext = project.getExtensions().create("replacement", ReplacementExtension.class);

        project.afterEvaluate((p) -> {
            File mainFile = new File(ext.fileName);
            for (int i = 0; i < ext.types.size(); i++) {
                Map<String, String> newValues = new HashMap<>();
                String type = ext.types.get(i);
                String taskName = "replace-%s".formatted(type);

                for (Map.Entry<String, List<String>> stringListEntry : ext.values.entrySet()) {
                    newValues.put(stringListEntry.getKey(), stringListEntry.getValue().get(i));
                }

                p.getTasks().register(taskName, ReplacementTask.class, task -> {
                    task.getInputFile().set(mainFile);
                    task.getReplacements().set(newValues);
                });
            }
        });
    }
}