package co.com.bancolombia.models.drivenadapters;

import co.com.bancolombia.Constants;
import co.com.bancolombia.models.Module;

public class SecretManagerDrivenAdapter extends Module {
    @Override
    public String getClassNameModule() {
        return Constants.SECRET_MANAGER_CLASS;
    }

    @Override
    public String getModuleClassContent() {
        return Constants.getSecretsManagerClassContent(super.getPackageName(), super.getModulePackage());
    }
    @Override
    public String getInterfaceNameModule() {
        return null;
    }

    @Override
    public String getModuleInterfaceContent() {
        return null;
    }
    @Override
    public String getBuildGradleModule() {
        return  null;
    }

    @Override
    public String getHelperModuleClassContent() {
        return null;
    }

    @Override
    public String getBuildGradleContentModule() {
        return Constants.getBuildGradleSecretsManager();
    }

    @Override
    public String getSettingsGradleModule() {
        return Constants.getSettingsSecretsManagerContent();
    }

    @Override
    public String getInterfaceModule() {
        return  Constants.getSecretsManagerInterfaceContent(super.getPackageName().concat(".").concat(Constants.COMMON).concat(".").concat(Constants.GATEWAYS));
    }
}
