package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:base-url.properties", "classpath:auth.properties"})

public interface Configuration extends Config {
    @Key("baseUrl")
    String baseUrl();

    @Key("username")
    String username();

    @Key("password")
    String password();
}


