package hse.file;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import hse.domains.facade.HseFacade;

import java.io.StringReader;
import java.util.List;

public class CsvFileImporter extends Reader<HseFacade> {

    @Override
    protected HseFacade parseData(String content) {
        try {
            CsvToBean<HseFacade> csvToBean = new CsvToBeanBuilder<HseFacade>(new StringReader(content))
                    .withType(HseFacade.class)    // Указываем класс для десериализации
                    .withIgnoreLeadingWhiteSpace(true)  // Игнорируем пробелы в начале строки
                    .build();

            List<HseFacade> hseFacades = csvToBean.parse();  // Парсим CSV в объекты

            return hseFacades.isEmpty() ? null : hseFacades.get(0);

        } catch (Exception e) {
            System.out.println("Ошибка при парсинге CSV: " + e.getMessage());
            return null;
        }
    }
}