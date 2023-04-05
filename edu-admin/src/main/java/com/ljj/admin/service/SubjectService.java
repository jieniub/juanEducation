package com.ljj.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ljj.admin.entity.Subject;
import com.ljj.admin.vo.SubjectNestedVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SubjectService extends IService<Subject> {
//    void batchImport();

    void SaveSubject(MultipartFile file,SubjectService subjectService);

    List<SubjectNestedVo> getNestList();
//    void importSubjectData(MultipartFile file,SubjectService subject);
}
