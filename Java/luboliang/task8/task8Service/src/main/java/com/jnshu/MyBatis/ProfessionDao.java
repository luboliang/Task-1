package com.jnshu.MyBatis;

import com.jnshu.model.Profession;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProfessionDao {

    List<Profession> all();
    List<Profession> after();
    List<Profession> ops();

}
