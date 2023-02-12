package com.API_Task.POJO;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Pet {
    private Integer id;
    private Category category;
    private String name;
    private List<Tag> tags = new ArrayList<>();
    private List<String> photoUrls = new ArrayList<>();
    private StatusEnum status;

}


