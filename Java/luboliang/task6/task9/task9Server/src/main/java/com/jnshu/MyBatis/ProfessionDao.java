package com.jnshu.MyBatis;

import com.jnshu.model.Profession;
import org.oasisopen.sca.annotation.Remotable;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Remotable
public interface ProfessionDao {

    List<Profession> all();
    List<Profession> after();
    List<Profession> ops();

}
