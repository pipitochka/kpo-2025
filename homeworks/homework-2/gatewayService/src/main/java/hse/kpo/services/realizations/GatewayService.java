package hse.kpo.services.realizations;

import hse.kpo.domain.MultipartFileResource;
import hse.kpo.dto.FileAnalysisDto;
import hse.kpo.dto.FileStorageDto;
import hse.kpo.services.interfaces.GatewayServiceInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Log4j2
@Component
@RequiredArgsConstructor
@Getter
public class GatewayService implements GatewayServiceInterface {

    public GatewayService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${remote.filestorage-service.base-url}")
    private String fileStorageBaseUrl;

    @Value("${remote.analysis-service.base-url}")
    private String fileAnalysisBaseUrl;

    @Override
    public Optional<FileAnalysisDto> getFileAnalysisByFileId(int fileId) {
        String url = fileAnalysisBaseUrl + "/api/analysis/" + fileId;
        try {
            FileAnalysisDto response = restTemplate.getForObject(url, FileAnalysisDto.class);
            return Optional.ofNullable(response);
        } catch (Exception e) {
            log.error("Ошибка при получении анализа файла с ID {}: {}", fileId, e.getMessage(), e);
            return Optional.empty();
        }
    }

    @Override
    public List<FileAnalysisDto> getAllFileAnalysis() {
        String url = fileAnalysisBaseUrl + "/api/analysis";
        try {
            FileAnalysisDto[] result = restTemplate.getForObject(url, FileAnalysisDto[].class);
            return result != null ? List.of(result) : List.of();
        } catch (Exception e) {
            log.error("Ошибка при получении анализа файлов: {}", e.getMessage(), e);
            return List.of();
        }
    }

    @Override
    public boolean deleteFileAnalysis(int fileId) {
        String url = fileAnalysisBaseUrl + "/api/analysis/" + fileId;
        try {
            restTemplate.delete(url);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            log.warn("Анализ с ID {} не найден для удаления", fileId);
            return false;
        } catch (Exception e) {
            log.error("Ошибка при удалении анализа файла с ID {}: {}", fileId, e.getMessage(), e);
            return false;
        }
    }

    @Override
    public FileStorageDto saveFile(MultipartFile file) {
        String url = fileStorageBaseUrl + "/api/files";
        log.info("Отправляю файл на URL: {}", url);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", new MultipartFileResource(file));

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            ResponseEntity<FileStorageDto> response =
                    restTemplate.postForEntity(url, requestEntity, FileStorageDto.class);

            return response.getBody();

        } catch (Exception e) {
            log.error("Ошибка при сохранении файла: {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Optional<FileStorageDto> getFileDescriptionById(int id) {
        String url = fileStorageBaseUrl + "/api/files/" + id;
        try {
            FileStorageDto response = restTemplate.getForObject(url, FileStorageDto.class);
            return Optional.ofNullable(response);
        } catch (Exception e) {
            log.error("Ошибка при получении описания файла с ID {}: {}", id, e.getMessage(), e);
            return Optional.empty();
        }
    }

    @Override
    public List<FileStorageDto> getAllFiles() {
        String url = fileStorageBaseUrl + "/api/files";
        try {
            FileStorageDto[] response = restTemplate.getForObject(url, FileStorageDto[].class);
            return response != null ? List.of(response) : List.of();
        } catch (Exception e) {
            log.error("Ошибка при получении описания всех файлов {}", e.getMessage(), e);
            return List.of();
        }
    }

    @Override
    public boolean deleteFile(int id) {
        String url = fileStorageBaseUrl + "/api/files/" + id;
        try {
            restTemplate.delete(url);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            log.warn("Файл с ID {} не найден для удаления", id);
            return false;
        } catch (Exception e) {
            log.error("Ошибка при удалении анализа файла с ID {}: {}", id, e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Optional<String> getFileContentById(int fileId) {
        String url = fileStorageBaseUrl + "/api/files/" + fileId;
        try {
            String response = restTemplate.getForObject(url, String.class);
            return Optional.ofNullable(response);
        } catch (Exception e) {
            log.error("Ошибка при получении содержимого файла с id: {} : {}", fileId, e.getMessage(), e);
            return Optional.empty();
        }
    }
}
