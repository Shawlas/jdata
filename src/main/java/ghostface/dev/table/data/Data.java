package ghostface.dev.table.data;

import ghostface.dev.exception.column.ColumnException;
import ghostface.dev.table.Table;
import ghostface.dev.table.column.Column;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Collection;

public interface Data {

    @NotNull Table getTable();

    /**
     * @throws IllegalArgumentException if Column is not belong to this Data
     * */
    <E> @UnknownNullability E get(@NotNull Column<E> column) throws IllegalArgumentException;

    /**
     * @throws ColumnException if the value is null and the column is either non-nullable or a key.
     * */
    <E> void set(@NotNull Column<E> column, @Nullable E value) throws ColumnException;

    @Unmodifiable @NotNull Collection<Object> toCollection();
}