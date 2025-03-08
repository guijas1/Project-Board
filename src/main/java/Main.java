import lombok.var;
import br.com.dio.persistence.migration.*;

import java.sql.SQLException;

import br.com.dio.persistence.config.ConnectorConfig;

public class Main {
    public static void main(String[] args) {
        
    try(var conn = ConnectorConfig.getConnection()) {
        new MigrationStrategy(conn).executeMigration();
    } catch (SQLException e) {
        e.printStackTrace();
    
    }    
}
}