package codes.shawlas.data.file;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.io.File;
import java.io.IOException;
import codes.shawlas.data.exception.file.FileAlreadyExistsException;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;

public interface FileStorage {

    @NotNull Path getRoot();

    @NotNull MetaFiles getFiles();

    // Classes

    interface MetaFiles extends Iterable<@NotNull MetaFile> {

        /**
         * Create an empty file
         *
         * @throws IllegalArgumentException if file {@code name} has an invalid character
         * @throws FileAlreadyExistsException if {@code name} is already in use
         * @throws IOException if an I/O error occurs
         * */
        @NotNull MetaFile create(@NotNull String name) throws FileAlreadyExistsException, IOException;

        /**
         * Create an empty file into directory
         *
         * @throws InvalidPathException if the path with {@code directories} and file {@code name} result as invalid path
         * @throws FileAlreadyExistsException if {@code name} is already in use in the directory
         * @throws IOException if an I/O error occurs
         * */
        @NotNull MetaFile create(@NotNull String directories, @NotNull String name) throws FileAlreadyExistsException, IOException, InvalidPathException;

        /**
         * Stores an uploaded file
         *
         * @throws UnsupportedOperationException if file path is incorrect. this includes it being a directory
         * @throws FileAlreadyExistsException if file name is already stored in this path
         * @throws IOException if an I/O error occurs
         * */
        @NotNull MetaFile store(@NotNull File file) throws FileAlreadyExistsException, IOException;

        boolean delete(@NotNull Path path);

        @NotNull Optional<? extends @NotNull MetaFile> get(@NotNull Path path);

        @Unmodifiable @NotNull Collection<? extends @NotNull MetaFile> toCollection();
    }
}
