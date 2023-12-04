package com.audition.model;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditionComment {

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
        final AuditionComment auditionComment = (AuditionComment) o;
        return postId == auditionComment.postId && id == auditionComment.id && Objects.equals(name,
            auditionComment.name)
            && Objects.equals(email, auditionComment.email) && Objects.equals(body, auditionComment.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, id, name, email, body);
    }
}
