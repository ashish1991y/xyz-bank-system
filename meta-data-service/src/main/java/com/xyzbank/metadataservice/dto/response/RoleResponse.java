package com.xyzbank.metadataservice.dto.response;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class RoleResponse implements IResponse {
    private Long id;
    private String name;
    private String status;
}
