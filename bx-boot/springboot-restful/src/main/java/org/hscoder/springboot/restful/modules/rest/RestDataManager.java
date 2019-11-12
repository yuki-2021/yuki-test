package org.hscoder.springboot.restful.modules.rest;

import org.hscoder.springboot.restful.modules.rest.domain.Customer;
import org.hscoder.springboot.restful.modules.rest.domain.Pet;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class RestDataManager {

    private static Map<String, Customer> customers = new ConcurrentHashMap<String, Customer>();
    private static Map<String, Map<String, Pet>> pets = new ConcurrentHashMap<String, Map<String, Pet>>();

    public RestDataManager(){
        System.out.println("执行构造方法 !!!");
    }

    //用于预先定义初始化方法
    @PostConstruct
    public void init() {
        System.out.println("初始化工厂 !!!");
        String[] customerNames = new String[] { "LiLei", "HanMeimei", "JimGreen" };

        for (String customerName : customerNames) {
            customers.put(customerName, new Customer(customerName));
        }
    }

    /**
     * 获取customer
     * 
     * @param customer
     * @return
     */
    public Customer getCustomer(String customer) {
        if (StringUtils.isEmpty(customer)) {
            return null;
        }
        return customers.get(customer);
    }

    /**
     * 获取customer名下的 pet 列表
     * 
     * @param customer
     * @return
     */
    public List<Pet> getPets(String customer) {
        if (StringUtils.isEmpty(customer)) {
            return Collections.emptyList();
        }

        if (!pets.containsKey(customer)) {
            return Collections.emptyList();
        }

        return pets.get(customer).values().stream().collect(Collectors.toList());
    }

    /**
     * 获取某个pet
     * 
     * @param customer
     * @param petId
     * @return
     */
    public Pet getPet(String customer, String petId) {
        if (StringUtils.isEmpty(customer) || StringUtils.isEmpty(petId)) {
            return null;
        }

        if (!pets.containsKey(customer)) {
            return null;
        }
        return pets.get(customer).get(petId);
    }

    /**
     * 删除pet
     * 
     * @param customer
     * @param petId
     * @return
     */
    public boolean removePet(String customer, String petId) {
        if (StringUtils.isEmpty(customer) || StringUtils.isEmpty(petId)) {
            return false;
        }

        if (!pets.containsKey(customer)) {
            return false;
        }
        return pets.get(customer).remove(petId) != null;
    }

    /**
     * 添加pet
     * 
     * @param customer
     * @param pet
     * @return
     */
    public Pet addPet(String customer, Pet pet) {
        if (StringUtils.isEmpty(customer) || pet == null) {
            return null;
        }

        Map<String, Pet> customerPets = null;
        if (!pets.containsKey(customer)) {

            customerPets = new LinkedHashMap<String, Pet>();

            // 使用putIfAbsent保证原子性
            Map<String, Pet> previous = pets.putIfAbsent(customer, customerPets);

            // 已经存在
            if (previous != null) {
                customerPets = previous;
            }
        } else {
            customerPets = pets.get(customer);
        }

        if (pet.getPetId() == null) {
            pet.setPetId(UUID.randomUUID().toString());
        }

        customerPets.put(pet.getPetId(), pet);
        return pet;
    }

    /**
     * 更新某个pet
     * 
     * @param customer
     * @param petPojo
     * @return
     */
    public Pet updatePet(String customer, Pet petPojo) {
        if (StringUtils.isEmpty(customer) || petPojo == null) {
            return null;
        }

        if (petPojo.getPetId() == null) {
            return null;
        }

        Pet pet = getPet(customer, petPojo.getPetId());
        pet.setType(petPojo.getType());
        pet.setName(petPojo.getName());
        pet.setDescription(petPojo.getDescription());

        return pet;
    }

    /**
     * 清除pet
     * 
     * @param customer
     */
    public void clearPets(String customer) {
        if (StringUtils.isEmpty(customer)) {
            return;
        }

        pets.remove(customer);
    }

}
