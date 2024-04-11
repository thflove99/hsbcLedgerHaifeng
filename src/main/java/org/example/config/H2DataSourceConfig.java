package org.example.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * Initialize the H2 database.
 */
@Slf4j
@Service
@AutoConfigureAfter(DataSource.class) // Initialize this class after DataSource is created
public class H2DataSourceConfig {

    // Initialize SQL scripts
    private static final String schema = "classpath:db/schema.sql";
    private static final String insert = "classpath:db/insert.sql";

    @Autowired
    DataSource dataSource;

    @Autowired
    ApplicationContextRegister applicationContextRegister; // Custom registrar

    // JDK annotation, a method with this annotation will be executed when the project starts,
    // or in other words, when the Spring container is initialized
    @PostConstruct
    public void init() throws Exception {
        log.info("--------------Initializing H2 database----------------------");
        // Load resource files
        Resource resource = applicationContextRegister.getResource(schema);
        // Manually execute SQL statements
        ScriptUtils.executeSqlScript(dataSource.getConnection(), resource);

        Resource resourceInsert = applicationContextRegister.getResource(insert);
        ScriptUtils.executeSqlScript(dataSource.getConnection(), resourceInsert);
    }
}
