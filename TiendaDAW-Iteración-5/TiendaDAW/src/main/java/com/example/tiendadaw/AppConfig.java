package com.example.tiendadaw;

import jakarta.faces.annotation.FacesConfig;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.FacesConfig;
import jakarta.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import org.glassfish.soteria.identitystores.annotation.Credentials;
import org.glassfish.soteria.identitystores.annotation.EmbeddedIdentityStoreDefinition;
@EmbeddedIdentityStoreDefinition({
        @Credentials(callerName = "admin@admin", password = "admin", groups = {"ADMINISTRADORES"}),
        @Credentials(callerName = "user@user", password = "user", groups = {"USUARIOS"})
})
@FormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/registro/login.xhtml",
                errorPage = "/registro/login.xhtml?error",


                useForwardToLogin = false
        )
)
@ApplicationScoped
@FacesConfig
public class AppConfig {
}
