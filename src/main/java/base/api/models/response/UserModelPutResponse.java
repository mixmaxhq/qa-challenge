package base.api.models.response;

import lombok.Data;

import java.util.Calendar;

@Data
public class UserModelPutResponse {
    private int id;
    private String name;
    private String job;
    private Calendar updatedAt;
}
