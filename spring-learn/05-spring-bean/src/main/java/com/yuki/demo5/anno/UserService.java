package com.yuki.demo5.anno;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;


@NoArgsConstructor
@Data
@ToString
@AllArgsConstructor
public class UserService {

    Car car;
}
