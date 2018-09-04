package com.generator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 模板生成器
 * 
 * @author sl
 *
 */
public class Generator {
  private static final String projectName = "holyshared";
  private static final String modualName = "user";
  private static final String projectType = "mongo";// mysql//mongo//both
  private static final String templateProjectPath =
      "/Users/sl/Downloads/{projectName}-{modualName}-service";

  public static void main(String[] args) throws IOException {
    generator(templateProjectPath);
  }

  private static void generator(String demoProjectPath) throws IOException {
    File file = new File(demoProjectPath);
    if (!file.exists()) {
      return;
    }
    File[] listFiles = file.listFiles();
    if (listFiles.length == 0) {
      return;
    }
    for (File file2 : listFiles) {
      String absolutePath = file2.getAbsolutePath();
      if (absolutePath.contains("/.git") || absolutePath.contains("/.settings")
          || absolutePath.contains("/.DS_Store") || absolutePath.contains("/.project")
          || absolutePath.contains("/target")) {
        continue;
      }

      if (file2.isFile()) {
        createNewProject(file2);
      } else if (file2.isDirectory()) {
        String path = file2.getAbsolutePath();
        String newProjectPath = path;
        if ("mongo".equals(projectType) && newProjectPath.contains("mapper")) {
          continue;
        }
        if ("mysql".equals(projectType) && newProjectPath.contains("repository")) {
          continue;
        }

        if (newProjectPath.contains("{projectName}")) {
          newProjectPath = newProjectPath.replaceAll("\\{projectName\\}", projectName);
        }
        if (newProjectPath.contains("{modualName}")) {
          newProjectPath = newProjectPath.replaceAll("\\{modualName\\}", modualName);
        }
        File newFile = new File(newProjectPath);
        if (!newFile.exists()) {
          newFile.mkdirs();
        }
        generator(absolutePath);
      }
    }

  }

  private static void createNewProject(File file2) throws IOException {
    String absolutePath = file2.getAbsolutePath();
    if ("mongo".equals(projectType) && (absolutePath.contains("mybatis-config.xml")
        || absolutePath.contains("PingMapper.java") || absolutePath.contains("PingMapper.xml"))) {
      return;
    }
    if ("mysql".equals(projectType) && absolutePath.contains("CommonRepository.java")) {
      return;
    }
    if (absolutePath.contains("{projectName}")) {
      absolutePath = absolutePath.replaceAll("\\{projectName\\}", projectName);
    }
    if (absolutePath.contains("{modualName}")) {
      absolutePath = absolutePath.replaceAll("\\{modualName\\}", modualName);
    }

    BufferedReader br =
        new BufferedReader(new InputStreamReader(new FileInputStream(file2), "utf8"));
    BufferedWriter bw =
        new BufferedWriter(new OutputStreamWriter(new FileOutputStream(absolutePath)));
    String line;
    boolean b = false;
    while ((line = br.readLine()) != null) {
      if (line.contains("{projectName}")) {
        line = line.replaceAll("\\{projectName\\}", projectName);
      }
      if (line.contains("{modualName}")) {
        line = line.replaceAll("\\{modualName\\}", modualName);
      }
      switch (projectType) {
        case "mysql":
          if (line.contains("<mysql-config>") || line.contains("</mysql-config>")) {
            continue;
          }
          if (line.contains("<mongo-config>")) {
            b = true;
          }
          if (line.contains("</mongo-config>")) {
            b = false;
            continue;
          }
          break;
        case "mongo":
          if (line.contains("<mongo-config>") || line.contains("</mongo-config>")) {
            continue;
          }
          if (line.contains("<mysql-config>")) {
            b = true;
          }
          if (line.contains("</mysql-config>")) {
            b = false;
            continue;
          }
          break;
        case "both":
          if (line.contains("</mongo-config>") || line.contains("<mongo-config>")
              || line.contains("</mysql-config>") || line.contains("<mysql-config>")) {
            continue;
          }
          break;
        default:
          break;
      }
      if (!b) {
        bw.write(line);
        bw.newLine();
      }
    }
    bw.flush();
    bw.close();
    br.close();
  }
}
