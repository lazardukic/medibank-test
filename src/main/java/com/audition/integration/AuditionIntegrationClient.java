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
    private transient RestTemplate restTemplate; // marked as transient due to PMD requirements

    @Value("${url.posts}")
    private transient String postsUrlBase;

    @Value("${url.comments}")
    private transient String commentsUrlBase;

    public List<AuditionPost> getPosts() {
        // TODO make RestTemplate call to get Posts from https://jsonplaceholder.typicode.com/posts - DONE
        final ResponseEntity<AuditionPost[]> response = restTemplate.getForEntity(postsUrlBase, AuditionPost[].class);
        final AuditionPost[] posts = response.getBody();
        if (posts != null && posts.length > 0) {
            return Arrays.asList(posts);
        }
        return new ArrayList<>();
    }

    public AuditionPost getPostById(final String id) {
        // TODO get post by post ID call from https://jsonplaceholder.typicode.com/posts/ - DONE
        try {
            final String url = postsUrlBase + "/" + id;
            return restTemplate.getForObject(url, AuditionPost.class);
        } catch (final HttpClientErrorException e) {
            // TODO Find a better way to handle the exception so that the original error message is not lost. Feel free to change this function. - DONE
            throw new SystemException("Cannot find a Post with id " + id, e);
        }
    }

    // TODO Write a method GET comments for a post from https://jsonplaceholder.typicode.com/posts/{postId}/comments - the comments must be returned as part of the post. - DONE
    public AuditionPost getPostWithComments(final String id) {
        final String url = postsUrlBase + "/" + id + "/comments";
        final Comment[] comments = restTemplate.getForObject(url, Comment[].class);
        final AuditionPost post = getPostById(id);
        post.setComments(Arrays.asList(comments));
        return post;
    }

    // TODO write a method. GET comments for a particular Post from https://jsonplaceholder.typicode.com/comments?postId={postId}. - DONE
    // The comments are a separate list that needs to be returned to the API consumers. Hint: this is not part of the AuditionPost pojo.
    public List<Comment> getCommentsByPostId(final String id) {
        final String url = commentsUrlBase + "?postId=" + id;
        final Comment[] comments = restTemplate.getForObject(url, Comment[].class);
        return Arrays.asList(comments);
    }
}
