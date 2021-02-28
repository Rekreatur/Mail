package management.mail.domain;

import lombok.*;
import management.mail.misc.TypeEnum;

import javax.persistence.*;

@Entity
@Table
@Data
public class Mail {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "type")
    @Enumerated(EnumType.ORDINAL)
    private TypeEnum type;
    @Column(name = "index")
    private String index;
    @Column(name = "address")
    private String address;
    @Column(name = "name")
    private String name;
}
