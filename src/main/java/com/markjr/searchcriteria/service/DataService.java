package com.markjr.searchcriteria.service;

import com.markjr.searchcriteria.dto.SearchDataCriteria;
import com.markjr.searchcriteria.entity.Data;
import com.markjr.searchcriteria.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class DataService {

    @Autowired
    DataRepository dataRepository;

    public Data getAllWithCriteria(SearchDataCriteria criteria) {
        Data data = new Data();
        escapeCharacterDataCriteria(criteria);
        Specification<Data> spec = createSpecData(criteria);
        data = (Data) dataRepository.findAll(spec);
        return data;
    }

    private void escapeCharacterDataCriteria(SearchDataCriteria criteria) {
        if (criteria.getName() != null) {
            criteria.setName(escapeCharacterString(criteria.getName()));
        }
    }

    private String escapeCharacterString(String str) {
        return StringUtils.replace(str, "%", "\\%");
    }

    private Specification<Data> createSpecData(SearchDataCriteria criteria) {
        Specification<Data> spec = Specification.where(null);
        if (criteria != null) {
            if (criteria.getName() != null) {
                if(spec == null){
                    spec = Specification.where((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + criteria.getName() + "%", '\\'));
                } else {
                    spec = spec.and((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + criteria.getName() + "%", '\\'));
                }
            }

            if (criteria.getMobileNo()!= null) {
                if(spec == null){
                    spec = Specification.where((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("mobileNo"), criteria.getMobileNo()));
                } else {
                    spec = spec.and((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("mobileNo"), criteria.getMobileNo()));
                }
            }

//            if (criteria.getStatus() != null) {
//                if(spec == null){
//                    spec = Specification.where((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.join("table").get("status"), criteria.getStatus()));
//                } else {
//                    spec = spec.and((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.join("table").get("status"), criteria.getStatus()));
//                }
//            }
        }
        return spec;
    }
}

