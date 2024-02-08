package assignments;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Logger;

import assignments.annotations.FullNameProcessorGeneratorAnnotation;
import assignments.annotations.ListIteratorAnnotation;
import assignments.annotations.ReadFullProcessorNameAnnotation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalProcessor {
    private StringBuilder processorName;
    private Long period = 10_000_000_000_000L;
    protected StringBuilder processorVersion;
    private Integer valueOfCheap;
    private Scanner informationScanner;
    private static List<String> stringArrayList;
    private static final Logger LOGGER = Logger.getLogger(LocalProcessor.class.getName());

    public LocalProcessor(StringBuilder processorName, Long period, StringBuilder processorVersion, Integer valueOfCheap,
                          Scanner informationScanner, List<String> stringArrayList) {
        this.processorName = processorName;
        this.period = period;
        this.processorVersion = processorVersion;
        this.valueOfCheap = valueOfCheap;
        this.informationScanner = informationScanner;
        LocalProcessor.stringArrayList = stringArrayList;
    }

    public LocalProcessor() {
    }

    @ListIteratorAnnotation
    public void listIterator(List<String> stringList) {
        stringArrayList = new LinkedList<>(stringList);
        for (String s : stringList) {
            if (s != null) {
                LOGGER.info("Iteration inside stringList " + s);
                System.out.println(s.hashCode());
            } else {
                LOGGER.info("Iterating over stringList: null");
            }
        }
    }

    @FullNameProcessorGeneratorAnnotation
    public String fullNameProcessorGenerator(List<String> stringList) {
        for (String s : stringList) {
            if (s!=null){
                processorName.append(s).append(' ');
            }
        }
        LOGGER.info("Full name" + processorName);
        return processorName.toString();
    }

    @ReadFullProcessorNameAnnotation
    public void readFullProcessorName(File file) throws FileNotFoundException {
        if (file == null){
            throw new RuntimeException("File is empty");
        }
        try {
            informationScanner = new Scanner(file);
            while (informationScanner.hasNext()) {
                processorVersion.append(informationScanner.nextLine());
            }
        } catch (IOException e) {
            LOGGER.warning("Error reading processor information" + e);
            throw new RuntimeException("Error reading processor information", e);
        }
        finally {
            if (informationScanner!=null){
                informationScanner.close();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocalProcessor that = (LocalProcessor) o;

        if (!Objects.equals(processorName, that.processorName))
            return false;
        if (!Objects.equals(period, that.period)) return false;
        if (!Objects.equals(processorVersion, that.processorVersion))
            return false;
        if (!Objects.equals(valueOfCheap, that.valueOfCheap)) return false;
        return Objects.equals(informationScanner, that.informationScanner);
    }

    @Override
    public int hashCode() {
        int result = processorName != null ? processorName.hashCode() : 0;
        result = 31 * result + (period != null ? period.hashCode() : 0);
        result = 31 * result + (processorVersion != null ? processorVersion.hashCode() : 0);
        result = 31 * result + (valueOfCheap != null ? valueOfCheap.hashCode() : 0);
        result = 31 * result + (informationScanner != null ? informationScanner.hashCode() : 0);
        return result;
    }
}
