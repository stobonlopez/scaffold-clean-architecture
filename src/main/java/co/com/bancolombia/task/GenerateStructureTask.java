package co.com.bancolombia.task;

import co.com.bancolombia.Constants;
import co.com.bancolombia.Utils;
import org.gradle.api.DefaultTask;
import org.gradle.api.logging.Logger;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.options.Option;
import java.io.IOException;


public class GenerateStructureTask extends DefaultTask {
    private Logger logger = getProject().getLogger();
    private String packageName = "co.com.bancolombia";
    private String type = "imperative";
    private String projectName = "cleanArchitecture";

    @Option(option = "package", description = "Set the principal package to use in the project")
    public void setPackage(String packageName) { this.packageName = packageName; }

    @Option(option = "type", description = "Set project type, the options are  (reactive | imperative) ")
    public void setType(String type) {
        this.type = type;
    }

    @Option(option = "name", description = "Set the project name, by default is cleanArchitecture ")
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @TaskAction
    public void generateStructureTask() throws IOException {
        logger.lifecycle("Clean Architecture plugin version: {}", Utils.getVersionPlugin());
        logger.lifecycle("Package: {}", packageName);
        packageName = packageName.replaceAll("\\.", "\\/");
        logger.lifecycle("Project Type: {}", type);
        logger.lifecycle("Project Name: {}", projectName);
        logger.lifecycle("Generating Dirs");
        createDirs();
        logger.lifecycle("Generated Dirs");
        logger.lifecycle("Writing in Files");
        writeFiles();
        logger.lifecycle("Writed in Files");
    }

    private void createDirs()  {
        getProject().mkdir(Constants.DEPLOYMENT);
        getProject().mkdir(Constants.APPLICATION.concat("/").concat(Constants.MAIN_JAVA).concat("/").concat(packageName).concat("/").concat(Constants.CONFIG));
        getProject().mkdir(Constants.APPLICATION.concat("/").concat(Constants.TEST_JAVA).concat("/").concat(packageName));
        getProject().mkdir(Constants.APPLICATION.concat("/").concat(Constants.MAIN_RESOURCES));

        getProject().mkdir(Constants.INFRASTRUCTURE.concat("/").concat(Constants.DRIVEN_ADAPTERS));
        getProject().mkdir(Constants.INFRASTRUCTURE.concat("/").concat(Constants.ENTRY_POINTS));
        getProject().mkdir(Constants.INFRASTRUCTURE.concat("/").concat(Constants.HELPERS));

        getProject().mkdir(Constants.DOMAIN.concat("/").concat(Constants.MODEL).concat("/").concat(Constants.MAIN_JAVA).concat("/").concat(packageName).concat("/").concat(Constants.MODEL));
        getProject().mkdir(Constants.DOMAIN.concat("/").concat(Constants.MODEL).concat("/").concat(Constants.TEST_JAVA).concat("/").concat(packageName).concat("/").concat(Constants.MODEL));
        getProject().mkdir(Constants.DOMAIN.concat("/").concat(Constants.USECASE).concat("/").concat(Constants.MAIN_JAVA).concat("/").concat(packageName).concat("/").concat(Constants.USECASE));
        getProject().mkdir(Constants.DOMAIN.concat("/").concat(Constants.USECASE).concat("/").concat(Constants.TEST_JAVA).concat("/").concat(packageName).concat("/").concat(Constants.USECASE));

    }

    private void writeFiles() throws IOException {
        Utils.writeString(getProject(), Constants.DOMAIN.concat("/").concat(Constants.USECASE).concat("/").concat(Constants.BUILD_GRADLE), Constants.BUILD_GRADLE_USE_CASE_CONTENT);
        Utils.writeString(getProject(), Constants.DOMAIN.concat("/").concat(Constants.MODEL).concat("/").concat(Constants.BUILD_GRADLE),"");
        Utils.writeString(getProject(), Constants.DEPLOYMENT.concat("/").concat(Constants.DOCKERFILE), Constants.DOCKER_FILE_CONTENT);
        Utils.writeString(getProject(), Constants.LOMBOK_CONFIG, Constants.LOMBOK_CONFIG_CONTENT);
        Utils.writeString(getProject(), Constants.GITIGNORE, Constants.GIT_IGNORE_CONTENT);
        Utils.writeString(getProject(), Constants.READ_ME, Constants.README_CONTENT);
        Utils.writeString(getProject(), Constants.GRADLE_PROPERTIES, Constants.getGradlePropertiesContent(packageName));
        Utils.writeString(getProject(), Constants.SETTINGS_GRADLE, Constants.getSettingsGradleContent(this.projectName));
        Utils.writeString(getProject(), Constants.BUILD_GRADLE, Constants.getBuildGradleContent());
        Utils.writeString(getProject(), Constants.APPLICATION.concat("/").concat(Constants.MAIN_RESOURCES).concat("/").concat(Constants.APPLICATION_PROPERTIES), Constants.getApplicationPropertiesContent(this.projectName));
        Utils.writeString(getProject(), Constants.APPLICATION.concat("/").concat(Constants.MAIN_RESOURCES).concat("/").concat(Constants.LOG_4_J), Constants.LOG_4_J_CONTENT);
        Utils.writeString(getProject(), Constants.MAIN_GRADLE, Constants.mainGradleContent(type));
        Utils.writeString(getProject(), Constants.APPLICATION.concat("/").concat(Constants.BUILD_GRADLE), Constants.buildGradleApplicationContent(type));
        Utils.writeString(getProject(), Constants.APPLICATION.concat("/").concat(Constants.MAIN_JAVA).concat("/").concat(packageName).concat("/").concat(Constants.MAIN_APPLICATION), Constants.getMainApplicationContent(this.projectName));
    }
}
