package management.mail.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import management.mail.misc.StatusEnum;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@ToString(of = {"id", "status", "date", "mail_id", "post_office_id"})
@EqualsAndHashCode(of = {"id"})
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

    public  Traffic() {};

    public Long getMail_id() {
        return mail_id;
    }

    public void setMail_id(Long mail_id) {
        this.mail_id = mail_id;
    }

    public Long getPost_office_id() {
        return post_office_id;
    }

    public void setPost_office_id(Long post_office_id) {
        this.post_office_id = post_office_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
