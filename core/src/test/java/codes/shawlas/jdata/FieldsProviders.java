package codes.shawlas.jdata;

import codes.shawlas.data.core.database.Authentication;
import codes.shawlas.data.core.impl.core.DatabaseImpl;
import codes.shawlas.data.core.impl.core.FileStorageImpl;
import codes.shawlas.data.core.impl.core.NestStorageImpl;
import codes.shawlas.data.core.impl.core.TableStorageImpl;
import org.jetbrains.annotations.NotNull;

import java.net.InetSocketAddress;

public final class FieldsProviders {

    private static final @NotNull DatabaseImpl database;

    static {
        database = new DatabaseImpl(Authentication.create(new InetSocketAddress(80), "user", "test"));
    }

    public static @NotNull FileStorageImpl getFileStorage() {
        return database.getFileStorage();
    }

    public static @NotNull TableStorageImpl getTableStorage() {
        return database.getTableStorage();
    }

    public static @NotNull NestStorageImpl getNestStorage() {
        return database.getNestStorage();
    }

    // Constructor

    private FieldsProviders() {
        throw new UnsupportedOperationException();
    }
}
