package com.jnshu.service;

import com.jnshu.model.Profession;
import org.oasisopen.sca.annotation.Remotable;

import java.rmi.Remote;
import java.util.List;
@Remotable
public interface ProfessionService {
    List<Profession> all();
    List<Profession> after();
    List<Profession> ops();

}
