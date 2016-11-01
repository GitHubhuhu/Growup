package com.yl.RequestBody;

import com.yl.entity.Person;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * <pre>
 *   请求对象实体
 *      注意: get 和 set 方法 是必须的.
 * </pre>
 * <p>
 * Created by luxiaohu at 16/10/24 10:38
 */
public class PersonRequest {

    private Long id;
    @NotBlank
    private String fullName;
    @Email(message = "邮箱格式不正确")
    private String email;
    @NotNull
    private Integer age;


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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 转成数据库实体对象
     * @return
     */
    public Person toPerson(){
        return new Person(fullName,email,age);
    }

}
