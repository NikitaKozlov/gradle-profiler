package org.gradle.profiler.mutations;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.File;

public abstract class AbstractJavaSourceFileMutator extends AbstractFileChangeMutator {
    public AbstractJavaSourceFileMutator(File sourceFile) {
        super(sourceFile);
        if (!sourceFile.getName().endsWith(".java")) {
            throw new IllegalArgumentException("Can only modify Java source files");
        }
    }

    @Override
    protected void applyChangeTo(StringBuilder text) {
        CompilationUnit compilationUnit = JavaParser.parse(text.toString());
        applyChangeTo(compilationUnit);
        text.replace(0, text.length(), compilationUnit.toString());
    }

    protected abstract void applyChangeTo(CompilationUnit compilationUnit);
}
