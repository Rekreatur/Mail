package management.mail.dto;

import lombok.Data;
import management.mail.misc.TypeEnum;

@Data
public final class MailDto {
    private Long id;
    private TypeEnum type;
    private String index;
    private String address;
    private String name;
}
