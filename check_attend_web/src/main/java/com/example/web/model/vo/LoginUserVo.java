package com.example.web.model.vo;

import com.example.core.model.pojo.User;
import com.example.web.model.pojo.Employe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

/**
 * @author lincheon
 * @since 2022/5/20 11:27
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LoginUserVo extends User {
    public String token;
    public Employe employe;
}
