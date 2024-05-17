package modelDB;

import jakarta.persistence.*;
import lombok.Getter;

@Entity(name = "tpp_ref_account_type")
public class TppRefAccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "internal_id", nullable = false)
    @Getter
    private Long id;


    @Column(length = 100, nullable = false, unique = true, name = "value_s")
    String value;
}
