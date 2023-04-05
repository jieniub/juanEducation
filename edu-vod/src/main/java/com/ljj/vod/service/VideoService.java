package com.ljj.vod.service;

import com.ljj.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {
    String uploadVideo(MultipartFile file);

    boolean removeById(String id);

    boolean removeByIds(List<String> ids);

    String getAuth(String id);
}
