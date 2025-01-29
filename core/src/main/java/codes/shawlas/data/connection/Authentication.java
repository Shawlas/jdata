package codes.shawlas.data.connection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public interface Authentication {

    // Static initializers

    static @NotNull Authentication create(@NotNull InetSocketAddress address, @NotNull String username, @NotNull String password) {
        return new Authentication() {
            @Override
            public @NotNull InetAddress getAddress() {
                return address.getAddress();
            }

            @Override
            public @Range(from = 0, to = 65535) int getPort() {
                return address.getPort();
            }

            @Override
            public @NotNull String getUsername() {
                return username;
            }

            @Override
            public @NotNull String getPassword() {
                return password;
            }
        };
    }

    // Objects

    @NotNull InetAddress getAddress();

    @Range(from = 0, to = 65535) int getPort();

    @NotNull String getUsername();

    @NotNull String getPassword();

}