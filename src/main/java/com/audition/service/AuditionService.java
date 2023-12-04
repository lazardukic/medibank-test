package com.audition.service;

import com.audition.integration.AuditionIntegrationClient;
import com.audition.model.AuditionComment;
import com.audition.model.AuditionPost;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditionService {

    @Autowired
    private transient AuditionIntegrationClient auditionIntegrationClient;

    public List<AuditionPost> getPosts() {
        return auditionIntegrationClient.getPosts();
    }

    public AuditionPost getPostById(final Integer postId) {
        return auditionIntegrationClient.getPostById(postId);
    }

    public AuditionPost getPostWithComments(final Integer postId) {
        return auditionIntegrationClient.getPostWithComments(postId);
    }

    public List<AuditionComment> getCommentsByPostId(final Integer postId) {
        return auditionIntegrationClient.getCommentsByPostId(postId);
    }

    public List<AuditionPost> filterPostsByUserId(final List<AuditionPost> auditionPosts, final Integer userId) {
        return auditionPosts.stream()
            .filter(post -> post.getUserId() == userId)
            .collect(Collectors.toList());
    }

}
