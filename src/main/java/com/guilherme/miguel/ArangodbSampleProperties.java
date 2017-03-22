package com.guilherme.miguel;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Miguel Guilherme
 */
@Data
@ConfigurationProperties(prefix = "arangodb")
public class ArangodbSampleProperties {

    private String host = "localhost";
    private int port = 8529;
    private String database = "_system";
    private String username;
    private String password;
}
