package com.audition.model;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {

    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Comment comment = (Comment) o;
        return postId == comment.postId && id == comment.id && Objects.equals(name, comment.name)
            && Objects.equals(email, comment.email) && Objects.equals(body, comment.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, id, name, email, body);
    }
}
