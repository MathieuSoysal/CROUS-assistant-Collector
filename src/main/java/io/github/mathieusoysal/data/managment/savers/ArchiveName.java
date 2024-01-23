package io.github.mathieusoysal.data.managment.savers;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

public enum ArchiveName {
    ALL_LOGEMENTS(() -> "all_logements"),
    HASH_ALL_LOGEMENTS(() -> "hash_all_logements"),
    DAY_SUM_UP(() -> "sum-up"),
    HOUR(() -> OffsetDateTime.now().toLocalTime().format(DateTimeFormatter.ofPattern("HH")));

    private final Supplier<String> nameSupplier;

    ArchiveName(Supplier<String> nameSupplier) {
        this.nameSupplier = nameSupplier;
    }

    public String getName() {
        return nameSupplier.get();
    }
}
