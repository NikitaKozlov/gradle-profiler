package org.gradle.profiler.jfr;

import org.gradle.profiler.JvmArgsCalculator;

import java.util.List;

public class JFRJvmArgsCalculator extends JvmArgsCalculator {
    @Override
    public void calculateJvmArgs(List<String> jvmArgs) {
        jvmArgs.add("-XX:+UnlockCommercialFeatures");
        jvmArgs.add("-XX:+FlightRecorder");
        jvmArgs.add("-XX:FlightRecorderOptions=stackdepth=1024");
        jvmArgs.add("-XX:+UnlockDiagnosticVMOptions");
        jvmArgs.add("-XX:+DebugNonSafepoints");
    }
}
