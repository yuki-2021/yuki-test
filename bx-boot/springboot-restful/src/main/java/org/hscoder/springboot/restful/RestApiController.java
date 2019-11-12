package org.hscoder.springboot.restful;

import io.swagger.annotations.*;
import org.hscoder.springboot.restful.modules.rest.RestDataManager;
import org.hscoder.springboot.restful.modules.rest.domain.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * 实现一个restful风格api样例
 * 
 * @author atp
 *
 */
@Api(value = "Pet Restful api")
@RestController
@RequestMapping("/rest/pets/{customer}")
public class RestApiController {

    @Autowired
    private RestDataManager dataManager;

    @ApiOperation("添加宠物")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "customer", dataType = "String", required = true, value = "客户名", defaultValue = ""),
            @ApiImplicitParam(paramType = "body", name = "pet", dataType = "Pet", required = true, value = "pet 请求", defaultValue = "") })
    @ApiResponses({
        @ApiResponse(code = 201, message = "添加成功"),
        @ApiResponse(code = 404, message = "资源不存在")
        
    })
    /**
     * 添加宠物
     * 
     * @param customer
     * @param pet
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> addPet(@PathVariable String customer, @RequestBody Pet pet) {
        validateCustomer(customer);

        Pet newPet = dataManager.addPet(customer, pet);

        // 返回 201.created
        if (newPet != null) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{petId}")
                    .buildAndExpand(newPet.getPetId()).toUri();

            System.out.println(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUri().toString());
            return ResponseEntity.created(location).build();
        }

        // 返回 204.noContent
        return ResponseEntity.noContent().build();
    }

    @ApiOperation("获取宠物列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "customer", dataType = "String", required = true, value = "客户名", defaultValue = ""), })
    @ApiResponses({ 
        @ApiResponse(code = 200, message = "操作成功"),
        @ApiResponse(code = 404, message = "资源不存在")
        
    })
    /**
     * 获取宠物列表
     * 
     * @param customer
     * @return
     */
    @GetMapping
    @ResponseBody
    public List<Pet> listPets(@PathVariable String customer) {
        validateCustomer(customer);

        List<Pet> pets = dataManager.getPets(customer);
        return pets;
    }

    @ApiOperation("获取宠物信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "customer", dataType = "String", required = true, value = "客户名", defaultValue = ""),
            @ApiImplicitParam(paramType = "path", name = "petId", dataType = "String", required = true, value = "宠物ID", defaultValue = ""), })
    @ApiResponses({ 
        @ApiResponse(code = 200, message = "操作成功", response=Pet.class),
        @ApiResponse(code = 404, message = "资源不存在")
        })
    /**
     * 获取某个宠物
     * 
     * @param customer
     * @param petId
     */
    @GetMapping("/{petId}")
    @ResponseBody
    public Pet getPet(@PathVariable String customer, @PathVariable String petId) {
        validateCustomer(customer);
        validatePet(customer, petId);

        Pet pet = dataManager.getPet(customer, petId);
        return pet;
    }

    
    @ApiOperation("更新宠物信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "customer", dataType = "String", required = true, value = "客户名", defaultValue = ""),
            @ApiImplicitParam(paramType = "path", name = "petId", dataType = "String", required = true, value = "宠物ID", defaultValue = ""),
            @ApiImplicitParam(paramType = "body", name = "pet", dataType = "Pet", required = true, value = "宠物信息", defaultValue = "")
    })
    @ApiResponses({ 
        @ApiResponse(code = 200, message = "操作成功", response=Pet.class), 
        @ApiResponse(code = 404, message = "资源不存在"),
    })
    /**
     * 更新宠物信息
     * 
     * @param customer
     * @param pet
     */
    @PutMapping("/{petId}")
    public ResponseEntity<Object> updatePet(@PathVariable String customer, @PathVariable String petId,
            @RequestBody Pet pet) {
        validateCustomer(customer);
        validatePet(customer, petId);

        pet.setPetId(petId);
        Pet petObject = dataManager.updatePet(customer, pet);
        if (petObject != null) {
            return ResponseEntity.ok(petObject);
        }

        return ResponseEntity.noContent().build();

    }

    @ApiOperation("删除宠物")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "customer", dataType = "String", required = true, value = "客户名", defaultValue = ""),
            @ApiImplicitParam(paramType = "path", name = "petId", dataType = "String", required = true, value = "宠物ID", defaultValue = ""), })
    @ApiResponses({ 
        @ApiResponse(code = 200, message = "操作成功"), 
        @ApiResponse(code = 404, message = "资源不存在"), 
    })
    /**
     * 删除某个宠物
     * 
     * @param customer
     * @param petId
     * @return
     */
    @DeleteMapping("/{petId}")
    public ResponseEntity<Object> removePet(@PathVariable String customer, @PathVariable String petId) {
        validateCustomer(customer);
        validatePet(customer, petId);

        dataManager.removePet(customer, petId);
        return ResponseEntity.ok().build();
    }

    /**
     * 校验customer是否存在
     * 
     * @param customer
     */
    public void validateCustomer(String customer) {
        if (dataManager.getCustomer(customer) == null) {
            throw new ObjectNotFoundException(String.format("the customer['%s'] is not found", customer));
        }
    }

    /**
     * 校验pet是否存在
     * 
     * @param customer
     */
    public void validatePet(String customer, String petId) {
        if (dataManager.getPet(customer, petId) == null) {
            throw new ObjectNotFoundException(String.format("the pet['%s/%s'] is not found", customer, petId));
        }
    }

    /**
     * 自定义异常，及拦截逻辑
     * 
     * @author atp
     *
     */
    @SuppressWarnings("serial")
    public static class ObjectNotFoundException extends RuntimeException {

        public ObjectNotFoundException(String msg) {
            super(msg);
        }
    }

    @ResponseBody
    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String objectNotFoundExceptionHandler(ObjectNotFoundException ex) {
        return ex.getMessage();
    }
}
