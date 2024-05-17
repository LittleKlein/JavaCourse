package modelDB;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_pool_id", referencedColumnName = "id")
    @Getter @Setter
    private AccountPool accountPoolId;

    @Column(name = "account_number", length = 25)
    @Getter @Setter
    private String accountNumber;

    @Column
    @Getter @Setter
    private Boolean bussy;
}
