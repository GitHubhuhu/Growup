package com.yl.controller;

import com.yl.Repository.PersonRepository;
import com.yl.RequestBody.PersonRequest;
import com.yl.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <pre>
 *   Persion接口
 *
 * </pre>
 * <p>
 * Created by luxiaohu at 16/10/21 11:11
 */
@RestController
@RequestMapping("/v1/persons")
public class PersonController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonRepository personRepository;

    /**
     * 新增一个个人信息,新增成功,返回实体信息
     * (FRO表单提交的方式)
     * @param fullName
     * @param email
     * @param age
     * @return
     */
    @PostMapping
    public Person add(@RequestParam(value = "fullName",required = true) String fullName,
                         @RequestParam(value = "email",required = false) String email,
                         @RequestParam(value = "age") int age){
        Person person = new Person(fullName,email,age);
        personRepository.save(person);
        return person;
    }

    /**
     * 删除成功,返回空
     * @param id
     */
    @DeleteMapping(value = "/{id}")
    public void deletePerson(@PathVariable Long id){
        personRepository.delete(id);
    }

    /**
     * <p>
     *  requestBody的提交方式
     *  接收 headers : Content-Type = application/json;charset=UTF-8 (默认)
     *  响应 produces
     * </p>
     * @param id 请求对象ID
     * @param personRequest 请求对象
     * @return
     */
    @PutMapping(value = "/{id}",headers = "Content-Type=application/json;charset=UTF-8")
    public Person update(@PathVariable Long id, @RequestBody @Valid  PersonRequest personRequest){
        Person person = personRequest.toPerson();
        person.setId(id);
        return personRepository.save(person);
    }
    /**
     * 查询所有用户信息
     * @param page
     * @param size
     * @return
     */
    @GetMapping
    public Page<Person> findByPage(int page, int size){
        PageRequest pageable = new PageRequest(page,size);
        return personRepository.findAll(pageable);
    }



}
