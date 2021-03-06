package org.jenkinsci.plugins.tokenmacro.impl;

import hudson.Extension;
import hudson.model.AbstractBuild;
import hudson.model.TaskListener;
import java.io.IOException;

import org.jenkinsci.plugins.tokenmacro.DataBoundTokenMacro;
import org.jenkinsci.plugins.tokenmacro.MacroEvaluationException;

@Extension
public class JobDescriptionMacro extends DataBoundTokenMacro {

    public static final String MACRO_NAME = "JOB_DESCRIPTION";

    @Parameter
    public Boolean removeNewlines = false;

    @Override
    public boolean acceptsMacroName(String macroName) {
        return macroName.equals(MACRO_NAME);
    }

    @Override
    public String evaluate(AbstractBuild<?, ?> build, TaskListener listener, String macroName)
            throws MacroEvaluationException, IOException, InterruptedException {
        String val = build.getParent().getDescription();
        if(val != null) {
            if (removeNewlines) {
                val = val.replaceAll("[\\n\\r]", " ");
            }
        } else {
            val = "";
        }
        return val;
    }
}