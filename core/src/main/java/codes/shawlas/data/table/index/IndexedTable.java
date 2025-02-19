package codes.shawlas.data.table.index;

import codes.shawlas.data.exception.table.column.ColumnAlreadyIndexedException;
import codes.shawlas.data.exception.table.column.ColumnAttributeException;
import codes.shawlas.data.exception.table.column.ColumnException;
import codes.shawlas.data.exception.table.column.InvalidColumnException;
import codes.shawlas.data.table.standard.Column;
import codes.shawlas.data.table.standard.Element;
import codes.shawlas.data.table.EntryData;
import codes.shawlas.data.table.standard.Table;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Collection;
import java.util.Optional;

/**
 * A Table with support to a retrieval of data operations.
 * The indexed Table save the data into a B-tree structure
 * where the elements are filtered by values and increase the data retrieval
 * */
public interface IndexedTable extends Table {

    @Override
    @NotNull IndexedElements getElements();

    @Override
    @NotNull IndexedColumns getColumns();

    @NotNull Indexes getIndexes();

    // Classes

    interface IndexedElements extends Elements {

        @NotNull IndexedTable getTable();

        /**
         * @throws InvalidColumnException if {@code data#getColumn} does not exists in this table
         * */
        @NotNull Collection<? extends @NotNull Element> getFiltered(@NotNull EntryData<?> value) throws InvalidColumnException;

        /**
         * @throws InvalidColumnException if {@code data#getColumn} does not exists in this table
         * @throws ColumnAttributeException if {@code data#getColumn} is not a key
         * */
        @NotNull Optional<? extends @NotNull Element> getUnique(@NotNull EntryData<?> data) throws ColumnException;

        /**
         * Delete all elements that contains the value
         *
         * @throws InvalidColumnException if {@code data#getColumn} does not exists in this table
         * */
        boolean deleteFiltered(@NotNull EntryData<?> data) throws InvalidColumnException;

        /**
         * @throws InvalidColumnException if {@code data#getColumn} does not exists in this table
         * @throws ColumnAttributeException if {@code data#getColumn} is not a key
         * */
        boolean deleteUnique(@NotNull EntryData<?> data) throws ColumnException;

    }

    interface IndexedColumns extends Columns {

        @NotNull IndexedTable getTable();

        @Unmodifiable @NotNull Collection<? extends @NotNull Column<?>> getIndexed();

    }

    interface Indexes {

        @NotNull IndexedTable getTable();

        /**
         * @throws ColumnAlreadyIndexedException if the column is already indexed
         * @throws InvalidColumnException if the column doest not belong to this table
         * */
        @NotNull Index create(@NotNull Column<?> column) throws ColumnException;

        boolean delete(@NotNull Column<?> column);

        @NotNull Collection<? extends @NotNull Index> getAll();

    }
}