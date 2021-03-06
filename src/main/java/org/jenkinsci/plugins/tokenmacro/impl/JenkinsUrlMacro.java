package org.jenkinsci.plugins.tokenmacro.impl;

import hudson.Extension;
import hudson.Util;
import hudson.model.AbstractBuild;
import hudson.model.TaskListener;
import java.io.IOException;
import jenkins.model.Jenkins;

import org.jenkinsci.plugins.tokenmacro.DataBoundTokenMacro;
import org.jenkinsci.plugins.tokenmacro.MacroEvaluationException;

@Extension
public class JenkinsUrlMacro extends DataBoundTokenMacro {

    private static final String MACRO_NAME = "JENKINS_URL";
    private static final String ALTERNATE_MACRO_NAME = "HUDSON_URL";

    @Override
    public boolean acceptsMacroName(String macroName) {
        return macroName.equals(MACRO_NAME) || macroName.equals(ALTERNATE_MACRO_NAME);
    }

    @Override
    public String evaluate(AbstractBuild<?, ?> context, TaskListener listener, String macroName)
            throws MacroEvaluationException, IOException, InterruptedException {
        String jenkinsUrl = Jenkins.getActiveInstance().getRootUrl();

        if (jenkinsUrl == null) {
            return "";
        }

        if (!jenkinsUrl.endsWith("/")) {
            jenkinsUrl += "/";
        }

        return Util.encode(jenkinsUrl);
    }
}
