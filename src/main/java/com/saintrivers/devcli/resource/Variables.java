package com.saintrivers.devcli.resource;

import java.util.HashMap;
import java.util.Map;

public class Variables extends HashMap<String, String> {
    public Variables(String databaseVendor, String database, String username, String password) {
        this.put("POSTGRES_DB", database);
        this.put("POSTGRES_USER", username);
        this.put("POSTGRES_PASSWORD", password);
    }


    public static Variables using(String databaseVendor, String database, String username, String password) {
        return new Variables(databaseVendor, database, username, password);
    }

    public static Map<String, String> of(String databaseVendor, String database, String username, String password) {
        return new HashMap<>() {{
            put("POSTGRES_DB", database);
            put("POSTGRES_USER", username);
            put("POSTGRES_PASSWORD", password);
        }};
    }

    public Map<String, String> getVariables() {
        return this;
    }
}
