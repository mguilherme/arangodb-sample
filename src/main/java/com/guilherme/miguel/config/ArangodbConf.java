package com.guilherme.miguel.config;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDatabase;
import com.arangodb.velocypack.VPackSlice;
import com.guilherme.miguel.ArangodbSampleProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.time.LocalDateTime;
import java.util.function.Function;

/**
 * @author Miguel Guilherme
 */
@Configuration
public class ArangodbConf {

    @Bean
    public ArangoDB arangoDB(ArangodbSampleProperties properties) {
        ArangoDB.Builder builder = new ArangoDB.Builder();
        builder.host(properties.getHost(), properties.getPort());

        final Function<VPackSlice, Integer> vPackFunction = VPackSlice::getAsInt;
        builder.registerDeserializer(LocalDateTime.class, (parent, vpack, context) -> {
            final VPackSlice dateSlice = vpack.get("date");
            final VPackSlice timeSlice = vpack.get("time");

            return LocalDateTime.of(vPackFunction.apply(dateSlice.get("year")),
                    vPackFunction.apply(dateSlice.get("month")),
                    vPackFunction.apply(dateSlice.get("day")),
                    vPackFunction.apply(timeSlice.get("hour")),
                    vPackFunction.apply(timeSlice.get("minute")),
                    vPackFunction.apply(timeSlice.get("second")));
        });

        return builder.build();
    }

    @Bean
    @DependsOn("arangoDB")
    public ArangoDatabase arangoDatabase(ArangoDB arangoDB, ArangodbSampleProperties properties) {
        return arangoDB.db(properties.getDatabase());
    }
}
