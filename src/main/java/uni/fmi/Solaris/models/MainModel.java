package uni.fmi.Solaris.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class MainModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
}
