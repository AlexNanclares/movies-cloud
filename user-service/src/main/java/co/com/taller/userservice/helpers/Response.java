package co.com.taller.userservice.helpers;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class Response {
    private Integer code;
    private Object data;
}
