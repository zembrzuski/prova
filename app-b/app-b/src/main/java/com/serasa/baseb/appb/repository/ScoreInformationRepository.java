package com.serasa.baseb.appb.repository;

import com.serasa.baseb.appb.entity.ScoreInformation;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "score_info", path = "score_info")
public interface ScoreInformationRepository extends PagingAndSortingRepository<ScoreInformation, Long> {

    List<ScoreInformation> findByAge(@Param("age") String age);

}
