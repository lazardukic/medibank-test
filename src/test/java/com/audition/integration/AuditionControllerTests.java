package com.audition.integration;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

import com.audition.model.AuditionPost;
import com.audition.model.Comment;
import com.audition.service.AuditionService;
import com.audition.web.AuditionController;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class AuditionControllerTests {

    @Autowired
    private transient AuditionController auditionController;

    @MockBean
    private transient AuditionService auditionService;

    private static final String EXPECTED_OK = "Expected OK response";
    private static final String EXPECTED_BAD_REQUEST = "Expected BAD_REQUEST response";
    private static final String EXPECTED_LIST = "Expected list from mocked service";
    private static final String EXPECTED_AUDITION_POST = "Expected AuditionPost from mocked service";

    @Test
    void postsTest() {
        when(auditionService.getPosts()).thenReturn(Collections.emptyList());
        final ResponseEntity<List<AuditionPost>> responseEntity = auditionController.getPosts(null);
        assertEquals(EXPECTED_OK, HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(EXPECTED_LIST, Collections.emptyList(), responseEntity.getBody());
    }

    @Test
    void postsInvalidIdTest() {
        when(auditionService.getPosts()).thenReturn(Collections.emptyList());
        final ResponseEntity<List<AuditionPost>> responseEntity = auditionController.getPosts(-97);
        assertEquals(EXPECTED_BAD_REQUEST, HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void postTest() {
        when(auditionService.getPostById(1)).thenReturn(new AuditionPost());
        final ResponseEntity<AuditionPost> responseEntity = auditionController.getPost(1);
        assertEquals(EXPECTED_OK, HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(EXPECTED_AUDITION_POST, new AuditionPost(), responseEntity.getBody());
    }

    @Test
    void postInvalidIdTest() {
        when(auditionService.getPostById(1)).thenReturn(new AuditionPost());
        final ResponseEntity<AuditionPost> responseEntity = auditionController.getPost(null);
        assertEquals(EXPECTED_BAD_REQUEST, HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void postWithCommentsTest() {
        when(auditionService.getPostWithComments(1)).thenReturn(new AuditionPost());
        final ResponseEntity<AuditionPost> responseEntity = auditionController.getPostWithComments(1);
        assertEquals(EXPECTED_OK, HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(EXPECTED_AUDITION_POST, new AuditionPost(), responseEntity.getBody());
    }

    @Test
    void postWithCommentsInvalidIdTest() {
        when(auditionService.getPostWithComments(1)).thenReturn(new AuditionPost());
        final ResponseEntity<AuditionPost> responseEntity = auditionController.getPostWithComments(-97);
        assertEquals(EXPECTED_BAD_REQUEST, HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void commentsByPostIdTest() {
        when(auditionService.getCommentsByPostId(1)).thenReturn(Collections.emptyList());
        final ResponseEntity<List<Comment>> responseEntity = auditionController.getCommentsByPostId(1);
        assertEquals(EXPECTED_OK, HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(EXPECTED_LIST, Collections.emptyList(), responseEntity.getBody());
    }

    @Test
    void commentsByPostIdInvalidIdTest() {
        when(auditionService.getCommentsByPostId(1)).thenReturn(Collections.emptyList());
        final ResponseEntity<List<Comment>> responseEntity = auditionController.getCommentsByPostId(null);
        assertEquals(EXPECTED_BAD_REQUEST, HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}
