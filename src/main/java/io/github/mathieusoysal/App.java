package io.github.mathieusoysal;

import java.io.IOException;
import java.time.LocalDate;
import java.util.stream.IntStream;

import com.github.forax.beautifullogger.Logger;

import io.github.mathieusoysal.data.managment.collectors.DataCollectorFromArchive;
import io.github.mathieusoysal.data.managment.collectors.DataCollectorFromCrous;
import io.github.mathieusoysal.data.managment.savers.ArchiveName;
import io.github.mathieusoysal.data.managment.savers.DataSaver;
import io.github.mathieusoysal.exceptions.PropertiesNotFoundRuntimeException;
import io.github.mathieusoysal.logement.LogementsClassifier;

public class App {
    private static final Logger LOGGER = Logger.getLogger();
    private static final String MAIL_PROPERTIES_NAME = "MAIL";
    private static final String PASSWORD_PROPERTIES_NAME = "PASSWORD";
    private static final String LINK_TO_DATA_PROPERTIE_NAME = "LINK_TO_DATA";

    public static void main(String[] args) {
        String linkToData = "https://mathieusoysal.github.io/CROUS-assistant-Collector/v1/logements-crous/available/";
        var dataCollector = new DataCollectorFromArchive(linkToData);
        var collectors = new LogementsClassifier();
        IntStream.range(1, 12)
                .mapToObj(i -> dataCollector.getSumUpConvertedOfDay(LocalDate.of(2024, 1, i)))
                .forEach(collectors::addLogements);
        DataSaver.save(ArchiveName.ALL_LOGEMENTS, collectors.getLogements());
    }

    private static void createArchiveSumUpForThisDay() {
        String linkToData = getPropertie(LINK_TO_DATA_PROPERTIE_NAME);
        var dataCollector = new DataCollectorFromArchive(linkToData);
        var sumUpOfTheDay = dataCollector.getSumUpOfDay(LocalDate.now());
        DataSaver.save(ArchiveName.DAY_SUM_UP, sumUpOfTheDay);
    }

    private static boolean sumupdayModIsActivated() {
        return System.getenv(LINK_TO_DATA_PROPERTIE_NAME) != null;
    }

    private static void createArchiveForThisHour()
            throws IOException {
        var logements = DataCollectorFromCrous.getAvailableLogementsWithConnection(getEmail(), getPassword());
        DataSaver.save(ArchiveName.HOUR, logements);
    }

    private static String getPropertie(final String propertieName) {
        LOGGER.info(() -> "Getting " + propertieName + " from environment variables");
        String propertie = System.getenv(propertieName);
        if (propertie == null) {
            LOGGER.error(() -> propertieName + " not found in environment variables");
            throw new PropertiesNotFoundRuntimeException(propertieName);
        }
        return propertie;
    }

    private static String getEmail() {
        return getPropertie(MAIL_PROPERTIES_NAME);
    }

    private static String getPassword() {
        return getPropertie(PASSWORD_PROPERTIES_NAME);
    }

}
