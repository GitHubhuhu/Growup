package com.yl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * <pre>
 *   示例实体
 *
 * </pre>
 * <p>
 * Created by luxiaohu at 16/10/20 11:56
 */

@Entity
public class Person {


    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false,length = 100)
    private String fullName;
    @Column(length = 20)
    private String email;
    @Column(nullable = false)
    private int age;

    protected Person(){

    }

    public Person(String fullName, String email, int age) {
        this.fullName = fullName;
        this.email = email;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
