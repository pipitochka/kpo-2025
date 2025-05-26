package hse.kpo.domains.realizations;

import hse.kpo.domains.interfaces.AnalysisInterface;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "analysis")
@ToString
@NoArgsConstructor
public class AnalysisRealization implements AnalysisInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private int fileId;

    @Column(nullable = false)
    private int wordCount;

    @Column(nullable = false)
    private int paragraphCount;

    @Column(nullable = false)
    private int characterCount;
}
