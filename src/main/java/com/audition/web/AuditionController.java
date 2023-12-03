package com.audition.web;

import static com.audition.common.validation.Validator.isInputValid;

import com.audition.model.AuditionPost;
import com.audition.model.Comment;
import com.audition.service.AuditionService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuditionController {

    @Autowired
    private transient AuditionService auditionService; // marked as transient due to PMD requirements

    // added health check endpoint
    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public String health() {
        return "OK";
    }

    // TODO Add a query param that allows data filtering. The intent of the filter is at developers discretion. - DONE
    @RequestMapping(value = "/posts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<AuditionPost>> getPosts(
        @RequestParam(value = "userId", required = false) final Integer userId) {
        final List<AuditionPost> auditionPosts = auditionService.getPosts();
        // TODO Add logic that filters response data based on the query param - DONE
        if (userId != null) {
            final List<AuditionPost> filteredPosts = auditionPosts.stream()
                .filter(post -> post.getUserId() == userId)
                .collect(Collectors.toList());
            return ResponseEntity.ok(filteredPosts);
        }
        return ResponseEntity.ok(auditionPosts);
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<AuditionPost> getPost(@PathVariable("id") final String postId) {
        if (isInputValid(postId)) {
            final AuditionPost auditionPost = auditionService.getPostById(postId);
            return ResponseEntity.ok(auditionPost);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    // TODO Add additional methods to return comments for each post. Hint: Check https://jsonplaceholder.typicode.com/ - DONE
    @RequestMapping(value = "/postWithComments/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<AuditionPost> getPostWithComments(@PathVariable("id") final String postId) {
        if (isInputValid(postId)) {
            final AuditionPost postWithComments = auditionService.getPostWithComments(postId);
            return ResponseEntity.ok(postWithComments);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/comments/{postId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<Comment>> getCommentsByPostId(
        @PathVariable("postId") final String postId) {
        if (isInputValid(postId)) {
            final List<Comment> postComments = auditionService.getCommentsByPostId(postId);
            return ResponseEntity.ok(postComments);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
