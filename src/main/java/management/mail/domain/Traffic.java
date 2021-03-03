package management.mail.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import management.mail.constants.TrafficOfficeStatusEnum;

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
    private TrafficOfficeStatusEnum status;
    @Column(name = "date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
}
