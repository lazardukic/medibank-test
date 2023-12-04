package com.audition.web;

import static com.audition.common.validation.Validator.isNull;
import static com.audition.common.validation.Validator.isValidId;

import com.audition.model.AuditionPost;
import com.audition.model.Comment;
import com.audition.service.AuditionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Audition Controller", description = "REST endpoints related to audition posts and comments")
@RestController
public class AuditionController {

    @Autowired
    private transient AuditionService auditionService;

    private final static String POST_ID = "postId";
    private final static String USER_ID = "userId";

    @Operation(description = "Returns a list of all audition posts that can optionally be filtered by userId")
    @Parameter(name = USER_ID, description = "ID of the user that created the audition post", required = false)
    @GetMapping(value = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<AuditionPost>> getPosts(
        @RequestParam(value = USER_ID, required = false) final Integer userId) {
        if (isNull(userId)) {
            final List<AuditionPost> auditionPosts = auditionService.getPosts();
            return ResponseEntity.ok(auditionPosts);
        } else {
            if (isValidId(userId)) {
                final List<AuditionPost> auditionPosts = auditionService.getPosts();
                final List<AuditionPost> filteredPosts = auditionService.filterPostsByUserId(auditionPosts, userId);
                return ResponseEntity.ok(filteredPosts);
            } else {
                return ResponseEntity.badRequest().build();
            }
        }
    }

    @Operation(description = "Returns a specific audition post based on the postId")
    @Parameter(name = POST_ID, description = "ID of the audition post", required = true)
    @GetMapping(value = "/posts/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<AuditionPost> getPost(
        @PathVariable(value = POST_ID, required = true) final Integer postId) {
        if (isValidId(postId)) {
            final AuditionPost auditionPost = auditionService.getPostById(postId);
            return ResponseEntity.ok(auditionPost);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(description = "Returns a specific audition post containing it's related comments based on the postId")
    @Parameter(name = POST_ID, description = "ID of the audition post", required = true)
    @GetMapping(value = "/postWithComments/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<AuditionPost> getPostWithComments(
        @PathVariable(value = POST_ID, required = true) final Integer postId) {
        if (isValidId(postId)) {
            final AuditionPost postWithComments = auditionService.getPostWithComments(postId);
            return ResponseEntity.ok(postWithComments);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(description = "Returns a list of comments related to a specific post based on the postId")
    @Parameter(name = POST_ID, description = "ID of the audition post", required = true)
    @GetMapping(value = "/comments/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<Comment>> getCommentsByPostId(
        @PathVariable(value = POST_ID, required = true) final Integer postId) {
        if (isValidId(postId)) {
            final List<Comment> postComments = auditionService.getCommentsByPostId(postId);
            return ResponseEntity.ok(postComments);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
