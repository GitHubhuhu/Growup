package com.yl.Repository;

import com.yl.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * <pre>
 *   数据库操作
 *
 * </pre>
 * <p>
 * Created by luxiaohu at 16/10/20 15:35
 */
public interface PersonRepository extends CrudRepository<Person, Long> {


    Page<Person> findAll(Pageable pageable);

    Person findByFullNameAndEmailAllIgnoringCase(String fullName, String email );


}
