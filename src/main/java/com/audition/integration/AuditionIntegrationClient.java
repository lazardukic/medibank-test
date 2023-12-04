package com.audition.integration;

import com.audition.common.exception.SystemException;
import com.audition.model.AuditionPost;
import com.audition.model.Comment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class AuditionIntegrationClient {

    @Autowired
    private transient RestTemplate restTemplate;

    @Value("${url.posts}")
    private transient String postsUrlBase;

    @Value("${url.comments}")
    private transient String commentsUrlBase;

    public List<AuditionPost> getPosts() {
        final ResponseEntity<AuditionPost[]> response = restTemplate.getForEntity(postsUrlBase, AuditionPost[].class);
        final AuditionPost[] posts = response.getBody();
        if (posts != null && posts.length > 0) {
            return Arrays.asList(posts);
        }
        return new ArrayList<>();
    }

    public AuditionPost getPostById(final Integer id) {
        try {
            final String url = postsUrlBase + "/" + id;
            return restTemplate.getForObject(url, AuditionPost.class);
        } catch (final HttpClientErrorException e) {
            throw new SystemException("Cannot find a Post with id " + id, e);
        }
    }

    public AuditionPost getPostWithComments(final Integer id) {
        final String url = postsUrlBase + "/" + id + "/comments";
        final Comment[] comments = restTemplate.getForObject(url, Comment[].class);
        final AuditionPost post = getPostById(id);
        post.setComments(Arrays.asList(comments));
        return post;
    }

    public List<Comment> getCommentsByPostId(final Integer id) {
        final String url = commentsUrlBase + "?postId=" + id;
        final Comment[] comments = restTemplate.getForObject(url, Comment[].class);
        return Arrays.asList(comments);
    }
}
