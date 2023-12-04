package com.audition.model;

import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditionPost {

    private int userId;
    private int id;
    private String title;
    private String body;
    private List<AuditionComment> auditionComments;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AuditionPost that = (AuditionPost) o;
        return userId == that.userId && id == that.id && Objects.equals(title, that.title)
            && Objects.equals(body, that.body) && Objects.equals(auditionComments, that.auditionComments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, id, title, body, auditionComments);
    }
}
