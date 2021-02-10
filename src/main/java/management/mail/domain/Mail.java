package management.mail.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import management.mail.misc.TypeEnum;

import javax.persistence.*;

@Entity
@Table
@ToString(of = {"id", "type", "index", "address", "name"})
@EqualsAndHashCode(of = {"id"})
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
