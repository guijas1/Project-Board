package br.com.dio.persistence.migration;

import lombok.AllArgsConstructor;
import lombok.var;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import br.com.dio.persistence.config.ConnectorConfig;

import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.Contexts;
import liquibase.LabelExpression;

@AllArgsConstructor
public class MigrationStrategy {

    private final Connection conn;

    public void executeMigration() {
        var originalOut = System.out;
        var originalErr = System.err;
            try (var fos = new FileOutputStream("liquibase.log")) {
                System.setOut(new PrintStream(fos));
                System.setErr(new PrintStream(fos));
                try (var conn = ConnectorConfig.getConnection()) {
                    var database = new JdbcConnection(conn);
                    var liquibase = new Liquibase("db/changelog/db.changelog-master.yml", new ClassLoaderResourceAccessor(), database);
                    liquibase.update(new Contexts(), new LabelExpression());
                    liquibase.close();
                } catch (LiquibaseException | SQLException error) {
                    error.printStackTrace();
                    
                } 
                System.setErr(originalErr);
            } catch (IOException error) {
                error.printStackTrace();
            } finally {
                System.setOut(originalOut);
                System.setErr(originalErr);
            }
        
    }
}


