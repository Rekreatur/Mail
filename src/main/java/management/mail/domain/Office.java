package management.mail.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@Data
public class Office {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "index")
    private String index;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
}
