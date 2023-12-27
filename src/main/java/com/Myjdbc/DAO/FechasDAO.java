package com.Myjdbc.DAO;

import com.Myjdbc.entity.Fechas;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FechasDAO extends CrudRepository<Fechas,Integer> {

    

}
