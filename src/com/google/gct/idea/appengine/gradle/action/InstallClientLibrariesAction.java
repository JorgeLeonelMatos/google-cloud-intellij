/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gct.idea.appengine.gradle.action;

import com.android.tools.idea.gradle.invoker.GradleInvoker;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

import java.util.Arrays;

/**
 * Action to generate client libraries for an AppEngine endpoints project and copy them to an associated android project
 */
public class InstallClientLibrariesAction extends AnAction {

  private static final String ERROR_MSG_TITLE = "Install Client Libraries";

  @Override
  public void actionPerformed(AnActionEvent e) {
    final Project project = e.getProject();
    final Module appEngineModule = e.getData(LangDataKeys.MODULE);

    if(project == null || appEngineModule == null) {
      Messages.showErrorDialog(project, "Please select an App Engine module.", ERROR_MSG_TITLE);
      return;
    }
    // TODO : check if module is App Engine Module or not

    GradleInvoker.getInstance(project).executeTasks(Arrays.asList(appEngineModule.getName() + ":appengineEndpointsInstallClientLibs"));
  }
}
