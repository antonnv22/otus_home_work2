package otus.msa.crud.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Error {
    private Integer code;
    private String message;
}
