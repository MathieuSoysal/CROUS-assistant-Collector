package io.github.mathieusoysal.archivers;

public enum Archivers implements Archiver {
    ARCHIVER_DAY(new ArchiverDay()), ARCHIVER_HOUR(new ArchiverHour());

    private Archiver archiver;

    Archivers(Archiver archiver) {
        this.archiver = archiver;
    }

    @Override
    public void archive() {
        archiver.archive();
    }

}
