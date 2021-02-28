package management.mail.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import management.mail.misc.StatusEnum;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
public class Traffic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "mail_id", nullable = false)
    private Long mail_id;
    @Column(name = "post_office_id", nullable = false)
    private Long post_office_id;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private StatusEnum status;
    @Column(name = "date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
}
