package modelDB;

import jakarta.persistence.*;
import lombok.Getter;


import java.sql.Timestamp;

@Entity(name = "tpp_ref_product_register_type")
public class TppRefProductRegisterType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "internal_id", nullable = false)
    @Getter
    private Long id;

    @Column(length = 100, nullable = false, unique = true, name = "value_s")
    @Getter
    String value;
    @Column(name = "register_type_name", length = 100, nullable = false)
    String registerTypeName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_class_code", referencedColumnName = "value_s")
    TppRefProductClass tppRefProductClass;
    @Column(name = "register_type_start_date")
    Timestamp registerTypeStartDate;
    @Column(name = "register_type_end_date")
    Timestamp registerTypeEndDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_type", referencedColumnName = "value_s")
    TppRefAccountType tppRefAccountType;
}
