package hse.kpo.domains.realizations;

import hse.kpo.domains.interfaces.FileInterface;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "files")
@ToString
@NoArgsConstructor
public class FileRealization implements FileInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String path;

    @Column(nullable = false, unique = true)
    private String hash;

    public FileRealization(String name, String path, String hash) {
        this.name = name;
        this.path = path;
        this.hash = hash;
    }

}
