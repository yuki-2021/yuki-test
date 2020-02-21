package com.yuki.demo7.advisor;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class WaiterDelegate {

    Waiter waiter;

    public void serve(String username) {
        waiter.serveTo(username);
        waiter.greetTo(username);
    }
}
