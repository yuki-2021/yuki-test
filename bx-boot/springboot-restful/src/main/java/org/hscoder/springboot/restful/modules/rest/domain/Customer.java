package org.hscoder.springboot.restful.modules.rest.domain;

public class Customer {

    private String name;

    public Customer() {
        super();
    }

    public Customer(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
