package org.gradle.profiler;

import org.gradle.tooling.ProjectConnection;

import java.util.List;
import java.util.function.Consumer;

public class ToolingApiInvoker extends BuildInvoker {
    private final ProjectConnection projectConnection;

    public ToolingApiInvoker(ProjectConnection projectConnection, List<String> jvmArgs, List<String> gradleArgs, PidInstrumentation pidInstrumentation,
                             Consumer<BuildInvocationResult> resultsConsumer) {
        super(jvmArgs, gradleArgs, pidInstrumentation, resultsConsumer);
        this.projectConnection = projectConnection;
    }

    @Override
    protected BuildInvoker copy(List<String> jvmArgs, List<String> gradleArgs, PidInstrumentation pidInstrumentation, Consumer<BuildInvocationResult> resultsConsumer) {
        return new ToolingApiInvoker(projectConnection, jvmArgs, gradleArgs, pidInstrumentation, resultsConsumer);
    }

    @Override
    protected void run(List<String> tasks, List<String> gradleArgs, List<String> jvmArgs) {
        run(projectConnection.newBuild(), build -> {
            build.forTasks(tasks.toArray(new String[0]));
            build.withArguments(gradleArgs);
            build.setJvmArguments(jvmArgs);
            build.run();
            return null;
        });
    }
}

