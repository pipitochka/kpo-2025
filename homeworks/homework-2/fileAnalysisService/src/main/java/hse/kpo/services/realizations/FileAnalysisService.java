package hse.kpo.services.realizations;

import hse.kpo.domains.realizations.AnalysisRealization;
import hse.kpo.dto.FileAnalysisDto;
import hse.kpo.mappers.interfaces.FileAnalysisMapperInterface;
import hse.kpo.repositories.FileAnalysisRepositoryInterface;
import hse.kpo.services.interfaces.FileAnalysisServiceInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Log4j2
@Getter
public class FileAnalysisService implements FileAnalysisServiceInterface {

    private final FileAnalysisRepositoryInterface repositoryInterface;

    private final RestTemplate restTemplate = new RestTemplate();

    private final FileAnalysisMapperInterface fileAnalysisMapper;

    @Value("${remote.filestorage-service.base-url}")
    private String fileStorageBaseUrl;


    @Override
    public Optional<FileAnalysisDto> getFileAnalysisByFileId(int fileId) {
        Optional<AnalysisRealization> existingAnalysis = repositoryInterface.findByFileId(fileId);

        if (existingAnalysis.isPresent()) {
            AnalysisRealization analysis = existingAnalysis.get();
            return Optional.of(fileAnalysisMapper.toDto(analysis));
        }

        Optional<String> file;
        try {
            file = getFileContentFromFileStorage(fileId);
        } catch (Exception e) {
            return Optional.empty();
        }
        var fileContent = file.get();
        if (fileContent == null || fileContent.isEmpty()) {
            return Optional.empty();
        }


        AnalysisRealization newAnalysis = new AnalysisRealization();
        newAnalysis.setFileId(fileId);
        newAnalysis.setWordCount(countWords(fileContent));
        newAnalysis.setParagraphCount(countParagraphs(fileContent));
        newAnalysis.setCharacterCount(fileContent.length());

        repositoryInterface.save(newAnalysis);

        return Optional.of(fileAnalysisMapper.toDto(newAnalysis));

    }



    @Override
    public List<FileAnalysisDto> getAllFileAnalysis() {
        return repositoryInterface.findAll().stream()
                .map(fileAnalysisMapper::toDto)
                .toList();
    }

    @Override
    public boolean deleteFileAnalysis(int fileId) {
        Optional<AnalysisRealization> analysis = repositoryInterface.findByFileId(fileId);
        if (analysis.isPresent()) {
            repositoryInterface.delete(analysis.get());
            return true;
        }
        return false;
    }

    private int countWords(String text) {
        if (text == null || text.isBlank()) {
            return 0;
        }
        String[] words = text.trim().split("\\s+");
        return words.length;
    }


    private int countParagraphs(String text) {
        if (text == null || text.isBlank()) {
            return 0;
        }
        String[] paragraphs = text.trim().split("(\\r?\\n){2,}");
        return paragraphs.length;
    }

    private Optional<String> getFileContentFromFileStorage(int fileId) {
        String url = fileStorageBaseUrl + "/api/files/" + fileId + "/content";
        log.info("Requesting file content from: {}", url);
        try {
            return Optional.ofNullable(restTemplate.getForObject(url, String.class));
        } catch (HttpClientErrorException.NotFound e) {
            log.warn("Файл с id {} не найден в FileStorage", fileId);
            return Optional.empty();
        } catch (Exception e) {
            log.error("Ошибка при получении содержимого файла: {}", e.getMessage());
            return null;
        }
    }
}
