package com.school.entity.wx;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class WXAuth {

    private String encryptedData;
    private String iv;
//    private String ticket;
}
