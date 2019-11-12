package org.hscoder.springboot.restful.modules.rest.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("宠物信息")
public class Pet {

    @ApiModelProperty(name="petId", value="宠物ID")
    private String petId;
    
    @ApiModelProperty(name="name", value="宠物名称")
    private String name;
    
    @ApiModelProperty(name="type", value="宠物类型")
    private String type;
    
    @ApiModelProperty(name="description", value="宠物描述")
    private String description;

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
