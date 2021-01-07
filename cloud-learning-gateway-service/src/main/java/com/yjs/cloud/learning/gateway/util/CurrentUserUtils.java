package com.yjs.cloud.learning.gateway.util;

import cn.hutool.core.convert.Convert;
import com.yjs.cloud.learning.gateway.dto.UserDTO;
import com.nimbusds.jose.JWSObject;
import net.minidev.json.JSONObject;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.text.ParseException;
import java.util.Arrays;

/**
 * current user util
 *
 * @author Andrew.xiao
 * @since 2020/8/5
 */
public class CurrentUserUtils {

    public static UserDTO get(ServerRequest request){
        UserDTO userDTO = new UserDTO();
        String token = request.exchange().getRequest().getHeaders().getFirst("Authorization");
        if (token != null) {
            String realToken = token.replace("Bearer ", "");
            try {
                JWSObject jwsObject = JWSObject.parse(realToken);
                JSONObject userObject = jwsObject.getPayload().toJSONObject();
                userDTO.setUsername(userObject.getAsString("user_name"));
                userDTO.setId(userObject.getAsNumber("id").longValue());
                Object authorities = userObject.get("authorities");
                if (authorities != null) {
                    userDTO.setAuthorities(Arrays.asList(Convert.toStrArray(authorities)));
                }
                return userDTO;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
