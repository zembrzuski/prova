package com.serasa.basea.base_a.repository;

import com.serasa.basea.base_a.domain.Foo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "foo", path = "foo")
public interface FooRepository extends PagingAndSortingRepository<Foo, Long> {

    List<Foo> findByName(@Param("name") String name);

}
