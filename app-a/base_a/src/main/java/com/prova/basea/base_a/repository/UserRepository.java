package com.prova.basea.base_a.repository;

import com.prova.basea.base_a.domain.SerasaUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends PagingAndSortingRepository<SerasaUser, Long> {

    List<SerasaUser> findByCpf(@Param("cpf") String cpf);

}
