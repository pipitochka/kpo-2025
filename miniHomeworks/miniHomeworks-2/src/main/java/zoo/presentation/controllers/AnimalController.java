package zoo.presentation.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zoo.application.interfaces.AnimalRepository;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalRepository animalRepository;

//    @GetMapping
//    public List<AnimalDTO> getAllAnimals() {
//        return animalRepository.getAllAnimals().stream()
//                .map(AnimalConverter::toDTO)
//                .collect(Collectors.toList());
//    }
}
