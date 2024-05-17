package modelDB;

import jakarta.persistence.*;
import lombok.Getter;

@Entity(name = "tpp_ref_product_class")
public class TppRefProductClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "internal_id", nullable = false)
    @Getter
    private Long id;

    @Column(length = 100, nullable = false, unique = true, name = "value_s")
    String value;
    @Column(length = 50)
    String gbi_code;
    @Column(length = 100)
    String gbi_name;
    @Column(length = 50)
    String product_row_code;
    @Column(length = 100)
    String product_row_name;
    @Column(length = 50)
    String subclass_code;
    @Column(length = 100)
    String subclass_name;
}
